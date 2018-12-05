package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/25/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetDetailCVByJobCustomerRequest extends BaseRequest<DetailCVCustomerResponse, ErrorResponse> {
    private int mId;

    public GetDetailCVByJobCustomerRequest(int mId) {
        this.mId = mId;
    }


    @Override
    public Class<DetailCVCustomerResponse> getResponseSuccessClass() {
        return DetailCVCustomerResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<DetailCVCustomerResponse> getListResponseSuccessClass() {
        List<DetailCVCustomerResponse> detailCVCustomerResponses = new ArrayList<>();
        return detailCVCustomerResponses;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        return errorResponses;
    }

    @Override
    public Type getType() {
        return null;
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
        return ApiConstant.GET_DETAIL_CV_BY_JOB_CUSTOMER + "/" + mId;
    }
}
