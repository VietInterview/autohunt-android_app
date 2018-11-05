package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.myprofile.MyProfileResponse;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.model.Carrer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 11/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SaveMyProfileRequest extends BaseJsonRequest<MyProfileResponse, ErrorResponse> {
    private String fullNameColl;
    private String addressColl;
    private String careerColl;
    private ArrayList<Carrer> carrers;
    private String emailColl;
    private String phoneColl;

    public SaveMyProfileRequest(String fullNameColl, String addressColl, String careerColl, ArrayList<Carrer> carrers, String emailColl, String phoneColl) {
        this.fullNameColl = fullNameColl;
        this.addressColl = addressColl;
        this.careerColl = careerColl;
        this.carrers = carrers;
        this.emailColl = emailColl;
        this.phoneColl = phoneColl;
    }

    @Override
    public Class<MyProfileResponse> getResponseSuccessClass() {
        return MyProfileResponse.class;
    }

    @Override
    public Class<ErrorResponse> getResponseFailClass() {
        return ErrorResponse.class;
    }

    @Override
    public List<MyProfileResponse> getListResponseClass() {
        List<MyProfileResponse> myProfileResponses = new ArrayList<>();
        return myProfileResponses;
    }

    @Override
    public List<ErrorResponse> getListResponseFailClass() {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        return errorResponses;
    }

    @Override
    public Type getType() {
        return new TypeToken<List<MyProfileResponse>>() {
        }.getType();
    }

    @Override
    protected JSONObject putJsonParams() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("addressColl", addressColl);
        jsonParams.put("careerColl", careerColl);
        JSONArray jArray = new JSONArray();// /ItemDetail jsonArray
        for (int i = 0; i < carrers.size(); i++) {
            JSONObject jGroup = new JSONObject();// /sub Object
            try {
                jGroup.put("id", carrers.get(i).getId());
                jGroup.put("name", carrers.get(i).getName());
                jArray.put(jGroup);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonParams.put("desideratedCareer", jArray);
        jsonParams.put("emailColl", emailColl);
        jsonParams.put("fullNameColl", fullNameColl);
        jsonParams.put("idColl", null);
        jsonParams.put("phoneColl", phoneColl);
        return jsonParams;
    }

    @Override
    public int getMethod() {
        return ApiConstant.POST;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    protected String getAbsoluteUrl() {
        return ApiConstant.UPDATE_MY_PROFILE;
    }
}
