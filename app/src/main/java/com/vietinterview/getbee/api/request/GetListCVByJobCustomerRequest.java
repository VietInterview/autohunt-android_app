package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.listcvcustomer.CvsCustomerResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 11/30/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetListCVByJobCustomerRequest extends BaseRequest<CvsCustomerResponse, ErrorResponse> {
    private int mPage;
    private String mId;
    private int mStatus;

    public GetListCVByJobCustomerRequest(int mPage, String id, int mStatus) {
        this.mPage = mPage;
        this.mId = id;
        this.mStatus = mStatus;
    }

    @Override
    public Class<CvsCustomerResponse> getResponseSuccessClass() {
        return CvsCustomerResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<CvsCustomerResponse> getListResponseSuccessClass() {
        List<CvsCustomerResponse> cvsCustomerResponses = new ArrayList<>();
        return cvsCustomerResponses;
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
        RequestParams requestParams = new RequestParams();
        requestParams.put("itemPerPage", ApiConstant.LIMIT + "");
        requestParams.put("page", mPage + "");
        requestParams.put("id", mId);
        if (mStatus != 11)
            requestParams.put("status", mStatus + "");
        return requestParams;
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
        return ApiConstant.GET_CV_BY_JOB_CUSTOMER;
    }
}
