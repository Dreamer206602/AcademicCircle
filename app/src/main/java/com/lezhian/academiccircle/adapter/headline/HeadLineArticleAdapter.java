package com.lezhian.academiccircle.adapter.headline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.BaseRecyclerAdapter;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;
import com.lezhian.academiccircle.network.Api;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${CQ} on 2016/6/28.
 * 学术头条文章列表
 */
public class HeadLineArticleAdapter extends BaseRecyclerAdapter<BannerBean, HeadLineArticleAdapter.MyViewHolder> {

    public HeadLineArticleAdapter(Context context, ArrayList<BannerBean> mItemList) {
        super(context, mItemList);
    }

    public void addItems(List<BannerBean> arrayList){
        if (null != arrayList && arrayList.size() > 0) {
            addToLast(arrayList);
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_headline_article, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        LogUtils.i("kiki", "onBindViewHolder======position=====" + position);
        MyViewHolder viewHolder = null;
        if (holder instanceof MyViewHolder) {
            viewHolder = (MyViewHolder)holder;
            final BannerBean bean = mArrayList.get(position);
            viewHolder.titleTV.setText(bean.getTitle());
            String from = "";
            if ("1".equals(bean.getSource())) {
                from = "凤凰网";
            } else if ("2".equals(bean.getSource())) {
                from = "果壳网";
            } else if ("3".equals(bean.getSource())) {
                from = "新浪网";
            } else {
                from = "丫丫网";
            }
            viewHolder.fromTV.setText(from);
            viewHolder.numTV.setText(bean.getReadCount());
            int w = UIUtils.getScreenWidth() / 3;
            int h = w/5 * 4;
            viewHolder.iconIV.setLayoutParams(new LinearLayout.LayoutParams(w, h));
            String url = Api.BASE_API_HEAD_LINE.substring(0, Api.BASE_API_HEAD_LINE.length()-1) + mArrayList.get(position).getImageUrl();
            ImageLoader.getInstance().displayImage(url, viewHolder.iconIV);

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iconIV;
        TextView titleTV;
        TextView fromTV;
        TextView numTV;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            iconIV = (ImageView) itemView.findViewById(R.id.iha_iv_icon);
            titleTV = (TextView) itemView.findViewById(R.id.iha_tv_title);
            fromTV = (TextView) itemView.findViewById(R.id.iha_tv_from);
            numTV = (TextView) itemView.findViewById(R.id.iha_tv_num);
        }
    }
}
