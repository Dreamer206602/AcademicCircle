package com.lezhian.academiccircle.activity;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.headline.ThemeAdapter;
import com.lezhian.academiccircle.adapter.headline.ThemeChildAdapter;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.SubscribeBean;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;
import com.lezhian.academiccircle.mvp.model.SubscribeModel;
import com.lezhian.academiccircle.mvp.presenter.impl.SubscribePresenterImpl;
import com.lezhian.academiccircle.network.NetWork;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.SharedPrefsUtil;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 选择学术圈界面
 */
public class SelectAcademicCircleActivity extends BaseActivity<SubscribePresenterImpl> implements SubscribeModel.SubscribeView, View.OnClickListener {

    /** 用户id */
    private String mUserId = "1";

    @Bind(R.id.asac_lv_listview)
    ListView mListView;
    @Bind(R.id.tv_next)
    TextView mNextTV;

    private ThemeAdapter mAdapter;
    private ArrayList<ThemeBean> mArrayList = new ArrayList<>();
    private ThemeChildAdapter mChildAdapter;

    private Dialog dialog;

    /** 当前的一级分类 */
    private int mCurFirstIndex;
    /** 当前的二级分类 */
    private int mCurSecIndex;

    private ArrayList<String> mPreAddShare = new ArrayList<>();
    private ArrayList<String> mPreDelShare = new ArrayList<>();

    private ArrayList<ThemeBean> mSaveBeans = new ArrayList<>();
    private ArrayList<ThemeBean> mDelBeans = new ArrayList<>();

    /** 文章类型 */
    private ArrayList<ThemeBean> mTypeBeans = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case AcademicDefines.Handler_ListItemClicked:
                    ThemeBean bean = (ThemeBean)msg.getData().getSerializable(AcademicDefines.Const_Serializable_Key);
                    mChildAdapter = new ThemeChildAdapter(context, mHandler, bean.getCathgoryTag());
                    dialog = UIUtils.createThemeChooseDialog(SelectAcademicCircleActivity.this, mHandler, bean.getTitle(), mChildAdapter);
                    dialog.show();
                    break;
                case AcademicDefines.Handler_ListChildItemClicked:
                    ThemeBean thirdBean = (ThemeBean)msg.getData().getSerializable(AcademicDefines.Const_Serializable_Key);
                    if (thirdBean.isSelected()) {
                        mPreAddShare.add(thirdBean.getCategoryId());
                        mSaveBeans.add(thirdBean);
                    } else {
                        mPreDelShare.add(thirdBean.getCategoryId());
                        mDelBeans.add(thirdBean);
                    }
                    break;
                case AcademicDefines.Handler_DialogCancleButtonClicked:
                    mPreAddShare.clear();
                    mPreDelShare.clear();
                    mSaveBeans.clear();
                    mDelBeans.clear();
                    break;
                case AcademicDefines.Handler_DialogOkButtonClicked:
                    SharedPrefsUtil.putValue(SelectAcademicCircleActivity.this, AcademicDefines.SharedPreferences_AddTheme, mPreAddShare);
                    SharedPrefsUtil.removeValue(SelectAcademicCircleActivity.this, AcademicDefines.SharedPreferences_AddTheme, mPreDelShare);
                    for (int i = 0 ; i < mSaveBeans.size() ; i++) {
                        SharedPrefsUtil.putValue(SelectAcademicCircleActivity.this, mSaveBeans.get(i).getCategoryId(), mSaveBeans.get(i).getTitle());
                    }
                    for (int i = 0 ; i < mDelBeans.size() ; i++) {
                        SharedPrefsUtil.removeValue(SelectAcademicCircleActivity.this, mDelBeans.get(i).getCategoryId());
                    }
                    mPreAddShare.clear();
                    mPreDelShare.clear();
                    mSaveBeans.clear();
                    mDelBeans.clear();

