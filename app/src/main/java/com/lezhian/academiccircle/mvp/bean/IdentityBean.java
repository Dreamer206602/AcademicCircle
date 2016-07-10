package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/6/21.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 获取身份的实体类
 */
public class IdentityBean implements Serializable{


        private int identityId;
        private String imageUrl;
        private String level;
        private String parentId;
        private String title;
        private String treeCode;

        public int getIdentityId() {
            return identityId;
        }

        public void setIdentityId(int identityId) {
            this.identityId = identityId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTreeCode() {
            return treeCode;
        }

        public void setTreeCode(String treeCode) {
            this.treeCode = treeCode;
        }

}

