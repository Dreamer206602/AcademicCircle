package com.lezhian.academiccircle.fragment;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.AcademicConcernActivity;
import com.lezhian.academiccircle.activity.AcademicSaveActivity;
import com.lezhian.academiccircle.activity.AccountManageActivity;
import com.lezhian.academiccircle.activity.HeadLineSaveActivity;
import com.lezhian.academiccircle.activity.LoginActivity;
import com.lezhian.academiccircle.activity.MessageActivity;
import com.lezhian.academiccircle.activity.MyPageActivity;
import com.lezhian.academiccircle.activity.SettingActivity;
import com.lezhian.academiccircle.activity.SubscribeTopicActivity;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.RoundImageView;

import butterknife.Bind;

/**
 * 我的界面
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    View ret;
    @Bind(R.id.iv_headImage)
    RoundImageView mIvHeadImage;
    @Bind(R.id.tv_isLogin)
    TextView mTvIsLogin;
    @Bind(R.id.tv_subscribe_topic)
    TextView mTvSubscribeTopic;
    @Bind(R.id.tv_headLine_save)
    TextView mTvHeadLineSave;
    @Bind(R.id.tv_my_page)
    TextView mTvMyPage;
    @Bind(R.id.tv_message)
    TextView mTvMessage;
    @Bind(R.id.tv_academic_concern)
    TextView mTvAcademicConcern;
    @Bind(R.id.tv_academic_save)
    TextView mTvAcademicSave;
    @Bind(R.id.tv_account_manage)
    TextView mAccountManage;


    public static volatile MineFragment mInstance=null;

    public static MineFragment getInstance(){
        MineFragment inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (MineFragment.class){
                inst=mInstance;
                if(inst==null){
                    inst=new MineFragment();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }



    @Override
    protected View initView() {
        if (ret == null) {
            ret = View.inflate(UIUtils.getActivity(), R.layout.fragment_mine, null);
        }
        return ret;
    }

    @Override
    protected void initData() {
        mTvLeft.setVisibility(View.GONE);
        mTvMiddle.setText(UIUtils.getString(R.string.mine));
        mTvRight.setText(UIUtils.getString(R.string.setting));


        mTvRight.setOnClickListener(this);
        mIvHeadImage.setOnClickListener(this);
        mTvIsLogin.setOnClickListener(this);
        mAccountManage.setOnClickListener(this);
        mTvSubscribeTopic.setOnClickListener(this);
        mTvHeadLineSave.setOnClickListener(this);
        mTvMessage.setOnClickListener(this);
        mTvMyPage.setOnClickListener(this);
        mTvAcademicConcern.setOnClickListener(this);
        mTvAcademicSave.setOnClickListener(this);


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                //设置的界面
                startActivities(SettingActivity.class);
                break;
            case R.id.iv_headImage:
                //更改头像
                break;
            case R.id.tv_isLogin:
                //是否登录
                //跳转到登录的界面
                startActivities(LoginActivity.class);
                break;
            case R.id.tv_account_manage:
                //跳转到帐号管理的界面
                startActivities(AccountManageActivity.class);
                break;
            case R.id.tv_subscribe_topic:
                //订阅热点
                startActivities(SubscribeTopicActivity.class);
                break;
            case R.id.tv_headLine_save:
                //头条收藏
                startActivities(HeadLineSaveActivity.class);
                break;
            case R.id.tv_my_page:
                //我的主页
                startActivities(MyPageActivity.class);
                break;
            case R.id.tv_message:
                //我的消息
                startActivities(MessageActivity.class);
                break;
            case R.id.tv_academic_concern:
                //学术关注
                    startActivities(AcademicConcernActivity.class);
                break;
            case R.id.tv_academic_save:
                //学术收藏
                startActivities(AcademicSaveActivity.class);
                break;

        }

    }


    private void startActivities(Class<? extends Activity> activity) {
        startActivity(new Intent(getActivity(), activity));
    }




}
