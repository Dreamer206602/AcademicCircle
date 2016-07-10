package com.lezhian.academiccircle.fragment.headline;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.headline.HeadLineArticleAdapter;
import com.lezhian.academiccircle.adapter.headline.ImageCarouselAdapter;
import com.lezhian.academiccircle.adapter.headline.WrapAdapter;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${CQ} on 2016/6/27.
 * 学术头条 - 文章列表
 */
public class HeadLineArticleFragment extends Fragment {

    private View mView;

    @Bind(R.id.frh_srl_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.afl_rv_recyclerView)
    RecyclerView mRecyclerView;

//    @Bind(R.id.crl_prv_recyclerView)
//    PullLoadMoreRecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;

    @Bind(R.id.crl_view_foot)
    View footView;

    /** 列表当前页 */
    private int mCurPage = 1;
    private int mPerPageCount = 20;

    /** 文章类型 */
    private String type;

    private HeadLineArticleAdapter mArticleAdapter;
    private ArrayList<BannerBean> mArticleArrayList = new ArrayList<>();

    ViewPager mCarouselVP;


    private LinearLayout headerLayout;//头布局
    // 数据适配器包装类
    private WrapAdapter<HeadLineArticleAdapter> mWrapAdapter;

    private ImageCarouselAdapter mImageCarouselAdapter;
    private ArrayList<BannerBean> mArrayList = new ArrayList<>();
    private int mCurItemIndex = 0;

    private final int MSG_PAGE_CHANGED = 0x111;
    private final int MSG_UPDATE_IMAGE = 0x112;
    /** 请求暂停轮播 */
    private final int MSG_KEEP_SILENT = 0x113;
    /** 请求恢复轮播 */
    private final int MSG_REBACK_SILENT = 0x114;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch(msg.what) {
                case MSG_PAGE_CHANGED:
                    mCurItemIndex = Integer.parseInt(msg.obj.toString());
                    break;
                case MSG_UPDATE_IMAGE:
                    mCurItemIndex++;
                    mCarouselVP.setCurrentItem(mCurItemIndex, true);
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, 3000);
                    break;
                case MSG_KEEP_SILENT:

