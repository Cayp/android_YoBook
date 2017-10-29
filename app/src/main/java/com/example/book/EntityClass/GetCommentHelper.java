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
     * dataList : [{"id":2,"shareId":7,"content":"很好看哈哈哈","userId":24,"time":1500650729000,"reply_id":0,"replyUsername":"","avatar":null},{"id":3,"shareId":7,"content":"很好看哈哈哈","userId":24,"time":1500651785000,"reply_id":0,"replyUsername":"","avatar":null}]
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

    public static class CommentItem {
        /**
         * id : 2
         * shareId : 7
         * content : 很好看哈哈哈
         * userId : 24
         * time : 1500650729000
         * reply_id : 0
         * replyUsername :
         * avatar : null
         */

        private int id;
        private int shareId;
        private String content;
        private int userId;
        private long time;
        private int reply_id;
        private String replyUsername;
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

        public int getReply_id() {
            return reply_id;
        }

        public void setReply_id(int reply_id) {
            this.reply_id = reply_id;
        }

        public String getReplyUsername() {
            return replyUsername;
        }

        public void setReplyUsername(String replyUsername) {
            this.replyUsername = replyUsername;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
