package com.example.book.Chat.entity;

/**
 * Created by Clanner on 2017/9/15.
 */
public class Notice {
    private String from;
    private String content;

    public Notice(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
