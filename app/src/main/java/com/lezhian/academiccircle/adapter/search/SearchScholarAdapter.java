package com.lezhian.academiccircle.adapter.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.bean.SearchBean;

import java.util.List;

/**
 * Created by hww on 2016/7/6.
 */
public class SearchScholarAdapter extends BaseQuickAdapter<SearchBean> {


    public SearchScholarAdapter(int layoutResId, List<SearchBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, SearchBean item) {

//        Glide.with(mContext)
//                .load(item.getData().get(holder.getPosition()).getAvatar())
//                .crossFade().placeholder(R.mipmap.app_icon)
//                .into((RoundImageView)holder.getView(R.id.iv_headImage));
        holder.setText(R.id.tv_name,item.getData().get(holder.getPosition()).getUsername())
                .setText(R.id.tv_identity,item.getData().get(holder.getPosition()).getNickname());

    }
}