                    if (null != dialog && dialog.isShowing()) dialog.dismiss();
                    boolean third = false;
                    for (int i = 0 ; i < mChildAdapter.getCount() ; i++) {
                        if (mChildAdapter.getAllArrayList().get(i).isSelected()) {
                            third = true;
                            break;
                        }
                    }
                    // 三级有选择，更新二级选择状态
//                    if (third) {
                        for (int j = 0 ; j < mAdapter.getCount() ; j++) {
                            if (null != mAdapter.getAllArrayList().get(j).getCathgoryTag() && mAdapter.getAllArrayList().get(j).getCathgoryTag().size() > 0) {
                                ArrayList<ThemeBean> secArray = mAdapter.getAllArrayList().get(j).getCathgoryTag();
                                for (int k = 0 ; k < mAdapter.getAllArrayList().get(j).getCathgoryTag().size() ; k++) {
                                    if (mAdapter.getAllArrayList().get(j).getCathgoryTag().get(k).getCategoryId().equals(mChildAdapter.getAllArrayList().get(0).getParentId())) {
                                        if (third) {
                                            mAdapter.getAllArrayList().get(j).getCathgoryTag().get(k).setSelected(true);
                                        } else {
                                            mAdapter.getAllArrayList().get(j).getCathgoryTag().get(k).setSelected(false);
                                        }
                                    }
                                }
                            }
                        }
//                    }
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_academic_circle;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        //TODO
//        mPresenter.getSubscribeList(params);
        mNextTV.setOnClickListener(this);
        getSharedPreferencesTypeInfo();
        queryList();
    }

    String mCategoryStr = "";
    private void getSharedPreferencesTypeInfo() {
        mCategoryStr = "";
        ArrayList<String> arrayList = SharedPrefsUtil.getValue(UIUtils.getActivity(), AcademicDefines.SharedPreferences_AddTheme);
        if (null != arrayList && arrayList.size() > 0) {
            for (int i = 0 ; i < arrayList.size() ; i++) {
                mCategoryStr = mCategoryStr + "," + arrayList.get(i);
            }
            mCategoryStr = mCategoryStr.replaceFirst(",", "");
        }
        LogUtils.i("kiki", "mCategoryStr====" + mCategoryStr);
    }

    private void queryList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", "");
        NetWork.getHeadLineApi().getThemeList(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<List<ThemeBean>>>() {
                    @Override
                    public void call(BaseBean<List<ThemeBean>> listBaseBean) {
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

    private void updateView(BaseBean<List<ThemeBean>> listBaseBean) {
        if (null != listBaseBean.getData() && listBaseBean.getData().size() > 0) {
            mArrayList.clear();
            mArrayList.addAll(listBaseBean.getData());
//            List<ThemeBean> beans = listBaseBean.getData();
            List<ThemeBean> beans = updateCategoryData(listBaseBean.getData());
            mAdapter = new ThemeAdapter(UIUtils.getActivity(), mHandler, beans);
            mListView.setAdapter(mAdapter);
        }
    }

    private List<ThemeBean> updateCategoryData(List<ThemeBean> arrayList) {
        List<ThemeBean> beans = arrayList;
        for (int i = 0 ; i < beans.size() ; i++) {
            if (null != beans.get(i).getCathgoryTag() && beans.get(i).getCathgoryTag().size() > 0) {
                for (int j = 0 ; j < beans.get(i).getCathgoryTag().size() ; j++) {
                    if (null != beans.get(i).getCathgoryTag().get(j).getCathgoryTag() && beans.get(i).getCathgoryTag().get(j).getCathgoryTag().size() > 0) {
                        boolean has = false;
                        for (int k = 0 ; k < beans.get(i).getCathgoryTag().get(j).getCathgoryTag().size() ; k++) {
                            if (mCategoryStr.contains(beans.get(i).getCathgoryTag().get(j).getCathgoryTag().get(k).getCategoryId())) {
                                beans.get(i).getCathgoryTag().get(j).getCathgoryTag().get(k).setSelected(true);
                                has = true;
                            } else {
                                beans.get(i).getCathgoryTag().get(j).getCathgoryTag().get(k).setSelected(false);
                            }
                        }
                        beans.get(i).getCathgoryTag().get(j).setSelected(has);
                    }
                }
            }
        }
        return beans;
    }

    @Override
    public void onClick(View v) {
        if (v == mNextTV) {
            setResult(AcademicDefines.ResultCode_Normal_Success);
            this.finish();
        }
    }

    @Override
    protected SubscribePresenterImpl getPresenter() {
        return new SubscribePresenterImpl(activity,this);
    }

    @Override
    public void addSubscribeData(List<SubscribeBean> subscribeBeen) {

        LogUtils.d("dd",subscribeBeen.get(0).getTitle());

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
