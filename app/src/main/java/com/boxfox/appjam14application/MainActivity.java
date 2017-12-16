package com.boxfox.appjam14application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.card.RequestCardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsm2016 on 2017-12-16.
 */

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout_cardlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout_cardlist = findViewById(R.id.layout_cardlist);
        loadRequestList();
    }

    private void loadRequestList() {
        RequestData data = new RequestData();
        data.setCost(10);
        data.setName("기밍느");
        data.setPaymentType("asdasd");
        data.setProfileUrl("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/13892285_597933247053303_7922493801672574281_n.jpg?oh=de10d9f3bd8f068541ded5bf2e6a942f&oe=5A8CC89F");
        data.setSubInfo("3학년 1반");
        data.setItemList(createItemList());
        layout_cardlist.addView(new RequestCardView(this, true, data));
    }

    private List<RequestItem> createItemList() {
        List<RequestItem> list = new ArrayList();
        list.add(new RequestItem("12asfasv", 1));
        list.add(new RequestItem("ㅁㄴ암ㄴㄴ홈ㄴ ", 4));
        list.add(new RequestItem("ㅁㄴㅇㅁㄴ ", 6));
        list.add(new RequestItem("ㅁㄴㅇㅁㄴ ", 6));
        return list;
    }

}
