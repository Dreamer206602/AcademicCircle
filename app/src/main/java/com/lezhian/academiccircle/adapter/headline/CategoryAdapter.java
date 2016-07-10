package com.lezhian.academiccircle.adapter.headline;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by ${CQ} on 2016/7/7.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private Handler mHandler;
    private ArrayList<ThemeBean> mArrayList = new ArrayList<>();

    public CategoryAdapter(Context context, Handler handler) {
        setContext(context);
        setHandler(handler);
    }

    public CategoryAdapter(Context context, Handler handler, ArrayList<ThemeBean> arrayList) {
        setContext(context);
        setHandler(handler);
        addAll(arrayList);
    }

    public CategoryAdapter(Context context) {
        setContext(context);
    }

    private void setContext(Context context) {
        this.mContext = context;
    }

    private void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void removeAll() {
        mArrayList.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<ThemeBean> arrayList) {
        if (null != arrayList && arrayList.size() > 0) {
            mArrayList.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ThemeBean bean = mArrayList.get(position);
        ViewHolder mViewHolder = null;
        if (null == convertView) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_type, null);
            mViewHolder.mCategoryNameTV = (TextView) convertView.findViewById(R.id.it_tv_typeName);
            mViewHolder.line = convertView.findViewById(R.id.it_line_type);
            mViewHolder.view = convertView.findViewById(R.id.it_rl_root);
            convertView.setTag(mViewHolder);
        }
        mViewHolder = (ViewHolder) convertView.getTag();

        if (null != bean) {
            mViewHolder.mCategoryNameTV.setText(bean.getTitle());

//            if (position == 0) {
//                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.purple));
//            } else if (position == 1) {
//                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.base_red));
//            }
//            LogUtils.i("kiki", position + "title=-=-=-=-=-=" + bean.getTitle());

            if (bean.isSelected()) {
                mViewHolder.line.setVisibility(View.VISIBLE);
            } else {
                mViewHolder.line.setVisibility(View.GONE);
            }
        }
        int width = UIUtils.dp2px(mContext, 20 + bean.getTitle().length()*16);
//        convertView.setLayoutParams(new RelativeLayout.LayoutParams(width, RelativeLayout.LayoutParams.MATCH_PARENT));
//        mViewHolder.mCategoryNameTV.setLayoutParams(new RelativeLayout.LayoutParams(width, RelativeLayout.LayoutParams.MATCH_PARENT));
//        LogUtils.i("kiki", position+"width=-=-=-=-=-=" + width);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(bean);
                notifyDataSetChanged();
                Message msg = new Message();
                msg.what = AcademicDefines.Handler_ListItemClicked;
                msg.obj = this;
                Bundle bundle = new Bundle();
                bundle.putString(AcademicDefines.Const_Serializable_Key, bean.getCategoryId());
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        });
        return convertView;
    }

    private void update(ThemeBean bean) {
        for (int i = 0 ; i < mArrayList.size() ; i++) {
            if (bean.getCategoryId().equals(mArrayList.get(i).getCategoryId())) {
                mArrayList.get(i).setSelected(true);
            } else {
                mArrayList.get(i).setSelected(false);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    class ViewHolder {
        TextView mCategoryNameTV;
        View line;
        View view;
    }
}
