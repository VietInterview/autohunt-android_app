package com.vietinterview.getbee.api.request;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.GsonUtils;
import com.vietinterview.getbee.utils.MySSLSocketFactory;
import com.vietinterview.getbee.utils.TrustManagerManipulator;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
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

public abstract class BaseRequest<T, V> {
    private AsyncHttpClient client = new AsyncHttpClient();
    private JsonHttpResponseHandler mJsonHttpResponseHandler;
    private ApiObjectCallBack<T, V> mApiObjectCallBack;
    private List<T> tList;
    private List<V> vList;
    private Context mContext;

    public void callRequest(Context context, ApiObjectCallBack<T, V> tApiObjectCallBack) {
        client.setTimeout(ApiConstant.REQUEST_TIMEOUT);
        this.mContext = context;
        client.setConnectTimeout(ApiConstant.REQUEST_TIMEOUT);
        client.setResponseTimeout(ApiConstant.REQUEST_TIMEOUT);
        if (getAccessToken() != null) {
            client.addHeader("Content-Type", "application/json;charset=UTF-8");
            client.addHeader("Authorization", "Bearer " + getAccessToken());
        }
        mApiObjectCallBack = tApiObjectCallBack;
        mJsonHttpResponseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response != null) {
                    DebugLog.jsonFormat("response " + getAbsoluteUrl(), response);
                    mApiObjectCallBack.onSuccess(statusCode, GsonUtils.fromJson(response.toString(), getResponseSuccessClass()), null, "");
                } else {
                    DebugLog.showLogCat(statusCode + "");
                    if (statusCode == 0)
                        mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.timeout));
                    else
                        mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response != null) {
                    DebugLog.jsonFormat("response " + getAbsoluteUrl(), response);
                    tList = getListResponseSuccessClass();
                    tList = GsonUtils.fromJson(response.toString(), getType());
                    mApiObjectCallBack.onSuccess(statusCode, null, tList, "");
                } else {
                    DebugLog.showLogCat(statusCode + "");
                    if (statusCode == 0)
                        mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.timeout));
                    else
                        mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                DebugLog.showLogCat(responseString);
                mApiObjectCallBack.onSuccess(statusCode, null, null, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                if (errorResponse != null) {
                    DebugLog.jsonFormat("response " + getAbsoluteUrl(), errorResponse);
                    vList = getListResponseFailClass();
                    vList = GsonUtils.fromJson(errorResponse.toString(), getType());
                    mApiObjectCallBack.onFail(statusCode, null, vList, throwable.getMessage());
                } else {
                    DebugLog.showLogCat(statusCode + "");
                    if (statusCode == 0)
                        mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.timeout));
                    else
                        mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                DebugLog.showLogCat(statusCode + " " + throwable.getMessage());
                if (errorResponse != null)
                    mApiObjectCallBack.onFail(statusCode, GsonUtils.fromJson(errorResponse.toString(), getResponseFailClass()), null, throwable.getMessage());
                else {
                    mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugLog.showLogCat(statusCode + " " + throwable.toString());
                if (statusCode == 0)
                    mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.timeout));
                else
                    mApiObjectCallBack.onFail(statusCode, null, null, mContext.getResources().getString(R.string.error_please_try));
            }
        };
        if (putParams() != null)
            DebugLog.showLogCat(getAbsoluteUrl() + "\n" + putParams() + "\nBearer " + getAccessToken());
        TrustManagerManipulator.allowAllSSL();

        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            trustStore.load(null, null);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        MySSLSocketFactory socketFactory = null;
        try {
            socketFactory = new MySSLSocketFactory(trustStore);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        client.setSSLSocketFactory(socketFactory);

        if (getMethod() == ApiConstant.POST) {
            client.post(getAbsoluteUrl(), putParams(), mJsonHttpResponseHandler);
        } else if (getMethod() == ApiConstant.GET) {
            client.get(getAbsoluteUrl(), putParams(), mJsonHttpResponseHandler);
        } else if (getMethod() == ApiConstant.DELETE) {
            client.delete(getAbsoluteUrl(), putParams(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        DebugLog.showLogCat(new String(responseBody, "UTF-8"));
                        mApiObjectCallBack.onSuccess(statusCode, null, null, new String(responseBody, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    DebugLog.showLogCat(error.getMessage());
                    mApiObjectCallBack.onFail(statusCode, null, null, error.getMessage());
                }
            });
        }
    }

    public void cancelRequest() {
        client.cancelAllRequests(true);
    }

    abstract public Class<T> getResponseSuccessClass();

    abstract public Class<V> getResponseFailClass();

    abstract public List<T> getListResponseSuccessClass();

    abstract public List<V> getListResponseFailClass();

    abstract public Type getType();

    protected abstract RequestParams putParams();

    protected abstract String getAccessToken();

    abstract public int getMethod();

    protected abstract String getAbsoluteUrl();
}
