package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.data.SavedReqeustData;
import com.boxfox.appjam14application.data.UserData;
import com.boxfox.appjam14application.view.card.RequestCardView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyRequestActivity extends AppCompatActivity {
    private LinearLayout layout_cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request);

        findViewById(R.id.btn_back).setOnClickListener(e -> finish());
        layout_cardList = findViewById(R.id.layout_cardList);
        loadMyRequest();
    }

    public void loadMyRequest() {
        Realm realm =  Realm.getDefaultInstance();
        RealmResults<SavedReqeustData> rs = realm.where(SavedReqeustData.class).findAll();
        for(SavedReqeustData data : rs){
            RequestData coverData = new RequestData();
            coverData.setProfileUrl(data.getProfileUrl());
            coverData.setSubInfo(data.getSubInfo());
            coverData.setPaymentType("즉시 결제");
            coverData.setPrice(data.getPrice());
            coverData.setCost(data.getTip());
            coverData.setName(data.getName());
            RequestCardView view = new RequestCardView(MyRequestActivity.this, false, coverData);
            view.setMyRequestMode();
            layout_cardList.addView(view);
        }
    }

}
