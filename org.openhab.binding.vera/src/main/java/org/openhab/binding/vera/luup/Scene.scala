package org.openhab.binding.vera.luup;

import scala.beans.BeanProperty

import com.fasterxml.jackson.annotation.JsonProperty;

class Scene {
    @BeanProperty
    protected var id: Int = _

    @BeanProperty
    protected var name: String = _

    @JsonProperty("room")
    @BeanProperty
    protected var roomId: Int = _

    @BeanProperty
    protected var state: Int = _

    @BeanProperty
    protected var active: Boolean = _

    @BeanProperty
    protected var comment: String = _

    override def toString(): String = s"Scene[$id]: $name"
}
