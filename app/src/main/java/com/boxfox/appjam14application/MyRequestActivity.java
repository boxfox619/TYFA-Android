package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.data.UserData;
import com.boxfox.appjam14application.view.card.RequestCardView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        Ion.with(this)
                .load(getString(R.string.url_serverHost) + getString(R.string.url_myJob)+ UserData.getDefaultUser().getAccessToken())
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (result == null) return;
                        try {
                            JSONArray arr = new JSONArray(result);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject object = (JSONObject) arr.get(i);
                                RequestData data = RequestData.fromJson(object);
                                RequestCardView view = new RequestCardView(MyRequestActivity.this, false, data);
                                view.setMyRequestMode();
                                layout_cardList.addView(view);
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }

}
