package com.boxfox.appjam14application.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by boxfox on 2017-12-16.
 */

public class RequestItem {
    private String name;
    private int amount;

    public RequestItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static List<RequestItem> createFromJson(JSONObject menu) {
        List<RequestItem> itemList = new ArrayList();
        try {
            JSONObject object = new JSONObject(menu.toString());
            for (Iterator<String> it = menu.keys(); it.hasNext(); ) {
                String key = it.next();
                itemList.add(new RequestItem(key, object.getInt(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
