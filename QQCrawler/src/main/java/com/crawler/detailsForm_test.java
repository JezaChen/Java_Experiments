package com.crawler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class detailsForm_test extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    private JEditorPane jEditorPane;

    public detailsForm_test() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        try {
            File file = new File("/home/jeza/temp.html");
            String path = file.getAbsolutePath();
            path = "file:" + path;
            jEditorPane = new JEditorPane(path);
            add(new JScrollPane(jEditorPane));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
