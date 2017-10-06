package com.example.book.Chat.entity;

/**
 * Created by Clanner on 2017/9/13.
 */
public class Message {

    /**
     * id : 信息id
     * fromId : 好友id
     * toId : 自己的id
     * content : 内容
     * image : 图片
     * text : 长文本
     */
    private int id;
    private int fromId;
    private int toId;
    private String content;
    private String image;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
