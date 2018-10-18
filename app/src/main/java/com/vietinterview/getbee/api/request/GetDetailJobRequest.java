package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.detailjobresponse.DetailJobResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/18/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetDetailJobRequest extends BaseRequest<DetailJobResponse> {
    private int jobId;

    public GetDetailJobRequest(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public Class<DetailJobResponse> getResponseClass() {
        return DetailJobResponse.class;
    }

    @Override
    public List<DetailJobResponse> getListResponseClass() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    protected RequestParams putParams() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("jobId", jobId + "");
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
        return ApiConstant.GET_DETAIL_JOB;
    }
}
