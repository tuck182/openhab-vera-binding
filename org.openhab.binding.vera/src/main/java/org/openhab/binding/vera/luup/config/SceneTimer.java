package org.openhab.binding.vera.luup.config;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SceneTimer {
    private int id;

    private String name;

    // FIXME: This is likely an enum:
    private String type;

    private boolean enabled;

    @JsonProperty("days_of_week")
    private String daysOfWeek;

    private String time;

    @JsonProperty("last_run")
    private Date lastRun;

    @JsonProperty("next_run")
    private Date nextRun;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getLastRun() {
        return lastRun;
    }

    public void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }

    public Date getNextRun() {
        return nextRun;
    }

    public void setNextRun(Date nextRun) {
        this.nextRun = nextRun;
    }
}
