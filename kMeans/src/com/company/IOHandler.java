package com.company;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IOHandler {
    @Nullable
    public static ArrayList<Node> readData(String fileName) {
        try {
            minXValue = Double.MAX_VALUE;
            maxXValue = Double.MIN_VALUE;
            minYValue = Double.MAX_VALUE;
            maxYValue = Double.MIN_VALUE;

            File targetFile = new File(fileName);

            Scanner scanner = new Scanner(targetFile);
            ArrayList<Node> Arr = new ArrayList<>();

            while (scanner.hasNextDouble()) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();

                minXValue = Math.min(minXValue, x);
                maxXValue = Math.max(maxXValue, x);
                minYValue = Math.min(minYValue, y);
                maxYValue = Math.max(maxYValue, y);

                Arr.add(new Node(x, y));
            }
            return Arr;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            return null;
        }
    }

    @Nullable
    public static ArrayList<Node> readData(String dirPath, String fileName) {
        try {
            minXValue = Double.MAX_VALUE;
            maxXValue = Double.MIN_VALUE;
            minYValue = Double.MAX_VALUE;
            maxYValue = Double.MIN_VALUE;

            File targetFile = new File(dirPath, fileName);

            Scanner scanner = new Scanner(targetFile);
            ArrayList<Node> Arr = new ArrayList<>();

            while (scanner.hasNextDouble()) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();

                minXValue = Math.min(minXValue, x);
                maxXValue = Math.max(maxXValue, x);
                minYValue = Math.min(minYValue, y);
                maxYValue = Math.max(maxYValue, y);

                Arr.add(new Node(x, y));
            }
            return Arr;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            return null;
        }
    }
    private static double minXValue;
    private static double maxXValue;
    private static double minYValue;
    private static double maxYValue;

    public static double getMinXValue() {
        return minXValue;
    }

    public static double getMaxXValue() {
        return maxXValue;
    }

    public static double getMinYValue() {
        return minYValue;
    }

    public static double getMaxYValue() {
        return maxYValue;
    }
}
