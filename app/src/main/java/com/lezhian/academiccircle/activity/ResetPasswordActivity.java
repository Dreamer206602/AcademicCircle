package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 重置密码界面
 */
public class ResetPasswordActivity extends BaseActivity {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.btn_login)
    Button mBtnLogin;

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
        mTvMiddle.setText(UIUtils.getString(R.string.resetPassword));
        mTvRight.setVisibility(View.GONE);

    }

    @OnClick(R.id.btn_login)
    public  void login(){
        startActivities(LoginActivity.class);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }


}
