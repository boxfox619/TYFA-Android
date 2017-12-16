package com.boxfox.appjam14application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class Login2Activity extends AppCompatActivity {
    EditText login2_school_input, login2_grade_input, login2_class_input, login2_num_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        login2_num_input = findViewById(R.id.login2_num_input);
        login2_class_input = findViewById(R.id.login2_class_input);
        login2_grade_input = findViewById(R.id.login2_grade_input);
        login2_school_input = findViewById(R.id.login2_school_input);

        //학교 이름
        // 학년 반 번호

    }
}
