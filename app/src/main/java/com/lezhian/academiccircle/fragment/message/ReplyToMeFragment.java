package com.lezhian.academiccircle.fragment.message;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.ReplyToMeBean;
import com.lezhian.academiccircle.mvp.model.ReplyToMeModel;
import com.lezhian.academiccircle.mvp.presenter.impl.ReplyToMePresenterImpl;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 回复我的界面
 */
public class ReplyToMeFragment extends BaseFragment<ReplyToMePresenterImpl> implements ReplyToMeModel.ReplyToMeView {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static volatile ReplyToMeFragment mInstance=null;

    public static ReplyToMeFragment getInstance(){
        ReplyToMeFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (ReplyToMeFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new ReplyToMeFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }


    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_reply_to_me,null);
    }

    @Override
    protected void initData() {
        mPresenter.getSubscribeList("1",1);

    }

    @Override
    protected ReplyToMePresenterImpl getPresenter() {
        return new ReplyToMePresenterImpl(UIUtils.getActivity(),this);
    }


    @Override
    public void addReplyToMeData(BaseBean<List<ReplyToMeBean>> subscribeBeen) {

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
