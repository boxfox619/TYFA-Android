package com.boxfox.appjam14application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.SavedReqeustData;
import com.boxfox.appjam14application.data.UserData;
import com.boxfox.appjam14application.view.card.CardViewPagerAdapter;
import com.boxfox.appjam14application.view.card.RequestCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

//모든 요청, 미리 결제된 요청, 현금 결제 요청, 대신 구매 요청
public class MainActivity extends AppCompatActivity {
    private LinearLayout layout_requestList, layout_requestList2;
    private ViewPager viewpager_requestList;

    private CardViewPagerAdapter adapter;

    private List<String> list;

    private Spinner main_spinner;
    private String[] category = {"모든 요청", "미리 결제된 요청", "현금 결제 요청", "대신 구매 요청"};
    private String selected_category = "";
    private Button main_deliveryreq_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new CardViewPagerAdapter(this);
        list = new ArrayList<>();
        layout_requestList = findViewById(R.id.layout_requestList);
        layout_requestList2 = findViewById(R.id.layout_requestList2);
        viewpager_requestList = findViewById(R.id.viewpager_requestList);
        viewpager_requestList.setAdapter(adapter);
        viewpager_requestList.setClipToPadding(false);
        viewpager_requestList.setPageMargin(-(int) getResources().getDimension(R.dimen.cardview_viewpager_margin));
        main_deliveryreq_button = findViewById(R.id.main_deliveryreq_button);
        findViewById(R.id.iv_folder).setOnClickListener(v->{
            Intent intent = new Intent(this, MyRequestActivity.class);
            startActivity(intent);
        });
        main_deliveryreq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > 0) {
                    for (String calltoken : list) {
                        Realm realm =  Realm.getDefaultInstance();
                        realm.beginTransaction();
                        for(RequestData datas : adapter.getItems()){
                            if(datas.getItemToken().equals(calltoken)){
                                SavedReqeustData data22 =  realm.createObject(SavedReqeustData.class);
                                data22.setName(datas.getName());
                                data22.setSubInfo(datas.getSubInfo());
                                data22.setPrice(datas.getPrice());
                                data22.setTip(datas.getCost());
                                data22.setProfileUrl(datas.getProfileUrl());
                            }
                        }
                        realm.commitTransaction();
                    }
                    Toast.makeText(MainActivity.this, "수락하였습니다!", Toast.LENGTH_LONG).show();
                    loadRequestItems();
                } else {
                    Intent intent = new Intent(MainActivity.this, StoreDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
        main_spinner = findViewById(R.id.main_spinner);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(
                getApplicationContext(), R.layout.main_spinner_item, category
        );
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        main_spinner.setAdapter(dayAdapter);

        main_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_category = (String) adapterView.getSelectedItem().toString();
                Log.d("DEBUG", selected_category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loadRequestItems();
    }

    private void loadRequestItems() {
        adapter.removeAllItem();
        layout_requestList.removeAllViews();
        Ion.with(this)
                .load(getString(R.string.url_serverHost) + getString(R.string.url_jobList) + UserData.getDefaultUser().getAccessToken())
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
                                adapter.addItem(data);
                                RequestCardView cardView = new RequestCardView(MainActivity.this, true, data);
                                layout_requestList.addView(cardView);
                                cardView.setCheckedChangeListener(checked -> {
                                    if (checked) {
                                        list.add(cardView.getToken());
                                    } else {
                                        list.remove(cardView.getToken());
                                    }
                                    changeMode();
                                });
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }


    public void onBackPressed() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("정말 종료하시겠습니까?");
        alertBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertBuilder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertBuilder.show();
    }

    private void changeMode() {
        if (list.size() > 0) {
            main_deliveryreq_button.setText("배달 수락하기");
        } else {
            main_deliveryreq_button.setText("배달 요청하기");
        }
    }
}
