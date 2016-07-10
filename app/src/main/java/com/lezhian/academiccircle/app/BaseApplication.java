package com.lezhian.academiccircle.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.lezhian.academiccircle.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

import cn.smssdk.SMSSDK;

/**
 * Created by hww on 2016/6/23.
 */
public class BaseApplication extends Application {

    private static Context context = null;
    private static volatile  BaseApplication mInstance;

    public static void setContext(Context context) {
        BaseApplication.context = context;
    }

    public static BaseApplication getInstance(){
        BaseApplication inst=mInstance;//创建临时变量
        if(inst==null){
            synchronized (BaseApplication.class){
                inst=mInstance;
                if(inst==null){
                    inst=new BaseApplication();
                    mInstance=inst;
                }
            }
        }
        return inst;//注意返回的是临时变量
    }
    private static final List<Activity> list = new ArrayList<>();
    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static void removeAllActivity() {

        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        try {
            removeAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //检测内存泄漏
        LeakCanary.install(this);
        initImageLoader();
        SMSSDK.initSDK(this, "147bfb00dd8e8", "99bc1e266f2e7399fbd1b32085859bd7");

    }
    public static Context getContext() {
        return context;
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.mipmap.app_icon)
                        .showImageForEmptyUri(R.mipmap.app_icon)
                        .showImageOnFail(R.mipmap.app_icon).cacheInMemory(true)
                        .cacheOnDisk(true)
                        .considerExifParams(true)
                        .imageScaleType(ImageScaleType.EXACTLY)
                        .displayer(new RoundedBitmapDisplayer(0)).build())
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }
}
