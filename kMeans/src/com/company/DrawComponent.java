package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;
    public boolean drawOriginal = true;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double xMin = -kMeans.getMinXValue();
        double yMin = -kMeans.getMinYValue();

        double xRange = kMeans.getXRange();
        double yRange = kMeans.getYRange();

        if (drawOriginal) {
            ArrayList<Node> nodes = kMeans.getNodes();
            for (Node curr : nodes) {
                Ellipse2D pointOval = new Ellipse2D.Double((curr.getX() + xMin) * 40, (curr.getY() + yMin) * 40, 8, 8);
                g2.setColor(Color.RED);
                g2.fill(pointOval);
            }
        } else {
            ArrayList<ArrayList<Node>> clusters = kMeans.getClusters();
            for (int i = 0; i < clusters.size(); i++) {
                for (Node curr : clusters.get(i)) {
                    Ellipse2D pointOval = new Ellipse2D.Double((curr.getX() + xMin) * 40, (curr.getY() + yMin) * 40, 8, 8);
                    g2.setColor(selectColor(i));
                    g2.fill(pointOval);
                }
            }
        }
    }

    private Color selectColor(int k) {
        switch (k) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GRAY;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.PINK;
            case 6:
                return Color.LIGHT_GRAY;
            case 7:
                return Color.ORANGE;
            default:
                return Color.BLACK;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
