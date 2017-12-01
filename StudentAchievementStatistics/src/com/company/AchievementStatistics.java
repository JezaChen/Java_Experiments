package com.company;

import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class AchievementStatistics {

    public static void main(String[] args) {
        solve();
    }

    static ArrayList<Student> readFile(String filePath) {
        return IOHandler.reader(filePath);
    }


    static void solve() {
        ArrayList<Student> arr;
        System.out.println("Input FilePath: ");

        Scanner in = new Scanner(System.in);
        String FilePath = in.nextLine();

        arr = readFile(FilePath);
        Student[] students = new Student[arr.size()];
        arr.toArray(students);

        MaxMinData maxmin = StudentStatisticsHandler.MaxMin(students);
        int maxScore = maxmin.getMax();
        int minScore = maxmin.getMin();

        double aveScore = StudentStatisticsHandler.averageScore(students);

        CountData countData = StudentStatisticsHandler.ScoreHistogram(students);

        System.out.println("Input The File Path You Would Like to Write");
        FilePath = in.nextLine();
        IOHandler.writer(FilePath, maxScore, minScore, aveScore, countData);

        System.out.println("Completed. Enjoy it!");
    }
}
