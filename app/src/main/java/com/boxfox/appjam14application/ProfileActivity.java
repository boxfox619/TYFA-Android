package com.boxfox.appjam14application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.boxfox.appjam14application.data.UserData;

public class ProfileActivity extends AppCompatActivity {
    private TextView tv_name, tv_school, tv_grade;
    private ImageView iv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


    }

    private void initUserProfile(){
        UserData data = UserData.getDefault();
        tv_name.setText(data.getName());
        tv_grade.setText(data.getGrade());
        tv_school.setText(data.getSchool());
        AQuery aq = new AQuery(this);
        aq.id(R.id.iv_profileImage).image(data.getProfileImageUrl());
    }
}
