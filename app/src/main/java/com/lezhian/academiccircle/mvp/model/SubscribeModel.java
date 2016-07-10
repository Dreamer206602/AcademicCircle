package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.SubscribeBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hww on 2016/6/21.
 */
public interface SubscribeModel {

    interface  SubscribeView extends BaseView{
        void addSubscribeData(List<SubscribeBean> subscribeBeen);
    }

    interface SubscribePresenter extends IPresenter{
//        void getSubscribeList();
//        void getSubscribeList(int userId);
        void getSubscribeList(HashMap<String, String> params);
    }


}
