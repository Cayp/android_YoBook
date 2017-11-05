package com.example.book.EntityClass;

/**
 * Created by ljp on 2017/11/1.
 */

public class NotFoundDetailHelper {

    /**
     * msg : book_not_found
     * code : 6000
     * request : GET /v2/book/isbn/9787115439777
     */

    private String msg;
    private int code;
    private String request;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
