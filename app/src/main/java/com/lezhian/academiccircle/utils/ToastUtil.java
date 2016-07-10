package com.lezhian.academiccircle.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hww on 2016/6/21.
 */
public class ToastUtil {

    public static void showShort(Context context, String msg) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
    }
}
