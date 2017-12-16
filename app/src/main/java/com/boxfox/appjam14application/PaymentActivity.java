package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class PaymentActivity extends AppCompatActivity {
    //결재할때 카드 번호 , 카드 비밀번호 , 카드 만료기간 , 카드 사용자 생년월일
    private EditText et_cardNumber, et_password, et_cardDurationMonth, et_cardDurationYear, et_birth;
    private Button btn_requestPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        et_cardDurationMonth = findViewById(R.id.et_cardDurationMonth);
        et_cardDurationYear = findViewById(R.id.et_cardDurationYear);
        et_password = findViewById(R.id.et_cardPassword);
        et_cardNumber = findViewById(R.id.et_cardNumber);
        et_birth = findViewById(R.id.et_birth);

        btn_requestPayment = findViewById(R.id.btn_requestPayment);

        btn_requestPayment.setOnClickListener(e -> requestPayment());

    }

    private String getCardNumber() {
        return et_cardNumber.getText().toString();
    }

    private String getCardPassword() {
        return et_password.getText().toString();
    }

    private String getCardDuration() {
        String month = et_cardDurationMonth.getText().toString();
        String year = et_cardDurationYear.getText().toString();
        return year + "-" + month;
    }

    private String getBirth() {
        return et_birth.getText().toString();
    }

    public void requestPayment() {
        Ion.with(this)
                .load(getString(R.string.url_serverHost) + getString(R.string.url_requestPayment))
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
