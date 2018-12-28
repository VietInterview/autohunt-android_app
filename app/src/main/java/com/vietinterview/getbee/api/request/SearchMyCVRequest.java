package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/24/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SearchMyCVRequest extends BaseRequest<CVResponse,ErrorResponse> {
    private int mPage;
    private int mCareerId;
    private int mCityId;

    public SearchMyCVRequest(int mPage, int mCareerId, int mCityId) {
        this.mPage = mPage;
        this.mCareerId = mCareerId;
        this.mCityId = mCityId;
    }

    @Override
    public Class<CVResponse> getResponseSuccessClass() {
        return CVResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<CVResponse> getListResponseSuccessClass() {
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
        requestParams.put("itemPerPage", ApiConstant.LIMIT + "");
        requestParams.put("page", mPage + "");
        requestParams.put("careerId", mCareerId + "");
        requestParams.put("cityId", mCityId + "");
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
        return AccountManager.getApiConstantTest().getSEARCH_MY_CV();
    }
}
