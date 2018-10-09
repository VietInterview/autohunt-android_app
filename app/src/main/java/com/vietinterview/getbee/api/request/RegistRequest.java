package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.api.response.RegistResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hiepnguyennghia on 10/9/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RegistRequest extends BaseJsonRequest<RegistResponse> {
    private String mEmail;
    private String mPhone;

    public RegistRequest(String email, String phone) {
        this.mEmail = email;
        this.mPhone = phone;
    }

    @Override
    public Class<RegistResponse> getResponseClass() {
        return RegistResponse.class;
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("address", "string");
        jsonParams.put("career", "string");
        jsonParams.put("email", mEmail);
        jsonParams.put("fullName", "string");
        jsonParams.put("phone", mPhone);
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return ApiConstant.POST;
    }

    @Override
    protected String getAccessToken() {
        return null;
    }

    @Override
    protected String getAbsoluteUrl() {
        return ApiConstant.REGIST;
    }
}
