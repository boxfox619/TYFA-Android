package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.card.RequestCardView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

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
        /*Ion.with(this)
                .load(getString(R.string.url_serverHost) + getString(R.string.url_requestMyJob))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        //add

                    }
                });*/

        RequestData data = RequestData.getDummyData();
        RequestCardView view = new RequestCardView(this, false, data);
        view.setMyRequestMode();
        layout_cardList.addView(view);
        RequestCardView view2 = new RequestCardView(this, false, data);
        view2.setMyRequestMode();
        layout_cardList.addView(view2);
    }

    private List<RequestItem> createItemList() {
        List<RequestItem> list = new ArrayList();
        list.add(new RequestItem("가가난", 1));
        list.add(new RequestItem("가가22난", 10));
        list.add(new RequestItem("가가난", 23));
        list.add(new RequestItem("가ㅁㄴㅇㅁㄴㅇ", 31));
        return list;
    }

}
