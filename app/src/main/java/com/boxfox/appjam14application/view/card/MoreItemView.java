package com.boxfox.appjam14application.view.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.R;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.dialog.ItemListDialog;

import java.util.List;

/**
 * Created by boxfox on 2017-12-16.
 */

public class MoreItemView extends LinearLayout {
    private List<RequestItem> itemList;

    public MoreItemView(Context context, List<RequestItem> itemList) {
        super(context);
        this.itemList = itemList;
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.layout_itemlist_more_btn, this, false);
        addView(view);
        ((TextView) findViewById(R.id.tv_label)).setText("+" + (itemList.size() - 3) + "개 메뉴");
        findViewById(R.id.tv_label).setOnClickListener(e->{
            new ItemListDialog(getContext(), itemList).show();
        });
    }
}
