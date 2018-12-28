package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetListRejectReasonRequest extends BaseRequest<RejectReasonResponse, ErrorResponse> {
    List<RejectReasonResponse> rejectReasonResponses = new ArrayList<>();

    @Override
    public Class<RejectReasonResponse> getResponseSuccessClass() {
        return RejectReasonResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<RejectReasonResponse> getListResponseSuccessClass() {
        return rejectReasonResponses;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        return errorResponses;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<RejectReasonResponse>>() {
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
        return AccountManager.getApiConstantTest().getLIST_REJECT_REASON();
    }
}
