package com.example.mobilecontent.items;

import android.graphics.drawable.Drawable;

public class recyclerItem_alream {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String cycleStr;
    private String timeStr;
    private  String weeksStr;

    public Drawable getIcon() {
        return iconDrawable;
    }
    public void setIcon(Drawable icon){
        iconDrawable=icon;
    }
    public void settitle(String title){
        titleStr=title;
    }
    public void setcycle(String cycle){
        cycleStr=cycle;
    }
    public void setTime(String time){
        timeStr=time;
    }
    public void setweek(String week){
        weeksStr=week;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getcycle() {
        return this.cycleStr ;
    }
    public String gettime() {
        return this.timeStr ;
    }
    public String getweeks() {
        return this.weeksStr ;
    }
}
