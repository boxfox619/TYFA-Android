package com.boxfox.appjam14application.view.store;


public class StoreDetailItem {


    private String item_title, item_price;

    public StoreDetailItem(String item_title, String item_price) {
        this.item_title = item_title;
        this.item_price = item_price;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }
}
