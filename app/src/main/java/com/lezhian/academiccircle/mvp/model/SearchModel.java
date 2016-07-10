package com.lezhian.academiccircle.mvp.model;

import com.lezhian.academiccircle.mvp.bean.SearchBean;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.mvp.view.BaseView;

/**
 * Created by hww on 2016/7/6.
 */
public interface SearchModel  {

    interface SearchView extends BaseView{
        void addLoadData(SearchBean bean);
    }

    interface SearchPresenter extends IPresenter{
        void getSearch(String userId,String keyword,int pageIndex);
    }

}
