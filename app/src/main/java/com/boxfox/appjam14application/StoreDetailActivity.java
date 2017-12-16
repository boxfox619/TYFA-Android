package com.boxfox.appjam14application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.boxfox.appjam14application.view.store.StoreCardItemView;
import com.boxfox.appjam14application.view.store.StoreDetailItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class StoreDetailActivity extends AppCompatActivity {
    private JSONObject object = new JSONObject();
    private Map<String, Integer> priceMap = new HashMap<>();
    private int totalPrice;
    ArrayList<StoreDetailItem> storeDetailItems = new ArrayList<>();
    ListView storedetail_others_category;
    StoreListAdapter storeListAdapter;
    private LinearLayout storedetail_bread_category;
    private TextView tv_totalPrice;
    private View main_deliveryreq_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        main_deliveryreq_button = findViewById(R.id.main_deliveryreq_button);
        storedetail_others_category = findViewById(R.id.storedetail_others_category);
        ViewGroup.LayoutParams params = storedetail_others_category.getLayoutParams();
        params.height = 5 * 100;
        storedetail_others_category.setLayoutParams(params);
        storeListAdapter = new StoreListAdapter();
        storedetail_others_category.setAdapter(storeListAdapter);
        storedetail_bread_category = findViewById(R.id.storedetail_bread_category);
        tv_totalPrice = findViewById(R.id.tv_totalPrice);
        main_deliveryreq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreDetailActivity.this, CalculateActivity.class);
                startActivity(intent);
            }
        });
        loadItems();
    }

    private void loadItems() {
        Ion.with(this)
                .load(getString(R.string.url_serverHost) + getString(R.string.url_menu))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (result == null) return;
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
                                StoreCardItemView view = new StoreCardItemView(StoreDetailActivity.this, name, price + "원", imageUrl, name2, price2 + "원", imageUrl2);
                                storedetail_bread_category.addView(view);
                                view.setFirstOnClick(new StoreCardItemView.OnItemSelected() {
                                    @Override
                                    public void selected(String name, int price) {
                                        addItem(name, price);
                                    }
                                });
                                view.setSecondOnClick(new StoreCardItemView.OnItemSelected() {
                                    @Override
                                    public void selected(String name, int price) {
                                        addItem(name, price);
                                    }
                                });
                            }
                        }
                    }
                });
    }

    private void addItem(String name, int price) {
        priceMap.put(name, price);
        int count = 0;
        try {
            if (object.get(name) != null) {
                count = object.getInt(name);
            }
            object.put(name, ++count);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        totalPrice += price;
        tv_totalPrice.setText(totalPrice + "원");
    }

    private void removeitem(String name, int price) {
        priceMap.put(name, price);
        int count = 0;
        try {
            if (object.get(name) != null) {
                count = object.getInt(name);
            }
            if (count > 0) {
                object.put(name, --count);
                totalPrice -= price;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_totalPrice.setText(totalPrice + "원");
    }

}
