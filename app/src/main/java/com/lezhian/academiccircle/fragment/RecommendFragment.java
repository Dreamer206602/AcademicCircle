package com.lezhian.academiccircle.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.AcademicDetailActivity;
import com.lezhian.academiccircle.adapter.MultipleItemQuickAdapter;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.MultipleItemBean;
import com.lezhian.academiccircle.mvp.bean.RecommendBean;
import com.lezhian.academiccircle.mvp.model.RecommendListModel;
import com.lezhian.academiccircle.mvp.presenter.impl.RecommendListPresenterImpl;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 推荐的界面
 */
public class RecommendFragment extends BaseFragment<RecommendListPresenterImpl> implements  SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, RecommendListModel.RecommendView {

    public static volatile RecommendFragment mInstance=null;

    public static RecommendFragment getInstance(){
        RecommendFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (RecommendFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new RecommendFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

    MultipleItemQuickAdapter mAdapter;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int mCurrentCounter = 0;

    private String[] mImagesUrl = {
            "http://img4.imgtn.bdimg.com/it/u=2430963138,1300578556&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2755648979,3568014048&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2272739960,4287902102&fm=23&gp=0.jpg"
    };

    @Override
    protected View initView() {
        return View.inflate(getParentFragment().getActivity(),R.layout.fragment_recommend,null);
    }

    @Override
    protected void initData() {

        mPresenter.getSubscribeList("1",1,1);

    }

    @Override
    protected RecommendListPresenterImpl getPresenter() {
        return new RecommendListPresenterImpl(getParentFragment().getActivity(),this);
    }


    public static List<MultipleItemBean> getMultipleItemData(int len) {
        List<MultipleItemBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MultipleItemBean multipleItem = new MultipleItemBean();
            String str = null;
            multipleItem.setItemType(MultipleItemBean.IMG);
            if (i % 2 == 0) {
                //str = CYM_CHAD;
                multipleItem.setItemType(MultipleItemBean.TEXT);
            } else if (i % 3 == 0) {
                multipleItem.setItemType(MultipleItemBean.IMGS);
            }
            multipleItem.setContent(str);
            list.add(multipleItem);
        }
        return list;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        initAdapter();
        addHeadView();
        mRecyclerView.setAdapter(mAdapter);


    }
    private void initAdapter() {
        mAdapter=new MultipleItemQuickAdapter(getMultipleItemData(PAGE_SIZE));
        mAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mAdapter);
        mCurrentCounter = mAdapter.getData().size();
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(PAGE_SIZE, true);
        //or call mQuickAdapter.setPageSize(PAGE_SIZE);  mQuickAdapter.openLoadMore(true);
        addHeadView();
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ToastUtil.showShort(getParentFragment().getActivity(), "position" + i);
                startActivities(AcademicDetailActivity.class);

            }
        });

    }

    private void addHeadView() {

        View headView = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.flybanner,
                (ViewGroup) mRecyclerView.getParent(), false);
        FlyBanner mBanner= (FlyBanner) headView.findViewById(R.id.banner);
        List<String> imgesUrl = new ArrayList<>();
        for (int i = 0; i < mImagesUrl.length; i++) {
            imgesUrl.add(mImagesUrl[i]);
        }
        mBanner.setImagesUrl(imgesUrl);
        // 点击进行界面的跳转
        mBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                    ToastUtil.showShort(getParentFragment().getActivity(),"position:"+position);

            }
        });

        mAdapter.addHeaderView(headView);

    }

    public void startActivities(Class<? extends Activity>activity ){
        startActivity(new Intent(getParentFragment().getActivity(),activity));
    }




    @Override
    public void onRefresh() {

        Observable.timer(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mAdapter.setNewData(getMultipleItemData(PAGE_SIZE));
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
                            mAdapter.notifyDataChangedAfterLoadMore(getMultipleItemData(PAGE_SIZE), true);
                            mCurrentCounter = mAdapter.getData().size();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    @Override
    public void addRecommendData(BaseBean<List<RecommendBean>> subscribeBeen) {

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
