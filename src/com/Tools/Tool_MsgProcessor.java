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
    public static String messageParser(JSONObject msg){

        SendMsg result = new SendMsg();
        SendLoginCotent content = new SendLoginCotent();

        if(msg.get("type") == "login"){
            result.setType("login");
            //密码正确
            if( msg.get("password").toString().equals(sql.getPassword(msg.get("username").toString()))){
                content.setResult("pass");
                content.setCode("0");
            }else if(sql.getPassword(msg.get("username").toString()).equals("")){
                //查询步到用户
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

