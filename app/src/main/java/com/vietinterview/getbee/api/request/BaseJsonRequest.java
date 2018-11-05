package com.vietinterview.getbee.api.request;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.GsonUtils;
import com.vietinterview.getbee.utils.MySSLSocketFactory;
import com.vietinterview.getbee.utils.TrustManagerManipulator;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.droidparts.annotation.serialize.JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

/**
 * Created by hiepn on 22/04/2017.
 */

public abstract class BaseJsonRequest<T, V> {
    private AsyncHttpClient client = new AsyncHttpClient();
    private JsonHttpResponseHandler mJsonHttpResponseHandler;
    private ApiObjectCallBack<T, V> mApiObjectCallBack;
    private Context mContext;
    private List<T> tList;
    private List<V> vList;

    public void callRequest(Context context, ApiObjectCallBack<T, V> tApiObjectCallBack) {
        this.mContext = context;
        client.setTimeout(ApiConstant.REQUEST_TIMEOUT);
        client.setConnectTimeout(ApiConstant.REQUEST_TIMEOUT);
        client.setResponseTimeout(ApiConstant.REQUEST_TIMEOUT);
        mApiObjectCallBack = tApiObjectCallBack;
        mJsonHttpResponseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    DebugLog.jsonFormat(getAbsoluteUrl(), response);
                    mApiObjectCallBack.onSuccess(statusCode, GsonUtils.fromJson(response.toString(), getResponseSuccessClass()), null, "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                DebugLog.jsonFormat(getAbsoluteUrl(), response);
                tList = getListResponseClass();
                tList = GsonUtils.fromJson(response.toString(), getType());
                mApiObjectCallBack.onSuccess(statusCode, null, tList, "");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                DebugLog.showLogCat(responseString);
                mApiObjectCallBack.onSuccess(statusCode, null, null, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                DebugLog.showLogCat(statusCode + " - " + throwable.toString());
                if (errorResponse != null) {
                    try {
                        DebugLog.jsonFormat(getAbsoluteUrl(), errorResponse);
                        mApiObjectCallBack.onFail(statusCode, GsonUtils.fromJson(errorResponse.toString(), getResponseFailClass()), null, throwable.getMessage());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                DebugLog.jsonFormat(getAbsoluteUrl(), errorResponse);
                vList = getListResponseFailClass();
                vList = GsonUtils.fromJson(errorResponse.toString(), getType());
                mApiObjectCallBack.onFail(statusCode, null, vList, throwable.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugLog.showLogCat(throwable.toString() + " - " + statusCode);
                mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
            }
        };
        StringEntity entity = null;
        try {
            entity = new StringEntity(putJsonParams().toString(), "UTF-8");
            DebugLog.jsonFormat(getAbsoluteUrl(), putJsonParams().toString() + " \n" + getAccessToken());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        TrustManagerManipulator.allowAllSSL();

//        KeyStore trustStore = null;
//        try {
//            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//        try {
//            trustStore.load(null, null);
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        MySSLSocketFactory socketFactory = null;
//        try {
//            socketFactory = new MySSLSocketFactory(trustStore);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (UnrecoverableKeyException e) {
//            e.printStackTrace();
//        }
//        socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//
//        client.setSSLSocketFactory(socketFactory);
        client.addHeader("Content-Type", "application/json;charset=UTF-8");
        client.setResponseTimeout(ApiConstant.REQUEST_TIMEOUT);
        if (getAccessToken() != null) {
            client.addHeader("Authorization", "Bearer " + getAccessToken());
        }
        client.post(mContext, getAbsoluteUrl(), entity, "application/json;charset=UTF-8", mJsonHttpResponseHandler);
    }

    public void cancelRequest() {
        client.cancelAllRequests(true);
    }

    abstract public Class<T> getResponseSuccessClass() throws JSONException;

    abstract public Class<V> getResponseFailClass() throws JSONException;

    abstract public List<T> getListResponseClass();

    abstract public List<V> getListResponseFailClass();

    abstract public Type getType();

    protected abstract JSONObject putJsonParams() throws JSONException;

    abstract public int getMethod();

    protected abstract String getAccessToken();

    protected abstract String getAbsoluteUrl();
}
