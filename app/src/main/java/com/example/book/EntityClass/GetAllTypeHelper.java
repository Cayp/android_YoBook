package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/9/28.
 */

public class GetAllTypeHelper {
    /**
     * code : 20000
     * message : 获取类型成功
     * data : null
     * dataList : [{"id":3,"name":"教科书"},{"id":1,"name":"文学"}]
     */

    private int code;
    private String message;
    private Object data;
    private List<TypeData> dataList;

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

    public List<TypeData> getDataList() {
        return dataList;
    }

    public void setDataList(List<TypeData> dataList) {
        this.dataList = dataList;
    }

    public static class TypeData {
        /**
         * id : 3
         * name : 教科书
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
