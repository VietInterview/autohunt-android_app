package com.vietinterview.getbee.api.request;

import android.content.Context;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ResetPasswordFailResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 1/23/19.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ResetPasswordRequest extends BaseJsonRequest<Object, ResetPasswordFailResponse> {
    private String email;

    public ResetPasswordRequest(String email) {
        this.email = email;
    }

    @Override
    protected String getStringParam() {
        return email;
    }

    @Override
    public Class<Object> getResponseSuccessClass() throws JSONException {
        return Object.class;
    }

    @Override
    public Class<ResetPasswordFailResponse> getResponseFailClass() throws JSONException {
        return ResetPasswordFailResponse.class;
    }

    @Override
    public List<Object> getListResponseClass() {
        List<Object> objects = new ArrayList<>();
        return objects;
    }

    @Override
    public List<ResetPasswordFailResponse> getListResponseFailClass() {
        List<ResetPasswordFailResponse> resetPasswordFailResponses = new ArrayList<>();
        return resetPasswordFailResponses;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        return null;
    }

    @Override
    public int getMethod() {
        return AccountManager.getApiConstantTest().POST;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getRESET_PASSWORD();
    }
}
