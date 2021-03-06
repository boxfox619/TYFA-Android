package com.boxfox.appjam14application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.card.RequestItemView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.realm.Realm;

public class CalculateActivity extends AppCompatActivity {
    private LinearLayout btn_payRight, btn_payMoney, btn_payPlease;
    private LinearLayout selectedView, layout_itemList;
    private String selectedPriceType = "바로 결제";

    private TextView tv_guide, tv_submit, tv_price, tv_price2, tv_cost;

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
        tv_guide = findViewById(R.id.tv_guide);
        tv_submit = findViewById(R.id.tv_submit);
        tv_price = findViewById(R.id.tv_price);
        tv_price2 = findViewById(R.id.tv_price2);
        tv_cost = findViewById(R.id.tv_cost);

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


        tv_price.setText(getTotalPrice()+"원");
        tv_price2.setText(getPrice()+"원");
        tv_cost.setText("+ (요청료) "+getTip()+"원");
        loadItems();
    }

    private void setSelectedView(View view) {
        ((LinearLayout) view).getChildAt(0).setBackground(getResources().getDrawable(R.drawable.circle_active));
        ((TextView) ((LinearLayout) view).getChildAt(1)).setTextColor(getResources().getColor(R.color.colorPrimary));
        this.selectedPriceType = ((TextView) ((LinearLayout) view).getChildAt(1)).getText().toString();
        selectedView.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.circle_deactive));
        ((TextView) selectedView.getChildAt(1)).setTextColor(getResources().getColor(R.color.colorDefultColor));
        this.selectedView = (LinearLayout) view;

        switch (selectedPriceType){
            case "바로 결제":
                tv_guide.setText("");
                tv_submit.setText("결제하기");
                break;
            case "현금 결제":
                tv_guide.setText("현금은 수령시 배달 학생에게 지급해주세요!");
                tv_submit.setText("요청하기");
                break;
            case "대신 구매":
                tv_guide.setText("일정기간 후 꼭 대금을 지급해야합니다!");
                tv_submit.setText("요청하기");
                break;
        }
    }

    private void loadItems() {
        try {
            JSONObject object = new JSONObject(getIntent().getStringExtra("data"));
            for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                String key = it.next();
                layout_itemList.addView(new RequestItemView(this, new RequestItem(key, object.getInt(key))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int getPrice(){
        return Integer.valueOf(getIntent().getStringExtra("price").substring(0, getIntent().getStringExtra("price").length()-1));
    }

    private int getTip(){
        return getPrice()/10;
    }

    private int getTotalPrice(){
        return getPrice() + getTip();
    }

    private void submit() {
        String time = et_time.getText().toString();
        String location = et_location.getText().toString();
        String content = et_content.getText().toString();
        String priceType = this.selectedPriceType;

        switch (selectedPriceType){
            case "바로 결제":
                Intent intent = new Intent(CalculateActivity.this, PaymentActivity.class);
                intent.putExtra("price", getPrice());
                intent.putExtra("tip", getTip());
                intent.putExtra("data", getIntent().getStringExtra("data"));
                intent.putExtra("priceType", priceType);
                startActivity(intent);
                break;
            case "현금 결제":
                Intent intent3 = new Intent(CalculateActivity.this, PurchasePleaseActivity.class);
                intent3.putExtra("price", getPrice());
                intent3.putExtra("tip", getTip());
                intent3.putExtra("data", getIntent().getStringExtra("data"));
                intent3.putExtra("priceType", priceType);
                startActivity(intent3);
                break;
            case "대신 구매":
                Intent intent2 = new Intent(CalculateActivity.this, PurchasePleaseActivity.class);
                intent2.putExtra("price", getPrice());
                intent2.putExtra("tip", getTip());
                intent2.putExtra("data", getIntent().getStringExtra("data"));
                intent2.putExtra("priceType", priceType);
                startActivity(intent2);
                break;
        }


    }
}
