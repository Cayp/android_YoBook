package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/9/17.
 */

public class ShowBook_TardeHelper {


    /**
     * code : 20000
     * message : 获取旧书成功
     * data : null
     * dataList : [{"id":3,"userId":0,"name":"fsdf","cover":"0d3327d7310412c4daf0fd6992731544.jpg","description":"sfaf","typeId":3,"publish":"asfsaf","price":"asfas"},{"id":1,"userId":24,"name":"《彼岸花》","cover":"0d3327d7310412c4daf0fd6992731544.jpg","description":"颓废文学，安妮宝贝","typeId":2,"publish":"北进十月文艺出版社","price":"面谈"}]
     */

    private int code;
    private String message;
    private Object data;
    private List<BookData> dataList;

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

    public List<BookData> getDataList() {
        return dataList;
    }

    public void setDataList(List<BookData> dataList) {
        this.dataList = dataList;
    }

    public static class BookData {
        /**
         * id : 3
         * userId : 0
         * name : fsdf
         * cover : 0d3327d7310412c4daf0fd6992731544.jpg
         * description : sfaf
         * typeId : 3
         * publish : asfsaf
         * price : asfas
         */

        private int id;
        private int userId;
        private String name;
        private String cover;
        private String description;
        private int typeId;
        private String publish;
        private String price;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
