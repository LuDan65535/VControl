package com.utils;

import com.android.ddmlib.IDevice;

public class DeviceContent {
    private int num;
    private IDevice[] content;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public IDevice[] getDevicesList() {
        return content;
    }
    public void setContent(IDevice[] content) {
        this.content = content;
    }
}
