package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/9/18.
 */

public class GetUserDataHelper {
    /**
     * code : 20000
     * message : 获取用户信息成功
     * data : {"id":24,"account":null,"password":null,"username":"Cafe","sex":"m","brief":null,"avatar":null}
     * dataList : null
     */

    private int code;
    private String message;
    private UserData data;
    private Object dataList;

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

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }

    public static class UserData {
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
        private String account =null;
        private String password = null;
        private String username = null;
        private String sex = null;
        private String brief = null;
        private String avatar = null;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
