package com.company;


import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class kMeans {

    private static ArrayList<Node> nodes;
    private static ArrayList<ArrayList<Node>> clusters;
    private static double range;

    @Contract(pure = true)
    public static ArrayList<Node> getNodes() {
        return nodes;
    }

    @Contract(pure = true)
    public static ArrayList<ArrayList<Node>> getClusters() {
        return clusters;
    }

    private static int k;

    private static double minXValue;
    private static double minYValue;
    private static double maxXValue;
    private static double maxYValue;
    private static double XRange;
    private static double YRange;


    public static double getMinXValue() {
        return minXValue;
    }

    public static double getMinYValue() {
        return minYValue;
    }

    public static double getMaxXValue() {
        return maxXValue;
    }

    public static double getMaxYValue() {
        return maxYValue;
    }

    public static double getXRange() {
        return XRange;
    }

    public static double getYRange() {
        return YRange;
    }

    public static void setRange(double range) {
        kMeans.range = range;
    }

    public static void setK(int k) {
        kMeans.k = k;
    }

    public static void main(String[] args) {
        nodes = new ArrayList<>();
        clusters = new ArrayList<>();

        Demo demo = new Demo();
        demo.setVisible(true);

    }

    static void readFile(String dirPath, String fileName) {
        nodes = IOHandler.readData(dirPath, fileName);
    }

    static void kMeansSolve() {
        minXValue = IOHandler.getMinXValue();
        minYValue = IOHandler.getMinYValue();
        maxXValue = IOHandler.getMaxXValue();
        maxYValue = IOHandler.getMaxYValue();
        XRange = maxXValue - minXValue;
        YRange = maxYValue - minYValue;

        clusters.clear();

        Random random = new Random();

        //随机生成k个点
        Node[] clusterCenters = new Node[k];
        Node[] newClusterCenters = new Node[k];

        for (int i = 0; i < k; i++)
            clusters.add(new ArrayList<Node>());

        for (int i = 0; i < k; i++) {
            double x = random.nextDouble() * XRange + minXValue;
            double y = random.nextDouble() * YRange + minYValue;
            newClusterCenters[i] = new Node(x, y);
        }

        while (true) {
            for (int i = 0; i < clusters.size(); i++) {
                clusters.get(i).clear();
            }
            clusterCenters = newClusterCenters;
            newClusterCenters = new Node[k];

            //选择每个点中合适的簇点，并且加入其中
            Iterator<Node> iterator = nodes.listIterator();
            while (iterator.hasNext()) {
                Node currNode = iterator.next();
                int r = NodeHandler.nearestCluster(currNode, clusterCenters, k);
                clusters.get(r).add(currNode);
            }

            //计算质心
            for (int i = 0; i < k; i++) {
                Node newClusterCenter = NodeHandler.ClusterCentre(clusters.get(i));
                newClusterCenters[i] = newClusterCenter;
            }

            //判断是否需要继续
            boolean isCompleted = false;
            for (int i = 0; i < k; i++) {
                if (Math.abs(clusterCenters[i].getX() - newClusterCenters[i].getX()) > range &&
                        Math.abs(clusterCenters[i].getY() - newClusterCenters[i].getY()) > range)
                    isCompleted = true;
            }

            if (!isCompleted) break;
        }

        //print
        for (int i = 0; i < k; i++) {
            System.out.println("Node " + i + ": " + clusterCenters[i].getX() + "     " + clusterCenters[i].getY());
        }
    }
}
