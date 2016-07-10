package com.lezhian.academiccircle.mvp.bean;

import java.util.List;

/**
 * Created by hww on 2016/7/6.
 */
public class SearchBean {


    /**
     * data : [{"avatar":"upload/1_1465897147909_381.jpg","beConcerned":0,"createTime":"2016-07-05 15:51:32.0","enable":"","gender":"0","nickname":"小A","openId":"abc345","password":"543212","scholarId":"1","token":"","userId":1,"userType":"1","username":"rss1360"}]
     * message : 请求成功
     * status : 2000
     */
    private String message;
    private int status;
    /**
     * avatar : upload/1_1465897147909_381.jpg
     * beConcerned : 0
     * createTime : 2016-07-05 15:51:32.0
     * enable :
     * gender : 0
     * nickname : 小A
     * openId : abc345
     * password : 543212
     * scholarId : 1
     * token :
     * userId : 1
     * userType : 1
     * username : rss1360
     */

    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String avatar;
        private int beConcerned;
        private String createTime;
        private String enable;
        private String gender;
        private String nickname;
        private String openId;
        private String password;
        private String scholarId;
        private String token;
        private int userId;
        private String userType;
        private String username;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getBeConcerned() {
            return beConcerned;
        }

        public void setBeConcerned(int beConcerned) {
            this.beConcerned = beConcerned;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getScholarId() {
            return scholarId;
        }

        public void setScholarId(String scholarId) {
            this.scholarId = scholarId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
