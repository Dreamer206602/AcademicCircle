package com.lezhian.academiccircle.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.BaseActivity;
import com.lezhian.academiccircle.adapter.headline.ThemeAdapter;
import com.lezhian.academiccircle.adapter.headline.ThemeChildAdapter;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.app.BaseApplication;
import com.lezhian.academiccircle.mvp.bean.headline.ThemeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

/**
 * Created by hww on 2016/6/20.
 */
public class UIUtils {

    public static Context getContext() {
        return BaseActivity.getContext();
    }

    public static Activity getActivity() {
        return BaseActivity.getActivity();
    }


    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }


    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    public static int getScreenWidth() {
        WindowManager wManager = (WindowManager) BaseApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        int width = wManager.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getScreenHeight() {
        WindowManager wManager = (WindowManager) BaseApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        int width = wManager.getDefaultDisplay().getHeight();
        return width;
    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for(int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


    public static Dialog createThemeChooseDialog(Context context, Handler handler, String title, final ThemeChildAdapter adapter) {

        final Handler handlerFinal = handler;

        final Dialog dialog = new Dialog(context, R.style.customdialog);// 创建自定义样式dialog

        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dlg_theme, null);// 得到加载view

        TextView textView = (TextView) layout.findViewById(R.id.dt_tv_title);
        textView.setText(title);

        GridView gridView = (GridView) layout.findViewById(R.id.dt_gv_gridview);
        gridView.setAdapter(adapter);

        LinearLayout cancleButton = (LinearLayout) layout.findViewById(R.id.dt_ll_cancel);
        Button okButton = (Button) layout.findViewById(R.id.dt_btn_ok);

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = AcademicDefines.Handler_DialogCancleButtonClicked;
                msg.obj = dialog;
                handlerFinal.sendMessage(msg);
                dialog.hide();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Message msg = new Message();
                msg.what = AcademicDefines.Handler_DialogOkButtonClicked;
                msg.obj = dialog;
                Bundle bundle = new Bundle();
                bundle.putSerializable(AcademicDefines.Const_Serializable_Key, adapter.getAllArrayList());
                msg.setData(bundle);
                handlerFinal.sendMessage(msg);
            }
        });


        dialog.setCancelable(false);// 不可以用“返回键”取消
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        return dialog;

    }
}
