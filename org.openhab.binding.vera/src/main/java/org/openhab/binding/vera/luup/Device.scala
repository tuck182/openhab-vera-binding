package org.openhab.binding.vera.luup

import java.{util => ju}

import scala.beans.BeanProperty
import scala.collection.JavaConversions._

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class Device {
    @BeanProperty
    protected var id: Int = _

    @BeanProperty
    protected var name: String = _

    @BeanProperty
    @JsonProperty("altid")
    protected var alternateId: Int = _

    @BeanProperty
    @JsonProperty("parent")
    protected var parentId: Int = _

    @BeanProperty
    @JsonProperty("category")
    protected var categoryId: Int = _

    @BeanProperty
    @JsonProperty("subcategory")
    protected var subcategoryId: Int = _

    @BeanProperty
    @JsonProperty("room")
    protected var roomId: Int = -1

    protected var properties: Map[String, String] = Map.empty

    override def toString(): String = s"Device[$id]: $name"

    @JsonIgnore
    def getPropertyKeys(): ju.Set[String] = properties.keySet

    @JsonAnySetter
    protected def setUnknownProperty(key: String, value: String) = properties += (key -> value)

    @JsonAnyGetter
    def getProperties: ju.Map[String, String] = properties

    def getProperty(name: String) = properties(name)

    def hasProperty(name: String) = properties.isDefinedAt(name)

    def mergeWith(other: Device): Device = {
        if (other.name != null) this.name = other.name
        if (other.properties != null) this.properties = other.properties
        this.alternateId = other.alternateId
        this.parentId = other.parentId
        this.categoryId = other.categoryId
        this.subcategoryId = other.subcategoryId
        this.roomId = other.roomId
        this
    }
}
