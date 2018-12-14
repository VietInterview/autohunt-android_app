package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetDetailProcessResumeRequest extends BaseRequest<DetailProcessResumeResponse, ErrorResponse> {
    private int cvId;
    private int jobId;

    public GetDetailProcessResumeRequest(int cvId, int jobId) {
        this.cvId = cvId;
        this.jobId = jobId;
    }

    @Override
    public Class<DetailProcessResumeResponse> getResponseSuccessClass() {
        return DetailProcessResumeResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<DetailProcessResumeResponse> getListResponseSuccessClass() {
        List<DetailProcessResumeResponse> detailProcessResumeResponses = new ArrayList<>();
        return detailProcessResumeResponses;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        return errorResponses;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<DetailProcessResumeResponse>>() {
        }.getType();
    }

    @Override
    protected RequestParams putParams() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("cvId", cvId);
        requestParams.put("jobId", jobId);
        return requestParams;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    public int getMethod() {
        return AccountManager.getApiConstantTest().getGET();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getDETAIL_PROCESS_RESUME();
    }
}
