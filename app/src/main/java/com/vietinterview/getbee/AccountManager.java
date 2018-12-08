package com.vietinterview.getbee;

import android.provider.Settings;
import android.text.TextUtils;

import com.vietinterview.getbee.constant.ApiConstantTest;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.SharedPrefUtils;
import com.google.gson.Gson;


/**
 * Created by User on 9/29/2015.
 */
public class AccountManager {

    private static final String KEY_USER_INFO = "user_info";
    private static final String KEY_ACCESS_TOKEN = "key_access_token";
    private static final String KEY_API = "API";


    private static UserInfoBean userInfoBean;
    private static ApiConstantTest apiConstantTest;
    private static String accessToken;

    private static String UDID;

    public static UserInfoBean getUserInfoBean() {
        if (userInfoBean == null && SharedPrefUtils.containKey(KEY_USER_INFO)) {
            final String json = SharedPrefUtils.getString(KEY_USER_INFO, null);
            userInfoBean = new Gson().fromJson(json, UserInfoBean.class);
        }
        return userInfoBean;
    }

    public static void setApiConstantTest(ApiConstantTest apiConstantTest) {
        AccountManager.apiConstantTest = apiConstantTest;
        String json = new Gson().toJson(apiConstantTest);
        SharedPrefUtils.putString(KEY_API, json);
    }

    public static ApiConstantTest getApiConstantTest() {
        if (apiConstantTest == null && SharedPrefUtils.containKey(KEY_API)) {
            final String json = SharedPrefUtils.getString(KEY_API, null);
            apiConstantTest = new Gson().fromJson(json, ApiConstantTest.class);
        }
        return apiConstantTest;
    }

    public static void setUserInfoBean(UserInfoBean userInfoBean) {
        AccountManager.userInfoBean = userInfoBean;
        if (!TextUtils.isEmpty(userInfoBean.access_token)) {
            accessToken = userInfoBean.access_token;
            storeAccessToken();
        }
        storeUserInfo();
    }

    private static void storeUserInfo() {
        String json = new Gson().toJson(userInfoBean);
        SharedPrefUtils.putString(KEY_USER_INFO, json);
    }

    public static String getAccessToken() {
        if (getUserInfoBean() != null) {
            accessToken = userInfoBean.access_token;
        }
        if (accessToken == null || accessToken.isEmpty()) {
            accessToken = SharedPrefUtils.getString(KEY_ACCESS_TOKEN, "");
        }
        return accessToken;
    }

    public static void setAccessToken(String token) {
        accessToken = token;
        storeAccessToken();
    }

    private static void storeAccessToken() {
        if (accessToken == null) {
            SharedPrefUtils.removeKey(KEY_ACCESS_TOKEN);
        } else {
            SharedPrefUtils.putString(KEY_ACCESS_TOKEN, accessToken);
        }
    }

    public static void logout() {
        accessToken = null;
        userInfoBean = null;
        SharedPrefUtils.removeKey(KEY_ACCESS_TOKEN);
        SharedPrefUtils.removeKey(KEY_USER_INFO);
    }

    public static String getUDID() {
        if (UDID == null) {
            //Test git master switch dev
            UDID = Settings.Secure.getString(GetBeeApplication.getInstance().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return UDID;
    }
}
