package com.lezhian.academiccircle.adapter.headline;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${CQ} on 2016/7/5.
 */
public class ThemeChildAdapter extends BaseAdapter {

    private Context mContext = null;
    private Handler mHandler = null;

    private ArrayList<ThemeBean> mArrayList = new ArrayList<>();

    public ThemeChildAdapter(){}

    public ThemeChildAdapter(Context context, Handler mHandler) {
        this.mContext = context;
        this.mHandler = mHandler;
    }

    public ThemeChildAdapter(Context context, Handler mHandler, ArrayList<ThemeBean> arrayList) {
        this.mContext = context;
        this.mHandler = mHandler;
        this.mArrayList = arrayList;
    }

    public ThemeChildAdapter(Context context, List<ThemeBean> arrayList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (null == convertView) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_theme_child, null);
            mViewHolder.mTitleTV = (TextView) convertView.findViewById(R.id.itc_tv_childName);
            convertView.setTag(mViewHolder);
        }
        mViewHolder = (ViewHolder) convertView.getTag();

        final ThemeBean bean = mArrayList.get(position);
        if (null != bean) {
            mViewHolder.mTitleTV.setText(bean.getTitle());
            if (bean.isSelected()) {
                mViewHolder.mTitleTV.setBackgroundResource(R.drawable.pub_circle_bg_purple);
                mViewHolder.mTitleTV.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                mViewHolder.mTitleTV.setBackgroundResource(R.drawable.pub_rounded_bg_white_frame_purple_no_selector);
                mViewHolder.mTitleTV.setTextColor(mContext.getResources().getColor(R.color.purple));
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = AcademicDefines.Handler_ListChildItemClicked;
                msg.obj = this;
                Bundle bundle = new Bundle();
                bundle.putSerializable(AcademicDefines.Const_Serializable_Key, bean);
                msg.setData(bundle);
                mHandler.sendMessage(msg);

                mArrayList.get(position).setSelected(!bean.isSelected());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView mTitleTV;
    }
}

