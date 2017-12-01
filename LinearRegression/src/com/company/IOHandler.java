package com.company;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//static
public class IOHandler {
    @Nullable
    public static ArrayList<Node> reader(String FilePath) {
        try {
            File targetFile = new File(FilePath);

            Scanner scanner = new Scanner(targetFile);
            ArrayList<Node> arr = new ArrayList<>();
            while (scanner.hasNextDouble()) {
                double r = scanner.nextDouble();
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                arr.add(new Node(x, y));
            }

            return arr;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }
    }
}
