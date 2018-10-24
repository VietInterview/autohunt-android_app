package com.vietinterview.getbee.api.request;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.GsonUtils;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepn on 22/04/2017.
 */

public abstract class BaseRequest<T> {
    private AsyncHttpClient client = new AsyncHttpClient();
    private JsonHttpResponseHandler mJsonHttpResponseHandler;
    private ApiObjectCallBack<T> mApiObjectCallBack;
    private List<T> tList;

    public void callRequest(ApiObjectCallBack<T> tApiObjectCallBack) {
        client.setTimeout(ApiConstant.REQUEST_TIMEOUT);
        client.setConnectTimeout(ApiConstant.REQUEST_TIMEOUT);
        client.setResponseTimeout(ApiConstant.REQUEST_TIMEOUT);
        if (getAccessToken() != null) {
            client.addHeader("Content-Type", "application/json");
            client.addHeader("Authorization", "Bearer " + getAccessToken());
        }
        mApiObjectCallBack = tApiObjectCallBack;
        mJsonHttpResponseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response != null) {
                    DebugLog.jsonFormat("response " + getAbsoluteUrl(), response);
                    mApiObjectCallBack.onSuccess(GsonUtils.fromJson(response.toString(), getResponseClass()), null, statusCode, "");
                } else {
                    DebugLog.showLogCat(statusCode + "");
                    mApiObjectCallBack.onFail(statusCode, null, null, "Lỗi chưa biết");
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response != null) {
                    DebugLog.jsonFormat("response " + getAbsoluteUrl(), response);
                    tList = getListResponseClass();
                    tList = GsonUtils.fromJson(response.toString(), getType());
                    mApiObjectCallBack.onSuccess(null, tList, statusCode, "");
                } else {
                    DebugLog.showLogCat(statusCode + "");
                    mApiObjectCallBack.onFail(statusCode, null, null, "Lỗi chưa biết");
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                DebugLog.showLogCat(responseString);
                mApiObjectCallBack.onSuccess(null, null, statusCode, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                if (errorResponse != null) {
                    DebugLog.jsonFormat("response " + getAbsoluteUrl(), errorResponse);
                    tList = getListResponseClass();
                    tList = GsonUtils.fromJson(errorResponse.toString(), getType());
                    mApiObjectCallBack.onFail(statusCode, null, tList, throwable.getMessage());
                } else {
                    DebugLog.showLogCat(statusCode + "");
                    mApiObjectCallBack.onFail(statusCode, null, null, throwable.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                DebugLog.showLogCat(statusCode + " " + throwable.getMessage());
                if (errorResponse != null)
                    mApiObjectCallBack.onFail(statusCode, GsonUtils.fromJson(errorResponse.toString(), getResponseClass()), null, throwable.getMessage());
                else {
                    mApiObjectCallBack.onFail(statusCode, null, null, throwable.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugLog.showLogCat(responseString);
                mApiObjectCallBack.onFail(statusCode, null, null, responseString);
            }
        };
        DebugLog.showLogCat(getAbsoluteUrl() + "\n" + putParams() + "\n " + getAccessToken());
        if (getMethod() == ApiConstant.POST) {
            client.post(getAbsoluteUrl(), putParams(), mJsonHttpResponseHandler);
        } else {
            client.get(getAbsoluteUrl(), putParams(), mJsonHttpResponseHandler);
        }
    }

    public void cancelRequest() {
        client.cancelAllRequests(true);
    }

    abstract public Class<T> getResponseClass();

    abstract public List<T> getListResponseClass();

    abstract public Type getType();

    protected abstract RequestParams putParams();

    protected abstract String getAccessToken();

    abstract public int getMethod();

    protected abstract String getAbsoluteUrl();
}
