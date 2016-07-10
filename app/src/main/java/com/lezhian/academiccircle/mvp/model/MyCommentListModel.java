package com.lezhian.academiccircle.mvp.model;

/**
 * Created by hww on 2016/7/5.
 */

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.MyCommentListBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.List;

/**
 * 我的评论列表
 */
public interface MyCommentListModel {


    interface  MyCommentListView extends BaseView {
        void addCommentListData(BaseBean<List<MyCommentListBean>> subscribeBeen);
    }

    interface MyCommentListPresenter extends IPresenter {
        void getSubscribeList(String userId,int pageIndex);
    }


}
