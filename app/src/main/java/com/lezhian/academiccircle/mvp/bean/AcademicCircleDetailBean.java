package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/7/6.
 */

import java.util.List;

/**
 * 学术圈详情
 */
public class AcademicCircleDetailBean {

    /**
     * avatar : upload/1_1465897147909_381.jpg
     * circleId : 0
     * commentCount : 0
     * content : bbb
     * createTime : 2016-06-28 11:25:56.0
     * id : 1
     * likeCount : 0
     * likeUserList : []
     * title : aaa
     * userid : 1
     * username : rss1360
     */
    private DataBean data;
    /**
     * data : {"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"bbb","createTime":"2016-06-28 11:25:56.0","id":1,"likeCount":0,"likeUserList":[],"title":"aaa","userid":1,"username":"rss1360"}
     * message : 请求成功
     * status : 2000
     */
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
    public static class DataBean {
        private String avatar;
        private int circleId;
        private int commentCount;
        private String content;
        private String createTime;
        private int id;
        private int likeCount;
        private String title;
        private int userid;
        private String username;
        private List<?> likeUserList;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCircleId() {
            return circleId;
        }

        public void setCircleId(int circleId) {
            this.circleId = circleId;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getLikeUserList() {
            return likeUserList;
        }

        public void setLikeUserList(List<?> likeUserList) {
            this.likeUserList = likeUserList;
        }
    }
}
