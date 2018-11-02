package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.SubmitCVResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/29/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SubmitCVRequest extends BaseJsonRequest<SubmitCVResponse, ErrorResponse> {
    private int cvId;
    private int jobId;
    private int type;

    public SubmitCVRequest(int cvId, int jobId, int type) {
        this.cvId = cvId;
        this.jobId = jobId;
        this.type = type;
    }

    @Override
    public Class<SubmitCVResponse> getResponseSuccessClass() throws JSONException {
        return SubmitCVResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<SubmitCVResponse> getListResponseClass() {
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
        jsonParams.put("type", type);
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
        return ApiConstant.SUBMIT_CV;
    }
}
