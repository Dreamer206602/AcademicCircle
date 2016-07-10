package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.exception.ErrorHanding;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.SubscribeBean;
import com.lezhian.academiccircle.mvp.model.SubscribeModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.network.NetWork;

import java.util.HashMap;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/6/21.
 */
public class SubscribePresenterImpl extends BasePresenter<SubscribeModel.SubscribeView>implements SubscribeModel.SubscribePresenter {
    public SubscribePresenterImpl(Activity activity, SubscribeModel.SubscribeView view) {
        super(activity, view);
    }

    @Override
    public void getSubscribeList(HashMap<String, String> params) {
        Subscription subscription=NetWork.getApi().getSubscribeList(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<SubscribeBean>>>() {
                    @Override
                    public void call(BaseBean<List<SubscribeBean>> listBaseBean) {

                        mView.addSubscribeData(listBaseBean.getData());
                        if(listBaseBean.getData().size()==0){
                            mView.showNoData();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(ErrorHanding.handleError(throwable));
                        handleError(throwable);

                    }
                });
        addSubscribe(subscription);


    }
}
