package org.openhab.binding.vera.internal

import net.whistlingfish.openhab.binding.BindingConfigProperty

object MappingEnabledBindingConfig {
    protected val MAPPING_SPLITTER = """([^=]+)=([^=]+)""".r
}
abstract class MappingEnabledBindingConfig extends VeraBindingConfig {
    import MappingEnabledBindingConfig._

    protected var mappings: Map[String, String] = Map.empty

    @BindingConfigProperty(remaining = true)
    def addMapping(mapping: String) {
        mapping match {
            case MAPPING_SPLITTER(k, v) => mappings = mappings + (k -> v)
            case _ => throw new IllegalArgumentException(s"Invalid mapping $mapping")
        }
    }

}