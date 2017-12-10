package com.company;

import java.sql.*;
import java.util.Scanner;

public class UpdateInfo {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "ManagementSys";
    private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    private static final String nameUpdateQuery = "UPDATE StudentTable SET name = ? WHERE id = ?";
    private static final String nameQuery = "SELECT name FROM StudentTable WHERE id = ?";
    private static final String ageUpdateQuery = "UPDATE StudentTable SET age = ? WHERE id = ?";
    private static final String ageQuery = "SELECT age FROM StudentTable WHERE id = ?";

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("正在载入驱动程序");
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("载入驱动程序失败!");
        }
        Connection conn = null;
        try {
            System.out.println("正在连接数据库");
            conn = DriverManager.getConnection(connectionURL, "scut", "8888");

            System.out.println("连接成功");
            executeUpdate(conn);
        } catch (SQLException e) {
            System.out.println("数据库连接出现问题!");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void executeUpdate(Connection conn) {
        System.out.println("1)修改名字 2)修改年龄");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                executeNameUpdate(conn);
                return;
            case 2:
                executeAgeUpdate(conn);
                return;
            default:
                System.out.println("输入命令有错误!");
        }
    }

    private static void executeNameUpdate(Connection conn) {
        System.out.println("需要修改姓名的学生ID:");
        int targetId = in.nextInt();
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(nameQuery);
            stat.setInt(1, targetId);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                System.out.println("找不到相应ID的学生!");
                return;
            } else {
                String oldName = rs.getString(1);

                System.out.println("该学生原先名字为:" + oldName);
                System.out.println("现需要改为:");
                String newName = in.next();

                stat = conn.prepareStatement(nameUpdateQuery);
                stat.setString(1, newName);
                stat.setInt(2, targetId);
                int ans = stat.executeUpdate();

                System.out.println("已修改" + ans + "条记录");
            }
        } catch (SQLException e) {
            System.out.println("SQL出现错误");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stat != null)
                    stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void executeAgeUpdate(Connection conn) {
        System.out.println("需要修改姓名的学生ID:");
        int targetId = in.nextInt();
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ageQuery);
            stat.setInt(1, targetId);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                System.out.println("找不到相应ID的学生!");
                return;
            } else {
                String oldAge = rs.getString(1);

                System.out.println("该学生原先年龄为:" + oldAge);
                System.out.println("现需要改为:");
                int newAge = in.nextInt();

                stat = conn.prepareStatement(ageUpdateQuery);
                stat.setInt(1, newAge);
                stat.setInt(2, targetId);
                int ans = stat.executeUpdate();

                System.out.println("已修改" + ans + "条记录");
            }
        } catch (SQLException e) {
            System.out.println("SQL出现错误");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stat != null)
                    stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
