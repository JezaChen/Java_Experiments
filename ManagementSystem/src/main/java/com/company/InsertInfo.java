package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InsertInfo {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "ManagementSys";
    private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
    private static Scanner in = new Scanner(System.in);

    public InsertInfo() {
    }

    //插入单个记录
    public static void insertInfo(String name, int id, int age) {
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("载入驱动程序失败");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(connectionURL, "scut", "8888");
            String insertQuery = "INSERT INTO StudentTable VALUES(?,?,?)";
            stmt = conn.prepareStatement(insertQuery);

            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);

            stmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("SQL操作异常！");
            System.out.println(e.getMessage());
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

    //批量插入数据
    public static void insertInfos(ArrayList<String> name, ArrayList<Integer> id, ArrayList<Integer> age) {
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("载入驱动程序失败");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(connectionURL, "scut", "8888");
            String insertQuery = "INSERT INTO StudentTable VALUES(?,?,?)";
            stmt = conn.prepareStatement(insertQuery);
            for (int i = 0; i < id.size(); i++) {
                stmt.setInt(1, id.get(i));
                stmt.setString(2, name.get(i));
                stmt.setInt(3, age.get(i));

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("SQL操作异常！");
            System.out.println(e.getMessage());
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

    public static void main(String[] args) {
        System.out.println("正在载入驱动程序");
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("载入驱动程序失败");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            System.out.println("正在连接数据库");
            conn = DriverManager.getConnection(connectionURL, "scut", "8888");
            String insertQuery = "INSERT INTO StudentTable VALUES(?,?,?)";
            stmt = conn.prepareStatement(insertQuery);

            System.out.println("请输入需要添加的学生数目");
            int num = in.nextInt();

            for (int i = 0; i < num; i++) {
                System.out.print("学生ID:");
                int id = in.nextInt();
                System.out.print("学生姓名:");
                String name = in.next();
                System.out.print("学生年龄:");
                int age = in.nextInt();

                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setInt(3, age);

                stmt.executeUpdate();

                System.out.println("插入学生资料成功！");
            }
        } catch (SQLException e) {
            System.out.println("SQL操作异常！");
            System.out.println(e.getMessage());
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
}
