package com.lezhian.academiccircle.mvp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by hww on 2016/6/27.
 */
public class MultipleItemBean extends MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMGS = 3;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
