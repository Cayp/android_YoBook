package com.example.book.EntityClass;

import java.io.File;

/**
 * Created by ljp on 2017/10/3.
 */

public class PublishSecBookHelper {
    private String bookName;
    private File  bookCover;
    private String bookDescription;
    private String publishId;
    private int type_id;
    private String price;
    private String coverName;
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public File getBookCover() {
        return bookCover;
    }

    public void setBookCover(File bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }
}
