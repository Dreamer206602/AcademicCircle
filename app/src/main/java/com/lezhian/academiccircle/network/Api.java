package com.lezhian.academiccircle.network;

/**
 * Created by hww on 2016/6/20.
 */
public class Api {



    public static final String BASE_API="http://114.215.209.230:8888/AcademicCircle/";

    //  http://114.215.209.230:8888/AcademicHeadlines/academicCircle/releaseAcademicCircle    AcademicCircle
   // public static final String BASE_API="http://114.215.209.230:8888/AcademicHeadlines/";

    //   http://114.215.209.230:8888/AcademicHeadlines/academicCircle/releaseAcademicCircle    AcademicCircle
    public static final String BASE_API_HEAD_LINE="http://114.215.209.230:8888/AcademicHeadlines/";
//    public static final String BASE_API="http://114.215.209.230:8888/AcademicCircle/";


    //1. 获取身份列表
    public static final String  GET_INDENTIFY_LIST="user/getIdentityList";

    //2. 获取订阅列表
    /**
     * userId：用户id编号，`可选，若传了userId，则在列表里，判断给出哪些是已添加关注的，否则全部返回0。
     */
    public static final String GET_SUBSCRIBE_LIST="article/getSubscriptionList";


    // 3.获取头条列表
    public static final String GET_HEADLINE_LIST="article/getArticleList";

    //4. 获取首页轮播列表
    public static final String GET_BANNER_LIST = "article/getBannerList";

    //5.获取文章详情   articleId：文章id编号，必须
    public static final String GET_ARTICLE_DETAIL_LIST = "article/getArticleDetail";

    //6. 获取相关联的文章列表   articleId：文章id编号，必须
    public static final String GET_RELATED_ARTICLE_LIST = "article/getRelatedArticleList";


    //发布学术圈
    //7.请求参数 userId: 必填 title: 学术圈标题，必填  content:评论内容，必填   tags:标签id数组，选填
    public static final String PUBLISH_ACADEMIC_CIRCLE_CONTENT ="academicCircle/releaseAcademicCircle";


    //8.获得评论列表
    public static final String GETMYCOMMENTLIST ="message/getMyCommentList";
    //9.评论我的列表
    public static final String REPLYTOMECOMMENTLIST="message/getMyReplyList";

    //10. 获取学术圈的列表
    //请求的参数：userId  - recommend:是否获取推荐学术圈，1：推荐学术圈，其它则为所有（所有包括推荐的）
    public static final String GET_ACADEMIC_CIRCLE_LIST ="academicCircle/getCircleList";

    /**
     *  11.获取推荐列表
     */
    //请求的参数：userId  - recommend:是否获取推荐学术圈，1：推荐学术圈，其它则为所有（所有包括推荐的）
    public static final String GET_RECOMMEND_LIST ="academicCircle/getCircleList";

    /**
     * 12 获得学术圈的详情   相关的接口：   评论列表13    回复评论14   关注15
     */
    //   http://114.215.209.230:8888/AcademicCircle/academicCircle/getCircleDetailInfo
     //  参数  userId   circleId
    public static final String GET_ACADEMIC_CIRCLE_DETAIL="academicCircle/getCircleDetailInfo";

    /**
     * 13 .获取学术圈评论列表
     地址：academicCircle/getCircleCommentList
     - userId: 必填    circleId: 学术圈id编号，必填， pageIndex: 分页索引，可选，默认为1，
     */
    //    http://114.215.209.230:8888/AcademicCircle/academicCircle/getCircleCommentList
    public static final String GET_ACADEMIC_CIRCLE_COMMENT_LIST="academicCircle/getCircleCommentList";

    /**
     * 14. 学术圈回复  别人的评论
     */
    /**
     *  参数 - userId: 必填  circleId: 学术圈id编号，必填，
     *  content:评论内容，必填      commentId:若为回复评论者，则需要此字段，选填
     */
    public static final String  GET_ACADEMIC_CIRCLE_COMMENTLIST_OTHERS="academicCircle/commentCircle";


    /**
     * 15 关注取消关注
     *     user/followRelationship
     参数 userId - 关注人 followId - 被关注人   type - 0：建立关注 / 1：取消关注   OK
     */
    //  http://114.215.209.230:8888/AcademicCircle/user/followRelationship
     public static final String FOLLOW_RALATION_SHIP="user/followRelationship";


    /**
     * 16.获取关注/被关注列表  user/getFollowList
     *  参数  userId
     */
    //  http://114.215.209.230:8888/AcademicCircle/user/getFollowList
    public static final String GET_FOLLOW_LIST="user/getFollowList";


    /**
     * 17.获取用户信息  user/getUserInfo    userId:用户名，必须
      */
    //  http://114.215.209.230:8888/AcademicCircle/user/getUserInfo
    public static final String  GET_USER_INFO=" user/getUserInfo";


    /**
     *  18  获取我发布的学术圈
     *  academicCircle/getMyAcademicCircle
     *  参数  userId
     */
    //   http://114.215.209.230:8888/AcademicCircle/academicCircle/getMyAcademicCircle
    public static final String GET_MY_ACADEMIC_CIRCLE="academicCircle/getMyAcademicCircle";


    /**
     * 19.获取我的成果  user/getMyAchievement   - userId:用户名
     */
    // http://114.215.209.230:8888/AcademicCircle/academicCircle/getMyAchievement
    public static final String  GET_MY_ACHIEVEMENT="academicCircle/getMyAchievement";


    /**
     * 20  邮箱认证  user/verifyMail
     - userId:用户名，必须
     - institute：机构名称，必须
     - username: 用户名称，必须
     - mail: 邮箱地址，必须
     */
    public static final String  VERIFY_MAIL="user/verifyMail";


    /**
     * 21  获取我的收藏文章  article/getMyFavoredArticleList
     * 参数  userId
     */
    //   http://114.215.209.230:8888/AcademicCircle/article/getMyFavoredArticleList
    public static final String  GET_MY_FAVORATE_ARTICLE_LIST="article/getMyFavoredArticleList";

    /**
     *22  添加文章到我的收藏
     地址：article/addArticleToMyFavorites
     - userId：用户id编号，必须
     - articleId: 文章id编号，必须
     */
    // http://114.215.209.230:8888/AcademicCircle/article/addArticleToMyFavorites
    public static final String ADD_ARTICLE_TO_MY_FAVORITES="article/addArticleToMyFavorites";


    /**
     * 23.获取搜索学者  search/getScholarList
     请求参数：
     - userId: 用户id编号，可选，若填写，则返回的数据里，判断用户是否已关注
     - keyword: 搜索关键词，必填
     - pageIndex: 分页索引，可选，默认为1，
     - pageSize：分页大小，可选，默认为15，
     */
    //   http://114.215.209.230:8888/AcademicCircle/search/getScholarList
    public static final String  GET_SEARCH_SCHOLAR_LIST="search/getScholarList";














    /**
     * 26.注册
     地址：user/signUp
     请求参数：
     - username:用户名，必须
     - password:密码，必须，密码添加MD5。
     */
    //   http://114.215.209.230:8888/AcademicCircle/user/signUp
    public static final String  SIGN_UP="user/signUp";


    /**
     * 27 登录
          user/signIn
     - username:用户名，必须
     - password:密码，必须
     */
    public static final String  SIGN_IN="user/signIn";












}
