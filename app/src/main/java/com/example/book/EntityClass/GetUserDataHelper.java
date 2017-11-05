package com.example.book.EntityClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ljp on 2017/9/18.
 */

public class GetUserDataHelper  {

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

    public static class UserData implements Parcelable {
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
        private String brief;
        private String avatar;

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.username);
            dest.writeString(this.sex);
            dest.writeString(this.brief);
            dest.writeString(this.avatar);
        }

        public UserData() {
        }

        protected UserData(Parcel in) {
            this.id = in.readInt();
            this.username = in.readString();
            this.sex = in.readString();
            this.brief = in.readString();
            this.avatar = in.readString();
        }

        public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
            @Override
            public UserData createFromParcel(Parcel source) {
                return new UserData(source);
            }

            @Override
            public UserData[] newArray(int size) {
                return new UserData[size];
            }
        };
    }

}
