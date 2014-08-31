package org.openhab.binding.vera.luup.config;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IPRequest {
    private Date date;

    @JsonProperty("mac")
    private String macAddress;

    @JsonProperty("ip")
    private String ipAddress;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
