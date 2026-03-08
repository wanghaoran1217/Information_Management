package com.tjl.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static{
        InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties p=new Properties();
        try {
            p.load(is);
            driver=p.getProperty("driver");
            url=p.getProperty("url");
            username=p.getProperty("username");
            password=p.getProperty("password");

            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn, Statement statement, ResultSet result){
        try {
            if(result!=null){
                result.close();
                result=null;
            }
            if(statement!=null){
                statement.close();
                statement=null;
            }
            if(conn!=null){
                conn.close();
                conn=null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
