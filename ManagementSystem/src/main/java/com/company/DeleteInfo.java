package com.company;

import java.sql.*;
import java.util.Scanner;

public class DeleteInfo {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "ManagementSys";
    private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    private static final String idQuery = "SELECT * FROM StudentTable WHERE id = ?";
    private static final String deleteQuery = "DELETE FROM StudentTable WHERE id = ?";

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
            executeDeleteQuery(conn);
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

    private static void executeDeleteQuery(Connection conn) {
        System.out.println("输入要删除的ID:");
        int targetId = in.nextInt();
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(idQuery);
            stat.setInt(1, targetId);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                System.out.println("找不到相应的记录");
                return;
            } else {
                System.out.println("该生的记录为:");
                for (int i = 1; i <= 3; i++) {
                    if (i > 1)
                        System.out.print(", ");
                    System.out.print(rs.getString(i));
                }
                System.out.println();
                System.out.println("正在删除，请妥善保存原纪录");

                stat = conn.prepareStatement(deleteQuery);
                stat.setInt(1, targetId);
                int cnt = stat.executeUpdate();
                if (cnt == 1)
                    System.out.println("删除成功");
                else
                    System.out.println("输出出现问题");
            }
        } catch (SQLException e) {
            System.out.println("SQL出现错误");
            System.out.println(e.getMessage());
        }
    }
}
