package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;

import butterknife.Bind;

/**
 * 修改信息的界面
 */
public class ModifyMessageActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tv_job_modify)
    TextView mTvJobModify;
    @Bind(R.id.tv_person_introduction)
    TextView mTvPersonIntroduction;
    @Bind(R.id.tv_achievements_modify)
    TextView mTvAchievementsModify;


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
        mTvMiddle.setText(UIUtils.getString(R.string.modifyMessage));
        mTvRight.setVisibility(View.GONE);

        mTvJobModify.setOnClickListener(this);
        mTvAchievementsModify.setOnClickListener(this);
        mTvPersonIntroduction.setOnClickListener(this);


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_message;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_job_modify:
                //职位修改
                startActivities(JobModifyActivity.class);
                break;
            case R.id.tv_achievements_modify:
                //成果修改
                startActivities(AchievementsModifyActivity.class);
                break;
            case R.id.tv_person_introduction:
                //个人简介修改
                startActivities(PersonIntroductionActivity.class);
                break;
        }


    }


}
