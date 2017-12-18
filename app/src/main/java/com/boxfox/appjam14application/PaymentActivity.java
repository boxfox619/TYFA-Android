package com.boxfox.appjam14application;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(PaymentActivity.this, "잠시만 기다려주세요...", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PaymentActivity.this, ConfirmActivity.class);
                        intent.putExtra("price", getIntent().getIntExtra("price", 3000));
                        intent.putExtra("tip", getIntent().getIntExtra("tip", 300));
                        intent.putExtra("data", getIntent().getStringExtra("data"));
                        intent.putExtra("priceType", getIntent().getStringExtra("priceType"));
                        startActivity(intent);
                        finish();
                    }
                }, 5000);
            }
        });



    }
}
