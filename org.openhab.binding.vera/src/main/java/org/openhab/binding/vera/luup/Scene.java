package org.openhab.binding.vera.luup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scene {
    private int id;

    private String name;

    @JsonProperty("room")
    private int roomId;

    private int state;

    private boolean active;

    private String comment;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getState() {
        return state;
    }

    public boolean isActive() {
        return active;
    }

    public String getComment() {
        return comment;
    }
}
