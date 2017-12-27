package com.company;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelFunction {
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
        System.out.println("1)导出到Excel文档 2)导入Excel文档");
        int choice = in.nextInt();

        System.out.println("请给出相应的路径");
        String path = in.next();

        switch (choice) {
            case 1:
                outputExcel(conn, path);
                return;
            case 2:
                inputExcel(conn, path);
                return;
            default:
                System.out.println("输入命令有错误!");
        }
    }

    private static void outputExcel(Connection conn, String FilePath) {
        WritableWorkbook bWorkbook = null; //创建一个可写的workbook对象
        Statement stat = null;
        try {
            System.out.println("现在开始导出到Excel文档");
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(allQuery);
            bWorkbook = Workbook.createWorkbook(new File(FilePath));
            WritableSheet sheet = bWorkbook.createSheet("sheet", 0);

            //插入标题行
            Label titleNameLabel = new Label(0, 0, "姓名");
            Label titleIDLabel = new Label(1, 0, "学号");
            Label titleAgeLabel = new Label(2, 0, "年龄");

            sheet.addCell(titleNameLabel);
            sheet.addCell(titleIDLabel);
            sheet.addCell(titleAgeLabel);

            int line = 1;
            //插入学生信息
            if (!rs.next())
                System.out.println("没有相应的记录");
            else {
                do {
                    sheet.addCell(new Label(0, line, rs.getString("name")));
                    sheet.addCell(new Label(1, line, rs.getString("id")));
                    sheet.addCell(new Label(2, line, rs.getString("age")));
                    line++;
                } while (rs.next());
            }

            bWorkbook.write(); //开始写入
            System.out.println("导出到" + FilePath + "完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bWorkbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void inputExcel(Connection conn, String FilePath) {
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>(), age = new ArrayList<>();
        Workbook bWorkbook = null;
        try {
            File file = new File(FilePath);
            //file.createNewFile(); //强制把文档覆盖吧

            bWorkbook = Workbook.getWorkbook(file);
            Sheet sheet = bWorkbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                name.add(sheet.getCell(0, i).getContents());
                id.add(Integer.valueOf(sheet.getCell(1, i).getContents()));
                age.add(Integer.valueOf(sheet.getCell(2, i).getContents()));
            }

            InsertInfo.insertInfos(name, id, age);
            System.out.println("Excel文本导入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
