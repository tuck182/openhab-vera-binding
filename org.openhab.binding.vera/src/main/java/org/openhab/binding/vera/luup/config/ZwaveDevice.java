package org.openhab.binding.vera.luup.config;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZwaveDevice {
    private int id;

    private String name;

    @JsonProperty("id_parent")
    private int parentId;

    @JsonProperty("altid")
    private int alternateId;

    @JsonProperty("device_type")
    private String deviceType;

    private String model;

    @JsonProperty("device_file")
    private String deviceFile;

    private String manufacturer;

    private int room = -1;

    @JsonProperty("ip")
    private String ipAddress;

    @JsonProperty("mac")
    private String macAddress;

    @JsonProperty("pnp")
    private int plugAndPlayId;

    @JsonProperty("category_filter")
    private List<CategoryFilter> categoryFilters;

    private List<DeviceState> states;

    @JsonProperty("category_num")
    private int categoryNumber;

    @JsonProperty("subcategory_num")
    private int subcategoryNumber;

    @JsonProperty("time_created")
    private Date timeCreated;

    @JsonProperty("ControlURLs")
    private Map<String, ControlUrl> controlUrls;

    private boolean embedded;

    private boolean invisible;

    private boolean disabled;

    @JsonProperty("local_udn")
    private String localUdn;

    @JsonProperty("impl_file")
    private String implFile;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAlternateId() {
        return alternateId;
    }

    public void setAlternateId(int alternateId) {
        this.alternateId = alternateId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDeviceFile() {
        return deviceFile;
    }

    public void setDeviceFile(String deviceFile) {
        this.deviceFile = deviceFile;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getPlugAndPlayId() {
        return plugAndPlayId;
    }

    public void setPlugAndPlayId(int plugAndPlayId) {
        this.plugAndPlayId = plugAndPlayId;
    }

    public List<CategoryFilter> getCategoryFilters() {
        return categoryFilters;
    }

    public void setCategoryFilters(List<CategoryFilter> categoryFilters) {
        this.categoryFilters = categoryFilters;
    }

    public List<DeviceState> getStates() {
        return states;
    }

    public void setStates(List<DeviceState> states) {
        this.states = states;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public int getSubcategoryNumber() {
        return subcategoryNumber;
    }

    public void setSubcategoryNumber(int subcategoryNumber) {
        this.subcategoryNumber = subcategoryNumber;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Map<String, ControlUrl> getControlUrls() {
        return controlUrls;
    }

    public void setControlUrls(Map<String, ControlUrl> controlUrls) {
        this.controlUrls = controlUrls;
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getLocalUdn() {
        return localUdn;
    }

    public void setLocalUdn(String localUdn) {
        this.localUdn = localUdn;
    }

    public String getImplFile() {
        return implFile;
    }

    public void setImplFile(String implFile) {
        this.implFile = implFile;
    }

    public boolean isOnDashboard() {
        return onDashboard;
    }

    public void setOnDashboard(boolean onDashboard) {
        this.onDashboard = onDashboard;
    }

}
