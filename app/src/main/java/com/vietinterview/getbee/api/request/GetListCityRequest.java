package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetListCityRequest extends BaseRequest<CityResponse, ErrorResponse> {
    @Override
    public Class<CityResponse> getResponseSuccessClass() {
        return CityResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<CityResponse> getListResponseSuccessClass() {
        return new ArrayList<CityResponse>();
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        return null;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<CityResponse>>() {
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
        return AccountManager.getApiConstantTest().getGET_LIST_CITY();
    }
}
