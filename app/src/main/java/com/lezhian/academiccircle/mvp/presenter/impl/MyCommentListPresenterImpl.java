package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.MyCommentListBean;
import com.lezhian.academiccircle.mvp.model.MyCommentListModel;
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
public class MyCommentListPresenterImpl extends BasePresenter<MyCommentListModel.MyCommentListView>implements MyCommentListModel.MyCommentListPresenter {
    public MyCommentListPresenterImpl(Activity activity, MyCommentListModel.MyCommentListView view) {
        super(activity, view);
    }

    @Override
    public void getSubscribeList(String userId, int pageIndex) {
        Subscription subscription=NetWork.getApi().getMyCommenList(userId,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<MyCommentListBean>>>() {
                    @Override
                    public void call(BaseBean<List<MyCommentListBean>> listBaseBean) {
                              LogUtils.d("MyCommentsFragment",listBaseBean.getMessage());
                              mView.addCommentListData(listBaseBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);

    }
}
