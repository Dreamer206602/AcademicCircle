package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.model.RegisterModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/7/7.
 */
public class RegisterPresenterImpl extends BasePresenter implements RegisterModel.RegisterPresenter {

    public RegisterPresenterImpl(Activity activity, BaseView view) {
        super(activity, view);
    }

    @Override
    public void getRegister(String name, String pass) {

        NetWork.getApi().getSignUp(name,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean baseBean) {
                       LogUtils.d("Regis", baseBean.getMessage());
                       LogUtils.d("Regis", baseBean.getStatus()+"");
                            mView.showLoading();
                    }
                });

    }
}
