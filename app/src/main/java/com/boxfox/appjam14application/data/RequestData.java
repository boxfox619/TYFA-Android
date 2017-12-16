package com.boxfox.appjam14application.data;

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


    //price - 주문 금액
    //cost - 심부름값
}
