package com.ToolsAdbCommand;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;

public class Tool_AdbCommand {
    /*
    等待设备连接电脑
     */
    private static void waitDeviceList(AndroidDebugBridge bridge) {
        int count = 0;
        while (bridge.hasInitialDeviceList() == false) {
            try {
                Thread.sleep(100); // 如果没有获得设备列表，则等待
                System.err.println("wait for devices");
                count++;
            } catch (InterruptedException e) {}
            if (count > 300) {    // 设定时间超过300×100 ms的时候为连接超时
                   System.err.print("Time out");
                   break;
            }
        }
    }
    /*
    发现并连接设备
     */
    public static IDevice[] ConnDevice() {
        AndroidDebugBridge.init(false);

        //寻找adb命令的默认路径，如果没有则直接使用adb命令
        String adbLocation = System.getProperty("com.android.screenshot.bindir");

        if (adbLocation != null && adbLocation.length() != 0) {
            adbLocation += File.separator + "adb";
        } else {
            adbLocation = "adb";
        }

        AndroidDebugBridge bridge = AndroidDebugBridge.createBridge(adbLocation, false);
        waitDeviceList(bridge);

        IDevice devices[] = bridge.getDevices();

        System.out.println("device number = " + devices.length);

        int cnt = 1;
        for (IDevice device : devices)
        {
            System.out.println("Device " + cnt + " Name: "  +  device.getName());
            System.out.println("Device " + cnt + " isOnline: " + device.isOnline() );
            System.out.println("Device " + cnt + " SerialNumber: : " + device.getSerialNumber());
            cnt++;
        }

        return devices;
    }

    /*
    传输设备截图至电脑
     */
    public static void sendScreenShot(IDevice[] devices, String filepath){
        for(IDevice device: devices) {
            try {
                RawImage rawScreen = device.getScreenshot();

                if (rawScreen != null) {
                    Boolean landscape = false;
                    int width = landscape ? rawScreen.height : rawScreen.width;
                    int height = landscape ? rawScreen.width : rawScreen.height;

                    BufferedImage image = null;
                    if (image == null) {
                        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    } else {
                        if (image.getHeight() != height || image.getWidth() != width) {
                            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                        }
                    }
                    int index = 0;
                    int indexInc = rawScreen.bpp >> 3;
                    for (int y = 0; y < rawScreen.height; y++) {
                        for (int x = 0; x < rawScreen.width; x++, index += indexInc) {
                            int value = rawScreen.getARGB(index);
                            if (landscape)
                                image.setRGB(y, rawScreen.width - x - 1, value);
                            else
                                image.setRGB(x, y, value);
                        }
                    }
                    try {
                        ImageIO.write((RenderedImage) image, "JPEG", new File(filepath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (AdbCommandRejectedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}





