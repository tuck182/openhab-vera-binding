package org.openhab.binding.vera.luup.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private int id;

    @JsonProperty("Name")
    private String name;

    // FIXME: This might be an enum:
    @JsonProperty("Level")
    private int level;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
