package com.lezhian.academiccircle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ${CQ} on 2016/7/4.
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    protected final Context mContext;

    /** 数据集合 */
    protected LinkedList<T> mItemLists = new LinkedList();
    /** 数据集合 */
    protected ArrayList<T> mArrayList = new ArrayList<>();

    /** 删除条目监听 */
    protected OnDeleteListener mOnDeleteListener;
    /** 条目操作的回调监听 */
    protected OnRecyclerViewListener onRecyclerViewListener;

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    public BaseRecyclerAdapter(Context mContext, ArrayList<T> mItemLists) {
        this.mContext = mContext;
        if (null != mItemLists && mItemLists.size() > 0) {
            this.mArrayList = mItemLists;
        }
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    /**
     * 获取数据集合
     *
     * @return mItemLists
     */
    public ArrayList<T> getItemLists() {
        return mArrayList;
    }

    /**
     * 获取数据集合
     *
     * @return mItemLists
     */
    public ArrayList<T> getItemArrayLists() {
        ArrayList<T> arrayList = new ArrayList<T>();
        for (T mItem:mArrayList) {
            arrayList.add(mItem);
        }
        return arrayList;
    }

    /**
     * 设置数据集合
     *
     * @param itemLists
     */
    public void setItemLists(ArrayList<T> itemLists) {
        this.mArrayList = null;
        this.mArrayList = itemLists;
        notifyDataSetChanged();
    }

    /**
     * 刷新数据集合
     *
     * @param itemLists
     */
    public void refreshItemList(List<T> itemLists) {
        setItemLists(itemLists);
    }

    /**
     * 设置数据集合
     *
     * @param itemLists
     */
    public void setItemLists(List<T> itemLists) {
        if(null == itemLists) return;
        this.mArrayList.clear();
        for (int i = 0; i < itemLists.size(); i++) {
            this.mArrayList.add(i, itemLists.get(i));
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据,默认添加到尾部
     *
     * @param listDatas
     */
    public void add(List<T> listDatas) {
        addToLast(listDatas);
    }

    /**
     * 添加数据到尾部
     *
     * @param listDatas
     */
    public void addToLast(List<T> listDatas) {
        if (listDatas!= null) {
            mArrayList.addAll(listDatas);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据到首部
     *
     * @param listDatas
     */
    public void addToFirst(List<T> listDatas) {
        if (listDatas!= null) {
            for (T data : listDatas) {
                mArrayList.add(0, data);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mArrayList.clear();
        notifyDataSetChanged();
    }

    /**
     * 根据下标删除对应项
     *
     * @param index
     */
    public void deleteForIndex(int index) {
        if ((mOnDeleteListener != null && !mOnDeleteListener
                .onDeleteItem(index)) || index >= getItemCount()) {
            return;
        }
        mArrayList.remove(index);
    }

    /**
     * 根据下标删除对应项
     *
     * @param indexs
     */
    public void deleteForIndex(int[] indexs) {
        if (indexs.length <= 0) {
            return;
        }
        Arrays.sort(indexs);
        for (int index = indexs.length - 1; index >= 0; index--) {
            deleteForIndex(indexs[index]);
        }
    }

    /**
     * 设置删除监听器
     *
     * @param onDeleteListener
     */
    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }

    /**
     * 获取当前上下文对象
     */
    protected Context getContext() {
        return this.mContext;
    }

    /**
     * 删除监听接口
     */
    public interface OnDeleteListener {
        boolean onDeleteItem(int index);
    }

    /**
     * 设置条目操作监听
     *
     * @param l
     */
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.onRecyclerViewListener = l;
    }

    /**
     * 条目操作回调监听接口
     */
    public interface OnRecyclerViewListener {
        /**
         * 条目点击的监听回调
         *
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 长按点击的监听回调
         *
         * @param position
         */
        boolean onItemLongClick(int position);
    }
}
