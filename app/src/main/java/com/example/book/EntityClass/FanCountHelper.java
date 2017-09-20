package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/9/18.
 */

public class FanCountHelper {
    /**
     * code : 20000
     * message : 获取粉丝成功
     * data : null
     * dataList : [{"id":24,"account":null,"password":null,"username":"Cafe","sex":"m","brief":null,"avatar":null}]
     */

    private int code;
    private String message;
    private Object data;
    private List<DataListBean> dataList;

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

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * id : 24
         * account : null
         * password : null
         * username : Cafe
         * sex : m
         * brief : null
         * avatar : null
         */

        private int id;
        private Object account;
        private Object password;
        private String username;
        private String sex;
        private Object brief;
        private Object avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
            this.account = account;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }
    }
}
