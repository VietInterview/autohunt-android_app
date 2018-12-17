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
public class ContractStatusRequest extends BaseJsonRequest<ErrorResponse, ErrorResponse> {
    private int cvId;
    private int jobId;

    public ContractStatusRequest(int cvId, int jobId) {
        this.cvId = cvId;
        this.jobId = jobId;
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
        jsonParams.put("cvId", cvId);
        jsonParams.put("jobId", jobId);
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return ApiConstant.POST;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getCONTRACT_STATUS();
    }
}
