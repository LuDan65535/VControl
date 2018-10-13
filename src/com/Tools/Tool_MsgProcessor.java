package com.Tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ConnDB.OperateMySQL;
import com.utils.SendMsg;
import com.utils.SendLoginCotent;
import com.utils.RcvLoginContent;

public class Tool_MsgProcessor {

    private static OperateMySQL sql = new OperateMySQL();

    //消息解读器，根据不同的消息进行处理
    public static String messageParser(JSONObject msg,JSONObject rcvContent){

        SendMsg result = new SendMsg();
        SendLoginCotent content = new SendLoginCotent();

        //login 信息使用
        if(msg.get("type").equals("login")){
            result.setType("login");
            //密码正确
            if( rcvContent.get("password").toString().equals(sql.getPassword(rcvContent.get("username").toString()))){
                content.setResult("pass");
                content.setCode("0");
            }else if(sql.getPassword(rcvContent.get("username").toString()).equals("")){
                //查询不到用户
                content.setResult("fail");
                content.setCode("2");
            }else{
                //密码错误
                content.setResult("fail");
                content.setCode("1");
            }
        }
        result.setContent(content);
        return JSONObject.toJSONString(result);
    }
}

