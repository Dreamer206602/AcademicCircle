package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/7/6.
 */

import java.util.List;

/**
 *推荐实体类
 */
public class RecommendBean {


    /**
     * data : [{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"\"公务员\"","createTime":"2016-07-06 08:53:40.0","id":16,"likeCount":0,"title":"\"好咯我\"","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"Content","createTime":"2016-07-01 16:16:01.0","id":13,"imgUrl":"circle/1_1467360961349_6087.jpg;circle/1_1467360961425_6756.chm","likeCount":0,"title":"Title","userid":1,"username":"rss1360"},{"avatar":"http://127.0.0.1:8080/img/1002.jpg","circleId":0,"commentCount":0,"content":"test","createTime":"2016-07-01 10:32:26.0","id":9,"likeCount":0,"title":"test","userid":2,"username":"jjd2400"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"Content","createTime":"2016-06-30 10:40:18.0","id":8,"imgUrl":"circle/1_1467254417772_9227.jpg","likeCount":0,"title":"Title","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"bbb","createTime":"2016-06-28 11:51:12.0","id":7,"likeCount":0,"title":"aaa","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"bbb","createTime":"2016-06-28 11:49:38.0","id":6,"likeCount":0,"title":"aaa","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"bbb","createTime":"2016-06-28 11:46:18.0","id":5,"likeCount":0,"title":"aaa","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":0,"content":"bbb","createTime":"2016-06-28 11:43:15.0","id":4,"likeCount":0,"title":"aaa","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":3,"content":"bbb","createTime":"2016-06-28 11:26:47.0","id":3,"likeCount":0,"title":"aaa","userid":1,"username":"rss1360"},{"avatar":"upload/1_1465897147909_381.jpg","circleId":0,"commentCount":6,"content":"bbb","createTime":"2016-06-28 11:25:56.0","id":2,"likeCount":0,"title":"aaa","userid":1,"username":"rss1360"}]
     * message : 请求成功
     * status : 2000
     */

    /**
     * avatar : upload/1_1465897147909_381.jpg
     * circleId : 0
     * commentCount : 0
     * content : "公务员"
     * createTime : 2016-07-06 08:53:40.0
     * id : 16
     * likeCount : 0
     * title : "好咯我"
     * userid : 1
     * username : rss1360
     */

    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
    }
}
