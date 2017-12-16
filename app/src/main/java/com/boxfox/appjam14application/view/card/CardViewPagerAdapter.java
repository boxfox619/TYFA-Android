package com.boxfox.appjam14application.view.card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boxfox.appjam14application.R;
import com.boxfox.appjam14application.data.RequestData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boxfox on 2017-12-16.
 */

public class CardViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<RequestData> dataList;

    public CardViewPagerAdapter(Context context) {
        mContext = context;
        this.dataList = new ArrayList<>();
    }

    public void removeAllItem() {
        dataList.removeAll(dataList);
        this.notifyDataSetChanged();
    }

    public void addItem(RequestData data) {
        dataList.add(data);
        this.notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        RequestData data = dataList.get(position);
        View view = new RequestCardView(mContext, false, data);
        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
