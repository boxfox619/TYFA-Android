package com.boxfox.appjam14application.data;

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
}
