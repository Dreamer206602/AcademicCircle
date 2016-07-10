package com.lezhian.academiccircle.network;

import com.lezhian.academiccircle.utils.LogUtils;

import rx.Subscriber;

/**
 * Created by hww on 2016/6/20.
 */
public class MySubscriber<T> extends Subscriber<T> {
    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i("MySubscriber", "onStart被调用了");
    }

    @Override
    public void onCompleted() {
        LogUtils.i("MySubscriber", "onCompleted被调用了");
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.i("Throwable", e.getMessage());
        LogUtils.i("MySubscriber", "onError被调用了");
    }

    @Override
    public void onNext(T t) {
        LogUtils.i("MySubscriber", "onNext被调用了");
    }


}

