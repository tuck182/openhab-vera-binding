package org.openhab.binding.vera.luup.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceStatus {
    @JsonProperty("id")
    private int deviceId;

    private int status;

    private List<DeviceState> states;

    @JsonProperty("Jobs")
    private List<Object> jobs;

    private TooltipState tooltip;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DeviceState> getStates() {
        return states;
    }

    public void setStates(List<DeviceState> states) {
        this.states = states;
    }

    public List<Object> getJobs() {
        return jobs;
    }

    public void setJobs(List<Object> jobs) {
        this.jobs = jobs;
    }

    public TooltipState getTooltip() {
        return tooltip;
    }

    public void setTooltip(TooltipState tooltip) {
        this.tooltip = tooltip;
    }
}
