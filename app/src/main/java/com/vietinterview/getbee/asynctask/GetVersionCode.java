package com.vietinterview.getbee.asynctask;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.vietinterview.getbee.BuildConfig;
import com.vietinterview.getbee.utils.DebugLog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by hiepnguyennghia on 1/9/19.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetVersionCode extends AsyncTask<Void, String, String> {
    private Context mContext;

    public GetVersionCode(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String newVersion = null;
        try {
            Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get();
            DebugLog.showLogCat(document.toString());
            if (document != null) {
                Elements element = document.getElementsContainingOwnText("Current Version");
                for (Element ele : element) {
                    if (ele.siblingElements() != null) {
                        Elements sibElemets = ele.siblingElements();
                        for (Element sibElemet : sibElemets) {
                            newVersion = sibElemet.text();
                        }
                    }
                }
            }
        } catch (IOException e) {
            DebugLog.showLogCat(e.toString());
        }
        return newVersion;
    }

    @Override
    protected void onPostExecute(String onlineVersion) {
        super.onPostExecute(onlineVersion);
        try {
            String currentVersion = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    DebugLog.showLogCat("new update");
                }
            }
            DebugLog.showLogCat("Current: " + currentVersion + " playstore: " + onlineVersion);
        } catch (PackageManager.NameNotFoundException e) {
            DebugLog.showLogCat(e.toString());
        }
    }
}