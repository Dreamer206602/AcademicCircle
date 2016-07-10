package com.lezhian.academiccircle.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by ${CQ} on 2016/7/6.
 */
public class SharedPrefsUtil {

    public final static String SETTING = "Setting";

    private final static String Splitter = "Splitter";

    public static void putValue(Context context, String key, String value)
    {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        SharedPreferences get = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String lastValue = get.getString(key, "");
        lastValue = lastValue + Splitter + value;
        if (lastValue.startsWith(Splitter)) {
            lastValue = lastValue.replaceFirst(Splitter, "");
        }
        sp.putString(key, lastValue);
        sp.commit();
    }

    public static void putValue(Context context, String key, ArrayList<String> stringArrayList)
    {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        SharedPreferences get = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String lastValue = get.getString(key, "");
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (!lastValue.contains(stringArrayList.get(i))) {
                lastValue = lastValue + Splitter + stringArrayList.get(i);
            }
        }
        if (lastValue.startsWith(Splitter)) {
            lastValue = lastValue.replaceFirst(Splitter, "");
        }
        sp.putString(key, lastValue);
        sp.commit();
    }

    public static void removeValue(Context context, String key, ArrayList<String> stringArrayList) {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        SharedPreferences get = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String lastValue = get.getString(key, "");
        if (!TextUtils.isEmpty(lastValue)) {
            for (int i = 0 ; i < stringArrayList.size() ; i++) {
                if (lastValue.contains(stringArrayList.get(i))) {
                    if (lastValue.contains(Splitter + stringArrayList.get(i))) {
                        String old = Splitter + stringArrayList.get(i);
                        lastValue = lastValue.replace(old, "");
                    } else if (lastValue.contains(Splitter + stringArrayList.get(i) + Splitter)) {
                        String old = Splitter + stringArrayList.get(i) + Splitter;
                        lastValue = lastValue.replace(old, "");
                    } else if (lastValue.contains(stringArrayList.get(i) + Splitter)) {
                        String old = stringArrayList.get(i) + Splitter;
                        lastValue = lastValue.replace(old, "");
                    } else {
                        lastValue = lastValue.replace(stringArrayList.get(i), "");
                    }
                }
            }
            removeValue(context, key);
        }
        LogUtils.i("kiki", "lastValue===" + lastValue);
        sp.putString(key, lastValue);
        sp.commit();
    }

    public static ArrayList<String> getValue(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, "");
        if (value.isEmpty())
            return new ArrayList<>();
        String[] valueArray = value.split(Splitter);
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < valueArray.length; i++)
            stringArrayList.add(valueArray[i]);
        return stringArrayList;
    }

    public static void removeValue(Context context, String key)
    {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();

        sp.remove(key);
        sp.commit();
    }

    public static void putValue(Context context, String key, int value)
    {
        SharedPreferences.Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }

    public static int getValue(Context context, String key, int defValue)
    {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public static boolean getValue(Context context, String key, boolean defValue)
    {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public static String getValue(Context context, String key, String defValue)
    {
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    public static boolean isArrayListContainItem(Context context, String arraykey, String itemId)
    {

        boolean bContains = false;

        ArrayList<String> qiKanArrayList = SharedPrefsUtil.getValue(context, arraykey);

        if (qiKanArrayList != null)
        {
            bContains = isArrayListContainsString(qiKanArrayList, itemId);

        }
        return bContains;

    }

    //判断字符串是否在数组中
    public static boolean isArrayListContainsString(ArrayList<String> arrayList, String value) {
        boolean ret = false;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(value)) {
                ret = true;
                break;
            }
        }
        return ret;
    }
}
