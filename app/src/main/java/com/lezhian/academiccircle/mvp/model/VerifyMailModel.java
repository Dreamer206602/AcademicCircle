package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.presenter.IPresenter;

/**
 * Created by hww on 2016/7/7.
 */
public interface VerifyMailModel {

    interface  VerifyMailPresenter extends IPresenter{
        void verifyMail(String userId, String organization,String name,String  mail);
    }
}
