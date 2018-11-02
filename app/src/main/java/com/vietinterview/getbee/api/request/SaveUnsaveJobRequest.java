package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.AddRemoveJobResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/15/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SaveUnsaveJobRequest extends BaseJsonRequest<AddRemoveJobResponse, ErrorResponse> {
    private int mJobId;
    private int mStatus;

    public SaveUnsaveJobRequest(int jobId, int status) {
        this.mJobId = jobId;
        this.mStatus = status;
    }

    @Override
    public Class<AddRemoveJobResponse> getResponseSuccessClass() throws JSONException {
        return AddRemoveJobResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<AddRemoveJobResponse> getListResponseClass() {
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
        jsonParams.put("jobId", mJobId + "");
        jsonParams.put("status", mStatus + "");
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
        return ApiConstant.SAVE_UNSAVE_JOB;
    }
}
