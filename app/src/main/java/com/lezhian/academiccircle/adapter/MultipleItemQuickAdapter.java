package com.lezhian.academiccircle.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.bean.MultipleItemBean;

import java.util.List;

/**
 * Created by hww on 2016/6/27.
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItemBean>{

    public MultipleItemQuickAdapter(List<MultipleItemBean> data) {
        super(data);
        addItemType(MultipleItemBean.TEXT, R.layout.item_academiccircle_recommend_third);
        addItemType(MultipleItemBean.IMG, R.layout.item_academiccircle_recommend_second);
        addItemType(MultipleItemBean.IMGS, R.layout.item_academiccircle_recommend_first);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItemBean item) {
        switch (helper.getItemViewType()) {
            case MultipleItemBean.TEXT:
                //helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItemBean.IMG:
                // set img data
                break;
            case MultipleItemBean.IMGS:
                // set imgs data
                break;
        }

    }
}
