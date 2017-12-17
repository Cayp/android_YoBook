package com.example.book.EntityClass;

import org.litepal.crud.DataSupport;

/**
 * Created by ljp on 2017/11/14.
 */

public class Note extends DataSupport {
    private String isbn;
    private int id ;
    private String title;
    private long time;
    private String content;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
