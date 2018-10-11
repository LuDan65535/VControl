package com.ConnPhone;

import com.ToolsAdbCommand.Tool_AdbCommand;

public class DiscoverPhone {

    public static void main(String[] args){
        Tool_AdbCommand comm = new Tool_AdbCommand();
        comm.sendScreenShot(comm.ConnDevice(), "d:\\1.jpeg");
    }
}
