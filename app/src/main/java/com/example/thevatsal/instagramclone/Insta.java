package com.example.thevatsal.instagramclone;

/**
 * Created by thevatsal on 03/11/17.
 */

public class Insta {

    private String title, desc, imageurl;

    public Insta(){


    }

    public Insta(String title, String desc, String imageurl) {
        this.title = title;
        this.desc = desc;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
