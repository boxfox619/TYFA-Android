package com.boxfox.appjam14application.view.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.R;
import com.boxfox.appjam14application.data.RequestItem;

/**
 * Created by boxfox on 2017-12-16.
 */

public class RequestItemView extends LinearLayout {

    public RequestItemView(Context context, RequestItem item) {
        super(context);
        initView(item);
    }

    private void initView(RequestItem item) {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.layout_itemlist_item, this, false);
        addView(view);
        ((TextView)view.findViewById(R.id.tv_itemName)).setText(item.getName());
        ((TextView)view.findViewById(R.id.tv_itemCount)).setText(String.valueOf(item.getAmount()));
    }

}
