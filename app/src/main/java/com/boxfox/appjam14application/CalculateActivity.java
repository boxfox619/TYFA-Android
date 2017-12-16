package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.card.RequestItemView;

public class CalculateActivity extends AppCompatActivity {
    private LinearLayout btn_payRight, btn_payMoney, btn_payPlease;
    private LinearLayout selectedView, layout_itemList;
    private String selectedPriceType;

    private EditText et_time, et_location, et_content;
    private View btn_modify_location, btn_modify_time, btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        btn_payRight = findViewById(R.id.btn_payRight);
        btn_payMoney = findViewById(R.id.btn_payMoney);
        btn_payPlease = findViewById(R.id.btn_payPlease);
        layout_itemList = findViewById(R.id.layout_itemList);

        et_time = findViewById(R.id.et_time);
        et_content = findViewById(R.id.et_content);
        et_location = findViewById(R.id.et_location);
        btn_modify_location = findViewById(R.id.btn_modify_location);
        btn_modify_time = findViewById(R.id.btn_modify_time);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(view -> {
            submit();
        });

        findViewById(R.id.btn_back).setOnClickListener(view -> {
            finish();
        });


        btn_modify_time.setOnClickListener(view -> {
            et_time.requestFocus();
        });
        btn_modify_location.setOnClickListener(view -> {
            et_location.requestFocus();
        });

        selectedView = btn_payRight;

        btn_payRight.setOnClickListener(view -> setSelectedView(view));
        btn_payMoney.setOnClickListener(view -> setSelectedView(view));
        btn_payPlease.setOnClickListener(view -> setSelectedView(view));

        loadItems();
    }

    private void setSelectedView(View view) {
        ((LinearLayout) view).getChildAt(0).setBackground(getResources().getDrawable(R.drawable.circle_active));
        ((TextView) ((LinearLayout) view).getChildAt(1)).setTextColor(getResources().getColor(R.color.colorPrimary));
        this.selectedPriceType = ((TextView) ((LinearLayout) view).getChildAt(1)).getText().toString();
        selectedView.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.circle_deactive));
        ((TextView) selectedView.getChildAt(1)).setTextColor(getResources().getColor(R.color.colorDefultColor));
        this.selectedView = (LinearLayout) view;
    }

    private void loadItems() {
        layout_itemList.addView(new RequestItemView(this, new RequestItem("asdas", 123)));
    }

    private void submit() {
        String time = et_time.getText().toString();
        String location = et_location.getText().toString();
        String content = et_content.getText().toString();
        String priceType = this.selectedPriceType;


    }
}
