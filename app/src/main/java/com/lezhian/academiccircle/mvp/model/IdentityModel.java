package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.IdentityBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.List;

/**
 * Created by hww on 2016/6/21.
 */
public interface IdentityModel {

    interface IdentityView extends BaseView {

        void addIdentity(List<IdentityBean> identityBeen);

    }
    interface Presenter extends IPresenter {
        void getIdentity();
    }



}
