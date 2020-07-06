package com.example.mobilecontent.items;

import android.graphics.drawable.Drawable;

public class recyclerItem_QuestionWrite {
    private Drawable icon;
    private String title;
    private String catagri1;
    private String catagri2;
    private int views, goods;

    public void SetGoods(int goods) {
        this.goods = goods;
    }

    public void SetViews(int views) {
        this.views = views;
    }

    public void SetIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setcatagori1(String catagri1) {
        this.catagri1 = catagri1;
    }

    public void setcatagori2(String catagri2) {
        this.catagri2=catagri2;
    }
    public Drawable getIcon(){
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCatagri1() {
        return this.catagri1;
    }

    public String getCatagri2() {
        return this.catagri2;
    }

    public String getGoods() {
        return Integer.toString(this.goods);
    }

    public String getViews() {
        return Integer.toString(this.views);
    }
}
