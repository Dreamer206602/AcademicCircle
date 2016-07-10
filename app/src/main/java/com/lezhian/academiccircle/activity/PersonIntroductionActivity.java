package com.lezhian.academiccircle.activity;

import android.os.Bundle;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;

/**
 * 个人简介的界面
 */
public class PersonIntroductionActivity extends BaseActivity {


    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_introduction;
    }
}
