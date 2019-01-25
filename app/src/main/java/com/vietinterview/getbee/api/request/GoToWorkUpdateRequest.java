package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.GoToWorkUpdateResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 12/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GoToWorkUpdateRequest extends BaseJsonRequest<GoToWorkUpdateResponse, ErrorResponse> {
    private int cvId;
    private int id;
    private int countUpdate;
    private int jobId;
    private String startWorkDate;

    public GoToWorkUpdateRequest(int cvId, int id, int countUpdate, int jobId,  String startWorkDate) {
        this.cvId = cvId;
        this.id = id;
        this.countUpdate = countUpdate;
        this.jobId = jobId;
        this.startWorkDate = startWorkDate;
    }

    @Override
    protected String getStringParam() {
        return null;
    }

    @Override
    public Class<GoToWorkUpdateResponse> getResponseSuccessClass() throws JSONException {
        return GoToWorkUpdateResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<GoToWorkUpdateResponse> getListResponseClass() {
        List<GoToWorkUpdateResponse> goToWorkUpdateResponses = new ArrayList<>();
        return goToWorkUpdateResponses;
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
        jsonParams.put("countUpdate", countUpdate);
        jsonParams.put("cvId", cvId);
        jsonParams.put("id", id == -1 ? null : id);
        jsonParams.put("jobId", jobId);
        jsonParams.put("startWorkDate", startWorkDate);
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return AccountManager.getApiConstant().getPOST();
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstant().getGO_TO_WORK_UPDATE();
    }
}
