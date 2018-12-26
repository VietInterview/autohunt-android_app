package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.login.ErrorLoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 12/26/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class UpdateOSRequest extends BaseJsonRequest<ErrorLoginResponse, ErrorLoginResponse> {
    String deviceId;

    public UpdateOSRequest(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public Class<ErrorLoginResponse> getResponseSuccessClass() throws JSONException {
        return ErrorLoginResponse.class;
    }

    @Override
    public Class<ErrorLoginResponse> getResponseFailClass() throws JSONException {
        return ErrorLoginResponse.class;
    }

    @Override
    public List<ErrorLoginResponse> getListResponseClass() {
        List<ErrorLoginResponse> errorLoginResponses = new ArrayList<>();
        return errorLoginResponses;
    }

    @Override
    public List<ErrorLoginResponse> getListResponseFailClass() {
        List<ErrorLoginResponse> errorLoginResponses = new ArrayList<>();
        return errorLoginResponses;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("deviceId", deviceId);
        jsonParams.put("os", "ANDROID");
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return AccountManager.getApiConstantTest().getPOST();
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getUPDATE_OS();
    }
}
