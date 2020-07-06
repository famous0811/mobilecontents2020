package com.example.mobilecontent.frebase_class;

public class Question {
    private String year, month, day;
    private int goods, views;
    private String title, text, uesrs;

    public Question(String year, String month, String day, int goods, int views, String title, String text, String uesrs) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.goods = goods;
        this.views = views;
        this.title = title;
        this.text = text;
        this.uesrs = uesrs;
    }
}
