package com.company;

import java.sql.*;
import java.util.Scanner;

public class InsertInfo {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "ManagementSys";
    private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";
    private static Scanner in = new Scanner(System.in);

    public InsertInfo() {
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

                /*try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next())
                        System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
                }*/
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
