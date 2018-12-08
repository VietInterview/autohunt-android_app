package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobcustomer.JobCustomerResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 11/30/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetJobCustomerRequest extends BaseRequest<JobCustomerResponse, ErrorResponse> {
    private int mPage;
    private String cusName;
    private int mStatus;

    public GetJobCustomerRequest(int mPage, String cusName, int mStatus) {
        this.mPage = mPage;
        this.cusName = cusName;
        this.mStatus = mStatus;
    }

    @Override
    public Class<JobCustomerResponse> getResponseSuccessClass() {
        return JobCustomerResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<JobCustomerResponse> getListResponseSuccessClass() {
        List<JobCustomerResponse> jobCustomerResponses = new ArrayList<>();
        return jobCustomerResponses;
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
        requestParams.put("cusName", cusName );
        if (mStatus != -1)
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
        return AccountManager.getApiConstantTest().getGET_JOB_CUSTOMER();
    }
}
