package com.lezhian.academiccircle.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.AcademicDetailActivity;
import com.lezhian.academiccircle.activity.ModifyMessageActivity;
import com.lezhian.academiccircle.activity.PublishActivity;
import com.lezhian.academiccircle.adapter.MultipleItemQuickAdapter;
import com.lezhian.academiccircle.mvp.bean.AcademicCircleBean;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.MultipleItemBean;
import com.lezhian.academiccircle.mvp.model.AcademicCircleListModel;
import com.lezhian.academiccircle.mvp.presenter.impl.AcademicCircleListPresenterImpl;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 学术圈Item界面
 */
public class AcademicCircleItemFragment extends BaseFragment<AcademicCircleListPresenterImpl> implements  View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, AcademicCircleListModel.AcademicCircleView {

    public static volatile AcademicCircleItemFragment mInstance=null;

    public static AcademicCircleItemFragment getInstance(){
        AcademicCircleItemFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (AcademicCircleItemFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new AcademicCircleItemFragment();
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
    View headView;
    RoundImageView ivHeadImage;
    TextView tvName;


    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int mCurrentCounter = 0;

    public static List<MultipleItemBean> getMultipleItemData(int len) {
        List<MultipleItemBean> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
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
        headView= getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.item_academiccircle_top,
                (ViewGroup) mRecyclerView.getParent(), false);

        tvName= (TextView) headView.findViewById(R.id.tv_name);

        TextView tvModifyMessage= (TextView) headView.findViewById(R.id.tv_modify_message);
        TextView tvPublish= (TextView) headView.findViewById(R.id.tv_publish);
        ivHeadImage= (RoundImageView) headView.findViewById(R.id.iv_headImage);
        ivHeadImage.setOnClickListener(this);
        tvModifyMessage.setOnClickListener(this);
        tvPublish.setOnClickListener(this);
        mAdapter.addHeaderView(headView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_publish:
                //跳转到发布的界面
                startActivities(PublishActivity.class);
                break;
            case R.id.tv_modify_message:
                //跳转到修改信息的界面
                startActivities(ModifyMessageActivity.class);
                break;
        }
    }


    public void startActivities(Class<? extends Activity>activity ){
        startActivity(new Intent(getParentFragment().getActivity(), activity));
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
                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            mAdapter.notifyDataChangedAfterLoadMore(false);
                            View view = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
                            mAdapter.addFooterView(view);
                        } else {
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
    protected View initView() {
        return View.inflate(getParentFragment().getActivity(),R.layout.fragment_academic_circle_item,null);
    }

    @Override
    protected void initData() {
        mPresenter.getSubscribeList("1",1);
    }

    @Override
    protected AcademicCircleListPresenterImpl getPresenter() {
        return new AcademicCircleListPresenterImpl(getParentFragment().getActivity(),this);
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


    @Override
    public void addAcademicCircleData(BaseBean<List<AcademicCircleBean>> subscribeBeen) {
//        List<BaseBean>academicCircleBeans=new ArrayList<>();
//        academicCircleBeans.add(subscribeBeen);

    }
}
