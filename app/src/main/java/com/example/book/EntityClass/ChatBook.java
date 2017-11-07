package com.example.book.EntityClass;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by ljp on 2017/11/5.
 */

public class ChatBook extends DataSupport {
    private int toUserId;
    private List<ChatItem> chatRecord;

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public List<ChatItem> getChatRecord() {
        return chatRecord;
    }

    public void setChatRecord(List<ChatItem> chatRecord) {
        this.chatRecord = chatRecord;
    }
}
