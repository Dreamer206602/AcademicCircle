package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.exception.ErrorHanding;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.IdentityBean;
import com.lezhian.academiccircle.mvp.model.IdentityModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.network.NetWork;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/6/21.
 */
public class IdentityPresenterImpl extends BasePresenter<IdentityModel.IdentityView> implements IdentityModel.Presenter {
    public IdentityPresenterImpl(Activity activity, IdentityModel.IdentityView view) {
        super(activity, view);
    }

    @Override
    public void getIdentity() {

        Subscription subscription= NetWork.getApi().getIdentityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<IdentityBean>>>() {
                    @Override
                    public void call(BaseBean<List<IdentityBean>> listBaseBean) {
                        String   message = listBaseBean.getMessage();
                        mView.addIdentity(listBaseBean.getData());

                        if (listBaseBean.getData().size() == 0) {
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
