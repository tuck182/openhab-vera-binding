package org.openhab.binding.vera.luup;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Collections.unmodifiableMap;

public class Device {
    private int id;

    private String name;

    @JsonProperty("altid")
    private int alternateId;

    @JsonProperty("parent")
    private int parentId;

    @JsonProperty("category")
    private int categoryId;

    @JsonProperty("subcategory")
    private int subcategoryId;

    @JsonProperty("room")
    private int roomId = -1;

    private Map<String, String> properties = new HashMap<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAlternateId() {
        return alternateId;
    }

    public int getParentId() {
        return parentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public int getRoomId() {
        return roomId;
    }

    @JsonIgnore
    public Set<String> getPropertyKeys() {
        return properties.keySet();
    }

    @JsonAnySetter
    private void setUnknownProperty(String key, String value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    private Map<String, String> getProperties() {
        return unmodifiableMap(properties);
    }

    public String getProperty(String name) {
        return properties.get(name);
    }
}
