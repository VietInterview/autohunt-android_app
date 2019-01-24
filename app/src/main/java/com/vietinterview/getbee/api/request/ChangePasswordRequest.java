package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.ErrorChangePassResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 11/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ChangePasswordRequest extends BaseJsonRequest<Object, ErrorChangePassResponse> {
    private String currentPassword;
    private String newPassword;

    public ChangePasswordRequest(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    @Override
    protected String getStringParam() {
        return null;
    }

    @Override
    public Class<Object> getResponseSuccessClass() throws JSONException {
        return Object.class;
    }

    @Override
    public Class<ErrorChangePassResponse> getResponseFailClass() throws JSONException {
        return ErrorChangePassResponse.class;
    }

    @Override
    public List<Object> getListResponseClass() {
        List<Object> objects = new ArrayList<>();
        return objects;
    }

    @Override
    public List<ErrorChangePassResponse> getListResponseFailClass() {
        List<ErrorChangePassResponse> errorChangePassResponses = new ArrayList<>();
        return errorChangePassResponses;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<ErrorChangePassResponse>>() {
        }.getType();
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("currentPassword", currentPassword);
        jsonParams.put("newPassword", newPassword);
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return ApiConstant.POST;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getCHANGE_PASSWORD();
    }
}
