package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.AcademicCircleBean;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.model.AcademicCircleListModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/7/6.
 */
public class AcademicCircleListPresenterImpl extends BasePresenter<AcademicCircleListModel.AcademicCircleView>implements AcademicCircleListModel.AcademicCirclePresenter {
    public AcademicCircleListPresenterImpl(Activity activity, AcademicCircleListModel.AcademicCircleView view) {
        super(activity, view);
    }

    @Override
    public void getSubscribeList(String userId, int pageIndex) {

        Subscription subscription=NetWork.getApi().getAcademicCircleList(userId,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<AcademicCircleBean>>>() {
                    @Override
                    public void call(BaseBean<List<AcademicCircleBean>> listBaseBean) {
                        LogUtils.d("AcademicCircleItem", listBaseBean.getStatus() + "");
                        mView.addAcademicCircleData(listBaseBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);





    }
}
