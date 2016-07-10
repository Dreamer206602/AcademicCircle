package com.lezhian.academiccircle.activity;

import android.content.Intent;
import android.os.Bundle;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.BaseFragment;

/**
 * Created by hww on 2016/7/8.
 */

public abstract class AppActivity extends BaseActivity {

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

    }

    @Override
    protected int getLayoutId(){
        return  R.layout.activity_base;
    }
    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }


}
