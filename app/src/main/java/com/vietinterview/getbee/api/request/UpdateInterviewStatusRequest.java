package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.ViewEmailInterviewResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 12/14/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class UpdateInterviewStatusRequest extends BaseJsonRequest<ViewEmailInterviewResponse, ErrorResponse> {
    private int cvId;
    private int id;
    private String interviewAddress;
    private String interviewDate;
    private int jobId;
    private String note;
    private String round;
    private int status;

    public UpdateInterviewStatusRequest(int cvId, int id, String interviewAddress, String interviewDate, int jobId, String note, String round, int status) {
        this.cvId = cvId;
        this.id = id;
        this.interviewAddress = interviewAddress;
        this.interviewDate = interviewDate;
        this.jobId = jobId;
        this.note = note;
        this.round = round;
        this.status = status;
    }

    @Override
    public Class<ViewEmailInterviewResponse> getResponseSuccessClass() throws JSONException {
        return ViewEmailInterviewResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<ViewEmailInterviewResponse> getListResponseClass() {
        List<ViewEmailInterviewResponse> viewEmailInterviewResponses = new ArrayList<>();
        return viewEmailInterviewResponses;
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
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("cvId", cvId);
        jsonParams.put("id", id == -1 ? null : id);
        jsonParams.put("interviewAddress", interviewAddress);
        jsonParams.put("interviewDate", interviewDate);
        jsonParams.put("jobId", jobId);
        jsonParams.put("note", note);
        jsonParams.put("round", round);
        jsonParams.put("status", status);
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
        return AccountManager.getApiConstantTest().getUPDATE_INTERVIEW_STATUS();
    }
}
