package com.boxfox.appjam14application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    LoginButton login_facebook;
    LinearLayout login_facebook_fake;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        login_facebook_fake = findViewById(R.id.login_facebook_fake);

        login_facebook = findViewById(R.id.login_facebook);
        callbackManager = CallbackManager.Factory.create();

        login_facebook_fake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.login_facebook_fake) {
                    login_facebook.performClick();
                }
            }
        });

        login_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("DEBUG", "success" + loginResult.getAccessToken().getToken());

                Ion.with(getApplicationContext())
                        .load("http://soylatte.kr:1208/auth/facebook/" + loginResult.getAccessToken().getToken())
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (result.equals("User Not Found")) {
                                    //가입창

                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(result);
                                        jsonObject.get("name");
                                        jsonObject.get("token");
                                        jsonObject.get("school");
                                        jsonObject.get("class");
                                        jsonObject.get("number");
                                        jsonObject.get("profileurl");
                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }

                                    Log.d("DEBUG", e.toString());
                                }

                            }
                        });
            }

            @Override
            public void onCancel() {
                Log.d("DEBUG", "취소하셨습니다.");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("DEBUG", error.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
