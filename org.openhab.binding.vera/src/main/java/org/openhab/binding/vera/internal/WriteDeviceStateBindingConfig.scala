package org.openhab.binding.vera.internal

import net.whistlingfish.openhab.binding.BindingConfigProperty
import net.whistlingfish.openhab.binding.BindingConfigType

import org.openhab.core.types.Command
import org.openhab.core.types.State

import net.whistlingfish.openhab.binding.BindingDirection.OUT

@BindingConfigType(name = "device", direction = OUT)
class WriteDeviceStateBindingConfig extends MappingEnabledBindingConfig {
    @BindingConfigProperty(0)
    private var deviceName: String = _

    @BindingConfigProperty(1)
    private var serviceUrn: String = _

    @BindingConfigProperty(2)
    private var deviceProperty: String = _

    override def receiveCommand(binding: VeraBinding, command: Command) {
        val commandStr = command.toString()
        val newValue = mappings.get(commandStr) match {
            case Some(s) => s
            case None => commandStr
        }
        binding.triggerAction(deviceName, serviceUrn, deviceProperty, newValue)
    }
}