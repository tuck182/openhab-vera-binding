package org.openhab.binding.vera.luup

import scala.beans.BeanProperty

import com.fasterxml.jackson.annotation.JsonProperty

class Room {
    @BeanProperty
    protected var id: Int = _

    @BeanProperty
    @JsonProperty("section")
    protected var sectionId: Int = _

    @BeanProperty
    protected var name: String = _

    override def toString(): String = s"Room[$id]: $name"
}
