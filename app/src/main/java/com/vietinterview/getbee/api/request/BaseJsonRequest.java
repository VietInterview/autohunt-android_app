package com.vietinterview.getbee.api.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.GsonUtils;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepn on 22/04/2017.
 */

public abstract class BaseJsonRequest<T> {
    private AsyncHttpClient client = new AsyncHttpClient();
    private JsonHttpResponseHandler mJsonHttpResponseHandler;
    private ApiObjectCallBack<T> mApiObjectCallBack;
    private Context mContext;
    private List<T> tList;

    public void callRequest(Context context, ApiObjectCallBack<T> tApiObjectCallBack) {
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
                    mApiObjectCallBack.onSuccess(GsonUtils.fromJson(response.toString(), getResponseClass()), null, statusCode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                DebugLog.jsonFormat(getAbsoluteUrl(), response);
                Gson gson = new Gson();
                tList = getListResponseClass();
                tList = gson.fromJson(response.toString(), getType());
                mApiObjectCallBack.onSuccess(null, tList, statusCode);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    DebugLog.jsonFormat(getAbsoluteUrl(), responseString);
                    mApiObjectCallBack.onSuccess(GsonUtils.fromJson(responseString, getResponseClass()), null, statusCode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    DebugLog.jsonFormat(getAbsoluteUrl(), throwable.toString());
                    if (errorResponse != null)
                        mApiObjectCallBack.onFail(statusCode, GsonUtils.fromJson(errorResponse.toString(), getResponseClass()), throwable.getMessage());
                    else mApiObjectCallBack.onFail(statusCode, null, throwable.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugLog.jsonFormat(getAbsoluteUrl(), throwable.toString());
                mApiObjectCallBack.onFail(statusCode, null, throwable.toString());
            }
        };
        StringEntity entity = null;
        try {
            entity = new StringEntity(putJsonParams().toString());
            DebugLog.jsonFormat(getAbsoluteUrl(), putJsonParams().toString() + " \n " + getAccessToken());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Content-Type", "application/json");
        client.post(mContext, getAbsoluteUrl(), entity, "application/json", mJsonHttpResponseHandler);
    }

    public void cancelRequest() {
        client.cancelAllRequests(true);
    }

    abstract public Class<T> getResponseClass() throws JSONException;

    abstract public List<T> getListResponseClass();

    abstract public Type getType();

    protected abstract JSONObject putJsonParams() throws JSONException;

    abstract public int getMethod();

    protected abstract String getAccessToken();

    protected abstract String getAbsoluteUrl();
}
