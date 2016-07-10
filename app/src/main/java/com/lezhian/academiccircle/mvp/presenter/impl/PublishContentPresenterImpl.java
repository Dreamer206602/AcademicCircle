package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.model.PublishAcademicContentModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;

import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/7/4.
 */
public class PublishContentPresenterImpl extends BasePresenter implements PublishAcademicContentModel.Presenter{
    public PublishContentPresenterImpl(Activity activity, BaseView view) {
        super(activity, view);
    }
    @Override
    public void getPublishContent(Map<String,Object>params) {
        Subscription subscription=NetWork.getApi().getPublishAcademicContent(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean baseBean) {
                        LogUtils.d("PublishContentPresenter",baseBean.getMessage());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);


    }
}
