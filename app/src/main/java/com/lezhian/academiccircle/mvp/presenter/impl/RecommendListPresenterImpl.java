package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.RecommendBean;
import com.lezhian.academiccircle.mvp.model.RecommendListModel;
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
public class RecommendListPresenterImpl extends BasePresenter<RecommendListModel.RecommendView>implements RecommendListModel.RecommendPresenter {

    public RecommendListPresenterImpl(Activity activity, RecommendListModel.RecommendView view) {
        super(activity, view);
    }
    @Override
    public void getSubscribeList(String userId, int recommend, int pageIndex) {

        Subscription subscription=NetWork.getApi().getRecommendList(userId,recommend,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<RecommendBean>>>() {
                    @Override
                    public void call(BaseBean<List<RecommendBean>> listBaseBean) {
                        LogUtils.d("Recommend",listBaseBean.getMessage());
                            mView.addRecommendData(listBaseBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);

    }
}
