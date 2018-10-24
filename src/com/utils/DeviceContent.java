package com.utils;

import com.android.ddmlib.IDevice;

public class DeviceContent {
    private int num;
    private DeviceInfo[] content;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public DeviceInfo[] getDevicesList() {
        return content;
    }
    public void setContent(DeviceInfo[] content) {
        this.content = content;
    }
}
