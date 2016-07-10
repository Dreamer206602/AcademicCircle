package com.lezhian.academiccircle.mvp.bean.headline;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${CQ} on 2016/7/5.
 */
public class ThemeBean implements Serializable {
    /**  */
    private String categoryId;
    /**  */
    private String imageUrl;
    /**  */
    private String level;
    /**  */
    private String parentId;
    /**  */
    private String subscribed;
    /** 一级标题名称 */
    private String title;
    /**  */
    private String treeCode;
    /**  */
    private ArrayList<ThemeBean> cathgoryTag;
    /** 是否选择 */
    private boolean bSelected = false;

    public ThemeBean(String categoryId, String title, boolean bSelected) {
        setCategoryId(categoryId);
        setTitle(title);
        setSelected(bSelected);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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

    public ArrayList<ThemeBean> getCathgoryTag() {
        return cathgoryTag;
    }

    public void setCathgoryTag(ArrayList<ThemeBean> cathgoryTag) {
        this.cathgoryTag = cathgoryTag;
    }

    public boolean isSelected() {
        return bSelected;
    }

    public void setSelected(boolean bSelected) {
        this.bSelected = bSelected;
    }
}