                    break;
                case MSG_REBACK_SILENT:
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, 3000);
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        } else {
            mView = View.inflate(UIUtils.getActivity(), R.layout.cmn_refresh_listview, null);
        }
        type = getArguments().getString("type");
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHeaderView();
        initializeViews();
        queryBanner();
        queryArticleList();
    }

    /**
     * 初始化头布局
     */
    private void initHeaderView() {
        // 布局解析器，解析取得头部北荣
        headerLayout = (LinearLayout) LayoutInflater.from(UIUtils.getActivity()).inflate(R.layout.view_carousel, null);
        //头部控件初始化
        mCarouselVP = (ViewPager) headerLayout.findViewById(R.id.vc_vp_carousel);//初始化广告轮播ViewPager
    }

    /**
     * 初始化控件
     */
    private void initializeViews() {

        mImageCarouselAdapter = new ImageCarouselAdapter(UIUtils.getActivity());
        mCarouselVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurItemIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /** 新闻 **/
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        mLinearLayoutManager = new LinearLayoutManager(UIUtils.getActivity(), LinearLayout.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        // 设置添加删除item的时候的动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 新闻适配器
        mArticleAdapter = new HeadLineArticleAdapter(UIUtils.getActivity(), mArticleArrayList);
        mWrapAdapter = new WrapAdapter<>(mArticleAdapter);
        // 设置头部占据一行
        mWrapAdapter.adjustSpanSize(mRecyclerView);
        // 设置RecyclerView的数据适配器(适配器包装)
        mRecyclerView.setAdapter(mWrapAdapter);
        // 添加头布局
        mWrapAdapter.addHeaderView(headerLayout);

        mArticleAdapter.setOnItemClickListener(new HeadLineArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(UIUtils.getActivity(), "position==========" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurPage = 1;
                queryArticleList();
            }
        });

        mRecyclerView.addOnScrollListener(mLoadMore);
    }

    private RecyclerView.OnScrollListener mLoadMore = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == RecyclerView.SCROLL_STATE_IDLE){
                int lastVisiblePosition = mLinearLayoutManager.findLastVisibleItemPosition();
                if(lastVisiblePosition >= mLinearLayoutManager.getItemCount() - 1){
                    mCurPage++;
                    queryArticleList();
//                    Toast.makeText(UIUtils.getActivity(), "加载更多...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 获取轮播列表
     */
    private void queryBanner() {
        Map<String,String> params = new HashMap<>();
        params.put("userId","1");
        NetWork.getApi().getBannerList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<BannerBean>>>() {
                    @Override
                    public void call(BaseBean<List<BannerBean>> listBaseBean) {
                        LogUtils.i("kiki", listBaseBean.getStatus() + "");
                        if (listBaseBean.isSuccess()) {
                            LogUtils.i("kiki", listBaseBean.getMessage());
                            updateBanner(listBaseBean);
                        } else if (listBaseBean.getStatus() == 2001) {
                            ToastUtil.showShort(UIUtils.getActivity(), listBaseBean.getMessage());
                        } else {

                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void updateBanner(BaseBean<List<BannerBean>> listBaseBean) {
        if (null != listBaseBean.getData() && listBaseBean.getData().size() > 0) {
            List<BannerBean> bannerBeans = listBaseBean.getData();
            mArrayList.clear();
            mArrayList.addAll(bannerBeans);
            mImageCarouselAdapter.addItems(bannerBeans);
            mCarouselVP.setAdapter(mImageCarouselAdapter);
            mCarouselVP.setCurrentItem(mCurItemIndex);
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, 3000);
        }
    }

    /**
     * 获取文章列表
     */
    private void queryArticleList() {
        mRecyclerView.removeOnScrollListener(mLoadMore);
        if (mCurPage != 1) {
            footView.setVisibility(View.VISIBLE);
        }
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", type);
        params.put("pageIndex", mCurPage+"");
        params.put("pageSize", mPerPageCount+"");
        NetWork.getApi().getHeadLineArticleList(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<BannerBean>>>() {
                    @Override
                    public void call(BaseBean<List<BannerBean>> listBaseBean) {
                        LogUtils.i("kiki", listBaseBean.getStatus() + "");
                        if (listBaseBean.isSuccess()) {
                            updateView(listBaseBean);
                        } else if (listBaseBean.getStatus() == 2001) {
                            LogUtils.i("kiki", listBaseBean.getMessage());
                            ToastUtil.showShort(UIUtils.getActivity(), listBaseBean.getMessage());
                        } else {

                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void updateView(BaseBean<List<BannerBean>> listBaseBean) {
        if (null != listBaseBean.getData() && listBaseBean.getData().size() > 0) {
            List<BannerBean> beans = listBaseBean.getData();
//            if (beans.size() == mPerPageCount) mRecyclerView.setHasMore(true);
//            else mRecyclerView.setHasMore(false);
            if (mCurPage == 1) {
                mArticleAdapter.removeAll();
                mArticleArrayList.clear();
                mSwipeRefreshLayout.setRefreshing(false);
                mRecyclerView.addOnScrollListener(mLoadMore);
            }
            if (beans.size() < mPerPageCount) {
                mRecyclerView.removeOnScrollListener(mLoadMore);
            }
//            mArticleArrayList.addAll(beans);
            mArticleAdapter.addItems(beans);
            mArticleAdapter.notifyDataSetChanged();
            LogUtils.i("kiki", "updateView");
//            mRecyclerView.setPullLoadMoreCompleted();
        } else {
            mRecyclerView.removeOnScrollListener(mLoadMore);
        }
        footView.setVisibility(View.GONE);
    }
//
//    @Override
//    public void onRefresh() {
//        mCurPage = 1;
//        queryArticleList();
//    }
//
//    @Override
//    public void onLoadMore() {
//        mCurPage++;
//        queryArticleList();
//    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
