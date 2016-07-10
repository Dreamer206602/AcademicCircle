package com.lezhian.academiccircle.fragment.personPage;


import android.view.View;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

/**
 * 发布的界面
 */
public class PublishFragment extends BaseFragment {

    public static volatile PublishFragment mInstance;

    public static PublishFragment getInstance(){
        if(mInstance==null){
            synchronized (PublishFragment.class){
                if(mInstance==null){
                    mInstance=new PublishFragment();
                }
            }
        }
        return mInstance;
    }




    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_publish,null);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

}
