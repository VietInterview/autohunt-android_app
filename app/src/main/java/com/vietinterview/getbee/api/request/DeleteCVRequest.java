package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/25/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DeleteCVRequest extends BaseRequest<Object, ErrorResponse> {
    private int mId;

    public DeleteCVRequest(int mId) {
        this.mId = mId;
    }

    @Override
    public Class getResponseSuccessClass() {
        return null;
    }

    @Override
    public Class getResponseFailClass() {
        return null;
    }

    @Override
    public List getListResponseSuccessClass() {
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
        return null;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    public int getMethod() {
        return ApiConstant.DELETE;
    }

    @Override
    protected String getAbsoluteUrl() {
        return ApiConstant.DELETE_MY_CV + "/" + mId;
    }
}
