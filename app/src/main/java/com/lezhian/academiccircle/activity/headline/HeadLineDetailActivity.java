package com.lezhian.academiccircle.activity.headline;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.BaseActivity;
import com.lezhian.academiccircle.adapter.headline.RelatedHeadLineAdapter;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;
import com.lezhian.academiccircle.mvp.bean.headline.HeadLineDetailBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.network.Api;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.NoScrollListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by ${CQ} on 2016/6/29.
 */
public class HeadLineDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.cst_ll_back)
    LinearLayout mBackLL;
    @Bind(R.id.cst_tv_title)
    TextView mTitleBarTV;
    @Bind(R.id.ahd_tv_title)
    TextView mTitleTV;
    @Bind(R.id.ahd_tv_from)
    TextView mFromTV;
    @Bind(R.id.ahd_tv_time)
    TextView mTimeTV;
    @Bind(R.id.ahd_tv_readcount)
    TextView mReadCountTV;
    @Bind(R.id.ahd_tv_intro)
    TextView mIntroTV;
    @Bind(R.id.ahd_tv_type)
    TextView mTypeTV;
    @Bind(R.id.ahd_iv_img)
    ImageView mImgIV;
    @Bind(R.id.ahd_ll_praise)
    LinearLayout mPraiseLL;
    @Bind(R.id.ahd_iv_praise)
    ImageView mPraiseIV;
    @Bind(R.id.ahd_tv_praise)
    TextView mPraiseTV;
    @Bind(R.id.ahd_ll_related)
    LinearLayout mRelatedLL;
    @Bind(R.id.ahd_slv_listview)
    NoScrollListView mListView;

    /** 文章id */
    private String mArticleId;

    private RelatedHeadLineAdapter mReleatedHeadLineAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_headline_detail;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mArticleId = getIntent().getStringExtra(AcademicDefines.IntentParam_User1);
        if (null != mArticleId && !TextUtils.isEmpty(mArticleId)) {
            initView();
            queryDetail();
            queryRelated();
        }
    }

    private void initView() {
        mTitleBarTV.setText("详情");
        mBackLL.setOnClickListener(this);
        mReleatedHeadLineAdapter = new RelatedHeadLineAdapter(this);
        mListView.setAdapter(mReleatedHeadLineAdapter);
        mReleatedHeadLineAdapter.setOnItemClickListener(new RelatedHeadLineAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        mRelatedLL.setVisibility(View.GONE);
    }

    private void queryDetail() {
        Map<String, String> params = new HashMap<>();
        params.put("articleId", mArticleId);
        NetWork.getApi().getHeadLineDetailInfo(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<HeadLineDetailBean>>() {
                    @Override
                    public void call(BaseBean<HeadLineDetailBean> baseBean) {
                        if (baseBean.isSuccess()) {
                            updateDetailView(baseBean);
                        } else if (baseBean.getStatus() == 2001) {
                            LogUtils.i("kiki", baseBean.getMessage());
                            ToastUtil.showShort(UIUtils.getActivity(), baseBean.getMessage());
                        } else {

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void queryRelated() {
        Map<String, String> params = new HashMap<>();
        params.put("articleId", mArticleId);
        NetWork.getApi().getRelatedArticleList(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<BannerBean>>>() {
                    @Override
                    public void call(BaseBean<List<BannerBean>> baseBean) {
                        LogUtils.i("kiki", "status=====" + baseBean.getStatus());
                        if (baseBean.isSuccess()) {
                            updateRelatedArticleList(baseBean);
                            mRelatedLL.setVisibility(View.VISIBLE);
                        } else if (baseBean.getStatus() == 2001) {
                            LogUtils.i("kiki", baseBean.getMessage());

                        } else {
                            LogUtils.i("kiki", "请求失败");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void updateDetailView(BaseBean<HeadLineDetailBean> baseBean) {
        if (null != baseBean) {
            HeadLineDetailBean bean = baseBean.getData();
            mTitleTV.setText(bean.getTitle());
            String from = "";
            if ("1".equals(bean.getSource())) {
                from = "凤凰网";
            } else if ("2".equals(bean.getSource())) {
                from = "果壳网";
            } else if ("3".equals(bean.getSource())) {
                from = "新浪网";
            } else {
                from = "丫丫网";
            }
            mFromTV.setText(from);
            mTimeTV.setText(bean.getTime());
            mReadCountTV.setText(getResources().getString(R.string.headline_readcount) + bean.getReadCount());
            mIntroTV.setText(bean.getDescription());
            String url = Api.BASE_API.substring(0, Api.BASE_API.length()-1) + bean.getImageUrl();
            ImageLoader.getInstance().displayImage(url, mImgIV);

            mPraiseTV.setText(bean.getFavorite());
            mTypeTV.setText(bean.getKeyword());
        }
    }

    private void updateRelatedArticleList(BaseBean<List<BannerBean>> baseBean) {

    }

    @Override
    public void onClick(View v) {
        if (v == mBackLL) {
            this.finish();
        } else if (v == mPraiseLL) {

        }
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

}
