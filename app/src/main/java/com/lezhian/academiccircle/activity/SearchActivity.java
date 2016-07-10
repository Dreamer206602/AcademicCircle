package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.search.SearchScholarAdapter;
import com.lezhian.academiccircle.mvp.bean.SearchBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 搜索的界面
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tab_bar_keyword_et)
    EditText mEtContent;
    @Bind(R.id.clear_keyword_iv)
    ImageView mClearKeywordIv;
    @Bind(R.id.tab_bar_cancel_tv)
    TextView mTvCancel;
    @Bind(R.id.contentTextView)
    TextView mContentTextView;
    @Bind(R.id.recyclerView_history)
    RecyclerView mHistoryRecyclerView;
    @Bind(R.id.clear_history_btn)
    Button mClearHistoryBtn;
    @Bind(R.id.search_history_ll)
    LinearLayout mLinearLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.tv_result)
    TextView mTvResult;



    private SearchScholarAdapter mAdapter;
    private ArrayList<SearchBean> mDatas;

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mTvCancel.setOnClickListener(this);


//        RxTextView.textChanges(mEtContent)
//                .debounce(600, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
//                .subscribeOn(AndroidSchedulers.mainThread())  // 对etKey[EditText]的监听操作 需要在主线程操作
//                .filter(new Func1<CharSequence, Boolean>() {//对用户输入的关键字进行过滤
//                    @Override
//                    public Boolean call(CharSequence charSequence) {
//                        //当 EditText 中文字大于0的时候
//                        return charSequence.toString().trim().length() > 0;
//                    }
//                })
//                .switchMap(new Func1<CharSequence, Observable<SearchBean>>() {
//                    @Override
//                    public Observable<SearchBean> call(CharSequence charSequence) {
//                        //搜索
//                        LogUtils.d("search", charSequence.toString());
//                        return circleApi.getSearchData("1",charSequence.toString(),1)
//                                .flatMap(new Func1<SearchBean, Observable<SearchBean>>() {
//                                    @Override
//                                    public Observable<SearchBean> call(SearchBean searchBean) {
//                                        return Observable.just(searchBean);
//                                    }
//                                });
//                    }
//                })
//                        // 网络操作在io线程
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<SearchBean>() {
//                    @Override
//                    public void call(SearchBean searchBean) {
//                        LogUtils.d("searchs", searchBean.getMessage());
//                        LogUtils.d("searchs", searchBean.getStatus()+";");
//                        //LogUtils.d("searchss", searchBean.getData().get(0).getAvatar());
//                        //mDatas.add(searchBean);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                });
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getActivity()));
//        mAdapter = new SearchScholarAdapter(R.layout.item_search, mDatas);
//        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tab_bar_cancel_tv:
                finish();
                break;
        }
    }






}
