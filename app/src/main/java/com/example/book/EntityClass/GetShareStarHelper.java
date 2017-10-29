package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/10/26.
 */

public class GetShareStarHelper {
    /**
     * code : 20000
     * message : 获取点赞成功
     * data : null
     * dataList : [{"id":2,"shareId":7,"userId":24,"username":"Cafe","avatar":null,"sex":"m","brief":null}]
     */

    private int code;
    private String message;
    private Object data;
    private List<LIkeUserData> dataList;

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

    public List<LIkeUserData> getDataList() {
        return dataList;
    }

    public void setDataList(List<LIkeUserData> dataList) {
        this.dataList = dataList;
    }

    public static class LIkeUserData {
        /**
         * id : 2
         * shareId : 7
         * userId : 24
         * username : Cafe
         * avatar : null
         * sex : m
         * brief : null
         */

        private int id;
        private int shareId;
        private int userId;
        private String username;
        private String avatar;
        private String sex;
        private Object brief;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShareId() {
            return shareId;
        }

        public void setShareId(int shareId) {
            this.shareId = shareId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getBrief() {
            return brief;
        }

        public void setBrief(Object brief) {
            this.brief = brief;
        }
    }
}
