package org.openhab.binding.vera.luup;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeraSummaryStatus {
    private boolean full;

    private String version;

    private String model;

    // TODO: enum?
    private int state;

    @JsonProperty("zwave_heal")
    private int zwaveHeal;

    private String temperature;

    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("fwd1")
    private String forwardHost1;

    @JsonProperty("fwd2")
    private String forwardHost2;

    private List<Section> sections;

    private List<Room> rooms;

    private List<Scene> scenes;

    private List<Device> devices;

    private List<Category> categories;

    @JsonProperty("ir")
    private int infrared;

    @JsonProperty("irtx")
    private String infraredTransmit;

    @JsonProperty("loadtime")
    private Date loadTime;

    @JsonProperty("dataversion")
    private long dataVersion;

    private String comment;

    public VeraSummaryStatus mergeWith(VeraSummaryStatus other) {
        if (other.full)
            return other;
        dataVersion = other.dataVersion;
        loadTime = other.loadTime;

        return this;
    }

    public boolean getFull() {
        return full;
    }

    public String getVersion() {
        return version;
    }

    public String getModel() {
        return model;
    }

    public int getState() {
        return state;
    }

    public int getZwaveHeal() {
        return zwaveHeal;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getForwardHost1() {
        return forwardHost1;
    }

    public String getForwardHost2() {
        return forwardHost2;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int getInfrared() {
        return infrared;
    }

    public String getInfraredTransmit() {
        return infraredTransmit;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public long getDataVersion() {
        return dataVersion;
    }

    public String getComment() {
        return comment;
    }
}
