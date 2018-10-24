package com.Tools;

import com.ConnPhone.DiscoverPhone;
import com.alibaba.fastjson.JSONObject;
import com.ConnDB.OperateMySQL;
import com.utils.SendMsg;
import com.utils.SendContent;
import com.utils.DeviceContent;

public class Tool_MsgProcessor {

    private static OperateMySQL sql = new OperateMySQL();

    //消息解读器，根据不同的消息进行处理
    public static String messageParser(JSONObject msg,JSONObject rcvContent){

        SendMsg result = new SendMsg();
        SendContent content = new SendContent();
        DeviceContent dContent = new DeviceContent();

        //login 信息使用
        if(msg.get("type").equals("login")){
            result.setType("login");
            //账户或密码为空
            if(rcvContent.get("password").toString().equals("") || rcvContent.get("username").toString().equals("")){
                content.setResult("fail");
                content.setCode("2");
            }
            //密码正确
            else if( rcvContent.get("password").toString().equals(sql.getPassword(rcvContent.get("username").toString()))){
                content.setResult("pass");
                content.setCode("0");
            }
            //查询不到用户
            else if(sql.getPassword(rcvContent.get("username").toString()).equals("")){
                content.setResult("fail");
                content.setCode("2");
            }
            //密码错误
            else{
                content.setResult("fail");
                content.setCode("1");
            }
            //result.setContent(content);
        }
        if(msg.get("type").equals("ConnectDevices")){
            result.setType("ConnectDevices");
            if(rcvContent.get("message").toString().equals("ConnectDevices")){
                System.out.println(Tool_AdbCommand.deviceInfo.length);
                dContent.setNum(Tool_AdbCommand.deviceInfo.length);
                dContent.setContent(Tool_AdbCommand.deviceInfo);
                content.setDContent(dContent);
                content.setCode("3");
            }
        }
        System.out.println(dContent);
        System.out.println(content);
        result.setContent(content);
        System.out.println(result);
        return JSONObject.toJSONString(result);
    }
}

