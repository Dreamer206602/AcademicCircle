package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.selectLocation.LocationSelectorDialogBuilder;

import butterknife.Bind;

/**
 * 地区定位的界面
 */
public class AreaLocationActivity extends BaseActivity implements LocationSelectorDialogBuilder.OnSaveLocationLister, View.OnClickListener {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tv_current_area)
    TextView mTvCurrentArea;
    @Bind(R.id.iv_select_city)
    ImageView mIvSelectCity;
    @Bind(R.id.tv_city)
    TextView mTvCity;

    private LocationSelectorDialogBuilder locationBuilder;


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
        mTvMiddle.setText(UIUtils.getString(R.string.areaSelect));
        mTvRight.setVisibility(View.GONE);

        mIvSelectCity.setOnClickListener(this);

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_area_location;
    }

    @Override
    public void onSaveLocation(String location, String provinceId, String cityId) {
        mTvCity.setText(location);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_select_city:
                if (locationBuilder == null) {
                    locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
                    locationBuilder.setOnSaveLocationLister(this);
                }
                locationBuilder.show();
                break;
        }
    }
}
