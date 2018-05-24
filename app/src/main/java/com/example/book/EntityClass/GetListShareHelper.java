package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/10/14.
 */

public class GetListShareHelper {


    /**
     * code : 20000
     * message : 获取分享成功
     * data : null
     * dataList : [{"id":39,"avatar":"31/IMG_20180413_142037.jpg","content":"哈哈哈哈哈哈哈哈哈哈","name":"偷影子的人","cover":"","time":1510753433000,"typeId":1,"isbn":"9787540455958","commentNum":0,"starNum":1,"userName":"cayp"},{"id":38,"avatar":"31/IMG_20180413_142037.jpg","content":"在三毛的内心深处，撒哈拉沙漠是梦中情人，是不能解释的，属于前世回忆似得乡愁，莫名奇妙，她豪无保留地把自己交给了那一片陌生的大地，每一粒地的石子，尚且知道珍爱，每一次日出和日落，都舍不得忘怀，更何况，这一张张活生生的脸孔，又如何能在回忆里抹去他们。","name":"撒哈拉的故事","cover":"IMG_20171101_180334.jpg","time":1510669114000,"typeId":1,"isbn":"9787530214787","commentNum":9,"starNum":1,"userName":"cayp"},{"id":37,"avatar":"32/IMG_20171107_131549.jpg","content":"111","name":"C语言程序设计","cover":"","time":1510479246000,"typeId":1,"isbn":"9787040433166","commentNum":0,"starNum":0,"userName":"zhao"},{"id":36,"avatar":null,"content":"内容","name":"彼岸花","cover":"","time":1510049785000,"typeId":5,"isbn":"sssss","commentNum":1,"starNum":1,"userName":"Cafe"}]
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
         * id : 39
         * avatar : 31/IMG_20180413_142037.jpg
         * content : 哈哈哈哈哈哈哈哈哈哈
         * name : 偷影子的人
         * cover :
         * time : 1510753433000
         * typeId : 1
         * isbn : 9787540455958
         * commentNum : 0
         * starNum : 1
         * userName : cayp
         */

        private int id;
        private String avatar;
        private String content;
        private String name;
        private String cover;
        private long time;
        private int typeId;
        private String isbn;
        private int commentNum;
        private int starNum;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getStarNum() {
            return starNum;
        }

        public void setStarNum(int starNum) {
            this.starNum = starNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
