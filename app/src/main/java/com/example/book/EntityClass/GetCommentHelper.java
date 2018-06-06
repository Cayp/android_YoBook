package com.example.book.EntityClass;

import java.util.List;

/**
 * Created by ljp on 2017/10/23.
 */

public class GetCommentHelper {

    /**
     * code : 20000
     * message : 获取评论成功
     * data : null
     * dataList : [{"id":28,"shareId":12,"content":"di","userId":31,"time":1509808747000,"username":"cayp","avatar":"31/IMG_20171115_225742.jpg"},{"id":27,"shareId":12,"content":"嘻嘻","userId":31,"time":1509808712000,"username":"cayp","avatar":"31/IMG_20171115_225742.jpg"},{"id":25,"shareId":12,"content":"林健鹏好看","userId":31,"time":1509022520000,"username":"cayp","avatar":"31/IMG_20171115_225742.jpg"},{"id":24,"shareId":12,"content":"1111","userId":31,"time":1509022507000,"username":"cayp","avatar":"31/IMG_20171115_225742.jpg"},{"id":23,"shareId":12,"content":"1","userId":31,"time":1509022233000,"username":"cayp","avatar":"31/IMG_20171115_225742.jpg"}]
     */

    private int code;
    private String message;
    private Object data;
    private List<CommentItem> dataList;

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

    public List<CommentItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<CommentItem> dataList) {
        this.dataList = dataList;
    }

    public static class CommentItem{
        /**
         * id : 28
         * shareId : 12
         * content : di
         * userId : 31
         * time : 1509808747000
         * username : cayp
         * avatar : 31/IMG_20171115_225742.jpg
         */

        private int id;
        private int shareId;
        private String content;
        private int userId;
        private long time;
        private String username;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShareId() {
            return shareId;
        }

        public void setShareId(int shareId) {
            this.shareId = shareId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

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
    }
}
