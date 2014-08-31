package org.openhab.binding.vera.luup.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SceneStatus {
    @JsonProperty("id")
    private int sceneId;

    private int status;

    private boolean active;

    @JsonProperty("Jobs")
    private List<Object> jobs;

    private TooltipState tooltip;

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
