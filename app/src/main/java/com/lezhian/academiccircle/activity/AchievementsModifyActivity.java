package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.MyBaseAdapter;
import com.lezhian.academiccircle.mvp.bean.Car;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 成果修改的界面
 */
public class AchievementsModifyActivity extends BaseActivity implements View.OnClickListener, XListView.IXListViewListener {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    ImageView mTvRight;
    @Bind(R.id.iv_expand)
    ImageView mExpand;
    @Bind(R.id.listView)
    XListView mListView;

    private MyBaseAdapter mAdapter;
    private Boolean isRefresh = true;
    private Boolean isLoadMore = false;

    private int  start = 5;
    private int  size = 14;
    private Car car;


    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
       mTvLeft.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
        mExpand.setOnClickListener(this);

        isRefresh = true;
        findData();
        listener();

    }

    public List<Car>setData(int position){
        List<Car> list = new ArrayList<>();
        for (int i = 0; i < position; i++) {
            car = new Car();
            car.setId(i);
            car.setLicense("嘻嘻"+i);
            car.setType("哈哈"+i);
            car.setUser_id(i);
            list.add(car);
        }
        return list;
    }

    private void listener() {
        mAdapter = new MyBaseAdapter(UIUtils.getActivity(), setData(start));
        mListView.setAdapter(mAdapter);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    private void findData() {
        if (isRefresh) {
            size = 14;
            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(
                    UIUtils.getActivity(), setData(start));
            mListView.setAdapter(myBaseAdapter);
            isRefresh = false;
            return;
        }
        if (isLoadMore&&size<100) {
            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(
                    UIUtils.getActivity(), setData(size));
            mListView.setAdapter(myBaseAdapter);
            mListView.stopLoadMore();
            mListView.setSelection(size - 12);
            // 时间戳
            mListView.setRefreshTime("刚刚");
            myBaseAdapter.notifyDataSetChanged();

            isLoadMore = false;
            return;
        }else if (size>=100) {
            Toast.makeText(getApplicationContext(), "无更多内容", Toast.LENGTH_LONG).show();
            mListView.stopLoadMore();
            size = size-14;
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_achievements_modify;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_left:
                finish();
                break;
            case R.id.tv_right:
                //跳转到新增成果的界面
                startActivities(AddNewAchievementsActivity.class);
                break;
            case R.id.iv_expand:
                //展开和隐藏ListView
                if(mListView.getVisibility()==View.INVISIBLE){
                    mListView.setVisibility(View.VISIBLE);
                }else{
                    mListView.setVisibility(View.INVISIBLE);
                }
                break;

        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        findData();
        mListView.stopRefresh();
        // ʱ���
        mListView.setRefreshTime("刚刚");

    }

    @Override
    public void onLoadMore() {
        size = size+10;
        isLoadMore = true;
        findData();
    }
}
