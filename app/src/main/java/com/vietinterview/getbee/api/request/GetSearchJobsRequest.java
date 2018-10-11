package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.jobsresponse.JobsResponse;
import com.vietinterview.getbee.constant.ApiConstant;

/**
 * Created by hiepnguyennghia on 10/11/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetSearchJobsRequest extends BaseRequest<JobsResponse> {
    String mCareerId;
    String mCityId;
    String mItemPerPage;
    String mJobtile;
    int mPage;

    public GetSearchJobsRequest(String careerId, String cityId, String itemPerPage, String jobtile, int page) {
        this.mCareerId = careerId;
        this.mCityId = cityId;
        this.mItemPerPage = itemPerPage;
        this.mJobtile = jobtile;
        this.mPage = page;
    }

    @Override
    public Class<JobsResponse> getResponseClass() {
        return JobsResponse.class;
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
        return ApiConstant.GET_SEARCH_JOBS;
    }
}