package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.AcademicCircleBean;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

import java.util.List;

/**
 * Created by hww on 2016/7/6.
 */
public interface AcademicCircleDetailModel {

    interface  AcademicCircleDetailView extends BaseView {
        void addAcademicCircleData(BaseBean<List<AcademicCircleBean>> subscribeBeen);
    }

    interface AcademicCirclePresenter extends IPresenter {
        void getSubscribeList(String userId, int pageIndex);
    }
}
