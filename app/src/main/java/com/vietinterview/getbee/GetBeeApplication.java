package com.vietinterview.getbee;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
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
        ViewTarget.setTagId(R.id.glide_tag);
        sharedPreferences = new SharedPrefUtils(getApplicationContext());
    }

    public static SharedPrefUtils getSharedPreferences() {
        return sharedPreferences;
    }

}
