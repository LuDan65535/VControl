package com.ConnPhone;

import com.ToolsAdbCommand.Tool_AdbCommand;
import com.android.ddmlib.IDevice;

public class DiscoverPhone {
    public static String path = "d:\\1.jpeg";
    public static Tool_AdbCommand comm = new Tool_AdbCommand();

    public static void main(String[] args) {

        comm.initADB();
        comm.sendScreenShot(comm.ConnDevice(), path);
        runWatcher();


    }

    private static class DevicesWatcher implements Runnable {
        private IDevice[] mOldDevices;

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                final IDevice[] newDevices = Tool_AdbCommand.ConnDevice();
                Tool_AdbCommand.sendScreenShot(newDevices, path);
                if (newDevices != mOldDevices) {
                    mOldDevices = newDevices;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static DevicesWatcher dw = new DevicesWatcher();
    public static void runWatcher(){
        dw.run();
    }
}
