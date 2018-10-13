package com.ConnDB;

import com.Tools.Tool_MysqlCommand;

/**
 * 数据库结构：
 *  localhost:3306/VControl(远程连接使用：192.168.0.128:3306/VControl)
 *  地址：localhost
 *  端口：3306
 *  库名：VControl
 *  表：
 *      useraccounts: userID(int)   username(varchar)   password(varchar)
 */
public class OperateMySQL {

    private static String host = "jdbc:mysql://192.168.0.128:3306/VControl?useSSL=false&&serverTimezone=GMT%2B8&&allowPublicKeyRetrieval=true";
    private static String user = "root";
    private static String pwd = "123123";

    //用于登陆验证，根据用户名获取密码
    public static String getPassword(String name){
        Tool_MysqlCommand sql = new Tool_MysqlCommand();
        sql.connectInfo(host,user,pwd);
        String result = sql.query("select password from useraccounts WHERE username=\""+ name + "\"","password");
        System.out.println("password = " + result);
        sql.close();
        return result;
    }
}
