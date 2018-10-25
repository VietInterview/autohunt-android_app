package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/25/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GetDetailCVRequest extends BaseRequest<DetailCVResponse> {
    private int mId;

    public GetDetailCVRequest(int mId) {
        this.mId = mId;
    }

    @Override
    public Class<DetailCVResponse> getResponseClass() {
        return DetailCVResponse.class;
    }

    @Override
    public List<DetailCVResponse> getListResponseClass() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    protected RequestParams putParams() {
        return null;
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
        return ApiConstant.GET_DETAIL_CV + "/" + mId;
    }
}
