package org.openhab.binding.vera.internal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.openhab.binding.vera.jackson.CustomBooleanDeserializer;
import org.openhab.binding.vera.luup.Device;
import org.openhab.binding.vera.luup.VeraSummaryStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static java.lang.String.format;
import static java.util.Collections.EMPTY_LIST;

public class VeraClient {
    private static Logger logger = LoggerFactory.getLogger(VeraClient.class);

    private static final Integer DEFAULT_TIMEOUT_IN_SECONDS = 10 * 60;
    private static final Integer DEFAULT_MINIMUM_DELAY_IN_MILLSECONDS = 10;

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper() //
            .registerModule(new DefaultScalaModule())
            .registerModule(new SimpleModule("CustomDeserializers") {
                private static final long serialVersionUID = 1L;

                @Override
                public void setupModule(SetupContext context) {
                    addDeserializer(Boolean.class, new CustomBooleanDeserializer());
                    addDeserializer(Boolean.TYPE, new CustomBooleanDeserializer());

                    super.setupModule(context);
                }
            });

    // FIXME: need to set timeout to greater than DEFAULT_TIMEOUT_IN_SECONDS
    protected final Object CLIENT = new Object();

    private String hostname;
    private int port = 3480;
    private Set<VeraStateListener> listeners = new HashSet<>();
    private VeraSummaryStatus status;

    public VeraClient() {
    }

    public VeraClient(String hostname) {
        this.hostname = hostname;
    }

    public void registerListener(VeraStateListener listener) {
        listeners.add(listener);
    }

    public void start() {
        loadStatus();
        startStatusReload();
    }

    public void stop() {

    }

    private void startStatusReload() {
        /*
        try {
            BoundRequestBuilder request = createRequest("sdata", status.getLoadTime(), status.getDataVersion());
            Request built = request.build();
            logger.debug("Created request {}", built.getUrl());
            request.execute(new AsyncCompletionHandlerBase() {
                @Override
                public Response onCompleted(Response response) throws Exception {
                    try {
                        updateStatus(OBJECT_MAPPER.readValue(response.getResponseBody(), VeraSummaryStatus.class));
                        return super.onCompleted(response);
                    } finally {
                        startStatusReload();
                    }
                }

                @Override
                public void onThrowable(Throwable t) {
                    super.onThrowable(t);
                    startStatusReload();
                }
            });
        } catch (IOException e) {
            throw new VeraClientException(format("Error retrieving status from Vera"), e);
        }
        */
    }

    protected void updateStatus(VeraSummaryStatus status) {
        logger.debug(format("Got summary status:\n%s", dumpObject(status)));

        this.status = this.status.mergeWith(status);
    }

    public synchronized List<Device> getDevices() {
        if (status == null)
            loadStatus();
        return status.getDevices();
    }

    public synchronized VeraSummaryStatus getStatus() {
        if (status == null)
            loadStatus();
        return status;
    }

    protected synchronized void loadStatus() {
        try {
            status = OBJECT_MAPPER.readValue(performQuery(createRequest("sdata")), VeraSummaryStatus.class);
        } catch (IOException e) {
            throw new VeraClientException("Error parsing Vera config", e);
        }
    }

    protected String performQuery(Object request) {
        return null;
        /*
        try {
            Response response = request.execute().get();
            return response.getResponseBody();
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new VeraClientException(format("Error performing %s query", request), e);
        }
        */
    }

    private Object createRequest(String method) {
        return null; //CLIENT.prepareGet(createUrl(method));
    }

    private Object createRequest(String method, Date loadTime, long dataVersion) {
        if (hostname == null)
            throw new IllegalStateException("Hostname must be set before use");

        return null; /*CLIENT.prepareGet(createUrl(
                method,
                new URIBuilder()
                        .addParameter("loadtime", Long.valueOf(loadTime.getTime()).toString())
                        .addParameter("dataversion", Long.valueOf(dataVersion).toString())
                        .addParameter("timeout", DEFAULT_TIMEOUT_IN_SECONDS.toString())
                        .addParameter("minimumdelay", DEFAULT_MINIMUM_DELAY_IN_MILLSECONDS.toString())
                        .getQueryParams()));*/
    }

    private String createUrl(String method) {
        return createUrl(method, EMPTY_LIST);
    }

    // FIXME: Don't need URIBuilder; request builder has addParameter
    private String createUrl(String method, List<NameValuePair> parameters) {
        try {
            return new URIBuilder()
                    .setScheme("http")
                    .setHost(hostname)
                    .setPort(port)
                    .setPath("/data_request")
                    .addParameter("id", method)
                    .addParameters(parameters)
                    .addParameter("output_format", "json")
                    .build()
                    .toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to build vera luup url", e);
        }
    }

    protected String formatJson(String responseBody) throws IOException {
        return dumpObject(OBJECT_MAPPER.readValue(responseBody, Object.class));
    }

    protected String dumpObject(Object object) {
        try {
            return OBJECT_MAPPER.enable(INDENT_OUTPUT) //
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to json", e);
        }
    }
}
