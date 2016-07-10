package com.lezhian.academiccircle.adapter.headline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${CQ} on 2016/6/29.
 */
public class RelatedHeadLineAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BannerBean> mArrayList = new ArrayList<>();

    public RelatedHeadLineAdapter(Context context) {
        setContext(context);
    }

    private void setContext(Context context) {
        this.mContext = context;
    }

    public void addItems(List<BannerBean> arrayList){
        if (null != arrayList && arrayList.size() > 0) {
            mArrayList.addAll(arrayList);
        }
    }

    public void removeAll() {
        mArrayList.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final BannerBean bean = mArrayList.get(position);
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_headline_related, parent, false);
            viewHolder.titleTV = (TextView) convertView.findViewById(R.id.ihr_tv_title);
            viewHolder.btn1TV = (TextView) convertView.findViewById(R.id.ihr_tv_btn1);
            viewHolder.btn2TV = (TextView) convertView.findViewById(R.id.ihr_tv_btn2);
            viewHolder.btn3TV = (TextView) convertView.findViewById(R.id.ihr_tv_btn3);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        if (null != bean) {
            viewHolder.titleTV.setText(bean.getTitle());
            viewHolder.btn1TV.setText(bean.getKeyword());
            // 待处理
            viewHolder.btn2TV.setText(bean.getKeyword());
            viewHolder.btn3TV.setText(bean.getKeyword());
        }
        return convertView;
    }

    class ViewHolder {
        TextView titleTV;
        TextView btn1TV;
        TextView btn2TV;
        TextView btn3TV;
    }
}
