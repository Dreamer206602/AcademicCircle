package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.EditText.XEditText;

import butterknife.Bind;

/**
 * 邮箱认证的界面
 */
public class EmailAuthenticationActivity extends BaseActivity {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.et_trusts_name)
    XEditText mEtTrustsName;
    @Bind(R.id.et_user_name)
    XEditText mEtUserName;
    @Bind(R.id.et_email_trusts)
    XEditText mEtEmailTrusts;
    @Bind(R.id.btn_email_activated_link)
    Button mBtnEmailActivatedLink;

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

        mTvLeft.setVisibility(View.GONE);
        mTvRight.setVisibility(View.GONE);
        mTvMiddle.setText(UIUtils.getString(R.string.email_trusts));


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_authentication;
    }


}
