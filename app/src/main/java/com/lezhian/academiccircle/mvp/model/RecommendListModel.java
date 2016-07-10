package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.RecommendBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.List;

/**
 * Created by hww on 2016/7/6.
 */
public interface RecommendListModel {

    interface  RecommendView extends BaseView {
        void addRecommendData(BaseBean<List<RecommendBean>> subscribeBeen);
    }

    interface RecommendPresenter extends IPresenter {
        void getSubscribeList(String userId,int recommend, int pageIndex);
    }
}
