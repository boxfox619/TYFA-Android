package com.boxfox.appjam14application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.boxfox.appjam14application.view.store.StoreCardItemView;
import com.boxfox.appjam14application.view.store.StoreDetailItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class StoreDetailActivity extends AppCompatActivity {
    ArrayList<StoreDetailItem> storeDetailItems = new ArrayList<>();
    ListView storedetail_others_category;
    StoreListAdapter storeListAdapter;
    private LinearLayout storedetail_bread_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        storedetail_others_category = findViewById(R.id.storedetail_others_category);
        ViewGroup.LayoutParams params = storedetail_others_category.getLayoutParams();
        params.height = 5 * 100;
        storedetail_others_category.setLayoutParams(params);
        storeListAdapter = new StoreListAdapter();
        storedetail_others_category.setAdapter(storeListAdapter);
        storedetail_bread_category = findViewById(R.id.storedetail_bread_category);
        loadItems();
    }

    private void loadItems() {
        Ion.with(this)
                .load(getString(R.string.url_serverHost) + getString(R.string.url_menu))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject object = result.get(i).getAsJsonObject();
                            String name = object.get("name").getAsString();
                            int price = object.get("price").getAsInt();
                            String imageUrl = object.get("image").getAsString();
                            if (i > 5) {
                                storeListAdapter.addItem(new StoreDetailItem(name, price + "원"));
                                ViewGroup.LayoutParams params = storedetail_others_category.getLayoutParams();
                                params.height = storedetail_others_category.getChildCount() * 100;
                                storedetail_others_category.setLayoutParams(params);
                                storedetail_others_category.requestLayout();
                            } else {
                                JsonObject object2 = result.get(++i).getAsJsonObject();
                                String name2 = object2.get("name").getAsString();
                                int price2 = object2.get("price").getAsInt();
                                String imageUrl2 = object2.get("image").getAsString();
                                View view = new StoreCardItemView(StoreDetailActivity.this, name, price + "원", imageUrl, name2, price2 + "원", imageUrl2);
                                storedetail_bread_category.addView(view);
                            }
                        }
                    }
                });
    }

}
