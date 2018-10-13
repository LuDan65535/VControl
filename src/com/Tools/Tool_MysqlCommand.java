package com.Tools;


import java.sql.*;

public class Tool_MysqlCommand {

    public static String driver = "com.mysql.cj.jdbc.Driver";

    private static String host;

    private static String user;

    private static String pwd;

    private static Connection conn = null;

    private static Statement stmt = null;

    //输入连接信息
    public static void connectInfo(String host, String user, String pwd) {
        Tool_MysqlCommand.close();
        Tool_MysqlCommand.host = host;
        Tool_MysqlCommand.user = user;
        Tool_MysqlCommand.pwd = pwd;
    }

    /**
     *执行查询，返回结果
     **/
    public static synchronized String query(String sql, String obj) {
        return Tool_MysqlCommand.result(sql,obj);
    }

    /**
     *执行插入&&更新语句&&删除&&调用存储过程
     **/
    public static void update(String sql){
        if (stmt == null) {
            Tool_MysqlCommand.statement();
        }
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭数据库
    public static synchronized void close() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //连接数据库
    private static void connectMySQL() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(host, user, pwd);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //建立会话
    private static void statement() {
        if (conn == null) {
            Tool_MysqlCommand.connectMySQL();
        }
        try {
            stmt = (Statement) conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询结果
    private static String result(String sql, String obj) {
        ResultSet rs = null;
        String result = "";
        if (stmt == null) {
            Tool_MysqlCommand.statement();
        }
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()){
                result = rs.getString(obj);
            }else{
                result = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询操作
     *       Tool_MysqlCommand.connectInfo("localhost:3306/vcontrol", "root", "123123");-----------------{"Mysql服务器地址：端口/数据库名"}
     *       List<HashMap<String, String>> rs = Tool_MysqlCommand.query("select password from useraccounts WHERE username="admin"");----{表名}
     *       System.out.println(rs.get(0).get("test"));
     *       Tool_MysqlCommand.close();
     */

    /**
     *更新操作
     *      Tool_MysqlCommand.connectInfo("192.168.1.1/test", "test", "test");
     *      Tool_MysqlCommand.query("UPDATE persons set num=66 WHERE `name`=\"徐志摩\"");
     *      Tool_MysqlCommand.close();
     */

    /**
     *插入操作
     *      Tool_MysqlCommand.connectInfo("192.168.1.1/test", "test", "test");
     *      Tool_MysqlCommand.query("INSERT INTO `persons` (`name`, `num`) VALUES ('徐志摩', '45');");
     *      Tool_MysqlCommand.close();
     */

    /**
     *删除操作
     *      Tool_MysqlCommand.connectInfo("192.168.1.1/test", "test", "test");
     *      Tool_MysqlCommand.query("delete from persons WHERE `name`=\"徐志摩\"");
     *      Tool_MysqlCommand.close();
     */

    /**
     *调用存储过程
     *      Tool_MysqlCommand.connectInfo("192.168.1.1/test", "test", "test");
     *      Tool_MysqlCommand.query("call add_student(3)");
     *      Tool_MysqlCommand.close();
     */
}