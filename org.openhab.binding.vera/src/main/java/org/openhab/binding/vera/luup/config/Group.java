package org.openhab.binding.vera.luup.config;

import java.util.List;

public class Group {
    private int delay;
    private List<Action> actions;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
