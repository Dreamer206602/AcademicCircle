package com.lezhian.academiccircle.mvp.presenter.impl;

import android.app.Activity;

import com.lezhian.academiccircle.mvp.bean.SearchBean;
import com.lezhian.academiccircle.mvp.model.SearchModel;
import com.lezhian.academiccircle.mvp.presenter.BasePresenter;
import com.lezhian.academiccircle.network.NetWork;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by hww on 2016/7/6.
 */
public class SearchScholarPresenterImpl extends BasePresenter<SearchModel.SearchView>implements SearchModel.SearchPresenter {
    public SearchScholarPresenterImpl(Activity activity, SearchModel.SearchView view) {
        super(activity, view);
    }

    @Override
    public void getSearch(String userId, String keyword, int pageIndex) {

        Subscription subscription=NetWork.getApi().getSearchData(userId,keyword,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchBean>() {
                    @Override
                    public void call(SearchBean searchBean) {
                        mView.addLoadData(searchBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        addSubscribe(subscription);
    }
}
