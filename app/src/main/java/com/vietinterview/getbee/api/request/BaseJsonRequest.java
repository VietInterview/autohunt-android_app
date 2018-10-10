package com.vietinterview.getbee.api.request;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.GsonUtils;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by hiepn on 22/04/2017.
 */

public abstract class BaseJsonRequest<T> {
    private AsyncHttpClient client = new AsyncHttpClient();
    private JsonHttpResponseHandler mJsonHttpResponseHandler;
    private ApiObjectCallBack<T> mApiObjectCallBack;
    private Context mContext;

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
                    mApiObjectCallBack.onSuccess(GsonUtils.fromJson(response.toString(), getResponseClass()), statusCode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    DebugLog.jsonFormat(getAbsoluteUrl(), responseString);
                    mApiObjectCallBack.onSuccess(GsonUtils.fromJson(responseString, getResponseClass()), statusCode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    DebugLog.jsonFormat(getAbsoluteUrl(), errorResponse);
                DebugLog.jsonFormat(getAbsoluteUrl(), errorResponse.toString());
                mApiObjectCallBack.onFail(statusCode,GsonUtils.fromJson(errorResponse.toString(), getResponseClass()), throwable.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                DebugLog.jsonFormat(getAbsoluteUrl(), responseString.toString());
                mApiObjectCallBack.onFail(statusCode, responseString);
            }
        };
        StringEntity entity = null;
        try {
            entity = new StringEntity(putJsonParams().toString());
            DebugLog.jsonFormat(getAbsoluteUrl(), putJsonParams().toString());
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

    protected abstract JSONObject putJsonParams() throws JSONException;

    abstract public int getMethod();

    protected abstract String getAccessToken();

    protected abstract String getAbsoluteUrl();
}
