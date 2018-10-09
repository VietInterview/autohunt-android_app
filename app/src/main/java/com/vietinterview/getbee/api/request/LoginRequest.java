package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.api.response.loginresponse.LoginResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hiepn on 22/04/2017.
 */

public class LoginRequest extends BaseJsonRequest<LoginResponse> {
    private String mEmail;
    private String mPassword;

    public LoginRequest(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }

    @Override
    public Class<LoginResponse> getResponseClass() {
        return LoginResponse.class;
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("username", mEmail);
        jsonParams.put("password", mPassword);
        jsonParams.put("rememberMe", true);
        return jsonParams;
    }

    @Override
    protected String getAccessToken() {
        return null;
    }

    @Override
    public int getMethod() {
        return ApiConstant.POST;
    }

    @Override
    protected String getAbsoluteUrl() {
        return ApiConstant.LOGIN;
    }
}
