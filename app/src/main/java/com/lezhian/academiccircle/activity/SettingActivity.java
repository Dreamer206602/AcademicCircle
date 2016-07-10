package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.ToggleButton;

import butterknife.Bind;

public class SettingActivity extends BaseActivity {


    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.toggleButton)
    ToggleButton mToggleButton;
    @Bind(R.id.tv_cache_count)
    TextView mTvCacheCount;
    @Bind(R.id.tv_current_version)
    TextView mTvCurrentVersion;


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
        mTvMiddle.setText(UIUtils.getString(R.string.setting));
        mTvRight.setVisibility(View.GONE);


        mToggleButton.setChecked(true);
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ToastUtil.showShort(UIUtils.getContext(),isChecked?"ON":"OFF");
            }
        });



    }


    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


}
