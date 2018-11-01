package com.vietinterview.getbee.api.request;

import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.login.LoginResponse;
import com.vietinterview.getbee.constant.ApiConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hiepn on 22/04/2017.
 */

public class LoginRequest extends BaseJsonRequest<LoginResponse, ErrorResponse> {
    private String mEmail;
    private String mPassword;

    public LoginRequest(String email, String password) {
        this.mEmail = email;
        this.mPassword = password;
    }

    @Override
    public Class<LoginResponse> getResponseSuccessClass() {
        return LoginResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() throws JSONException {
        return ErrorResponse.class;
    }

    @Override
    public List<LoginResponse> getListResponseClass() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
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
