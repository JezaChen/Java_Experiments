package com.company;

public class Node {
    private double x;
    private double y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

class NodeHandler {
    public static double Xi2_sum(Node[] nodes) {
        double ans = 0;
        for (Node target : nodes) {
            ans += target.getX() * target.getX();
        }
        return ans;
    }

    public static double X_average(Node[] nodes) {
        double ans = 0;
        for (Node target : nodes) {
            ans += target.getX();
        }
        return ans / nodes.length;
    }

    public static double Y_average(Node[] nodes) {
        double ans = 0;
        for (Node target : nodes) {
            ans += target.getY();
        }
        return ans / nodes.length;
    }

    public static double XiYi_sum(Node[] nodes) {
        double ans = 0;
        for(Node target : nodes)
        {
            ans += target.getX() * target.getY();
        }
        return ans;
    }


}