package com.vietinterview.getbee.api;

import android.content.Context;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.callback.RetrofitAPI;
import com.vietinterview.getbee.utils.AppUltil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sangb on 2/6/2017.
 */

public class ApiClient {
    private Context mContext;

    public ApiClient(Context context) {
        this.mContext = context;
    }

    public RetrofitAPI createService() throws NoSuchAlgorithmException, KeyManagementException {
//        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.0.101:8081/");
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(AccountManager.getApiConstant().getBASE_URL());

        OkHttpClient okHttpClient = AppUltil.getOkHttpClient(true);
        Retrofit retrofit = builder.client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitAPI.class);
    }
}
