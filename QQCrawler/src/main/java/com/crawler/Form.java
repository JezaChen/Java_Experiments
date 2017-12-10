package com.crawler;


import javax.swing.*;

import org.jsoup.nodes.Document;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Form extends JFrame {
    private JTree tree1;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("腾讯网");
    private DefaultMutableTreeNode newsNode = new DefaultMutableTreeNode("新闻");
    private DefaultMutableTreeNode viewNode = new DefaultMutableTreeNode("观点");
    private DefaultMutableTreeNode financeNode = new DefaultMutableTreeNode("财经");
    private DefaultMutableTreeNode sportsNode = new DefaultMutableTreeNode("体育");
    private DefaultMutableTreeNode techNode = new DefaultMutableTreeNode("科技");
    private DefaultMutableTreeNode entNode = new DefaultMutableTreeNode("娱乐");
    private DefaultMutableTreeNode culNode = new DefaultMutableTreeNode("文化");
    private DefaultMutableTreeNode eduNode = new DefaultMutableTreeNode("教育");
    private DefaultMutableTreeNode autoNode = new DefaultMutableTreeNode("汽车");
    private DefaultMutableTreeNode gameNode = new DefaultMutableTreeNode("游戏");
    private DefaultMutableTreeNode comicNode = new DefaultMutableTreeNode("动漫");
    private DefaultMutableTreeNode otherNode = new DefaultMutableTreeNode("其他");

    private final int DEFAULT_WIDTH = 1200;
    private final int DEFAULT_HEGIHT = 800;

    private File waitingHtmlFile;
    private String waitingHtmlFileAbsolutePath;

    {
        waitingHtmlFile = new File("wait.html");
        waitingHtmlFileAbsolutePath = waitingHtmlFile.getAbsolutePath();
        waitingHtmlFileAbsolutePath = "file:" + waitingHtmlFileAbsolutePath;
    }


    private DownloadAndCutDetailsPageThread thread;
    private JEditorPane detailsPane = new JEditorPane();

    private Font formFont = new Font("Arial", Font.BOLD, 20);


    public Form() {
        initTree();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tree1.setFont(formFont);

        //setFont(formFont);
        // pack();
        setSize(DEFAULT_WIDTH, DEFAULT_HEGIHT);

        tree1.addTreeSelectionListener(event -> {
            TreePath path = tree1.getSelectionPath();
            if (path == null) return;
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();

            if (selectedNode.getUserObject().getClass().toString() != "class com.crawler.linkNode") {
                linkNode selectedLinkNode = (linkNode) selectedNode.getUserObject();

                getNewsDetailsInForm(selectedLinkNode);
            }
        });

        tree1.getSelectionModel()
                .setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.add(new JScrollPane(tree1));
        jPanel.add(new JScrollPane(detailsPane));

        add(jPanel, BorderLayout.CENTER);
    }

    /*
    * 初始化JTree
    * */
    private void initTree() {
        root.add(newsNode);
        root.add(viewNode);
        root.add(financeNode);
        root.add(sportsNode);
        root.add(techNode);
        root.add(entNode);
        root.add(culNode);
        root.add(eduNode);
        root.add(autoNode);
        root.add(gameNode);
        root.add(comicNode);
        root.add(otherNode);
        tree1 = new JTree(root);
    }

    public void addItem(String parentName, linkNode itemName) {
        if (parentName.equals("news")) {
            newsNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("view")) {
            viewNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("finance")) {
            financeNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("sports")) {
            sportsNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("tech")) {
            techNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("ent")) {
            entNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("auto")) {
            autoNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("game")) {
            gameNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("cul")) {
            culNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("edu")) {
            eduNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("comic")) {
            comicNode.add(new DefaultMutableTreeNode(itemName));
            return;
        }
        if (parentName.equals("other")) {
            otherNode.add(new DefaultMutableTreeNode(itemName));
        }
    }

    /*
    * 显示该节点所对应的新闻正文
    * */
    private synchronized void getNewsDetailsInForm(linkNode node) {
        try {
            String path = node.getUrlPath(); //获取该节点的URL
            /*String str = pageDownload.downloadPage(path); //下载对应的网页为字符串
            Document doc = pageDownload.stringToDocument(str); //字符串转Document

            detailsCutter.PageCut(doc); //开始切割正文*/

            thread = new DownloadAndCutDetailsPageThread(path, node.getTitle());
            thread.run();

            //然后用java Swing 的 JEditorPane 显示切割好的html页面

            String fileName = "details/" + node.getTitle() + ".html"; //还是放在一个目录里面吧...
            File file = new File(fileName);
            String absolutePath = file.getAbsolutePath();
            absolutePath = "file:" + absolutePath;
            detailsPane.setPage(absolutePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//用于下载和切割页面的线程
class DownloadAndCutDetailsPageThread implements Runnable {
    private String urlPath;
    private String titleName; //用于生成文件名

    DownloadAndCutDetailsPageThread(String urlPath, String titleName) {
        this.urlPath = urlPath;
        this.titleName = titleName;
    }

    public void run() {
        try {
            String str = pageDownload.downloadPage(urlPath);
            Document doc = pageDownload.stringToDocument(str);

            detailsCutter.PageCut(doc, titleName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}