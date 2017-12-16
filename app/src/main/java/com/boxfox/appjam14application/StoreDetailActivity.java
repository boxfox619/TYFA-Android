package com.boxfox.appjam14application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class StoreDetailActivity extends AppCompatActivity {
    ArrayList<StoreDetailItem> storeDetailItems = new ArrayList<>();
    ListView storedetail_others_category;
    StoreListAdapter storeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        storedetail_others_category = findViewById(R.id.storedetail_others_category);
        ViewGroup.LayoutParams params = storedetail_others_category.getLayoutParams();
        params.height =  5 * 100;
        storedetail_others_category.setLayoutParams(params);
        storeListAdapter = new StoreListAdapter();

        storeDetailItems.add(0, new StoreDetailItem("기저귀", "천만원"));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storeListAdapter.addItem(new StoreDetailItem(storeDetailItems.get(0).getItem_title(), storeDetailItems.get(0).getItem_price()));
        storedetail_others_category.setAdapter(storeListAdapter);




    }

}
