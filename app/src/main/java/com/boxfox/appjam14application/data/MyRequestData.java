package com.boxfox.appjam14application.data;

import io.realm.RealmObject;

/**
 * Created by boxfox on 2017-12-17.
 */

public class MyRequestData extends RealmObject {
    private String priceType, data;
    private int price, tip;

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
