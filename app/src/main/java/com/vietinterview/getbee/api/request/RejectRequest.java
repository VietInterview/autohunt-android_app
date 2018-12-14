package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RejectRequest extends BaseJsonRequest<RejectResponse, ErrorResponse> {
    private int cvId;
    private int jobId;
    private int reasonRejectId;
    private String reasonNote;
    private int rejectStep;

    public RejectRequest(int cvId, int jobId, int reasonRejectId, String reasonNote, int rejectStep) {
        this.cvId = cvId;
        this.jobId = jobId;
        this.reasonRejectId = reasonRejectId;
        this.reasonNote = reasonNote;
        this.rejectStep = rejectStep;
    }

    @Override
    public Class<RejectResponse> getResponseSuccessClass() throws JSONException {
        return RejectResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<RejectResponse> getListResponseClass() {
        List<RejectResponse> rejectResponses = new ArrayList<>();
        return rejectResponses;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        return errorResponses;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<RejectResponse>>() {
        }.getType();
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("cvId", cvId);
        jsonParams.put("jobId", jobId);
        jsonParams.put("reasonRejectId", reasonRejectId);
        jsonParams.put("reasonNote", reasonNote);
        jsonParams.put("rejectStep", rejectStep);
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return AccountManager.getApiConstantTest().getPOST();
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstantTest().getREJECT();
    }
}
