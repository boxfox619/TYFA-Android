package com.boxfox.appjam14application.data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boxfox on 2017-12-16.
 */

public class RequestData {
    private String name, subInfo, profileUrl, paymentType;
    private List<RequestItem> itemList;
    private int price, cost;

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
        data.setProfileUrl("");

        List<RequestItem> list = new ArrayList();
        list.add(new RequestItem("가가난", 1));
        list.add(new RequestItem("가가22난", 10));
        list.add(new RequestItem("가가난", 23));
        list.add(new RequestItem("가ㅁㄴㅇㅁㄴㅇ", 31));

        data.setItemList(list);
        return data;
    }

    //price - 주문 금액
    //cost - 심부름값
}
