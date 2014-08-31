package org.openhab.binding.vera.luup.config;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZwaveScene {
    private int id;

    private String name;

    private List<Trigger> triggers;

    private List<Group> groups;

    private List<SceneTimer> timers;

    @JsonProperty("Timestamp")
    private Date timestamp;

    private int room;

    @JsonProperty("notification_only")
    private int notificationOnly;

    private boolean onDashboard;

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

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<SceneTimer> getTimers() {
        return timers;
    }

    public void setTimers(List<SceneTimer> timers) {
        this.timers = timers;
    }

    public int getNotificationOnly() {
        return notificationOnly;
    }

    public void setNotificationOnly(int notificationOnly) {
        this.notificationOnly = notificationOnly;
    }

    public boolean isOnDashboard() {
        return onDashboard;
    }

    public void setOnDashboard(boolean onDashboard) {
        this.onDashboard = onDashboard;
    }
}
