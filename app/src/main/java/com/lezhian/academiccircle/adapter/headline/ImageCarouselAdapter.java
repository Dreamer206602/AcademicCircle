package com.lezhian.academiccircle.adapter.headline;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.activity.headline.HeadLineDetailActivity;
import com.lezhian.academiccircle.app.AcademicDefines;
import com.lezhian.academiccircle.mvp.bean.headline.BannerBean;
import com.lezhian.academiccircle.network.Api;
import com.lezhian.academiccircle.utils.UIUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/6/27.
 * 轮播
 */
public class ImageCarouselAdapter extends PagerAdapter
{
    private Context mContext;
    private ArrayList<BannerBean> mArrayList = new ArrayList<>();

    public ImageCarouselAdapter(Context context) {
        this.mContext = context;
    }

    public ImageCarouselAdapter(Context context, ArrayList<BannerBean> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
    }

    public void addItems(List<BannerBean> arrayList) {
        if (null == mArrayList) {
            mArrayList = new ArrayList<>();
        }
        mArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount()
    {
        //设置成最大，使用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= mArrayList.size();
        if(position<0) {
            position = mArrayList.size() + position;
        }
        final int pos = position;
        View view = LayoutInflater.from(mContext).inflate(R.layout.bean_carousel, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.urlIV = (ImageView) view.findViewById(R.id.bc_iv_url);
        viewHolder.titleTV = (TextView) view.findViewById(R.id.bc_tv_title);
        viewHolder.curTV = (TextView) view.findViewById(R.id.bc_tv_cur);
        viewHolder.totalTV = (TextView) view.findViewById(R.id.bc_tv_total);

        if (null != mArrayList && mArrayList.size() > 0 && position < mArrayList.size()){
            viewHolder.titleTV.setText(mArrayList.get(position).getTitle());
            viewHolder.curTV.setText((position+1)+"");
            viewHolder.totalTV.setText("/" + mArrayList.size());
            String url = Api.BASE_API_HEAD_LINE.substring(0, Api.BASE_API_HEAD_LINE.length()-1) + mArrayList.get(position).getImageUrl();
            ImageLoader.getInstance().displayImage(url, viewHolder.urlIV);
        }
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if(null != vp) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtils.getActivity(), HeadLineDetailActivity.class);
                intent.putExtra(AcademicDefines.IntentParam_User1, mArrayList.get(pos).getArticleId());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader)
    {
        super.restoreState(state, loader);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        //Warning：不要在这里调用removeView
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object)
    {
        return super.getItemPosition(object);
    }

    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    class ViewHolder{
        ImageView urlIV;
        TextView titleTV;
        TextView curTV;
        TextView totalTV;
    }

}
