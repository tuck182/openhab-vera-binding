package org.openhab.binding.vera.luup;

import scala.beans.BeanProperty

class Category {
    @BeanProperty
    protected var id: Int = _

    @BeanProperty
    protected var name: String = _
}
