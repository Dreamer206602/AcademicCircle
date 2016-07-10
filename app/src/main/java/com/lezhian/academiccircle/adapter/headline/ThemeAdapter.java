package com.lezhian.academiccircle.adapter.headline;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;
import com.lezhian.academiccircle.utils.ToastUtil;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.NoScrollGridView;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${CQ} on 2016/7/5.
 */
public class ThemeAdapter extends BaseAdapter {

    private Context mContext = null;
    private Handler mHandler = null;

    /** 当前的一级分类 */
    private int mCurFirstIndex;
    /** 当前的二级分类 */
    private int mCurSecIndex;

    private ArrayList<ThemeBean> mArrayList = new ArrayList<>();

    public ThemeAdapter(){}

    public ThemeAdapter(Context context, Handler mHandler) {
        this.mContext = context;
        this.mHandler = mHandler;
    }

    public ThemeAdapter(Context context, Handler mHandler, List<ThemeBean> arrayList) {
        this.mContext = context;
        this.mHandler = mHandler;
        addAll(arrayList);
    }

    public ThemeAdapter(Context context, List<ThemeBean> arrayList) {
        this.mContext = context;
        addAll(arrayList);
    }

    public void removeAll() {
        mArrayList.clear();
    }

    public void addAll(List<ThemeBean> arrayList) {
        for (int i = 0 ; i < arrayList.size() ; i++) {
            mArrayList.add(arrayList.get(i));
        }
    }

    public ArrayList<ThemeBean> getAllArrayList() {
        return mArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (null == convertView) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_theme, null);
            mViewHolder.mTitleTV = (TextView) convertView.findViewById(R.id.it_tv_themeTitle);
            mViewHolder.mGridViewGV = (GridView) convertView.findViewById(R.id.it_gv_gridview);
            convertView.setTag(mViewHolder);
        }
        mViewHolder = (ViewHolder) convertView.getTag();

        final ThemeBean bean = mArrayList.get(position);
        if (null != bean) {
            mViewHolder.mTitleTV.setText(bean.getTitle());
            if (null != bean.getCathgoryTag() && bean.getCathgoryTag().size() > 0) {
                // 有二级主题
                mViewHolder.mGridViewGV.setAdapter(new ThemeSecondAdapter(bean.getCathgoryTag()));
            }
        }
        return convertView;
    }

    class ViewHolder{
        TextView mTitleTV;
        GridView mGridViewGV;
    }

    private class ThemeSecondAdapter extends BaseAdapter{

        private ArrayList<ThemeBean> mSecondArrayList = new ArrayList<>();

        public ThemeSecondAdapter() {
        }

        public ThemeSecondAdapter(ArrayList<ThemeBean> beans) {
            this.mSecondArrayList = beans;
        }

        @Override
        public int getCount() {
            return mSecondArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mSecondArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SecViewHolder mSecViewHolder = null;
            if (null == convertView) {
                mSecViewHolder = new SecViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_theme_second, null);
                mSecViewHolder.mSecTitleTV = (TextView) convertView.findViewById(R.id.its_tv_title);
                convertView.setTag(mSecViewHolder);
            }
            mSecViewHolder = (SecViewHolder) convertView.getTag();

            final ThemeBean bean = mSecondArrayList.get(position);
            if (null != bean) {
                mSecViewHolder.mSecTitleTV.setText(bean.getTitle());
                if (bean.isSelected()) {
                    mSecViewHolder.mSecTitleTV.setBackgroundResource(R.drawable.bg_theme);
                } else {
                    mSecViewHolder.mSecTitleTV.setBackgroundResource(R.drawable.bg_white);
                }
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != bean.getCathgoryTag() && bean.getCathgoryTag().size() > 0) {
                        Message msg = new Message();
                        msg.what = AcademicDefines.Handler_ListItemClicked;
                        msg.obj = this;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(AcademicDefines.Const_Serializable_Key, bean);
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);
//                        ToastUtil.showShort(mContext, bean.getTitle());
                    } else {
                        ToastUtil.showShort(mContext, bean.getTitle()+"没有子项");
                    }
                }
            });
            return convertView;
        }

        class SecViewHolder {
            TextView mSecTitleTV;
        }
    }
}

