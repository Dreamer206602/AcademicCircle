package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.model.VerifyMailModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/7/8.
 */
public class VerifyMailPresenterImpl extends BasePresenter implements VerifyMailModel.VerifyMailPresenter{

    public VerifyMailPresenterImpl(Activity activity, BaseView view) {
        super(activity, view);
    }

    @Override
    public void verifyMail(String userId, String organization, String name, String mail) {

        NetWork.getApi().VerifyMail(userId,organization,name,mail)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean baseBean) {

                        LogUtils.d("VerifyMail",baseBean.getMessage());
                        mView.showLoading();

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });


    }
}
