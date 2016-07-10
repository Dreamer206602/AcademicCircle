package com.lezhian.academiccircle.utils;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hww on 2016/6/20.
 */
public class ActivityCollector {

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


}
