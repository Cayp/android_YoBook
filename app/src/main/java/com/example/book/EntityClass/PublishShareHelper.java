package com.example.book.EntityClass;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by ljp on 2017/11/1.
 */

public class PublishShareHelper {
    private String content;
    private String name ;
    private int typeId;
    private File bookCover;
    private String CoverName;
    private String isbn;
    private List<Imagedata> files;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public File getBookCover() {
        return bookCover;
    }

    public void setBookCover(File bookCover) {
        this.bookCover = bookCover;
    }

    public String getCoverName() {
        return CoverName;
    }

    public void setCoverName(String coverName) {
        CoverName = coverName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Imagedata> getFiles() {
        return files;
    }

    public void setFiles(List<Imagedata> files) {
        this.files = files;
    }

}
