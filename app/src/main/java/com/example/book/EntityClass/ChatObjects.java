package com.example.book.EntityClass;

import org.litepal.crud.DataSupport;

/**
 * Created by ljp on 2017/11/12.
 */

public class ChatObjects extends DataSupport {
    private int toUserId;    //唯一
    private String avatar;
    private String name;
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
