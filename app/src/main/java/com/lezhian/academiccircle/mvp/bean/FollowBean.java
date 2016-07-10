package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/7/6.
 */

import java.util.List;

/**
 * 我关注的和关注我的实体类
 */
public class FollowBean {


    private DataBean data;
    /**
     * data : {"followers":[2],"following":[]}
     * message : 请求成功
     * status : 2000
     */
    public static class DataBean {
        private List<Integer> followers;
        private List<?> following;

        public List<Integer> getFollowers() {
            return followers;
        }

        public void setFollowers(List<Integer> followers) {
            this.followers = followers;
        }

        public List<?> getFollowing() {
            return following;
        }

        public void setFollowing(List<?> following) {
            this.following = following;
        }
    }
}
