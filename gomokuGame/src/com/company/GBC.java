package com.company;

import java.awt.*;

public class GBC extends GridBagConstraints {
    public GBC(int gridX, int gridY) {
        this.gridx = gridX;
        this.gridy = gridY;
    }

    public GBC(int gridX, int gridY, int gridWidth, int gridHeight) {
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
    }

    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }

    public GBC setWeight(int weightX, int weightY)
    {
        this.weightx = weightX;
        this.weighty = weightY;
        return this;
    }

    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}
