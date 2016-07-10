package com.lezhian.academiccircle.fragment.personPage;


import android.view.View;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.fragment.MineFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

/**
 * 成果的界面
 */
public class AchievementsFragment extends BaseFragment {

    public static volatile AchievementsFragment mInstance=null;

    public static AchievementsFragment getInstance(){
        AchievementsFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (AchievementsFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new AchievementsFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_achievements,null);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

}
