package com.boxfox.appjam14application.view.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.R;

/**
 * Created by boxfox on 2017-12-16.
 */

public class MoreItemView extends LinearLayout {
    public MoreItemView(Context context, int itemCount) {
        super(context);
        initView(itemCount);
    }

    private void initView(int itemCount) {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.layout_itemlist_more_btn, this, false);
        addView(view);
        ((TextView) findViewById(R.id.tv_label)).setText("+" + itemCount + "개 메뉴");
    }
}
