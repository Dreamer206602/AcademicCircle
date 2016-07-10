package com.lezhian.academiccircle.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.lezhian.academiccircle.app.BaseApplication;
import com.lezhian.academiccircle.fragment.BaseFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;

import butterknife.ButterKnife;



public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity{

    private long exitTime=0;
    public static Context context;
    public static Activity activity;
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        BaseApplication.getInstance().addActivity(this);
        context = BaseApplication.getContext();
        activity = this;
        mPresenter=getPresenter();
        initEventAndData(savedInstanceState);
        LogUtils.i("BaseActivity", getClass().getSimpleName());
    }


    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    //添加fragment
    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }



    void Toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }


    public static Context getContext() {
        return context;
    }

    public static Activity getActivity() {
        return activity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        BaseApplication.getInstance().removeActivity(this);
        if(mPresenter!=null)mPresenter.detachView();
    }

    protected abstract void initEventAndData(Bundle savedInstanceState);
    protected abstract T getPresenter();
    protected abstract int getLayoutId();

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode&& event.getAction()==KeyEvent.ACTION_DOWN) {
           // 两秒之内按返回键就会完全退出
            if((System.currentTimeMillis()-exitTime)>2000){
                Toast("再按一次退出程序");
                exitTime=System.currentTimeMillis();
            }else{
                BaseApplication.getInstance().exitApp(context);
            }
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public void startActivities(Class<? extends Activity> activity) {
        startActivity(new Intent(UIUtils.getActivity(), activity));
    }





}
