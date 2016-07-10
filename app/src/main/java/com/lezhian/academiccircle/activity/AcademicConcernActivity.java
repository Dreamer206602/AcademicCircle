package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.cmn.MyFragmentPagerAdapter;
import com.lezhian.academiccircle.fragment.concern.FollowingMeFragment;
import com.lezhian.academiccircle.fragment.concern.MyFollowingFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 学术关注的界面
 */
public class AcademicConcernActivity extends BaseActivity {
    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;


    private String mFollowing;
    private String mFollowers;

    private MyFragmentPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments;


    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvMiddle.setText(UIUtils.getString(R.string.academicConcern));
        mTvRight.setVisibility(View.GONE);

        mFollowing = UIUtils.getString(R.string.title_following);
        mFollowers = UIUtils.getString(R.string.title_followers);

        String[]mTitles={mFollowing,mFollowers};

        mFragments=new ArrayList<>();
        mFragments.add(MyFollowingFragment.getInstance());
        mFragments.add(FollowingMeFragment.getInstance());

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setText(String.format(mFollowing, 12));
        mTabLayout.getTabAt(1).setText(String.format(mFollowers, 233));

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_academic_concern;
    }

}
