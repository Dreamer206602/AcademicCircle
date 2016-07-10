package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.FollowBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.List;

/**
 * Created by hww on 2016/7/6.
 */
public interface FollowModel {

    interface  FollowView extends BaseView {
        void addFollowData(BaseBean<List<FollowBean>>been);
    }

    interface FollowPresenter extends IPresenter {
        void getFollowList(String userId,int pageIndex);
    }

}
