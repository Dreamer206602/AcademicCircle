package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.Bind;

/**
 * 职位修改的界面
 */
public class JobModifyActivity extends BaseActivity {


    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.et_content)
    MaterialEditText mEtContent;
    @Bind(R.id.tv_ok)
    TextView mTvOk;


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
        mTvMiddle.setText(UIUtils.getString(R.string.jobsModifyPersonIntroduction));
        mTvRight.setVisibility(View.GONE);


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_job_modify;
    }


}
