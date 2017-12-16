package com.boxfox.appjam14application;

import android.content.Context;
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

import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.card.CardViewPagerAdapter;
import com.boxfox.appjam14application.view.card.RequestCardView;

import java.util.ArrayList;
import java.util.List;

//모든 요청, 미리 결제된 요청, 현금 결제 요청, 대신 구매 요청
public class MainActivity extends AppCompatActivity {
    private LinearLayout layout_requestList, layout_requestList2;
    private ViewPager viewpager_requestList;

    private CardViewPagerAdapter adapter;

    private Spinner main_spinner;
    private String[] category = {"모든 요청", "미리 결제된 요청", "현금 결제 요청", "대신 구매 요청"};
    private String selected_category = "";
    private Button main_deliveryreq_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MyRequestActivity.class));
        setContentView(R.layout.activity_main);
        adapter = new CardViewPagerAdapter(this);
        layout_requestList = findViewById(R.id.layout_requestList);
        layout_requestList2 = findViewById(R.id.layout_requestList2);
        viewpager_requestList = findViewById(R.id.viewpager_requestList);
        viewpager_requestList.setAdapter(adapter);
        viewpager_requestList.setClipToPadding(false);
        viewpager_requestList.setPageMargin(-(int)getResources().getDimension(R.dimen.cardview_viewpager_margin));
        main_deliveryreq_button = findViewById(R.id.main_deliveryreq_button);
        main_deliveryreq_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreDetailActivity.class);
                startActivity(intent);
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

        RequestData data = RequestData.getDummyData();
        adapter.addItem(data);
        adapter.addItem(data);
        adapter.addItem(data);
        adapter.addItem(data);
        layout_requestList.addView(new RequestCardView(this, true, data));
        layout_requestList.addView(new RequestCardView(this, true, data));
        layout_requestList.addView(new RequestCardView(this, true, data));
        layout_requestList.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
        layout_requestList2.addView(new RequestCardView(this, true, data));
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
}
