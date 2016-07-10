package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/6/21.
 */

import java.util.List;

/**
 * 订阅列表的实体类
 */
public class SubscribeBean {


        private int categoryId;
        private String imageUrl;
        private String level;
        private String parentId;
        private String subscribed;
        private String title;
        private String treeCode;
        /**
         * categoryId : 4
         * cathgoryTag : [{"categoryId":11,"imageUrl":"http://127.0.0.1/img/2011.jpg","level":"3","parentId":"4","subscribed":"0","title":"基础医学","treeCode":""}]
         * imageUrl : http://127.0.0.1/img/2004.jpg
         * level : 2
         * parentId : 6
         * subscribed : 0
         * title : 天文学
         * treeCode : 6,5
         */

        private List<CathgoryTagBean> cathgoryTag;

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
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

        public String getSubscribed() {
            return subscribed;
        }

        public void setSubscribed(String subscribed) {
            this.subscribed = subscribed;
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

        public List<CathgoryTagBean> getCathgoryTag() {
            return cathgoryTag;
        }

        public void setCathgoryTag(List<CathgoryTagBean> cathgoryTag) {
            this.cathgoryTag = cathgoryTag;
        }

        public static class CathgoryTagBean {
            private int categoryId;
            private String imageUrl;
            private String level;
            private String parentId;
            private String subscribed;
            private String title;
            private String treeCode;
            /**
             * categoryId : 11
             * imageUrl : http://127.0.0.1/img/2011.jpg
             * level : 3
             * parentId : 4
             * subscribed : 0
             * title : 基础医学
             * treeCode :
             */

            private List<CathgoryTagBean> cathgoryTag;

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
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

            public String getSubscribed() {
                return subscribed;
            }

            public void setSubscribed(String subscribed) {
                this.subscribed = subscribed;
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

            public List<CathgoryTagBean> getCathgoryTag() {
                return cathgoryTag;
            }

            public void setCathgoryTag(List<CathgoryTagBean> cathgoryTag) {
                this.cathgoryTag = cathgoryTag;
            }


        }

}
