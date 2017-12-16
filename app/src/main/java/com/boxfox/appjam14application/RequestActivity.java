package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
    }

    public void requestJob(){
        Ion.with(this)
                .load(getString(R.string.url_serverHost)+getString(R.string.url_requestJob))
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                    }
                });
    }
}
