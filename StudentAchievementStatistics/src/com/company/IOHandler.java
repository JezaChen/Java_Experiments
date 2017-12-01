package com.company;

import org.jetbrains.annotations.Nullable;

import javax.print.attribute.standard.PrinterState;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class IOHandler {
    @Nullable
    public static ArrayList<Student> reader(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            ArrayList<Student> arr = new ArrayList<>();
            String str;

            /*while (scanner.hasNext()) {
                str = scanner.next();
                String[] strs = str.split(";");
                int id = Integer.valueOf(strs[0]);
                int scores = Integer.valueOf(strs[1]);
                arr.add(new Student(id, scores));
            }*/
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while((str = bufferedReader.readLine()) != null)
            {
                String[] strs = str.split(";");
                int id = Integer.valueOf(strs[0]);
                int score = Integer.valueOf(strs[1]);
                arr.add(new Student(id, score));
            }
            return arr;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
        catch(IOException e)
        {
            System.out.println("IOException");
            return null;
        }
    }

    public static void writer(String targetFilePath, int maxScore, int minScore, double aveScores, CountData countData) {
        try {
            File file = new File(targetFilePath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }

            PrintWriter pw = new PrintWriter(file);
            pw.println("The Highest Score is: " + maxScore);
            pw.println("The Lowest Score is: " + minScore);
            pw.println("The Average Score is: " + aveScores);

            pw.println("The Score Histogram between: ");
            pw.println("60-69: " + countData.getNum_60_69());
            pw.println("70-79: " + countData.getNum_70_79());
            pw.println("80-89: " + countData.getNum_80_89());
            pw.println("90-100: " + countData.getNum_90_100());

            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("Wrong Instruction!");
        }
    }
}
