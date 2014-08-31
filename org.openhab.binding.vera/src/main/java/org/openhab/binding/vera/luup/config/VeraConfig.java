package org.openhab.binding.vera.luup.config;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VeraConfig {
    private List<ZwaveDevice> devices;

    private List<ZwaveScene> scenes;

    private List<Section> sections;

    private int timezone;

    @JsonProperty("firmware_version")
    private int firmwareVersion;

    @JsonProperty("ExtraLuaFiles")
    private List<Object> extraLuaFiles;

    @JsonProperty("ServerBackup")
    private String serverBackup;

    @JsonProperty("InstalledPlugins")
    private List<Object> installedPlugins;

    @JsonProperty("InstalledPlugins2")
    private List<Object> installedPlugins2;

    @JsonProperty("PluginsSynced")
    private boolean pluginsSynced;

    @JsonProperty("overview_tabs")
    private List<OverviewTab> overviewTabs;

    @JsonProperty("PK_City")
    private String pkCity;

    @JsonProperty("PK_AccessPoint")
    private String pkAccessPoint;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String currency;

    @JsonProperty("KwhPrice")
    private BigDecimal kilowattHourPrice;

    @JsonProperty("City_description")
    private String cityDescription;

    @JsonProperty("Region_description")
    private String regionDescription;

    @JsonProperty("FirstSave")
    private long firstSave;

    private List<Room> rooms;

    @JsonProperty("ip_requests")
    private List<IPRequest> ipRequests;

    @JsonProperty("zwave_heal")
    private String zwaveHeal;

    @JsonProperty("UnassignedDevices")
    private int unassignedDevices;

    @JsonProperty("AutomationDevices")
    private int automationDevices;

    private List<User> users;

    @JsonProperty("BuildVersion")
    private String buildVersion;

    @JsonProperty("SvnVersion")
    private String svnVersion;

    private String model;

    @JsonProperty("local_udn")
    private String localUdn;

    @JsonProperty("gmt_offset")
    private int gmtOffset;

    @JsonProperty("RA_Server")
    private String raServer;

    @JsonProperty("RA_Server_Back")
    private String raBackupServer;

    @JsonProperty("ir")
    private boolean infrared;

    @JsonProperty("device_sync")
    private String deviceSync;

    @JsonProperty("DeviceSync")
    private String deviceSync2;

    @JsonProperty("DataVersion")
    private long dataVersion;

    @JsonProperty("LoadTime")
    private Date loadTime;

    @JsonProperty("static_data")
    private List<Map<String, Object>> staticData;

    @JsonProperty("SetupDevices")
    private List<Map<String, Object>> setupDevices;

    @JsonProperty("category_filter")
    private List<CategoryFilter> categoryFilters;

    public List<ZwaveDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<ZwaveDevice> devices) {
        this.devices = devices;
    }

    public List<ZwaveScene> getScenes() {
        return scenes;
    }

    public void setScenes(List<ZwaveScene> scenes) {
        this.scenes = scenes;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(int firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public List<Object> getExtraLuaFiles() {
        return extraLuaFiles;
    }

    public void setExtraLuaFiles(List<Object> extraLuaFiles) {
        this.extraLuaFiles = extraLuaFiles;
    }

    public String getServerBackup() {
        return serverBackup;
    }

    public void setServerBackup(String serverBackup) {
        this.serverBackup = serverBackup;
    }

    public List<Object> getInstalledPlugins() {
        return installedPlugins;
    }

    public void setInstalledPlugins(List<Object> installedPlugins) {
        this.installedPlugins = installedPlugins;
    }

    public List<Object> getInstalledPlugins2() {
        return installedPlugins2;
    }

    public void setInstalledPlugins2(List<Object> installedPlugins2) {
        this.installedPlugins2 = installedPlugins2;
    }

    public boolean isPluginsSynced() {
        return pluginsSynced;
    }

    public void setPluginsSynced(boolean pluginsSynced) {
        this.pluginsSynced = pluginsSynced;
    }

    public List<OverviewTab> getOverviewTabs() {
        return overviewTabs;
    }

    public void setOverviewTabs(List<OverviewTab> overviewTabs) {
        this.overviewTabs = overviewTabs;
    }

    public String getPkCity() {
        return pkCity;
    }

    public void setPkCity(String pkCity) {
        this.pkCity = pkCity;
    }

    public String getPkAccessPoint() {
        return pkAccessPoint;
    }

    public void setPkAccessPoint(String pkAccessPoint) {
        this.pkAccessPoint = pkAccessPoint;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getKilowattHourPrice() {
        return kilowattHourPrice;
    }

    public void setKilowattHourPrice(BigDecimal kilowattHourPrice) {
        this.kilowattHourPrice = kilowattHourPrice;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public long getFirstSave() {
        return firstSave;
    }

    public void setFirstSave(long firstSave) {
        this.firstSave = firstSave;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<IPRequest> getIpRequests() {
        return ipRequests;
    }

    public void setIpRequests(List<IPRequest> ipRequests) {
        this.ipRequests = ipRequests;
    }

    public String getZwaveHeal() {
        return zwaveHeal;
    }

    public void setZwaveHeal(String zwaveHeal) {
        this.zwaveHeal = zwaveHeal;
    }

    public int getUnassignedDevices() {
        return unassignedDevices;
    }

    public void setUnassignedDevices(int unassignedDevices) {
        this.unassignedDevices = unassignedDevices;
    }

    public int getAutomationDevices() {
        return automationDevices;
    }

    public void setAutomationDevices(int automationDevices) {
        this.automationDevices = automationDevices;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getSvnVersion() {
        return svnVersion;
    }

    public void setSvnVersion(String svnVersion) {
        this.svnVersion = svnVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocalUdn() {
        return localUdn;
    }

    public void setLocalUdn(String localUdn) {
        this.localUdn = localUdn;
    }

    public int getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(int gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public String getRaServer() {
        return raServer;
    }

    public void setRaServer(String raServer) {
        this.raServer = raServer;
    }

    public String getRaBackupServer() {
        return raBackupServer;
    }

    public void setRaBackupServer(String raBackupServer) {
        this.raBackupServer = raBackupServer;
    }

    public boolean isInfrared() {
        return infrared;
    }

    public void setInfrared(boolean infrared) {
        this.infrared = infrared;
    }

    public String getDeviceSync() {
        return deviceSync;
    }

    public void setDeviceSync(String deviceSync) {
        this.deviceSync = deviceSync;
    }

    public String getDeviceSync2() {
        return deviceSync2;
    }

    public void setDeviceSync2(String deviceSync2) {
        this.deviceSync2 = deviceSync2;
    }

    public long getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(long dataVersion) {
        this.dataVersion = dataVersion;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    public List<Map<String, Object>> getStaticData() {
        return staticData;
    }

    public void setStaticData(List<Map<String, Object>> staticData) {
        this.staticData = staticData;
    }

    public List<Map<String, Object>> getSetupDevices() {
        return setupDevices;
    }

    public void setSetupDevices(List<Map<String, Object>> setupDevices) {
        this.setupDevices = setupDevices;
    }

    public List<CategoryFilter> getCategoryFilters() {
        return categoryFilters;
    }

    public void setCategoryFilters(List<CategoryFilter> categoryFilters) {
        this.categoryFilters = categoryFilters;
    }
}
