package com.ConnPhone;

import com.Tools.Tool_AdbCommand;
import com.ConnDB.OperateMySQL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.ddmlib.IDevice;

import static com.Tools.Tool_MsgProcessor.messageParser;

public class DiscoverPhone {
    public static String path = "d:\\1.jpeg";
    public static Tool_AdbCommand comm = new Tool_AdbCommand();

    //主函数，提供逻辑顺序
    public static void main(String[] args) {
/*
        //ADB初始化
        comm.initADB();

        //首次检测设备并截图
        comm.sendScreenShot(comm.ConnDevice(), path);

        //使用线程检测设备并截图
        //需要修改为各自独立线程，截图频率远大于检测
        runWatcher();
*/
        OperateMySQL sql = new OperateMySQL();
        sql.getPassword("admin");
    }

    /**
     * 使用主线程进行实时设备检测，5s一次
     */
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

    //初始化设备实时检测类
    private static DevicesWatcher dw = new DevicesWatcher();

    //设备实时检测函数的调用入口函数
    public static void runWatcher(){
        dw.run();
    }
}
