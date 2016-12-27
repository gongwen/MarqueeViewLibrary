package com.gw.marquee;

/**
 * Created by GongWen on 16/12/27.
 */

public class ComplexItemEntity {

    private String title;
    private String secondTitle;
    private String time;

    public ComplexItemEntity(String title, String secondTitle, String time) {
        this.title = title;
        this.secondTitle = secondTitle;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
