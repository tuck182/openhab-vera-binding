package org.openhab.binding.vera.luup.config;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VeraFullStatus {
    private StartupStatus startup;

    private List<DeviceStatus> devices;

    private List<SceneStatus> scenes;

    @JsonProperty("LoadTime")
    private Date loadTime;

    @JsonProperty("DataVersion")
    private long dataVersion;

    @JsonProperty("UserData_DataVersion")
    private long userDataVersion;

    @JsonProperty("TimeStamp")
    private Date timestamp;

    @JsonProperty("ZWaveStatus")
    private int zwaveStatus;

    @JsonProperty("LocalTime")
    // FIXME: The 'D' is likely daylight vs standard time, so this will fail when daylight saving time ends.
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss 'D'")
    private Date localtime;

    public StartupStatus getStartup() {
        return startup;
    }

    public void setStartup(StartupStatus startup) {
        this.startup = startup;
    }

    public List<DeviceStatus> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceStatus> devices) {
        this.devices = devices;
    }

    public List<SceneStatus> getScenes() {
        return scenes;
    }

    public void setScenes(List<SceneStatus> scenes) {
        this.scenes = scenes;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    public long getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(long dataVersion) {
        this.dataVersion = dataVersion;
    }

    public long getUserDataVersion() {
        return userDataVersion;
    }

    public void setUserDataVersion(long userDataVersion) {
        this.userDataVersion = userDataVersion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getZwaveStatus() {
        return zwaveStatus;
    }

    public void setZwaveStatus(int zwaveStatus) {
        this.zwaveStatus = zwaveStatus;
    }

    public Date getLocaltime() {
        return localtime;
    }

    public void setLocaltime(Date localtime) {
        this.localtime = localtime;
    }
}
