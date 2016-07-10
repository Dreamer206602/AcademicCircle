package com.lezhian.academiccircle.fragment.concern;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.concern.ConcernAdapter;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.mvp.bean.ConcernBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 我的关注
 */
public class MyFollowingFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int mCurrentCounter = 0;
    private ConcernAdapter mAdapter;

    public static volatile MyFollowingFragment mInstance=null;
    public static MyFollowingFragment getInstance(){
        MyFollowingFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (MyFollowingFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new MyFollowingFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }


    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_my_following,null);
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getActivity()));
        initAdapter();
        mRecyclerView.setAdapter(mAdapter);


    }

    private void initAdapter() {

        mAdapter=new ConcernAdapter(R.layout.item_concern,getData(PAGE_SIZE));
        mAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mAdapter);
        mCurrentCounter = mAdapter.getData().size();
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(PAGE_SIZE, true);
        //or call mQuickAdapter.setPageSize(PAGE_SIZE);  mQuickAdapter.openLoadMore(true);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ToastUtil.showShort(UIUtils.getActivity(), "position" + i);
            }
        });

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    public List<ConcernBean> getData(int len){
        List<ConcernBean>mDatas=new ArrayList<>();
        for (int i = 0; i <len ; i++) {
            ConcernBean bean=new ConcernBean();
            bean.setImgUrl("http://img4.imgtn.bdimg.com/it/u=2430963138,1300578556&fm=23&gp=0.jpg");
            bean.setName("李四");
            mDatas.add(bean);
        }
        return mDatas;

    }

    @Override
    public void onRefresh() {

        Observable.timer(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mAdapter.setNewData(getData(PAGE_SIZE));
                        mAdapter.openLoadMore(PAGE_SIZE, true);
                        mCurrentCounter = PAGE_SIZE;
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    @Override
    public void onLoadMoreRequested() {

        Observable.timer(100,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if(mCurrentCounter>=TOTAL_COUNTER){
                            mAdapter.notifyDataChangedAfterLoadMore(false);
                            View view = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
                            mAdapter.addFooterView(view);
                        }else{
                            mAdapter.notifyDataChangedAfterLoadMore(getData(PAGE_SIZE), true);
                            mCurrentCounter = mAdapter.getData().size();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });


    }
}
