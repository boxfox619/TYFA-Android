package com.boxfox.appjam14application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boxfox.appjam14application.data.UserData;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import io.realm.Realm;

import static com.boxfox.appjam14application.data.UserData.getDefaultAccessToken;

public class PaymentActivity extends AppCompatActivity {
    //결재할때 카드 번호 , 카드 비밀번호 , 카드 만료기간 , 카드 사용자 생년월일
    private EditText payment_cardnum_input1, payment_cardnum_input2, payment_cardnum_input3, payment_cardnum_input4, payment_enddate_input1;
    private EditText payment_enddate_input2, payment_cardpw_input1, payment_cardpw_input2, payment_birth_input1;
    private RelativeLayout payment_layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        payment_cardnum_input1 = findViewById(R.id.payment_cardnum_input1);
        payment_cardnum_input2 = findViewById(R.id.payment_cardnum_input2);
        payment_cardnum_input3 = findViewById(R.id.payment_cardnum_input3);
        payment_cardnum_input4 = findViewById(R.id.payment_cardnum_input4);
        payment_enddate_input1 = findViewById(R.id.payment_enddate_input1);
        payment_enddate_input2 = findViewById(R.id.payment_enddate_input2);
        payment_cardpw_input1 = findViewById(R.id.payment_cardpw_input1);
        payment_cardpw_input2 = findViewById(R.id.payment_cardpw_input2);
        payment_birth_input1 = findViewById(R.id.payment_birth_input1);

        String cardnum1 = payment_cardnum_input1.getText().toString();
        String cardnum2 = payment_cardnum_input2.getText().toString();
        String cardnum3 = payment_cardnum_input3.getText().toString();
        String cardnum4 = payment_cardnum_input4.getText().toString();
        String enddate1 = payment_enddate_input1.getText().toString();
        String enddate2 = payment_enddate_input2.getText().toString();
        String cardpw1 = payment_cardpw_input1.getText().toString();
        String cardpw2 = payment_cardpw_input2.getText().toString();
        String birth1 =  payment_birth_input1.getText().toString();


        payment_layout1 = findViewById(R.id.payment_layout1);

        payment_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ion.with(getApplicationContext())
                        .load(getString(R.string.url_serverHost)+getString(R.string.url_payment))
                        .setBodyParameter("cardnum", cardnum1+cardnum2+cardnum3+cardnum4)
                        .setBodyParameter("enddate",enddate1+enddate2 )
                        .setBodyParameter("class_input", cardpw1+cardpw2)
                        .setBodyParameter("num_input", birth1)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (result != null) {
                                    Intent intent = new Intent(PaymentActivity.this, ConfirmActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.d("DEBUG", e.toString());
                                }

                            }
                        });
            }
        });



    }
}
