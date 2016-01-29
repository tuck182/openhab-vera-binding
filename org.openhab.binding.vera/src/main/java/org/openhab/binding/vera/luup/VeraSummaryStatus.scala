package org.openhab.binding.vera.luup;

import java.util.Date
import java.util.List

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter

class VeraSummaryStatus {
    @BeanProperty
    protected var full: Boolean = _

    @BeanProperty
    protected var version: String = _

    @BeanProperty
    protected var model: String = _

    // TODO: enum?
    @BeanProperty
    protected var state: Int = _

    @BeanProperty
    @JsonProperty("zwave_heal")
    protected var zwaveHeal: Int = _

    @BeanProperty
    protected var temperature: String = _

    @BeanProperty
    @JsonProperty("serial_number")
    protected var serialNumber: String = _

    @BeanProperty
    @JsonProperty("fwd1")
    protected var forwardHost1: String = _

    @BeanProperty
    @JsonProperty("fwd2")
    protected var forwardHost2: String = _

    @BeanProperty
    @JsonProperty("ir")
    protected var infrared: Int = _

    @BeanProperty
    @JsonProperty("irtx")
    protected var infraredTransmit: String = _

    @BeanProperty
    @JsonProperty("loadtime")
    protected var loadTime: Date = _

    @BeanProperty
    @JsonProperty("dataversion")
    protected var dataVersion: Long = _

    @BeanProperty
    protected var comment: String = _

    protected var sections: Map[Int, Section] = Map.empty
    protected var sectionIds: Map[String, Int] = Map.empty
    protected var rooms: Map[Int, Room] = Map.empty
    protected var roomIds: Map[String, Int] = Map.empty
    protected var scenes: Map[Int, Scene] = Map.empty
    protected var sceneIds: Map[String, Int] = Map.empty
    protected var devices: Map[Int, Device] = Map.empty
    protected var deviceIds: Map[String, Int] = Map.empty
    protected var categories: Map[Int, Category] = Map.empty
    protected var categoryIds: Map[String, Int] = Map.empty

    @JsonGetter
    def getSections:List[Section] = sections.values.toList
    def getSection(name: String) = sections(sectionIds(name))
    def getSection(id: Int) = sections(id)

    @JsonSetter
    def setSections(sections: List[Section]) {
        this.sections = (for(section <- sections) yield (section.getId -> section)).toMap
        this.sectionIds = (for(section <- sections if section.getName != null)
            yield (section.getName -> section.getId)).toMap
    }

    @JsonGetter
    def getRooms:List[Room] = rooms.values.toList
    def getRoom(name: String) = rooms(roomIds(name))
    def getRoom(id: Int) = rooms(id)

    @JsonSetter
    def setRooms(rooms: List[Room]) {
        this.rooms = (for(room <- rooms)
            yield (room.getId -> room)).toMap
        this.roomIds = (for(room <- rooms if room.getName != null)
            yield (room.getName -> room.getId)).toMap
    }

    @JsonGetter
    def getScenes: List[Scene] = scenes.values.toList
    def getScene(name: String): Scene = scenes(sceneIds(name))
    def getScene(id: Int) = scenes(id)

    @JsonSetter
    def setScenes(scenes: List[Scene]) {
        this.scenes = (for(scene <- scenes) yield (scene.getId -> scene)).toMap
        this.sceneIds = (for(scene <- scenes if scene.getName != null)
            yield (scene.getName -> scene.getId)).toMap
    }

    @JsonGetter
    def getDevices: List[Device] = devices.values.toList
    def getDevice(name: String) = devices(deviceIds(name))
    def getDevice(id: Int) = devices(id)

    @JsonSetter
    def setDevices(devices: List[Device]) {
        this.devices = (for(device <- devices) yield (device.getId -> device)).toMap
        this.deviceIds = (for(device <- devices if device.getName != null)
            yield (device.getName -> device.getId)).toMap
    }

    @JsonGetter
    def getCategories: List[Category] = categories.values.toList
    def getCategory(name: String) = categories(categoryIds(name))
    def getCategory(id: Int) = categories(id)

    @JsonSetter
    def setCategories(categories: List[Category]) {
        this.categories = (for(category <- categories) yield (category.getId -> category)).toMap
        this.categoryIds = (for(category <- categories if category.getName != null)
            yield (category.getName -> category.getId)).toMap
    }

    def mergeWith(other: VeraSummaryStatus): VeraSummaryStatus = {
        if (other.full)
            return other;
        version = other.version
        model = other.model
        state = other.state
        zwaveHeal = other.zwaveHeal
        temperature = other.temperature
        serialNumber = other.serialNumber
        forwardHost1 = other.forwardHost1
        forwardHost2 = other.forwardHost2
        infrared = other.infrared
        infraredTransmit = other.infraredTransmit
        loadTime = other.loadTime
        dataVersion = other.dataVersion
        comment = other.comment

        sections = sections ++ other.sections
        rooms = rooms ++ other.rooms
        scenes = scenes ++ other.scenes
        devices = other.devices ++ (for ((id, device) <- devices) yield {
            other.devices.get(id) match {
                case Some(otherDevice) => (id -> device.mergeWith(otherDevice))
                case None => (id -> device)
            }
        }).toMap
        categories = categories ++ other.categories

        return this;
    }
}
