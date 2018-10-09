package com.vietinterview.getbee;

import android.app.Application;

import com.vietinterview.getbee.utils.SharedPrefUtils;


/**
 * Created by Envy 15T on 9/11/2015.
 */
public class GetBeeApplication extends Application {

    private static GetBeeApplication instance;
    private static SharedPrefUtils sharedPreferences;

    public GetBeeApplication() {
        instance = this;
    }

    public static GetBeeApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = new SharedPrefUtils(getApplicationContext());
    }

    public static SharedPrefUtils getSharedPreferences() {
        return sharedPreferences;
    }

}
