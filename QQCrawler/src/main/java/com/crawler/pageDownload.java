package com.crawler;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.util.EntityUtils;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import javax.print.Doc;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;


public class pageDownload {
    public static synchronized String downloadPage(String urlPath)
            throws IOException {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36"));
        headers.add(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultHeaders(headers).build();

        HttpGet httpGet = new HttpGet(urlPath);
        HttpResponse response = httpClient.execute(httpGet);

        String context = null;

        int statCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            context = EntityUtils.toString(entity,"gbk");
            EntityUtils.consume(entity);
        }
        return context;
    }

    static synchronized Document stringToDocument(String str) {
        return Jsoup.parse(str);
    }

    static void analyseIndex(Document doc) {
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            if(link.childNodeSize() != 1) continue;
            String text = link.childNode(0).toString();
            System.out.println(text);
            String linkHref = link.attr("href");
            //String linkHref = link.toString();
            System.out.println(linkHref);
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String urlPath = in.nextLine();
        try {
            String context = downloadPage(urlPath);
            Document doc = stringToDocument(context);
            analyseIndex(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
