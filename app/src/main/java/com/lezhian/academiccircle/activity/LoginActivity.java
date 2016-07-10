package com.lezhian.academiccircle.activity;

import android.os.Bundle;

import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.fragment.LoginFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;

/**
 * 登录界面
 */
public class LoginActivity extends AppActivity  {
    @Override
    protected BaseFragment getFirstFragment() {
        return LoginFragment.getInstance();
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }
}
