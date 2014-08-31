package org.openhab.binding.vera.luup.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ControlUrl {
    private String service;
    @JsonProperty("ControlURL")
    private String controlUrl;
    @JsonProperty("EventURL")
    private String eventUrl;
    private String serviceType;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getControlUrl() {
        return controlUrl;
    }

    public void setControlUrl(String controlUrl) {
        this.controlUrl = controlUrl;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
