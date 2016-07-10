package com.lezhian.academiccircle.network;



import com.lezhian.academiccircle.mvp.bean.AcademicCircleBean;
import com.lezhian.academiccircle.mvp.bean.BaseBean;
import com.lezhian.academiccircle.mvp.bean.FollowBean;
import com.lezhian.academiccircle.mvp.bean.HeadLineListBean;
import com.lezhian.academiccircle.mvp.bean.IdentityBean;
import com.lezhian.academiccircle.mvp.bean.MyCommentListBean;
import com.lezhian.academiccircle.mvp.bean.RecommendBean;
import com.lezhian.academiccircle.mvp.bean.ReplyToMeBean;
import com.lezhian.academiccircle.mvp.bean.SearchBean;
import com.lezhian.academiccircle.mvp.bean.SubscribeBean;
import com.lezhian.academiccircle.mvp.bean.TestDataBean;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;
import com.lezhian.academiccircle.mvp.bean.headline.HeadLineDetailBean;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by hww on 2016/6/20.
 */
public interface AcademicCircleApi {

    //获取身份列表
    @POST(Api.GET_INDENTIFY_LIST)
    Observable<BaseBean<List<IdentityBean>>>getIdentityList();

    //获得订阅列表
    @POST(Api.GET_SUBSCRIBE_LIST)
    Observable<BaseBean<List<SubscribeBean>>>getSubscribeList(@QueryMap Map<String,String>params);


    //获得头条列表
    //@Header("categoryId=1")
    @POST(Api.GET_HEADLINE_LIST)
    Observable<BaseBean<List<HeadLineListBean>>>getHeadLineList(@QueryMap Map<String,String>params);

    /** 获取首页轮播列表 */
    @POST(Api.GET_BANNER_LIST)
    Observable<BaseBean<List<BannerBean>>>getBannerList();

    // 获得头条文章列表
    @POST(Api.GET_HEADLINE_LIST)
    Observable<BaseBean<List<BannerBean>>> getHeadLineArticleList(@QueryMap Map<String,String> params);

    /** 获取文章详情 */
    @POST(Api.GET_ARTICLE_DETAIL_LIST)
    Observable<BaseBean<HeadLineDetailBean>> getHeadLineDetailInfo(@QueryMap Map<String, String> params);

    /** 获取相关联的文章列表 */
    @POST(Api.GET_RELATED_ARTICLE_LIST)
    Observable<BaseBean<List<BannerBean>>> getRelatedArticleList(@QueryMap Map<String, String> params);

    //获得主题列表
    @POST(Api.GET_SUBSCRIBE_LIST)
    Observable<BaseBean<List<ThemeBean>>>getThemeList(@QueryMap Map<String,String>params);

    /**
     * 发布学术圈
     *
     */
    @POST(Api.PUBLISH_ACADEMIC_CIRCLE_CONTENT)
    Observable<BaseBean>getPublishAcademicContent(@QueryMap Map<String,Object>params);


    /**
     * 学术圈列表
     */
    @POST(Api.GET_ACADEMIC_CIRCLE_LIST)
    Observable<BaseBean<List<AcademicCircleBean>>>getAcademicCircleList(@Query("userId")String userId,@Query("pageIndex")int pageIndex);


    /**
     * 推荐列表
     */
    @POST(Api.GET_RECOMMEND_LIST)
    Observable<BaseBean<List<RecommendBean>>>getRecommendList(@Query("userId")String userId,@Query("recommend")int recommend,@Query("pageIndex")int pageIndex);


    /**
     * 我的评论
     */
    @POST(Api.GETMYCOMMENTLIST)
    Observable<BaseBean<List<MyCommentListBean>>>getMyCommenList(@Query("userId")String userId,@Query("pageIndex")int pageIndex);


    /***
     * 回复我的评论
     */

    @POST(Api.REPLYTOMECOMMENTLIST)
    Observable<BaseBean<List<ReplyToMeBean>>>getReplyToMeCommentList(@Query("userId")String userId,@Query("pageIndex")int pageIndex);


    /**
     * 我关注的  和关注我的
     */
    @POST(Api.GET_FOLLOW_LIST)
    Observable<BaseBean<List<FollowBean>>>getFollowList(@Query("userId")String userId,@Query("pageIndex")int pageIndex);

    /**
     * 搜索结果
     */
    @POST(Api.GET_SEARCH_SCHOLAR_LIST)
    Observable<SearchBean>getSearchData(@Query("userId")String userId,@Query("keyword")String keyword,@Query("pageIndex")int pageIndex);


    /**
     * 测试搜索的接口
     */
    @GET("/sug")
    Observable<TestDataBean>getTestData(@Query("code")String code,@Query("q")String keyword);

    /**
     * 注册
     */
    @POST(Api.SIGN_UP)
    Observable<BaseBean>getSignUp(@Query("username")String name,@Query("password")String password);


    /**
     * 邮箱验证
     */
    @POST(Api.VERIFY_MAIL)
        Observable<BaseBean>VerifyMail(@Query("userId")String userId,@Query("organization")String organization,
                                       @Query("name")String name,@Query("mail")String mail);




}
