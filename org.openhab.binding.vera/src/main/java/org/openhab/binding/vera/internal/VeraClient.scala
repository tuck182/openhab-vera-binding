package org.openhab.binding.vera.internal

import java.io.IOException
import java.lang.String.format
import java.lang.reflect.{Type, ParameterizedType}
import java.net.URISyntaxException
import java.util.Collections.emptyList
import java.util.Date
import java.util.concurrent.ExecutionException
import java.{util => ju}
import scala.collection.JavaConversions._
import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.implicitConversions
import scala.language.postfixOps
import scala.util.{ Try, Success, Failure }
import akka.actor.ActorSystem
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.Module.SetupContext
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import org.openhab.binding.vera.jackson.CustomBooleanDeserializer
import org.openhab.binding.vera.luup.Device
import org.openhab.binding.vera.luup.VeraSummaryStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spray.can.Http
import spray.http._
import spray.http.HttpHeaders._
import spray.http.MediaTypes._
import spray.http.Uri.Path
import spray.client.pipelining._
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport._
import spray.httpx.unmarshalling._
import com.typesafe.config.ConfigFactory
import org.openhab.binding.vera.luup.config.VeraFullStatus
import org.openhab.binding.vera.luup.config.VeraConfig

object VeraClient {
    protected val logger = LoggerFactory.getLogger(classOf[VeraClient])

    val DEFAULT_PORT = 3480

    protected val DEFAULT_TIMEOUT_IN_SECONDS = 10 * 60
    protected val DEFAULT_MINIMUM_DELAY_IN_MILLSECONDS = 10

    protected val OBJECT_MAPPER = new ObjectMapper() //
        .registerModule(new DefaultScalaModule)
        .registerModule(new SimpleModule("CustomDeserializers") {
            private val serialVersionUID = 1L

            override def setupModule(context: SetupContext) {
                addDeserializer(classOf[java.lang.Boolean], new CustomBooleanDeserializer)
                addDeserializer(java.lang.Boolean.TYPE, new CustomBooleanDeserializer)

                super.setupModule(context)
            }
        })
    implicit def MapAnyToMapString(map: Map[String, Any]): Map[String, String] =
        for ((k, v) <- map) yield { (k -> v.toString) }
}

class VeraClient(private val hostname: String, private val port: Int) {
    import VeraClient._

    def this(hostname: String) = this(hostname, VeraClient.DEFAULT_PORT)

    /*
     * Spray
     */
    private val config = ConfigFactory.load(classOf[VeraClient].getClassLoader)

    implicit val system = ActorSystem("veraClient", Some(config))
    implicit val timeout = Timeout(5 minutes)

    // import VeraClient._
    import system.dispatcher // execution context for futures

    // The vera server is broken and returns an invalid content-type
    val fixContentType: ResponseTransformer =
        r => r.withEntity(r.entity.flatMap(_.copy(contentType = `application/json`)))
    implicit def unmarshaller[T: Manifest]: Unmarshaller[T] = Unmarshaller[T](`application/json`) {
        case HttpEntity.NonEmpty(contentType, data) =>
            try {
                OBJECT_MAPPER.readValue[T](data.asString, new TypeReference[T] {
                    override def getType = manifest[T].runtimeClass
                })
            } catch {
                case e: Exception =>
                    logger.error("Failed to read json data", e)
                    throw e
            }
    }
    def pipeline[T: Manifest]: HttpRequest => Future[T] = {
        sendReceive ~> fixContentType ~> unmarshal[T]
    }

    private var listeners: Set[VeraStateListener] = Set.empty
    private var status: VeraSummaryStatus = _

    def registerListener(listener: VeraStateListener) {
        this.synchronized {
            listeners += listener
            if (status != null) {
                notifyListener(listener, status)
            }
        }
    }

    def triggerAction(deviceId: Int, serviceId: String, deviceProperty: String, newValue: String) {
        performQuery[Map[String, Object]](createRequest("action", Map(
                ("DeviceNum" -> deviceId),
                ("serviceId" -> serviceId),
                ("action" -> s"Set$deviceProperty"),
                (s"new$deviceProperty" -> newValue)
        )))
    }

    private def notifyListener(listener: VeraStateListener, status: VeraSummaryStatus) {
        val updatedDevices: List[Int] = (for (device <- status.getDevices) yield device.getId).toList
        val updatedScenes: List[Int] = (for (device <- status.getScenes) yield device.getId).toList
        val updatedRooms: List[Int] = (for (device <- status.getRooms) yield device.getId).toList
        notifyListener(listener, status, updatedDevices, updatedScenes, updatedRooms)
    }

