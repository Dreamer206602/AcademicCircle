package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/7/5.
 */

/**
 * 回复我的评论的实体类
 */
public class ReplyToMeBean {

    /**
     * id : 1
     * circleId : 2323
     * circleTitle : 互联网大会
     * content : 评论内容
     * createdAt : 123231232132
     */

    private int id;
    private int circleId;
    private String circleTitle;
    private String comment;
    private long createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getCircleTitle() {
        return circleTitle;
    }

    public void setCircleTitle(String circleTitle) {
        this.circleTitle = circleTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
