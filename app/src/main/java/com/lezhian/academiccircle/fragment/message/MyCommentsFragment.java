package com.lezhian.academiccircle.fragment.message;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.MyCommentListBean;
import com.lezhian.academiccircle.mvp.model.MyCommentListModel;
import com.lezhian.academiccircle.mvp.presenter.impl.MyCommentListPresenterImpl;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 我的评论的界面
 */
public class MyCommentsFragment extends BaseFragment<MyCommentListPresenterImpl> implements MyCommentListModel.MyCommentListView {

    public static final int pageIndex = 1;
    public static volatile MyCommentsFragment mInstance = null;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static MyCommentsFragment getInstance() {
        MyCommentsFragment inst = mInstance;//创建临时变量
        if (inst == null) {
            synchronized (MyCommentsFragment.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new MyCommentsFragment();
                    mInstance = inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(), R.layout.fragment_my_comments, null);
    }

    @Override
    protected void initData() {

        mPresenter.getSubscribeList("2",1);

    }

    @Override
    protected MyCommentListPresenterImpl getPresenter() {
        return new MyCommentListPresenterImpl(UIUtils.getActivity(),this);
    }


    @Override
    public void addCommentListData(BaseBean<List<MyCommentListBean>> subscribeBeen) {

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
