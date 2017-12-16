package com.boxfox.appjam14application.view.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.R;
import com.bumptech.glide.Glide;

/**
 * Created by boxfox on 2017-12-17.
 */

public class StoreCardItemView extends LinearLayout {
    public StoreCardItemView(Context context, String name, String price, String url, String name2, String price2, String url2) {
        super(context);
        initView();
        initFirst(name, price, url);
        initSecond(name2, price2, url2);
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.storedetail_list, this, false);
        addView(view);
    }

    private void initFirst(String name, String price, String url) {
        ((TextView) findViewById(R.id.tv_title1)).setText(name);
        ((TextView) findViewById(R.id.tv_price1)).setText(price);
        Glide.with(getContext())
                .load(url)
                .into((ImageView)findViewById(R.id.iv_first));
        //image load
    }

    private void initSecond(String name, String price, String url) {
        ((TextView) findViewById(R.id.tv_title2)).setText(name);
        ((TextView) findViewById(R.id.tv_price2)).setText(price);
        Glide.with(getContext())
                .load(url)
                .into((ImageView)findViewById(R.id.iv_second));

    }
}
