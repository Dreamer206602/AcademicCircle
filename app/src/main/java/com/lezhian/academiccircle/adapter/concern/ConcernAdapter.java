package com.lezhian.academiccircle.adapter.concern;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.bean.ConcernBean;
import com.lezhian.academiccircle.widget.RoundImageView;

import java.util.List;

/**
 * 学术关注的适配器
 */
public class ConcernAdapter extends BaseQuickAdapter<ConcernBean> {

    public ConcernAdapter(int layoutResId, List<ConcernBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ConcernBean item) {

        holder.setText(R.id.tv_name, item.getName());
        Glide.with(mContext)
                .load(item.getImgUrl())
                .crossFade().placeholder(R.mipmap.app_icon)
                .into((RoundImageView)holder.getView(R.id.iv_headImage));
    }

}
