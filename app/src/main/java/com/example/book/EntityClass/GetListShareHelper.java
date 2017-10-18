package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/10/14.
 */

public class GetListShareHelper {

    /**
     * code : 20000
     * message : 获取分享成功
     * data : null
     * dataList : [{"id":6,"userId":24,"content":"内容","name":"彼岸花","cover":null,"time":1500605976000,"typeId":1,"commentNum":0,"starNum":0,"isbn":null},{"id":7,"userId":24,"content":"内容","name":"彼岸花","cover":null,"time":1500613450000,"typeId":2,"commentNum":0,"starNum":1,"isbn":null}]
     */

    private int code;
    private String message;
    private Object data;
    private List<ShareItem> dataList;

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

    public List<ShareItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<ShareItem> dataList) {
        this.dataList = dataList;
    }

    public static class ShareItem {
        /**
         * id : 6
         * userId : 24
         * content : 内容
         * name : 彼岸花
         * cover : null
         * time : 1500605976000
         * typeId : 1
         * commentNum : 0
         * starNum : 0
         * isbn : null
         */

        private int id;
        private int userId;
        private String content;
        private String name;
        private String cover;
        private long time;
        private int typeId;
        private int commentNum;
        private int starNum;
        private String isbn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getStarNum() {
            return starNum;
        }

        public void setStarNum(int starNum) {
            this.starNum = starNum;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    }
}
