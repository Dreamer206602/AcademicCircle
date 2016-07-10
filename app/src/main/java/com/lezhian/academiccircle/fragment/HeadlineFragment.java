package com.lezhian.academiccircle.fragment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.SelectAcademicCircleActivity;
import com.lezhian.academiccircle.activity.headline.HeadLineDetailActivity;
import com.lezhian.academiccircle.adapter.headline.CategoryAdapter;
import com.lezhian.academiccircle.adapter.headline.HeadLineArticleAdapter;
import com.lezhian.academiccircle.adapter.headline.ImageCarouselAdapter;
import com.lezhian.academiccircle.adapter.headline.WrapAdapter;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.SharedPrefsUtil;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.NoScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 头条新闻的界面
 */
public class HeadlineFragment extends BaseFragment implements View.OnClickListener
{
    private View mView;

    @Bind(R.id.fh_srl_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.fh_rv_recyclerView)
    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;

    @Bind(R.id.fh_view_foot)
    View footView;
    @Bind(R.id.fh_ll_more)
    LinearLayout mMoreLL;
    @Bind(R.id.fh_iv_more)
    ImageView mMoreIV;

//    @Bind(R.id.fh_hsv_type)
//    HorizontalScrollView mHScrollView;
    @Bind(R.id.fh_ll_type)
    LinearLayout mTypeLL;
    @Bind(R.id.fh_gv_type)
    GridView mTypeGV;

    private CategoryAdapter mCategoryAdapter;

    /** 文章类型 */
    private ArrayList<ThemeBean> mTypeBeans = new ArrayList<>();

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

//    private MyFragmentPagerAdapter myFragmentPagerAdapter;
//    private ArrayList<Fragment> mFragments = new ArrayList<>();
//    private String[] mTitles = {"推荐", "融资", "药学", "移动互联网", "热门头条"};

    private ImageCarouselAdapter mImageCarouselAdapter;
    private ArrayList<BannerBean> mArrayList = new ArrayList<>();
    private int mCurItemIndex = 0;

    private final int MSG_PAGE_CHANGED = 0x111;
    private final int MSG_UPDATE_IMAGE = 0x112;
    /** 请求暂停轮播 */
    private final int MSG_KEEP_SILENT = 0x113;
    /** 请求恢复轮播 */
    private final int MSG_REBACK_SILENT = 0x114;

    public static volatile HeadlineFragment mInstance=null;

    public static HeadlineFragment getInstance(){
        HeadlineFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (HeadlineFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new HeadlineFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

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
                case AcademicDefines.Handler_ListItemClicked:
                    String id = (String)msg.getData().getSerializable(AcademicDefines.Const_Serializable_Key);
                    type = id;
                    mCurPage = 1;
                    queryArticleList();
                    break;
            }
        }
    };

    @Override
    protected View initView() {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        } else {
            mView = View.inflate(UIUtils.getActivity(), R.layout.fragment_headline, null);
        }
        return mView;
    }

    @Override
    protected void initData() {
        type = "-1";
        getSharedPreferencesTypeInfo();
        initHeaderView();
        initializeViews();
        updateHeader();
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

    private void getSharedPreferencesTypeInfo() {
        mTypeBeans.clear();
        ArrayList<String> arrayList = SharedPrefsUtil.getValue(UIUtils.getActivity(), AcademicDefines.SharedPreferences_AddTheme);
        if (null != arrayList && arrayList.size() > 0) {
            mTypeBeans.add(new ThemeBean("-1", "推荐", true));
            for (int i = 0 ; i < arrayList.size() ; i++) {
                String name = SharedPrefsUtil.getValue(UIUtils.getActivity(), arrayList.get(i), "");
                if (!TextUtils.isEmpty(name)) {
                    mTypeBeans.add(new ThemeBean(arrayList.get(i), name, false));
                }
            }
        } else {
            mTypeBeans.add(new ThemeBean("-1", "推荐", true));
        }
    }

    private void setGridViewType() {
        ViewGroup.LayoutParams params = mTypeGV.getLayoutParams();
        int dishtypes = mTypeBeans.size();
        int temp = 0;
        ViewTreeObserver observer = mTypeGV.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTypeGV.getWidth();
                LogUtils.i("kiki", "width=-=-=-=-=-=" + mTypeGV.getWidth());
            }
        });
        LogUtils.i("kiki", "temp===" + temp);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        params.width = UIUtils.dp2px(UIUtils.getActivity(), 90) * dishtypes;
        for (int i = 0 ; i < mTypeBeans.size() ; i++) {
            temp += UIUtils.dp2px(UIUtils.getActivity(), 20 + mTypeBeans.get(i).getTitle().length()*16);
        }

        mTypeGV.setLayoutParams(params);
        mTypeGV.setNumColumns(mTypeBeans.size());
//        mTypeGV.setLayoutParams(new LinearLayout.LayoutParams(temp, LinearLayout.LayoutParams.MATCH_PARENT));
//        mTypeLL.setLayoutParams(new FrameLayout.LayoutParams(temp, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    private void updateHeader() {
        //        for (int i = 0 ; i < mTypeBeans.size() ; i++) {
//            View view = LayoutInflater.from(UIUtils.getActivity()).inflate(R.layout.item_type, null);
//            ((TextView)view.findViewById(R.id.it_tv_typeName)).setText(mTypeBeans.get(i).getTitle());
//            if (mTypeBeans.get(i).isSelected()) {
//                view.findViewById(R.id.it_line_type).setVisibility(View.VISIBLE);
//            } else {
//                view.findViewById(R.id.it_line_type).setVisibility(View.GONE);
//            }
//            mTypeLL.addView(view);
//        }

        mCategoryAdapter.removeAll();
        mCategoryAdapter.addAll(mTypeBeans);
        setGridViewType();
    }

    /**
     * 初始化控件
     */
    private void initializeViews() {
        mCategoryAdapter = new CategoryAdapter(UIUtils.getActivity(), mHandler);
        mTypeGV.setAdapter(mCategoryAdapter);

        mMoreLL.setOnClickListener(this);

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
                Intent intent = new Intent(UIUtils.getActivity(), HeadLineDetailActivity.class);
                intent.putExtra(AcademicDefines.IntentParam_User1, mArticleAdapter.getItemArrayLists().get(position-1).getArticleId());
                startActivity(intent);
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
        NetWork.getHeadLineApi().getBannerList().subscribeOn(Schedulers.io())
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
//            mImageCarouselAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取文章列表
     */
    private void queryArticleList() {
        mRecyclerView.removeOnScrollListener(mLoadMore);
        if (mCurPage != 1) {
            footView.setVisibility(View.VISIBLE);
        } else {
            footView.setVisibility(View.GONE);
        }
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", type+"");
        params.put("pageIndex", mCurPage+"");
        params.put("pageSize", mPerPageCount+"");
        NetWork.getHeadLineApi().getHeadLineArticleList(params).subscribeOn(Schedulers.io())
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

    @Override
    public void onClick(View v) {
        if (v == mMoreLL) {
            startActivityForResult(new Intent(UIUtils.getActivity(), SelectAcademicCircleActivity.class), AcademicDefines.RequestCode_Normal);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AcademicDefines.ResultCode_Normal_Success) {
            ToastUtil.showShort(UIUtils.getActivity(), "哈哈哈哈");
            getSharedPreferencesTypeInfo();
            updateHeader();


        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

}
