package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

import butterknife.Bind;

/**
 * 忘记密码的界面
 */
public class ForgetPassWordActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;

    @Bind(R.id.btn_next)
    Button mBtnNext;
    @Bind(R.id.et_phoneNumber)
    EditText mEtPhoneNumber;
    @Bind(R.id.tv_getVerificationCode)
    TextView mTvGetVerificationCode;
    @Bind(R.id.et_verification)
    EditText mEtVerification;

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
        mTvMiddle.setText(UIUtils.getString(R.string.findPassword));
        mTvRight.setVisibility(View.GONE);

        mBtnNext.setOnClickListener(this);


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pass_word;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                startActivities(ResetPasswordActivity.class);
                break;
        }
    }
}
