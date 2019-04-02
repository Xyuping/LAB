package com.example.xyp.e_mail;

/**
 * Created by xyp on 2018/3/31.
 */

public class Mail {
    private String title;
    private String sender;
    private String content;
    private int image;
    private String date;
    private String summary;

    public Mail(String title, String sender,String content,String date,int image,String summary) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.date=date;
        this.image = image;
        this.summary = summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {

        return summary;
    }


    public void setImage(int image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {

        return sender;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
