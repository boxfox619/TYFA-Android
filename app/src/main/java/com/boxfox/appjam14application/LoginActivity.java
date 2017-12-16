package com.boxfox.appjam14application;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.boxfox.appjam14application.data.UserData;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {
    LoginButton login_facebook;
    LinearLayout login_facebook_fake;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            init_facebook_keyhash();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Realm.init(this);
        if (UserData.getDefaultUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        login_facebook_fake = findViewById(R.id.login_facebook_fake);
        login_facebook = findViewById(R.id.login_facebook);
        callbackManager = CallbackManager.Factory.create();
        login_facebook.setReadPermissions("public_profile", "user_friends");
        login_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("DEBUG", "success" + loginResult.getAccessToken().getToken());
                Profile profile = Profile.getCurrentProfile();
                String name = profile.getName();
                String profileUrl = profile.getProfilePictureUri(300, 300).toString();
                String token = loginResult.getAccessToken().getToken();
                Ion.with(getApplicationContext())
                        .load(getString(R.string.url_serverHost) + getString(R.string.url_login) + token)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (result == null) return;
                                if (result.equals("User Not Found")) {
                                    Intent intent = new Intent(LoginActivity.this, Login2Activity.class);
                                    intent.putExtra("token_input", token);
                                    intent.putExtra("name_input", name);
                                    intent.putExtra("profileUrl", profileUrl);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    try {
                                        Realm realm = Realm.getDefaultInstance();
                                        realm.beginTransaction();
                                        JSONObject jsonObject = new JSONObject(result);
                                        UserData userData = realm.createObject(UserData.class);
                                        userData.setName(jsonObject.getString("name"));
                                        userData.setAccessToken(jsonObject.getString("token"));
                                        userData.setGrade(Integer.valueOf(jsonObject.getString("grade")));
                                        userData.setProfileImageUrl(jsonObject.getString("profileUrl"));
                                        userData.setSchool(jsonObject.getString("school"));
                                        userData.setClazz(Integer.valueOf(jsonObject.getString("class")));
                                        userData.setNumber(Integer.valueOf(jsonObject.getString("number")));
                                        realm.commitTransaction();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                            }
                        });
            }

            @Override
            public void onCancel() {
                Log.e("DEBUG", "취소하셨습니다.");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("DEBUG", error.toString());
            }
        });
        login_facebook_fake.setOnClickListener(e->{
            login_facebook.performClick();
        });
    }

    public void init_facebook_keyhash() throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo info = getPackageManager().getPackageInfo("com.boxfox.appjam14application", PackageManager.GET_SIGNATURES);

        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            Log.d("boxfox", "facebook_keyhash : " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
