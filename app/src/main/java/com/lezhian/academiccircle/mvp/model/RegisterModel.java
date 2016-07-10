package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.presenter.IPresenter;

/**
 * Created by hww on 2016/7/7.
 */
public interface RegisterModel {

    interface  RegisterPresenter extends IPresenter{
        void getRegister(String name, String pass);
    }
}