    private def notifyListener(listener: VeraStateListener, status: VeraSummaryStatus,
            updatedDevices: List[Int], updatedScenes: List[Int],  updatedRooms: List[Int]) {
        for (deviceId <- updatedDevices) {
            listener.deviceUpdated(status.getDevice(deviceId))
        }
        for (sceneId <- updatedScenes) {
            listener.sceneUpdated(status.getScene(sceneId))
        }
        for (roomId <- updatedRooms) {
            listener.roomUpdated(status.getRoom(roomId))
        }
    }

    def start {
        loadStatus
        startStatusReload
    }

    def stop {

    }

    private def startStatusReload {
        val request = this.synchronized {
            createRequest("sdata", status.getLoadTime, status.getDataVersion)
        }
        for (response <- performQuery[VeraSummaryStatus](request)) {
            try {
                updateStatus(response)
            } finally {
                startStatusReload
            }
        }
    }

    protected def updateStatus(status: VeraSummaryStatus) {

        this.synchronized {
            val updatedDevices: List[Int] = (for (device <- status.getDevices) yield device.getId).toList
            val updatedScenes: List[Int] = (for (device <- status.getScenes) yield device.getId).toList
            val updatedRooms: List[Int] = (for (device <- status.getRooms) yield device.getId).toList
            this.status = if (this.status == null) status else this.status.mergeWith(status)

            logger.debug(format("Got new summary status; notifying %d listeners", Integer.valueOf(listeners.size)))
            for (listener <- listeners) {
                notifyListener(listener, this.status, updatedDevices, updatedScenes, updatedRooms)
            }
        }
    }

    def getDevices: ju.List[Device] = this.synchronized {
        if (status == null)
            loadStatus
        status.getDevices
    }

    def getDevice(name: String) = this.synchronized {
        if (status == null)
            loadStatus
        status.getDevice(name)
    }

    def getStatus: VeraSummaryStatus = this.synchronized {
        if (status == null)
            loadStatus
        status
    }

    def getFullStatus = {
        performQueryAndWait[VeraFullStatus](createRequest("status"))
    }

    def getConfig = {
        performQueryAndWait[VeraConfig](createRequest("user_data"))
    }

    protected def loadStatus = this.synchronized {
        updateStatus(performQueryAndWait[VeraSummaryStatus](createRequest("sdata")))
    }

    protected def performQueryAndWait[T: Manifest](request: HttpRequest): T =
        Await.result(performQuery(request), DEFAULT_TIMEOUT_IN_SECONDS seconds)

    protected def performQuery[T: Manifest](request: HttpRequest): Future[T] = {
        logger.debug(s"performQuery(${request.uri})")
        val pipe = pipeline[T]
        val result = for (response <- pipe(request)) yield {
            logger.debug("Got response:\n{}", response)
            response
        }
        result
    }

    private def createRequest(method: String): HttpRequest = {
        Get(createUri(method))
    }

    private def createRequest(method: String, parameters: Map[String, Any]): HttpRequest = {
        Get(createUri(method, parameters))
    }

    private def createRequest(method: String, loadTime: Date, dataVersion: Long): HttpRequest = {
        Get(createUri(
            method,
            Map("loadtime" -> loadTime.getTime,
                "dataversion" -> dataVersion,
                "timeout" -> (DEFAULT_TIMEOUT_IN_SECONDS - 1),
                "minimumdelay" -> DEFAULT_MINIMUM_DELAY_IN_MILLSECONDS)))
    }

    private def createUri(method: String): Uri = {
        createUri(method, Map.empty)
    }

    // FIXME: Don't need URIBuilder request builder has addParameter
    private def createUri(method: String, parameters: Map[String, Any]): Uri = {
        if (hostname == null)
            throw new IllegalStateException("Hostname must be set before use")
        Uri()
            .withScheme("http")
            .withHost(hostname)
            .withPort(port)
            .withPath(Path("/data_request"))
            .withQuery(parameters + ("id" -> method) + ("output_format" -> "json"))
    }

    @throws(classOf[IOException])
    protected def formatJson(responseBody: String): String = {
        dumpObject(OBJECT_MAPPER.readValue(responseBody, classOf[Object]))
    }

    protected def dumpObject(obj: Object): String = {
        try {
            OBJECT_MAPPER.enable(INDENT_OUTPUT).writeValueAsString(obj)
        } catch {
            case (e: JsonProcessingException) => {
                throw new RuntimeException("Failed to convert object to json", e)
            }
        }
    }
}
