package com.vietinterview.getbee;

import android.app.Application;
import android.content.res.Configuration;

import com.bumptech.glide.request.target.ViewTarget;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.Locale;


/**
 * Created by Envy 15T on 9/11/2015.
 */
public class GetBeeApplication extends Application {
    public static String sDefSystemLanguage;
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
        sDefSystemLanguage = Locale.getDefault().getLanguage();
        sharedPreferences = new SharedPrefUtils(getApplicationContext());
//        SharedPrefUtils.putString(AppConstant.LANGUAGE, "vi");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        sDefSystemLanguage = newConfig.locale.getLanguage();
    }

    public static SharedPrefUtils getSharedPreferences() {
        return sharedPreferences;
    }

}
