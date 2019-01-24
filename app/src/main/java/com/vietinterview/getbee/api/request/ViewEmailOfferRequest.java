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
public class ViewEmailOfferRequest extends BaseJsonRequest<ViewEmailInterviewResponse, ErrorResponse> {
    private int cvId;
    private int id;
    private int curency;
    private String position;
    private int jobId;
    private String note;
    private String round;
    private int status;
    private long salary;
    private String workAddress;
    private String workTime;

    public ViewEmailOfferRequest(int cvId, int id, int curency, String position, int jobId, String note, String round, int status, long salary, String workAddress, String workTime) {
        this.cvId = cvId;
        this.id = id;
        this.curency = curency;
        this.position = position;
        this.jobId = jobId;
        this.note = note;
        this.round = round;
        this.status = status;
        this.salary = salary;
        this.workAddress = workAddress;
        this.workTime = workTime;
    }

    @Override
    protected String getStringParam() {
        return null;
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
        jsonParams.put("curency", curency);
        jsonParams.put("position", position);
        jsonParams.put("jobId", jobId);
        jsonParams.put("note", note);
        jsonParams.put("round", round);
        jsonParams.put("status", status);
        jsonParams.put("salary", salary);
        jsonParams.put("workAddress", workAddress);
        jsonParams.put("workTime", workTime);
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
        return AccountManager.getApiConstantTest().getVIEW_EMAIL_OFFER();
    }
}
