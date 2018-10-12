package com.Tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tool_MysqlCommand {

    public static String driver = "com.mysql.jdbc.Driver";

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
    public static synchronized List<HashMap<String, String>> query(String sql) {
        return Tool_MysqlCommand.result(sql);
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
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://"
                            + host + "?useUnicode=true&characterEncoding=UTF8", user,
                    pwd);
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

    //查询结果集
    private static ResultSet resultSet(String sql) {
        ResultSet rs = null;
        if (stmt == null) {
            Tool_MysqlCommand.statement();
        }
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //查询结果
    private static List<HashMap<String, String>> result(String sql) {
        ResultSet rs = Tool_MysqlCommand.resultSet(sql);
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int cc = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, String> columnMap = new HashMap<String, String>();
                for (int i = 1; i <= cc; i++) {
                    columnMap.put(md.getColumnName(i), rs.getString(i));
                }
                result.add(columnMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询操作
     *       Tool_MysqlCommand.connectInfo("192.168.1.1/test", "test", "test");
     *       List<HashMap<String, String>> rs = Tool_MysqlCommand.query("SELECT * from test");
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