package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobs.JobsResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/11/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetApplyedSearchJobsRequest extends BaseRequest<JobsResponse, ErrorResponse> {
    String mCareerId;
    String mCityId;
    String mItemPerPage;
    String mJobtile;
    int mPage;

    public GetApplyedSearchJobsRequest(String careerId, String cityId, String itemPerPage, String jobtile, int page) {
        this.mCareerId = careerId;
        this.mCityId = cityId;
        this.mItemPerPage = itemPerPage;
        this.mJobtile = jobtile;
        this.mPage = page;
    }

    @Override
    public Class<JobsResponse> getResponseSuccessClass() {
        return JobsResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<JobsResponse> getListResponseSuccessClass() {
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
    protected RequestParams putParams() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("careerId", mCareerId);
        requestParams.put("cityId", mCityId);
        requestParams.put("itemPerPage", mItemPerPage);
        requestParams.put("jobtile", mJobtile);
        requestParams.put("page", mPage);
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
        return AccountManager.getApiConstant().getGET_APPLYED_SEARCH_JOBS();
    }
}
