package com.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContextAnalyze {
    private static LinkFilter validLinkFilter = new LinkFilter("qq.com/a/");
    private static LinkFilter newsLinkFilter = new LinkFilter("//news.qq.com"); //新闻
    private static LinkFilter viewLinkFilter = new LinkFilter("view.qq.com"); //腾讯评论、今日话题
    private static LinkFilter entLinkFilter = new LinkFilter("ent.qq.com"); //娱乐新闻
    private static LinkFilter moneyLinkFilter = new LinkFilter("money.qq.com"); //腾讯理财
    private static LinkFilter sportsLinkFilter = new LinkFilter("sports.qq.com"); //体育新闻
    private static LinkFilter fashionLinkFilter = new LinkFilter("//fashion.qq.com"); //时尚新闻
    private static LinkFilter financeLinkFilter = new LinkFilter("finance.qq.com"); //财经新闻
    private static LinkFilter stockLinkFilter = new LinkFilter("stock.qq.com"); //证券新闻
    private static LinkFilter autoLinkFilter = new LinkFilter("auto.qq.com"); //汽车新闻
    private static LinkFilter digiLinkFilter = new LinkFilter("digi.qq.com"); //数码新闻
    private static LinkFilter culLinkFilter = new LinkFilter("cul.qq.com"); //文化新闻
    private static LinkFilter astroLinkFilter = new LinkFilter("astro.qq.com"); //星座新闻
    private static LinkFilter eduLinkFilter = new LinkFilter("edu.qq.com"); //教育新闻
    private static LinkFilter gamesLinkFilter = new LinkFilter("games.qq.com"); //腾讯游戏
    private static LinkFilter comicLinkFilter = new LinkFilter("comic.qq.com"); //动漫
    private static LinkFilter rufodaoLinkFilter = new LinkFilter("rufodao.qq.com"); //腾讯佛学
    private static LinkFilter gongyiLinkFilter = new LinkFilter("gongyi.qq.com"); //公益
    private static LinkFilter lyLinkFilter = new LinkFilter("ly.qq.com"); //腾讯旅游
    private static LinkFilter techFilter = new LinkFilter("tech.qq.com"); //腾讯科技

    //结点归类
    private static ArrayList<linkNode> newsTitle = new ArrayList<>();
    private static ArrayList<linkNode> viewTitle = new ArrayList<>();
    private static ArrayList<linkNode> financeTitle = new ArrayList<>(); //理财、财经、证券归为一类
    private static ArrayList<linkNode> sportsTitle = new ArrayList<>(); //体育
    private static ArrayList<linkNode> techTitle = new ArrayList<>(); //数码、科技归为一类
    private static ArrayList<linkNode> entTitle = new ArrayList<>(); //娱乐时尚归为一类
    private static ArrayList<linkNode> culTitle = new ArrayList<>(); //文化
    private static ArrayList<linkNode> eduTitle = new ArrayList<>(); //教育
    private static ArrayList<linkNode> autoTitle = new ArrayList<>(); //汽车
    private static ArrayList<linkNode> gameTitle = new ArrayList<>(); //游戏
    private static ArrayList<linkNode> comicTitle = new ArrayList<>(); //动漫
    private static ArrayList<linkNode> otherTitle = new ArrayList<>(); //其他

    private static Form showForm = new Form();

    private static void analyseIndex(Document doc) {
        //选取有连接的区域
        Elements links = doc.select("a[href]");

        //开始遍历选择
        for (Element link : links) {
            if (link.childNodeSize() != 1) continue;

            String text;
            //去掉图片连接，只保留纯文字标题
            if (link.childNode(0).attr("alt") != "" || link.childNode(0).attr("src") != "")
                continue;
            else
                text = link.childNode(0).toString();

            String linkHref = link.attr("href");
            //去掉js链接
            if (linkHref.toLowerCase().contains("javascript")) continue;
            //忽略锚点
            int index;
            if ((index = linkHref.indexOf("#")) != -1)
                linkHref = linkHref.substring(0, index);

            //正则表达筛选
            if (validLinkFilter.isValid(linkHref)) {
                putInArr(text, linkHref);
            }

        }
    }

    //往分类集合里面塞东西
    private static void putInArr(String title, String urlPath) {
        if (newsLinkFilter.isValid(urlPath)) {
            newsTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (viewLinkFilter.isValid(urlPath)) {
            viewTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (financeLinkFilter.isValid(urlPath)
                || stockLinkFilter.isValid(urlPath)
                || moneyLinkFilter.isValid(urlPath)) {
            financeTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (sportsLinkFilter.isValid(urlPath)) {
            sportsTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (techFilter.isValid(urlPath) || digiLinkFilter.isValid(urlPath)) {
            techTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (entLinkFilter.isValid(urlPath) || fashionLinkFilter.isValid(urlPath)) {
            entTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (culLinkFilter.isValid(urlPath)) {
            culTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (eduLinkFilter.isValid(urlPath)) {
            eduTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (autoLinkFilter.isValid(urlPath)) {
            autoTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (gamesLinkFilter.isValid(urlPath)) {
            gameTitle.add(new linkNode(title, urlPath));
            return;
        }
        if (comicLinkFilter.isValid(urlPath)) {
            comicTitle.add(new linkNode(title, urlPath));
            return;
        }
        otherTitle.add(new linkNode(title, urlPath));
    }

    //在窗口中显示标题
    private static void showInForm() {
        for (linkNode title : newsTitle) {
            showForm.addItem("news", title);
        }
        for (linkNode title : viewTitle) {
            showForm.addItem("view", title);
        }
        for (linkNode title : financeTitle) {
            showForm.addItem("finance", title);
        }
        for (linkNode title : sportsTitle) {
            showForm.addItem("sports", title);
        }
        for (linkNode title : techTitle) {
            showForm.addItem("tech", title);
        }
        for (linkNode title : entTitle) {
            showForm.addItem("ent", title);
        }
        for (linkNode title : autoTitle) {
            showForm.addItem("auto", title);
        }
        for (linkNode title : culTitle) {
            showForm.addItem("cul", title);
        }
        for (linkNode title : eduTitle) {
            showForm.addItem("edu", title);
        }
        for (linkNode title : comicTitle) {
            showForm.addItem("comic", title);
        }
        for (linkNode title : gameTitle) {
            showForm.addItem("game", title);
        }
        for (linkNode title : otherTitle) {
            showForm.addItem("other", title);
        }
    }

    public static void main(String[] args) {
        showForm.setVisible(true);
        Scanner in = new Scanner(System.in);
        String urlPath = in.nextLine();
        try {
            String context = pageDownload.downloadPage(urlPath);
            Document doc = pageDownload.stringToDocument(context);
            analyseIndex(doc);

            //尝试输出新闻
            showInForm();
            /*for (int i = 0; i < entTitle.size(); i++) {
                System.out.println(entTitle.get(i));
                System.out.println(entURL.get(i));
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class LinkFilter {
    private Pattern listLinkPattern;

    public LinkFilter(String pattern) {
        listLinkPattern = Pattern.compile(pattern);
    }

    public boolean isValid(String linkHref) {
        if (linkHref == null)
            return false;
        Matcher matcher = listLinkPattern.matcher(linkHref);

        return matcher.find();
    }
}
