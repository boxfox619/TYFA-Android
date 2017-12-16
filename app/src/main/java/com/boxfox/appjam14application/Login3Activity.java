package com.boxfox.appjam14application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.boxfox.appjam14application.Login2Activity;
import com.boxfox.appjam14application.R;
import com.boxfox.appjam14application.data.UserData;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import io.realm.Realm;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class Login3Activity extends AppCompatActivity {

    RelativeLayout login3_main_layout;
    EditText login3_grade_input, login3_class_input, login3_num_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        String school_input = getIntent().getStringExtra("school_input");
        String token_input = getIntent().getStringExtra("token_input");
        String name_input = getIntent().getStringExtra("name_input");
        String profileUrl = getIntent().getStringExtra("profileUrl");
        login3_grade_input = findViewById(R.id.login3_grade_input);
        login3_class_input = findViewById(R.id.login3_class_input);
        login3_num_input = findViewById(R.id.login3_num_input);

        login3_main_layout = findViewById(R.id.login3_main_layout);
        login3_main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String grade_input = login3_grade_input.getText().toString();
                String class_input = login3_class_input.getText().toString();
                String num_input = login3_num_input.getText().toString();

                if(grade_input.equals("")||class_input.equals("")||num_input.equals("")){
                    Toast.makeText(getApplicationContext(),"학년, 반, 번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Ion.with(getApplicationContext( ))
                            .load(getString(R.string.url_serverHost)+getString(R.string.url_register))
                            .setBodyParameter("name", name_input)
                            .setBodyParameter("profileUrl", profileUrl)
                            .setBodyParameter("token", token_input)
                            .setBodyParameter("school", school_input)
                            .setBodyParameter("grade", grade_input)
                            .setBodyParameter("class", class_input)
                            .setBodyParameter("number", num_input)
                            .asString()
                            .setCallback(new FutureCallback<String>() {
                                @Override
                                public void onCompleted(Exception e, String result) {
                                    if (result != null) {
                                        Realm realm = Realm.getDefaultInstance();
                                        realm.beginTransaction();
                                        UserData userData = realm.createObject(UserData.class);
                                        userData.setName(name_input);
                                        userData.setAccessToken(token_input);
                                        userData.setGrade(Integer.valueOf(grade_input));
                                        userData.setProfileImageUrl(profileUrl);
                                        userData.setSchool(school_input);
                                        userData.setClazz(Integer.valueOf(class_input));
                                        userData.setNumber(Integer.valueOf(num_input));
                                        realm.commitTransaction();
                                        Intent intent = new Intent(Login3Activity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.d("DEBUG", e.toString());
                                    }

                                }
                            });
                }



            }
        });
    }

}

