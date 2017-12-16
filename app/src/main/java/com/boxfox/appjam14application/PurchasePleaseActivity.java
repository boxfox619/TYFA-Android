package com.boxfox.appjam14application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by dsm2016 on 2017-12-17.
 */

public class PurchasePleaseActivity extends AppCompatActivity {

    RelativeLayout purchaseplease_layout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaseplease);
        purchaseplease_layout1 = findViewById(R.id.purchaseplease_layout1);
        purchaseplease_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchasePleaseActivity.this, ConfirmActivity.class);
                startActivity(intent);

            }
        });


    }
}
