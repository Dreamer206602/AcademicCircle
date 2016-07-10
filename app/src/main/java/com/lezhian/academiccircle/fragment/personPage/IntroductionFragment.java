package com.lezhian.academiccircle.fragment.personPage;


import android.view.View;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

/**
 * 介绍的界面
 */
public class IntroductionFragment extends BaseFragment {



    public static volatile IntroductionFragment mInstance=null;

    public static IntroductionFragment getInstance(){
        IntroductionFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (IntroductionFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new IntroductionFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }



    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_introduction,null);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

}
