package com.boxfox.appjam14application.data;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boxfox on 2017-12-16.
 */

public class RequestData {
    private String name, subInfo, profileUrl, paymentType, school, userToken, itemToken;
    private List<RequestItem> itemList;
    private int price, cost, clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(String subInfo) {
        this.subInfo = subInfo;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public List<RequestItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<RequestItem> itemList) {
        this.itemList = itemList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    public static RequestData getDummyData(){
        RequestData data = new RequestData();
        data.setSubInfo("3학년 1반");
        data.setName("가나달");
        data.setPaymentType("먼저결제");
        data.setCost(300);
        data.setPrice(3000);
        data.setProfileUrl("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/13892285_597933247053303_7922493801672574281_n.jpg?oh=de10d9f3bd8f068541ded5bf2e6a942f&oe=5A8CC89F");

        List<RequestItem> list = new ArrayList();
        list.add(new RequestItem("가가난", 1));
        list.add(new RequestItem("가가22난", 10));
        list.add(new RequestItem("가가난", 23));
        list.add(new RequestItem("가ㅁㄴㅇㅁㄴㅇ", 31));

        data.setItemList(list);
        return data;
    }

    public static RequestData fromJson(JSONObject object) throws JSONException {
        RequestData requestData = new RequestData();
        requestData.setSchool(object.getString("school"));
        requestData.clazz = Integer.valueOf(object.getString("class"));
        requestData.setProfileUrl(object.getString("profile_url"));
        requestData.price = Integer.valueOf(object.getString("price"));
        requestData.cost = Integer.valueOf(object.getString("tip"));
        requestData.setPaymentType(object.getString("type"));
        requestData.userToken = object.getString("userToken");
        requestData.itemToken = object.getString("callToken");
        requestData.setItemList(RequestItem.createFromJson(object.getJSONObject("menu")));
        return requestData;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public String getItemToken() {
        return itemToken;
    }

    public void setItemToken(String itemToken) {
        this.itemToken = itemToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    //price - 주문 금액
    //cost - 심부름값
}
