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
    private String mName;
    private String mCarrer;
    private String mAddress;

    public RegistRequest(String email, String phone, String name, String carrer,String address) {
        this.mEmail = email;
        this.mPhone = phone;
        this.mName=name;
        this.mCarrer=carrer;
        this.mAddress=address;
    }

    @Override
    public Class<RegistResponse> getResponseClass() {
        return RegistResponse.class;
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("address", mAddress);
        jsonParams.put("career", mCarrer);
        jsonParams.put("email", mEmail);
        jsonParams.put("fullName", mName);
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