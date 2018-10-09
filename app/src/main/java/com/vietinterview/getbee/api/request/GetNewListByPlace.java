package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.getnewlistbyplace.NewListByPlace;
import com.vietinterview.getbee.constant.ApiConstant;
import com.loopj.android.http.RequestParams;

/**
 * Created by hiepn on 23/04/2017.
 */

public class GetNewListByPlace extends BaseRequest<NewListByPlace> {
    private String mApi_token;

    public GetNewListByPlace(String api_token) {
        this.mApi_token = api_token;
    }

    @Override
    public Class<NewListByPlace> getResponseClass() {
        return NewListByPlace.class;
    }

    @Override
    protected RequestParams putParams() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("api_token", mApi_token);
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
        return ApiConstant.GET_NEW_LIST_BY_PLACE;
    }
}
