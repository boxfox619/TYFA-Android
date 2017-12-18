package com.boxfox.appjam14application.view.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

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
                .load(getContext().getString(R.string.url_serverHost) + url)
                .into((ImageView) findViewById(R.id.iv_first));
        //image load
    }

    private void initSecond(String name, String price, String url) {
        ((TextView) findViewById(R.id.tv_title2)).setText(name);
        ((TextView) findViewById(R.id.tv_price2)).setText(price);
        Glide.with(getContext())
                .load(getContext().getString(R.string.url_serverHost) + url)
                .into((ImageView) findViewById(R.id.iv_second));

    }

    public void setFirstOnClick(OnItemSelected click) {
        (findViewById(R.id.view_first)).setOnClickListener(view ->{
            click.selected(((TextView)findViewById(R.id.tv_title1)).getText().toString(), Integer.valueOf(((TextView)findViewById(R.id.tv_price1)).getText().toString().substring(0, ((TextView)findViewById(R.id.tv_price1)).getText().toString().length()-1)));
        });
    }

    public void setSecondOnClick(OnItemSelected click) {
        (findViewById(R.id.view_second)).setOnClickListener(view ->{
            click.selected(((TextView)findViewById(R.id.tv_title2)).getText().toString(), Integer.valueOf(((TextView)findViewById(R.id.tv_price2)).getText().toString().substring(0, ((TextView)findViewById(R.id.tv_price2)).getText().toString().length()-1)));
        });
    }

    public interface OnItemSelected {
        public void selected(String name, int price);
    }
}
