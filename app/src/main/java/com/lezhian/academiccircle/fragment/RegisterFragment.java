package com.lezhian.academiccircle.fragment;


import android.app.Dialog;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.impl.RegisterPresenterImpl;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.Md5Utils;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.EditText.XEditText;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.Bind;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import rx.Observable;
import rx.Observer;
import rx.functions.Func3;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<RegisterPresenterImpl> implements View.OnClickListener, BaseView {

    // 默认使用中国区号
    private static final String DEFAULT_COUNTRY_ID = "86";

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;

    @Bind(R.id.et_phoneNumber)
    XEditText mEtPhoneNumber;
    @Bind(R.id.tv_getVerificationCode)
    TextView mTvGetVerificationCode;
    @Bind(R.id.et_verification)
    XEditText mEtVerification;
    @Bind(R.id.et_password)
    XEditText mEtPassword;
    @Bind(R.id.btn_register)
    Button mBtnRegister;
    @Bind(R.id.cb_select)
    CheckBox mCbSelect;

    private String mPhoneNumber;
    private String mVerificatonCode;
    private String mPassword;
    private EventHandler handler;

    private OnSendMessageHandler osmHandler;
    public void setOnSendMessageHandler(OnSendMessageHandler h) {
        osmHandler = h;
    }


    public static volatile RegisterFragment mInstance=null;

    public static RegisterFragment getInstance(){
        RegisterFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (RegisterFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new RegisterFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }



    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_register,null);
    }

    @Override
    protected void initData() {

        mTvLeft.setOnClickListener(this);
        mTvMiddle.setText(UIUtils.getString(R.string.register));
        mTvRight.setVisibility(View.GONE);
        mTvGetVerificationCode.setOnClickListener(this);


        handler=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功


                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };


    }

    @Override
    public void onResume() {
        super.onResume();
        SMSSDK.registerEventHandler(handler);
        Observable<CharSequence> PhoneNumberObservable = RxTextView.textChanges(mEtPhoneNumber).skip(1);
        Observable<CharSequence> VerificationChangeObservable = RxTextView.textChanges(mEtVerification).skip(1);
        Observable<CharSequence>   PasswordObservable = RxTextView.textChanges(mEtPassword).skip(1);

        Observable.combineLatest(PhoneNumberObservable,
                VerificationChangeObservable,
                PasswordObservable,
                new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence newPhoneNumber,
                                        CharSequence newVerification,
                                        CharSequence newPassword) {

                        boolean phoneValid = !TextUtils.isEmpty(newPhoneNumber);
                        if (!phoneValid) {
                            mEtPhoneNumber.setError("请输入正确的手机号!");
                        }

                        boolean verificationValid = !TextUtils.isEmpty(newVerification);
                        if (!verificationValid) {
                            mEtVerification.setError("请输入正确的验证码!");
                        }

                        boolean passwordValid = !TextUtils.isEmpty(newPassword)&& newPassword.length()>4;

                        if (!passwordValid) {
                            mEtPassword.setError("请输入长度大于4的密码");
                        }

                        mPhoneNumber=newPhoneNumber.toString().trim();
                        mVerificatonCode=newVerification.toString().trim();
                        mPassword=newPassword.toString().trim();

                        return phoneValid && verificationValid && passwordValid;

                    }
                })//
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean formValid) {
                        //_btnValidIndicator.setEnabled(formValid);
                        mBtnRegister.setEnabled(formValid);
                        mBtnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.getRegister(mPhoneNumber, Md5Utils.stringToMD5(mPassword));
                            }
                        });

                    }
                });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_left:
                removeFragment();
                break;
            case R.id.tv_getVerificationCode:
                mPhoneNumber=mEtPhoneNumber.getText().toString().trim();
                LogUtils.d("number", mPhoneNumber);
                new CountDownTimer(60000, 1000) {
                    // 第一个参数是总的倒计时时间
                    // 第二个参数是每隔多少时间(ms)调用一次onTick()方法
                    public void onTick(long millisUntilFinished) {
                        SMSSDK.getVerificationCode(DEFAULT_COUNTRY_ID, mPhoneNumber,osmHandler);
                        mTvGetVerificationCode.setText(millisUntilFinished / 1000 + "s后发送");
                        mTvGetVerificationCode.setBackgroundResource(R.drawable.bg_rectangle2);
                        mTvGetVerificationCode.setEnabled(false);
                    }
                    public void onFinish() {
                        mTvGetVerificationCode.setBackgroundResource(R.drawable.bg_rectangle);
                        mTvGetVerificationCode.setText(UIUtils.getString(R.string.getVerificationCode));
                        mTvGetVerificationCode.setEnabled(true);
                    }
                }.start();

                break;
        }
    }

    @Override
    protected RegisterPresenterImpl getPresenter() {
        return new RegisterPresenterImpl(UIUtils.getActivity(),this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(handler);
    }

    @Override
    public void showLoading() {
        new CBDialogBuilder(UIUtils.getActivity(), CBDialogBuilder.DIALOG_STYLE_PROGRESS_TITANIC)
                .setTouchOutSideCancelable(false)
                .showCancelButton(true)
                .setMessage("正在加载请稍后...")
                .setProgressTitanicText("拼命加载")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setOnProgressOutTimeListener(1, new CBDialogBuilder.onProgressOutTimeListener() {
                    @Override
                    public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView) {
                        addFragment(EmailAuthenticationFragment.getInstance());
                        //startActivities(EmailAuthenticationActivity.class);
                        dialog.dismiss();
                    }
                })
                .create().show();



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
