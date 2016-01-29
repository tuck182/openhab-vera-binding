package org.openhab.binding.vera.internal;

import net.whistlingfish.openhab.binding.AnnotationBasedBindingConfig;
import org.openhab.core.types.Command
import org.openhab.core.types.State

abstract class VeraBindingConfig extends
        AnnotationBasedBindingConfig[VeraBindingConfig, VeraBinding, VeraBindingProviderImpl] {
    override def bind(binding: VeraBinding) {
    }

    override def receiveCommand(binding: VeraBinding, command: Command) {
    }

    override def receiveUpdate(binding: VeraBinding, newState: State) {
    }
}
