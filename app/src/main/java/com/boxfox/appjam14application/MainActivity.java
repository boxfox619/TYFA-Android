package com.boxfox.appjam14application;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Created by dsm2016 on 2017-12-16.
 */
//모든 요청, 미리 결제된 요청, 현금 결제 요청, 대신 구매 요청
public class MainActivity extends AppCompatActivity {

    Spinner main_spinner;
    String[] category = {"모든 요청", "미리 결제된 요청", "현금 결제 요청", "대신 구매 요청"};
    String selected_category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
}
