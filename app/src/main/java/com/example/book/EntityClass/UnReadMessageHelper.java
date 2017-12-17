package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/11/13.
 */

public class UnReadMessageHelper {

    /**
     * code : 20000
     * message : 获取未读消息成功
     * data : null
     * dataList : [{"id":1,"fromId":2,"toId":1,"content":"你好呀","image":"图片","text":"长文本"}]
     */

    private int code;
    private String message;
    private Object data;
    private List<UnReadChats> dataList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<UnReadChats> getDataList() {
        return dataList;
    }

    public void setDataList(List<UnReadChats> dataList) {
        this.dataList = dataList;
    }

    public static class UnReadChats {
        /**
         * id : 1
         * fromId : 2
         * toId : 1
         * content : 你好呀
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
}
