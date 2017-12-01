package com.company;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Demo extends JFrame {

    private JPanel panel;
    private JMenu menuFile = new JMenu("File");
    private JMenuItem openItem = new JMenuItem("Open");
    private JMenuItem closeItem = new JMenuItem("Close");
    private JMenuBar menuBar = new JMenuBar();
    private FileDialog fileOpenDialog;

    public Demo() {
        this.setSize(200, 100);
        initMenu();

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fileOpenDialog = new FileDialog(Demo.this, "Open", FileDialog.LOAD); //Demo.this 外围类的引用
                fileOpenDialog.setVisible(true);
                String dirPath = fileOpenDialog.getDirectory();
                String fileName = fileOpenDialog.getFile();

                //IOHandler.readData(dirPath, fileName);
                kMeans.readFile(dirPath, fileName);
                String str_k = JOptionPane.showInputDialog("Input k: ");
                int k = Integer.valueOf(str_k);
                String str_range = JOptionPane.showInputDialog("Input range: ");
                double range = Double.valueOf(str_range);

                DrawComponent drawComponent = new DrawComponent();
                drawComponent.drawOriginal = true;

                Demo.this.add(drawComponent);
                pack();

                kMeans.setK(k);
                kMeans.setRange(range);
                kMeans.kMeansSolve();

                Demo.this.remove(drawComponent);
                drawComponent.drawOriginal = false;
                Demo.this.add(drawComponent);
                pack();
            }
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int answer = JOptionPane.showConfirmDialog(null, "是否关闭程序?", "关闭程序", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else
                    return;
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int answer = JOptionPane.showConfirmDialog(null, "是否关闭程序?", "关闭程序", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void initMenu() {

        menuFile.add(openItem);
        menuFile.add(closeItem);

        menuBar.add(menuFile);
        this.setJMenuBar(menuBar);
    }


}
