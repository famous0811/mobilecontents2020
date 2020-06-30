package com.example.mobilecontent.items;

import android.graphics.drawable.Drawable;

public class recyclerItem_write {
    private Drawable icon;
    private String views;
    private String goods;
    private String some_contents;
    private String title;

    public void setIcon(Drawable icon){
        this.icon=icon;
    }
    public void settitle(String title){
        this.title=title;
    }
    public void setViews(String views){
        this.views=views;
    }
    public void setGoods(String goods){
        this.goods=goods;
    }
    public void setcontents(String some_contents){
        this.some_contents=some_contents;
    }
    public Drawable getIcon() {
        return this.icon;
    }
    public String getViews() {
        return this.views ;
    }
    public String getGoods() {
        return this.goods;
    }
    public String getTitle() {
        return this.title;
    }
    public String getContents(){return this.some_contents;}
}
