//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "ManagementSys";
    private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    public CreateTable() {
    }

    public static void main(String[] args) /*throws SQLException, ClassNotFoundException*/ {
        System.out.println("Loading/Registering driver");
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("无法加载JDBC驱动程序. " + e.getMessage());
        }
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(connectionURL, "scut", "8888");
            String query = "CREATE TABLE StudentTable (id int, name varchar(50) NOT NULL, age int, PRIMARY KEY(id))";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("连接数据库出现错误. " + e.getMessage());
        } finally {
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
    }

    static {
        connectionURL = "jdbc:derby:" + dbName + ";create=true";
    }
}