package com.lezhian.academiccircle.fragment;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.SearchActivity;
import com.lezhian.academiccircle.adapter.cmn.MyFragmentPagerAdapter;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 学术圈的界面
 */
public class AcademicCircleFragment extends BaseFragment implements View.OnClickListener {

    public static volatile AcademicCircleFragment mInstance=null;

    public static AcademicCircleFragment getInstance(){
        AcademicCircleFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (AcademicCircleFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new AcademicCircleFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

    View ret;


    @Bind(R.id.iv_search)
    ImageView mIvSearch;
    @Bind(R.id.iv_email)
    ImageView mIvEmail;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private MyFragmentPagerAdapter mAdapter;

    private final String[] mTitles = {
            "学术圈","推荐"};
    private ArrayList<Fragment>mFragments;

    @Override
    protected View initView() {
        if (ret == null) {
            ret = View.inflate(UIUtils.getActivity(), R.layout.fragment_academic_circle, null);
        }
        return ret;
    }

    @Override
    protected void initData() {
        mFragments=new ArrayList<>();
        mFragments.add( AcademicCircleItemFragment.getInstance());
        mFragments.add( RecommendFragment.getInstance());

//        View decorView = UIUtils.getActivity().getWindow().getDecorView();
//        ViewPager vp = ViewFindUtils.find(decorView, R.id.viewPager);
//        SlidingTabLayout tabLayout = ViewFindUtils.find(decorView, R.id.tablayout);
//
//        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getChildFragmentManager());
//        vp.setAdapter(myPagerAdapter);
//        tabLayout.setViewPager(vp,mTitles);

        mAdapter=new MyFragmentPagerAdapter(getChildFragmentManager(),mFragments,mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mIvSearch.setOnClickListener(this);
        mIvEmail.setOnClickListener(this);



    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_search:
                //跳转到搜索的界面
                startActivity(new Intent(UIUtils.getActivity(), SearchActivity.class));
                break;
            case R.id.iv_email:
                //跳转到邮箱认证的界面
                break;
        }


    }



}
