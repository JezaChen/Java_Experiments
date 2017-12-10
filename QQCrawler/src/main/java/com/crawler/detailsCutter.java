package com.crawler;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//新闻详细页面裁剪
public class detailsCutter {
    public static synchronized void PageCut(Document doc, String fileName)
            throws IOException {
        Elements newsElem = doc.select("p[class]"); //获取正文对应的块

        //新建个html临时文件
        String pathName = "details/" + fileName + ".html";
        File outputFile = new File(pathName);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        } else {
            outputFile.delete();
            outputFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(outputFile);

        //写进去
        String ans = null;
        for (Element elem : newsElem) {
            if (elem.attr("class").contains("image") || elem.childNodeSize() == 0) //图片附属文字就撤掉吧，现在还不显示图片
                continue;
            fileWriter.write(elem.toString());
        }
        fileWriter.flush();
        fileWriter.close();
    }
/*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String urlPath = in.nextLine();
        try {
            String context = pageDownload.downloadPage(urlPath);
            Document doc = pageDownload.stringToDocument(context);
            PageCut(doc);

            //尝试输出新闻
            /*for (int i = 0; i < entTitle.size(); i++) {
                System.out.println(entTitle.get(i));
                System.out.println(entURL.get(i));
            }
            detailsForm_test form = new detailsForm_test();
            form.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
