package com.boxfox.appjam14application.data;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by boxfox on 2017-12-16.
 */

public class UserData extends RealmObject {
    public static String PARAM_NAME = "token";

    private String accessToken;
    private String name;
    private String profileImageUrl;
    private int graduation;
    private String school;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static String getDefaultAccessToken() {
        return Realm.getDefaultInstance().where(UserData.class).findFirst().getAccessToken();
    }
}
