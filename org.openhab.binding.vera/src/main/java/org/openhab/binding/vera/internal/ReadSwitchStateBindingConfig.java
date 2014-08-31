package org.openhab.binding.vera.internal;

import org.openhab.core.types.Command;
import org.openhab.core.types.State;

import net.whistlingfish.openhab.binding.BindingConfigType;
import static net.whistlingfish.openhab.binding.BindingDirection.IN;

@BindingConfigType(name = "state", direction = IN)
public class ReadSwitchStateBindingConfig extends VeraBindingConfig {
    @Override
    public void bind(VeraBinding binding) {
    }

    @Override
    public void receiveCommand(VeraBinding binding, Command command) {
    }

    @Override
    public void receiveUpdate(VeraBinding binding, State newState) {
    }
}