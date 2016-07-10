package com.lezhian.academiccircle.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.EditText.XEditText;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    public static volatile LoginFragment mInstance = null;

    public static LoginFragment getInstance() {
        LoginFragment inst = mInstance;//创建临时变量
        if (inst == null) {
            synchronized (LoginFragment.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new LoginFragment();
                    mInstance = inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.et_password)
    XEditText mEtPassword;
    @Bind(R.id.et_phoneNumber)
    XEditText mEtPhoneNumber;
    @Bind(R.id.tv_forget_password)
    TextView mTvForgetPassword;

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(), R.layout.fragment_login, null);
    }

    @Override
    protected void initData() {
        mTvLeft.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
        mTvForgetPassword.setOnClickListener(this);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                removeFragment();
                break;
            case R.id.tv_right:
                addFragment(RegisterFragment.getInstance());
                break;
            case R.id.tv_forget_password:
                // startActivities(ForgetPassWordActivity.class);
                break;

        }
    }


}
