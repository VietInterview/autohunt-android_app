package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.ctvprofile.MyProfileResponse;
import com.vietinterview.getbee.api.response.customerprofile.ProfileCustomerResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 11/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetCUSProfileRequest extends BaseRequest<ProfileCustomerResponse, ErrorResponse> {
    @Override
    public Class<ProfileCustomerResponse> getResponseSuccessClass() {
        return ProfileCustomerResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<ProfileCustomerResponse> getListResponseSuccessClass() {
        List<ProfileCustomerResponse> profileCustomerResponses = new ArrayList<>();
        return profileCustomerResponses;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        return errorResponses;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<ProfileCustomerResponse>>() {
        }.getType();
    }

    @Override
    protected RequestParams putParams() {
        return null;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    public int getMethod() {
        return ApiConstant.GET;
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getGET_CUS_PROFILE();
    }
}
