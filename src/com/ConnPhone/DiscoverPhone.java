package com.ConnPhone;

import com.Tools.Tool_AdbCommand;
import com.ConnDB.OperateMySQL;
import com.android.ddmlib.IDevice;
import com.websocket.WebSocket;


public class DiscoverPhone {
    public static String path = "d:\\1.jpeg";
    //初始化ADB命令类
    public static Tool_AdbCommand comm = new Tool_AdbCommand();
    //初始化设备实时检测类
    public static DevicesWatcher dw = new DevicesWatcher();
    //初始化设备实时截图类
    public static DevicesCapture dc = new DevicesCapture();

    public  static IDevice[] devices;

    //主函数，提供逻辑顺序
    public static void findDevices() {

        //ADB初始化
        comm.initADB();

        //线程循环检测设备
        runWatcher();
    }

    public static void capDevices() {
        //线程循环截图
        runCapture();

        //数据库测试
        //OperateMySQL sql = new OperateMySQL();
        //sql.getPassword("admin");

    }

    //设备实时检测函数的调用入口函数
    public static void runWatcher(){
        dw.run();
    }

    //设备实时截图函数的调用入口函数
    public static void runCapture(){
        dc.run();
    }

    //websocket 收到截图请求，根据其中的截图设备列表，更新截图程序中的列表
    public static void  syncDevices(IDevice[] devices){
        dc.devices = devices;
    }

    /**
     * 使用主线程进行实时设备检测，5s一次
     */
    private static class DevicesWatcher implements Runnable {
        public static IDevice[] mOldDevices;
        DiscoverPhone dp = new DiscoverPhone();

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                final IDevice[] newDevices = Tool_AdbCommand.ConnDevice();
                //截图
                //Tool_AdbCommand.sendScreenShot(newDevices, path);
                if (newDevices != mOldDevices) {
                    mOldDevices = newDevices;
                    dp.devices = mOldDevices;
                    sendDevices(dp.devices);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //发送设备信息，通过websocket
        public void sendDevices(IDevice[] devices){
            System.out.println(devices);

        }
    }

    /**
     * 使用主线程进行实时设备截图，1s一次
     */
    private static class DevicesCapture implements Runnable {
        public static IDevice[] devices;

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                final IDevice[] newDevices = devices;
                System.out.println(newDevices);
                //截图并发送
                Tool_AdbCommand.sendScreenShot(newDevices,path);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
