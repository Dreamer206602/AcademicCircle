package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.ReplyToMeBean;
import com.lezhian.academiccircle.mvp.model.ReplyToMeModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/7/5.
 */
public class ReplyToMePresenterImpl extends BasePresenter<ReplyToMeModel.ReplyToMeView>implements ReplyToMeModel.ReplyToMePresenter {
    public ReplyToMePresenterImpl(Activity activity, ReplyToMeModel.ReplyToMeView view) {
        super(activity, view);
    }

    @Override
    public void getSubscribeList(String userId, int pageIndex) {

        Subscription subscription=NetWork.getApi().getReplyToMeCommentList(userId,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<ReplyToMeBean>>>() {
                    @Override
                    public void call(BaseBean<List<ReplyToMeBean>> listBaseBean) {


                        LogUtils.d("ReplyToMeFragment",listBaseBean.getMessage()+";");
                        mView.addReplyToMeData(listBaseBean);


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);
    }
}
