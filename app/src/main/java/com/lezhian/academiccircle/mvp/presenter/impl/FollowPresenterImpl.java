package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.FollowBean;
import com.lezhian.academiccircle.mvp.model.FollowModel;
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
public class FollowPresenterImpl extends BasePresenter<FollowModel.FollowView>implements FollowModel.FollowPresenter {
    public FollowPresenterImpl(Activity activity, FollowModel.FollowView view) {
        super(activity, view);
    }

    @Override
    public void getFollowList(String userId, int pageIndex) {

        Subscription subscription=NetWork.getApi().getFollowList(userId,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<FollowBean>>>() {
                    @Override
                    public void call(BaseBean<List<FollowBean>> bean) {
                        LogUtils.d("Follow", bean.getMessage());
                        mView.addFollowData(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);

    }
}
