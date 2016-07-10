package com.lezhian.academiccircle.fragment;


import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.impl.VerifyMailPresenterImpl;
import com.lezhian.academiccircle.mvp.view.BaseView;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.EditText.XEditText;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.Bind;
import rx.Observable;
import rx.Observer;
import rx.functions.Func3;

/**
 * 邮箱认证的界面
 */
public class EmailAuthenticationFragment extends BaseFragment<VerifyMailPresenterImpl> implements BaseView {

    public static volatile EmailAuthenticationFragment mInstance=null;

    public static EmailAuthenticationFragment getInstance(){
        EmailAuthenticationFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (EmailAuthenticationFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new EmailAuthenticationFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }

    @Bind(R.id.et_trusts_name)
    XEditText mEtTrustsName;
    @Bind(R.id.et_user_name)
    XEditText mEtUserName;
    @Bind(R.id.et_email_trusts)
    XEditText mEtEmailTrusts;
    @Bind(R.id.btn_email_activated_link)
    Button mBtnEmailActivatedLink;

    private String organization;
    private String name;
    private String email;



    @Override
    protected View initView() {
        return View.inflate(UIUtils.getActivity(),R.layout.fragment_email_authentication,null);
    }

    @Override
    protected void initData() {


        Observable<CharSequence> TrustsNameObservable = RxTextView.textChanges(mEtTrustsName).skip(1);
        Observable<CharSequence> UserChangeObservable = RxTextView.textChanges(mEtUserName).skip(1);
        Observable<CharSequence>   EmailTrustsObservable = RxTextView.textChanges(mEtEmailTrusts).skip(1);

        Observable.combineLatest(TrustsNameObservable,
                UserChangeObservable,
                EmailTrustsObservable,
                new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence newPhoneNumber,
                                        CharSequence newVerification,
                                        CharSequence newPassword) {

                        boolean phoneValid = !TextUtils.isEmpty(newPhoneNumber);
                        if (!phoneValid) {
                            mEtTrustsName.setError("请输入机构!");
                        }

                        boolean verificationValid = !TextUtils.isEmpty(newVerification);
                        if (!verificationValid) {
                            mEtUserName.setError("请输入正确的用户名!");
                        }

                        boolean passwordValid = !TextUtils.isEmpty(newPassword);

                        if (!passwordValid) {
                            mEtEmailTrusts.setError("请输入正确的邮箱");
                        }

                        organization=mEtTrustsName.getText().toString().trim();
                        name=mEtUserName.getText().toString().trim();
                        email=mEtEmailTrusts.getText().toString().trim();
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

                        mBtnEmailActivatedLink.setEnabled(formValid);
                        mBtnEmailActivatedLink.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPresenter.verifyMail("1",organization,name,email);
                            }
                        });

                    }
                });




    }

    @Override
    protected VerifyMailPresenterImpl getPresenter() {
        return new VerifyMailPresenterImpl(UIUtils.getActivity(),this);
    }


    @Override
    public void showLoading() {

        //加载的进度条
        new CBDialogBuilder(UIUtils.getActivity(), CBDialogBuilder.DIALOG_STYLE_PROGRESS)
                .setTouchOutSideCancelable(false)
                .showCancelButton(true)
                .setMessage("正在加载请稍后...")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setOnProgressOutTimeListener(1, new CBDialogBuilder.onProgressOutTimeListener() {
                    @Override
                    public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView) {
                        //dialogMsgTextView.setText("出错啦");
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
