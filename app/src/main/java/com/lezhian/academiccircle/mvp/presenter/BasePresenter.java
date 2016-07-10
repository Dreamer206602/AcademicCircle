package com.lezhian.academiccircle.mvp.presenter;

import android.app.Activity;

import com.lezhian.academiccircle.exception.ErrorHanding;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.utils.ToastUtil;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hww on 2016/6/21.
 */
public abstract class BasePresenter<T extends BaseView>implements IPresenter{


    protected Activity mActivity;
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;


    protected void handleError(Throwable throwable) {
        ToastUtil.showShort(mActivity, ErrorHanding.handleError(throwable));
    }
    public BasePresenter(Activity activity, T view) {
        this.mActivity = activity;
        this.mView = view;
    }
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }




}
