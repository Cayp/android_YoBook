package com.example.book.EntityClass;

import org.litepal.crud.DataSupport;

/**
 * Created by ljp on 2017/11/14.
 */

public class NoteBook extends DataSupport {
    private String isbn;
    private String coverUrl;
    private String name ;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
