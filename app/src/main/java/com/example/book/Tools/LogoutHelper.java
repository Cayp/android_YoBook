package com.example.book.Tools;

/**
 * Created by ljp on 2017/7/31.
 */

public class LogoutHelper {
    /**
     * code : 20000
     * message : 退出登录成功
     * data : null
     * dataList : null
     */

    private int code;
    private String message;
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }
}
