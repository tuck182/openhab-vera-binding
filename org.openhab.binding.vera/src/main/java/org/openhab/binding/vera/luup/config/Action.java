package org.openhab.binding.vera.luup.config;

import java.util.List;

public class Action {
    private int device;
    private String service;
    private String action;
    private List<ActionArgument> arguments;

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<ActionArgument> getArguments() {
        return arguments;
    }

    public void setArguments(List<ActionArgument> arguments) {
        this.arguments = arguments;
    }
}
