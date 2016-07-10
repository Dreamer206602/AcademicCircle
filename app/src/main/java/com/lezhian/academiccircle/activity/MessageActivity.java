package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.cmn.MyFragmentPagerAdapter;
import com.lezhian.academiccircle.fragment.message.MyCommentsFragment;
import com.lezhian.academiccircle.fragment.message.ReplyToMeFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 消息的界面
 */
public class MessageActivity extends BaseActivity {


    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tablayout)
    TabLayout mTablayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;


    private ArrayList<Fragment>mFragments;
     String[]mTitles={UIUtils.getString(R.string.replyToMe),UIUtils.getString(R.string.myComments)};
    private MyFragmentPagerAdapter mAdapter;


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
        mTvMiddle.setText(UIUtils.getString(R.string.message));
        mTvRight.setVisibility(View.GONE);

        mFragments=new ArrayList<>();
        mFragments.add(ReplyToMeFragment.getInstance());
        mFragments.add(MyCommentsFragment.getInstance());
        mAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);

        mViewPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;



    }

}
