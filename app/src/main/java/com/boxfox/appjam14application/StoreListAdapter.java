package com.boxfox.appjam14application;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.boxfox.appjam14application.view.store.StoreDetailItem;
import com.boxfox.appjam14application.view.store.StoreDetailItemView;

import java.util.ArrayList;

/**
 * Created by dsm2016 on 2017-10-07.
 */

public class StoreListAdapter extends BaseAdapter {

    ArrayList<StoreDetailItem> items = new ArrayList<>();


    public void addItem(StoreDetailItem diary_item) {
        items.add(diary_item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Context context = parent.getContext();

        final StoreDetailItemView storeDetailItemView = new StoreDetailItemView(context);
        final StoreDetailItem diary_item = items.get(position);
        storeDetailItemView.setPriceItem(diary_item.getItem_price());
        storeDetailItemView.setTitleItem(diary_item.getItem_title());
        return storeDetailItemView;
    }
}
