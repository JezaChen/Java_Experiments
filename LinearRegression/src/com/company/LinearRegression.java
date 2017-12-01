package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class LinearRegression {

    public static void main(String[] args) {
        System.out.println("FilePath: ");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        ArrayList<Node> arr = IOHandler.reader(filePath);
        Node[] nodes = new Node[arr.size()];
        arr.toArray(nodes);


        double XiYi_sum = NodeHandler.XiYi_sum(nodes);
        double X_Average = NodeHandler.X_average(nodes);
        double Y_Average = NodeHandler.Y_average(nodes);
        double Xi2_sum = NodeHandler.Xi2_sum(nodes);
        int n = nodes.length;

        //calculate
        double b = (XiYi_sum - (double) n * X_Average * Y_Average) / (Xi2_sum - n * X_Average * X_Average);
        double a = Y_Average - b * X_Average;

        //print
        System.out.print("y = " + b + "x ");
        if (a > 0)
            System.out.println("+ " + a);
        else if(a<0)
            System.out.println("- "+(-a));
        else
            System.out.println();
    }
}
