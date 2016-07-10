package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.presenter.IPresenter;

import java.util.Map;

/**
 * Created by hww on 2016/7/4.
 */
public interface PublishAcademicContentModel {

    interface  Presenter extends IPresenter {
        void getPublishContent(Map<String,Object>parmas);
    }
}
