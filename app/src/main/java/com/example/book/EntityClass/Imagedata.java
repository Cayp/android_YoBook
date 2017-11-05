package com.example.book.EntityClass;

import java.io.File;

/**
 * Created by ljp on 2017/11/4.
 */

public class Imagedata {
    private String path;
    private File cover;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getCover() {
        return cover;
    }

    public void setCover(File cover) {
        this.cover = cover;
    }
}
