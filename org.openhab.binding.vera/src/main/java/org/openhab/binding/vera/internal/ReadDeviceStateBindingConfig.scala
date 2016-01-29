package org.openhab.binding.vera.internal

import net.whistlingfish.openhab.binding.BindingConfigProperty
import net.whistlingfish.openhab.binding.BindingConfigType
import org.openhab.binding.vera.luup.Device
import org.openhab.binding.vera.luup.Room
import org.openhab.binding.vera.luup.Scene
import org.openhab.core.library.types.StringType
import org.openhab.core.types.Command
import org.openhab.core.types.State
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import net.whistlingfish.openhab.binding.BindingDirection.IN
import java.lang.String.format
import org.openhab.core.library.items.SwitchItem
import org.openhab.core.library.types.OnOffType

object ReadDeviceStateBindingConfig {
    val logger = LoggerFactory.getLogger(classOf[ReadDeviceStateBindingConfig])
}
@BindingConfigType(name = "device", direction = IN)
class ReadDeviceStateBindingConfig extends MappingEnabledBindingConfig {
    import ReadDeviceStateBindingConfig._

    @BindingConfigProperty(0)
    private var deviceName: String = _

    @BindingConfigProperty(1)
    private var deviceProperty: String = _

    override def bind(binding: VeraBinding) {
        binding.registerListener(new VeraStateListener() {
            override def deviceUpdated(device: Device) {
                if (deviceName.equals(device.getName())) {
                    if (!device.hasProperty(deviceProperty)) {
                        return
                    }
                    val value = device.getProperty(deviceProperty)
                    val mappedValue = mappings.getOrElse(value, value)

                    val typedValue = item match {
                        case switch: SwitchItem => OnOffType.valueOf(mappedValue)
                        case _ => new StringType(mappedValue)
                    }

                    logger.debug(format("binding.postUpdate(%s, %s)", item.getName, typedValue))
                    binding.postUpdate(item.getName, typedValue)
                }
            }
        })
    }
}
