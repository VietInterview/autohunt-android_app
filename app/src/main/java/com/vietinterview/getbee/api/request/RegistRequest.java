package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/9/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RegistRequest extends BaseJsonRequest<ErrorResponse, ErrorResponse> {
    private String mEmail;
    private String mPhone;
    private String mName;
    private String mCarrer;
    private String mAddress;
    private String mContact;
    private int mBirthday;
    private int mType;
    private String mCompanyName;

    public RegistRequest(String mEmail, String mPhone, String mName, String mCarrer, String mCompanyName, String mContact, int mBirthday, int mType) {
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mName = mName;
        this.mCarrer = mCarrer;
        this.mContact = mContact;
        this.mBirthday = mBirthday;
        this.mType = mType;
        this.mCompanyName = mCompanyName;
    }

    public RegistRequest(String email, String phone, String name, String carrer, String address) {
        this.mEmail = email;
        this.mPhone = phone;
        this.mName = name;
        this.mCarrer = carrer;
        this.mAddress = address;
    }


    @Override
    public Class<ErrorResponse> getResponseSuccessClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<ErrorResponse> getListResponseClass() {
        return null;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        return null;
    }


    @Override
    public Type getType() {
        return null;
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("address", mAddress);
        jsonParams.put("career", mCarrer);
        jsonParams.put("email", mEmail);
        jsonParams.put("fullName", mName);
        jsonParams.put("phone", mPhone);
        jsonParams.put("os", "ANDROID");
        jsonParams.put("companyName", mCompanyName);
        jsonParams.put("type", mType);
        jsonParams.put("contact", mContact);
        jsonParams.put("birthday", mBirthday);
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
        return AccountManager.getApiConstantTest().getREGIST();
    }
}
