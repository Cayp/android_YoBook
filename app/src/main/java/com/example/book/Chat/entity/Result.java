package com.example.book.Chat.entity;

import java.util.List;

/**
 * Created by Clanner on 2017/5/14.
 */

public class Result<E,T> {
    private int code;
    private String message;
    private E data;
    private List<T> dataList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
