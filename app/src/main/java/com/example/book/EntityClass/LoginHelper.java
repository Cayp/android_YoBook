package com.example.book.EntityClass;

/**
 * Created by ljp on 2017/7/29.
 */

public class LoginHelper {
    /**
     * code : 20000
     * message : 登录成功
     * data : {"id":23,"account":"13692190631","password":null,"username":"Clanne","sex":"m","brief":null,"avatar":"13692190631"}
     * dataList : null
     */

    private int code;
    private String message;
    private User user;
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

    public User getData() {
        return user;
    }

    public void setData(User user) {
        this.user = user;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }

    public static class User {
        /**
         * id : 23
         * account : 13692190631
         * password : null
         * username : Clanne
         * sex : m
         * brief : null
         * avatar : 13692190631
         */

        private int id;
        private String account;
        private Object password;
        private String username;
        private String sex;
        private Object brief;
        private String avatar;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
