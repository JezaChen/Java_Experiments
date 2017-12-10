package com.qrcore;

import jdk.nashorn.internal.scripts.JO;
import sun.swing.MenuItemCheckIconFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

public class Form extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("文件");
    private JMenuItem openItem = new JMenuItem("打开二维码");
    private JMenuItem createItem = new JMenuItem("创建二维码");
    private JMenuItem saveItem = new JMenuItem("保存二维码");

    private JLabel imageLabel = new JLabel();
    private JTextArea textArea = new JTextArea();

    private FileDialog fileDialog;

    private final int DEFAULT_HEIGHT = 300;
    private final int DEFAULT_WEIGHT = 300;

    private String target;

    public Form() {
        init();
        setSize(DEFAULT_HEIGHT, DEFAULT_WEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        openItem.addActionListener(new ActionListener() { //打开文件
            public void actionPerformed(ActionEvent actionEvent) {
                saveItem.setEnabled(false); //只有创建二维码后这个才有效

                fileDialog = new FileDialog(Form.this, "打开二维码图片", FileDialog.LOAD);

                fileDialog.setVisible(true); //显示文件选择窗口

                String dirPath = fileDialog.getDirectory();
                String fileName = fileDialog.getFile();

                File loadFile = new File(dirPath, fileName); //创建file对象
                try {
                    imageLabel.setIcon(new ImageIcon(loadFile.getAbsolutePath()));
                    String ans = QRCore.decode(loadFile);
                    textArea.setText(ans);
                    pack(); //把窗体变成合适的大小
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        createItem.addActionListener(new ActionListener() { //创建文件
            public void actionPerformed(ActionEvent actionEvent) {
                saveItem.setEnabled(true); //这时候就可以保存了
                target = JOptionPane.showInputDialog("请输入需要转换成二维码的字符串: ");
                QRCore.encode(target, 300,300,"png"); //这一行的参数可以改变，对于使用者来说就不给他们看了

                //此后生成的文件为根目录下的temp.png
                imageLabel.setIcon(new ImageIcon("temp.png"));
                textArea.setText("生成" + target + "成功!");
                pack();
            }
        });

        saveItem.addActionListener(new ActionListener() { //保存文件
            public void actionPerformed(ActionEvent actionEvent) {
                fileDialog = new FileDialog(Form.this,"保存二维码图片",FileDialog.SAVE);
                fileDialog.setVisible(true);

                String targetPath = fileDialog.getDirectory();
                String fileName = fileDialog.getFile();

                File targetFile = new File(targetPath, fileName);
                QRCore.encodeAndSave(target, 300,300,"png",targetFile); //再调用一次Google Zxing的函数保存下来
            }
        });
    }

    private void initMenu(){
        menuFile.add(openItem);
        menuFile.add(createItem);
        menuFile.add(saveItem);

        menuBar.add(menuFile);
        this.setJMenuBar(menuBar);

        saveItem.setEnabled(false); //只有创建二维码后这个才有效
    }

    private void init(){
        initMenu();
        add(imageLabel, BorderLayout.CENTER);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        textArea.setFont(new Font("宋体",Font.BOLD,16));
        textArea.setEditable(false);
    }
}
