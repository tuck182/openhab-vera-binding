package org.openhab.binding.vera.luup.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceState {
    private int id;

    private String service;

    private String variable;

    private String value;

    @JsonProperty("pnp")
    private String plugAndPlayState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlugAndPlayState() {
        return plugAndPlayState;
    }

    public void setPlugAndPlayState(String plugAndPlayState) {
        this.plugAndPlayState = plugAndPlayState;
    }

}
