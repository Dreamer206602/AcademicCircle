package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.bean.IdentityBean;
import com.lezhian.academiccircle.mvp.model.IdentityModel;
import com.lezhian.academiccircle.mvp.presenter.impl.IdentityPresenterImpl;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 选择身份的界面
 */
public class SelectIdentityActivity extends BaseActivity<IdentityPresenterImpl> implements IdentityModel.IdentityView {


    @Bind(R.id.tv_select)
    TextView mTvSelect;
    @Bind(R.id.tv_next)
    TextView mTvNext;
    @Bind(R.id.tv_not_more)
    TextView mTvNotMore;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTvSelect.setText(UIUtils.getString(R.string.select_main_identity));
        mTvSelect.setTextColor(UIUtils.getColor(R.color.color_activity_identity));

        mTvNotMore.setText(UIUtils.getString(R.string.recommend_right_content));
        mTvNotMore.setTextColor(UIUtils.getColor(R.color.gray));

        mTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

        mPresenter.getIdentity();
    }

    @Override
    protected IdentityPresenterImpl getPresenter() {
        return new IdentityPresenterImpl(activity, this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_identity;
    }

    @Override
    public void addIdentity(List<IdentityBean> identityBeen) {
        LogUtils.d("Main", identityBeen.get(0).getTitle());

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError(String msg) {

    }
}
