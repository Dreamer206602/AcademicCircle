package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/7/5.
 */

import java.util.List;

/**
 * 我的评论列表
 */
public class MyCommentListBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * circleId : 0
         * commentCount : 0
         * createTime : 2016-06-28 11:25:56.0
         * id : 0
         * likeCount : 0
         * title : aaa
         * userid : 0
         * username : rss1360
         */

        private CaBean ca;
        private int circleId;
        private String comment;
        /**
         * beConcerned : 0
         * userId : 1
         * username : rss1360
         */

        private CommentatorBean commentator;
        private String createTime;
        private int id;

        public CaBean getCa() {
            return ca;
        }

        public void setCa(CaBean ca) {
            this.ca = ca;
        }

        public int getCircleId() {
            return circleId;
        }

        public void setCircleId(int circleId) {
            this.circleId = circleId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public CommentatorBean getCommentator() {
            return commentator;
        }

        public void setCommentator(CommentatorBean commentator) {
            this.commentator = commentator;
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

        public static class CaBean {
            private int circleId;
            private int commentCount;
            private String createTime;
            private int id;
            private int likeCount;
            private String title;
            private int userid;
            private String username;

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
        }

        public static class CommentatorBean {
            private int beConcerned;
            private int userId;
            private String username;

            public int getBeConcerned() {
                return beConcerned;
            }

            public void setBeConcerned(int beConcerned) {
                this.beConcerned = beConcerned;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
