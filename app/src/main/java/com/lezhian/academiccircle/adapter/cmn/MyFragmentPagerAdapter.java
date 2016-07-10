package com.lezhian.academiccircle.adapter.cmn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ${CQ} on 2016/6/27.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mArrayList;
    private String[] mTitles;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mArrayList = new ArrayList<>();
    }

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.mArrayList = list;
    }

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] title) {
        super(fm);
        this.mArrayList = list;
        this.mTitles = title;
    }

    public void addItems(ArrayList<Fragment> fragments) {
        if (null == mArrayList) mArrayList = new ArrayList<>();
        mArrayList.addAll(fragments);
        notifyDataSetChanged();
    }

    public void addTitles(String[] title) {
        if (null == mTitles)
            mTitles = new String[title.length];
        for (int i = 0 ; i < title.length ; i++) {
            mTitles[i] = title[i];
        }
    }

    public void removeAllItems() {
        mArrayList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (null != mTitles && mTitles.length > 0) {
            return mTitles[position];
        } else {
            return "";
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
