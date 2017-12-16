package com.boxfox.appjam14application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    LoginButton login_facebook;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        login_facebook = findViewById(R.id.login_facebook);
        callbackManager = CallbackManager.Factory.create();
        login_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("DEBUG","success");

                Ion.with(getApplicationContext())
                        .load(getString(R.string.url_serverHost))
                        .setBodyParameter("facebook_token", loginResult.getAccessToken().getUserId())
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (result != null) {
                                    Intent intent = new Intent(LoginActivity.this, Login2Activity.class);
                                    startActivity(intent);
                                } else {
                                    Log.d("DEBUG", e.toString());
                                }

                            }
                        });


                finish();


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
