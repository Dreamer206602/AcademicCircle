package com.lezhian.academiccircle.mvp.view;


/**
 * Created by hww on 2016/6/20.
 */
public interface BaseView {
    void showLoading();
    void showContent();
    void showNoData();
    void showError(String msg);

}
