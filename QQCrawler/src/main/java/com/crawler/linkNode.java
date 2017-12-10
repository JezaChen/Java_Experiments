package com.crawler;


class linkNode {
    private String title;
    private String urlPath;

    public linkNode(String tl, String up) {
        title = tl;
        urlPath = up;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public String toString(){
        return title;
    }
}