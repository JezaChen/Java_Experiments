package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchInfo {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String dbName = "ManagementSys";
    private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";

    private static final String allQuery = "SELECT * FROM StudentTable";
    private static final String idQuery = "SELECT * FROM StudentTable WHERE id = ?";
    private static final String nameQuery = "SELECT * FROM StudentTable WHERE name = ?";
    private static final String ageQuery = "SELECT * FROM StudentTable WHERE age = ?";

    private static Scanner in = new Scanner(System.in);
    //private static ArrayList<String> ans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("正在载入驱动程序");
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("载入驱动程序失败");
        }
        Connection conn = null;
        try {
            System.out.println("正在连接数据库");
            conn = DriverManager.getConnection(connectionURL, "scut", "8888");

            System.out.println("连接成功");
            executeQuery(conn);
        } catch (SQLException e) {
            System.out.println("数据库连接出现问题");
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

    private static void executeQuery(Connection conn) {
        System.out.println("1)查询ID 2)查询姓名 3)查询年龄 4)列出所有记录");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                executeIdQuery(conn);
                return;
            case 2:
                executeNameQuery(conn);
                return;
            case 3:
                executeAgeQuery(conn);
                return;
            case 4:
                executeAllQuery(conn);
                return;
            default:
                System.out.println("输入命令有错误!");
        }
    }

    private static void executeIdQuery(Connection conn) {
        System.out.println("输入要查询的ID:");
        int targetId = in.nextInt();
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(idQuery);
            stat.setInt(1, targetId);
            ResultSet rs = stat.executeQuery();
            if(!rs.next())
                System.out.println("没有相应的记录");
            else {
                do {
                    for (int i = 1; i <= 3; i++) {
                        if (i > 1)
                            System.out.print(", ");
                        System.out.print(rs.getString(i));
                    }
                    System.out.println();
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("数据库查询出现异常!");
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

    private static void executeNameQuery(Connection conn) {
        System.out.println("请输入要查询的名字:");
        String targetName = in.next();

        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(nameQuery);
            stat.setString(1, targetName);
            ResultSet rs = stat.executeQuery();
            if(!rs.next())
                System.out.println("没有相应的记录");
            else {
                do {
                    for (int i = 1; i <= 3; i++) {
                        if (i > 1)
                            System.out.print(", ");
                        System.out.print(rs.getString(i));
                    }
                    System.out.println();
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("数据库查询出现异常!");
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

    private static void executeAgeQuery(Connection conn) {
        System.out.println("输入需要查询的年龄:");
        int targetAge = in.nextInt();
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(ageQuery);
            stat.setInt(1, targetAge);

            ResultSet rs = stat.executeQuery();
            if(!rs.next())
                System.out.println("没有相应的记录");
            else {
                do {
                    for (int i = 1; i <= 3; i++) {
                        if (i > 1)
                            System.out.print(", ");
                        System.out.print(rs.getString(i));
                    }
                    System.out.println();
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("数据库查询出现异常!");
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

    private static void executeAllQuery(Connection conn) {
        System.out.println("现在输出所有的记录");
        Statement stat = null;
        try {
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(allQuery);
            if(!rs.next())
                System.out.println("没有相应的记录");
            else {
                do {
                    for (int i = 1; i <= 3; i++) {
                        if (i > 1)
                            System.out.print(", ");
                        System.out.print(rs.getString(i));
                    }
                    System.out.println();
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("数据库查询出现异常!");
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
