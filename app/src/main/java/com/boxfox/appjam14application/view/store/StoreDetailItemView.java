package com.boxfox.appjam14application.view.store;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.R;


public class StoreDetailItemView extends LinearLayout {
    TextView item_title, item_price;

    public StoreDetailItemView(Context context) {
        super(context);
        init(context);
    }

    public StoreDetailItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.storedetail_list_item, this, true);
        item_title = (TextView) findViewById(R.id.item_title);
        item_price = (TextView) findViewById(R.id.item_price);

    }


    public void setTitleItem(String title) {
        item_title.setText(title);
    }

    public void setPriceItem(String price) {
        item_price.setText(price);
    }

}
