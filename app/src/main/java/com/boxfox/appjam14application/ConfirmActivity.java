package com.boxfox.appjam14application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boxfox.appjam14application.data.MyRequestData;

import io.realm.Realm;

public class ConfirmActivity extends AppCompatActivity {

    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int price = getIntent().getIntExtra("price", 3000);
                int tip = getIntent().getIntExtra("tip", 3000);
                String data = getIntent().getStringExtra("data");
                String priceType = getIntent().getStringExtra("priceType");
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                MyRequestData data2 = realm.createObject(MyRequestData.class);
                data2.setData(data);
                data2.setPrice(price);
                data2.setTip(tip);
                data2.setPriceType(priceType);
                realm.commitTransaction();
                Intent intent =new Intent(ConfirmActivity.this, MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });
    }
}
