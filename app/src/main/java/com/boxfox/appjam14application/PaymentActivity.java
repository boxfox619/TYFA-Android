package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class PaymentActivity extends AppCompatActivity {
    //결재할때 카드 번호 , 카드 비밀번호 , 카드 만료기간 , 카드 사용자 생년월일
    private TextView tv_cardNumber, tv_password, tv_cardDuration, tv_birth;
    private Button btn_requestPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        btn_requestPayment.setOnClickListener(e -> requestPayment());

    }

    private String getCardNumber() {
        return tv_cardNumber.getText().toString();
    }

    private String getCardPassword() {
        return tv_password.getText().toString();
    }

    private String getCardDuration() {
        return tv_cardDuration.getText().toString();
    }

    private String getBirth() {
        return tv_birth.getText().toString();
    }

    public void requestPayment() {
        Ion.with(this)
                .load("http://example.com/thing.json")
                .setBodyParameter("user", "")
                .setBodyParameter("number", getCardNumber())
                .setBodyParameter("password", getCardPassword())
                .setBodyParameter("duration", getCardDuration())
                .setBodyParameter("birth", getBirth())
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                    }
                });
    }
}
