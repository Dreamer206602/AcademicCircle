package com.lezhian.academiccircle.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.fragment.AcademicCircleFragment;
import com.lezhian.academiccircle.fragment.HeadlineFragment;
import com.lezhian.academiccircle.fragment.MineFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    public static final String fragment1Tag = "fragment1";
    public static final String fragment2Tag = "fragment2";
    public static final String fragment3Tag = "fragment3";
    @Bind(R.id.activity_group_radioGroup)
    RadioGroup mActivityGroupRadioGroup;

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mActivityGroupRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment1 = fm.findFragmentByTag(fragment1Tag);
                Fragment fragment2 = fm.findFragmentByTag(fragment2Tag);
                Fragment fragment3 = fm.findFragmentByTag(fragment3Tag);
                if (fragment1 != null) {
                    ft.hide(fragment1);
                }
                if (fragment2 != null) {
                    ft.hide(fragment2);
                }
                if (fragment3 != null) {
                    ft.hide(fragment3);
                }

                switch (checkedId) {
                    case R.id.rb_headline:
                        if (fragment1 == null) {
                            fragment1 =  HeadlineFragment.getInstance();
                            ft.add(R.id.container, fragment1, fragment1Tag);
                        } else {
                            ft.show(fragment1);
                        }
                        break;
                    case R.id.rb_academicCircle:
                        if (fragment2 == null) {
                            fragment2 = AcademicCircleFragment.getInstance();
                            ft.add(R.id.container, fragment2, fragment2Tag);
                        } else {
                            ft.show(fragment2);
                        }
                        break;
                    case R.id.rb_mine:
                        if (fragment3 == null) {
                            fragment3 =  MineFragment.getInstance();
                            ft.add(R.id.container, fragment3,
                                    fragment3Tag);
                        } else {
                            ft.show(fragment3);
                        }
                        break;
                    default:
                        break;
                }
                ft.commit();
            }
        });
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment =  HeadlineFragment.getInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment1Tag).commit();
        }
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < mActivityGroupRadioGroup.getChildCount(); i++) {
            RadioButton mTab = (RadioButton) mActivityGroupRadioGroup.getChildAt(i);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag((String) mTab.getTag());
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment != null) {
                if (!mTab.isChecked()) {
                    ft.hide(fragment);
                }
            }
            ft.commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
