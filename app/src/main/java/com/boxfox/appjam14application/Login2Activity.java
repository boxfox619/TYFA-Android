package com.boxfox.appjam14application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class Login2Activity extends AppCompatActivity {
    RelativeLayout login2_login3_layout;
    EditText login2_school_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MainActivity.class));
        setContentView(R.layout.activity_login2);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(login2_school_input, InputMethodManager.SHOW_IMPLICIT);

        login2_school_input = findViewById(R.id.login2_school_input);
        login2_login3_layout = findViewById(R.id.login2_login3_layout);
        login2_login3_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String school = login2_school_input.getText().toString();
                if (school.equals("")) {
                    Toast.makeText(getApplicationContext(),"학교를 입력해주세요.",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Login2Activity.this, Login3Activity.class);
                    intent.putExtra("school_input", school);
                    startActivity(intent);
                }
            }
        });

    }
}
