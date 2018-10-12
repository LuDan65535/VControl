package com.Tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ConnDB.OperateMySQL;

import static com.ConnDB.OperateMySQL.getPassword;

public class Tool_MsgProcessor {

    //消息解读器，根据不同的消息进行处理
    public static String messageParser(JSONObject msg){

        Message result = new Message();
        Content content = new Content();

        if(msg.get("type") == "login"){
            result.setType("login");
            //密码正确
            if( msg.get("password").toString().equals(getPassword(msg.get("username").toString()))){
                content.setResult("pass");
                content.setCode("0");
            }else if(getPassword(msg.get("username").toString()).equals("")){
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

class Message {
    private String type;
    private Content content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}

class Content {

    private String result;
    private String code;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}