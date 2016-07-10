package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.ReplyToMeBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.List;

/**
 * Created by hww on 2016/7/5.
 */
public interface ReplyToMeModel {

    interface  ReplyToMeView extends BaseView {
        void addReplyToMeData(BaseBean<List<ReplyToMeBean>> subscribeBeen);
    }

    interface ReplyToMePresenter extends IPresenter {
        void getSubscribeList(String userId,int pageIndex);
    }
}
