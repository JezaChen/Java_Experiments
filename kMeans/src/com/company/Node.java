package com.company;

import java.util.ArrayList;

public class Node {
    private double x;
    private double y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}

class NodeHandler {
    static double EuclideanMetric(Node a, Node b) {
        return Math.sqrt(
                (a.getX() - b.getX()) * (a.getX() - b.getX())
                        + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    static Node ClusterCentre(ArrayList<Node> Cluster) {
        double aveX = 0, aveY = 0;
        //Iterator<Node> iterator = Cluster.iterator();
        for (Node node : Cluster) {
            aveX += node.getX();
            aveY += node.getY();
        }
        aveX /= Cluster.size();
        aveY /= Cluster.size();

        return new Node(aveX, aveY);
    }

    static int nearestCluster(Node target, Node[] clusters, int k) {
        int nearestClusterPosi = 0;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            if (EuclideanMetric(target, clusters[i]) < minDistance) {
                minDistance = EuclideanMetric(target, clusters[i]);
                nearestClusterPosi = i;
            }
        }
        return nearestClusterPosi;
    }
}