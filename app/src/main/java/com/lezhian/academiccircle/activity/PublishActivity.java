package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.impl.PublishContentPresenterImpl;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

//发布的界面
public class PublishActivity extends BaseActivity<PublishContentPresenterImpl> implements View.OnClickListener, BaseView, TextWatcher {
    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tv_title)
    MaterialEditText mTvTitle;
    @Bind(R.id.tv_content)
    MaterialEditText mTvContent;

    private String mTitle;
    private String mContent;


    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvMiddle.setText(UIUtils.getString(R.string.publish));
        mTvRight.setText(UIUtils.getString(R.string.send));
        mTvRight.setOnClickListener(this);

        mTvTitle.addTextChangedListener(this);
        mTvContent.addTextChangedListener(this);



    }

    @Override
    protected PublishContentPresenterImpl getPresenter() {
        return new PublishContentPresenterImpl(UIUtils.getActivity(), this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_right:
                LogUtils.d("title", mTitle);
                LogUtils.d("content", mContent);
                Map<String,Object>params=new HashMap<>();
                params.put("userId",1);
                params.put("title",mTitle);
                params.put("content",mContent);
                    mPresenter.getPublishContent(params);
                break;
        }

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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mTitle=mTvTitle.getText().toString().trim();
        mContent=mTvContent.getText().toString().trim();

    }
}
