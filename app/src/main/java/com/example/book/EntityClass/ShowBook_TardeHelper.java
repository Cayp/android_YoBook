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
     * dataList : [{"username":"ssssss","avatar":"ssssss.jpg","id":1,"name":"《彼岸花》","publish":"北进十月文艺出版社","cover":"0d3327d7310412c4daf0fd6992731544.jpg","description":"颓废文学，安妮宝贝","price":"面谈","userId":24,"typeid":1},{"username":null,"avatar":null,"id":3,"name":"fsdf","publish":"asfsaf","cover":"0d3327d7310412c4daf0fd6992731544.jpg","description":"sfaf","price":"asfas","userId":0,"typeid":3},{"username":"ssss","avatar":"123.jpg","id":4,"name":"彼岸花","publish":"你猜","cover":"0d3327d7310412c4daf0fd6992731544.jpg","description":"啦啦啦啦","price":"5","userId":24,"typeid":1},{"username":"Cafe","avatar":null,"id":5,"name":"彼岸花","publish":"未知","cover":"4d638e20716ec2088a00034c.jpg","description":"好看","price":"面议","userId":1,"typeid":1}]
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
         * username : ssssss
         * avatar : ssssss.jpg
         * id : 1
         * name : 《彼岸花》
         * publish : 北进十月文艺出版社
         * cover : 0d3327d7310412c4daf0fd6992731544.jpg
         * description : 颓废文学，安妮宝贝
         * price : 面谈
         * userId : 24
         * typeid : 1
         */

        private String username;
        private String avatar;
        private int id;
        private String name;
        private String publish;
        private String cover;
        private String description;
        private String price;
        private int userId;
        private int typeid;

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

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }
    }
}
