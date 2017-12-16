package com.boxfox.appjam14application.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.boxfox.appjam14application.R;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.card.RequestItemView;

import java.util.List;

/**
 * Created by boxfox on 2017-12-17.
 */

public class ItemListDialog extends AlertDialog {
    private LinearLayout layout_itemList;
    private View btn_confirm;

    private List<RequestItem> itemList;

    public ItemListDialog(@NonNull Context context, List<RequestItem> itemList) {
        super(context);
        this.itemList = itemList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_itemlist);
        btn_confirm = findViewById(R.id.btn_confirm);
        layout_itemList = findViewById(R.id.layout_itemList);
        btn_confirm.setOnClickListener(e->{
            this.dismiss();
        });
        initItems();
    }

    public void initItems() {
        for (RequestItem item : itemList) {
            layout_itemList.addView(new RequestItemView(getContext(), item));
        }
    }
}
