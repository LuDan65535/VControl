package com.utils;

public class DeviceInfo {
    private String name;
    private boolean isOnling;
    private String serialNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSrialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean getStatus() {
        return isOnling;
    }

    public void setStatus(boolean isOnling) {
        this.isOnling = isOnling;
    }
}
