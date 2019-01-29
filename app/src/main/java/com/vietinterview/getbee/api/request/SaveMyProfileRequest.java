package com.vietinterview.getbee.api.request;

import com.google.gson.reflect.TypeToken;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.ctvprofile.City;
import com.vietinterview.getbee.api.response.ctvprofile.Country;
import com.vietinterview.getbee.api.response.ctvprofile.MyProfileResponse;
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
    private Integer birthdayColl;
    private ArrayList<Carrer> carrers;
    private ArrayList<City> cities;
    private ArrayList<Country> countries;
    private String phoneColl;
    private String companyName;
    private Integer contractDate;

    public SaveMyProfileRequest(String fullNameColl, String addressColl, String careerColl, ArrayList<Carrer> carrers, ArrayList<City> cities, ArrayList<Country> countries, Integer birthdayColl, String phoneColl, String companyName, Integer contractDate) {
        this.fullNameColl = fullNameColl;
        this.addressColl = addressColl;
        this.careerColl = careerColl;
        this.carrers = carrers;
        this.cities = cities;
        this.countries = countries;
        this.birthdayColl = birthdayColl;
        this.phoneColl = phoneColl;
        this.companyName = companyName;
        this.contractDate = contractDate;
    }

    @Override
    protected String getStringParam() {
        return null;
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
        if (birthdayColl != 0)
            jsonParams.put("birthday", birthdayColl);
        jsonParams.put("companyName", companyName);
        if (contractDate != 0)
            jsonParams.put("contractDate", contractDate);
        JSONArray desideratedCareer = new JSONArray();
        for (int i = 0; i < carrers.size(); i++) {
            JSONObject jGroup = new JSONObject();
            try {
                jGroup.put("id", carrers.get(i).getId());
                jGroup.put("name", carrers.get(i).getName());
                desideratedCareer.put(jGroup);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonParams.put("desideratedCareer", desideratedCareer);
        JSONArray mCities = new JSONArray();
        for (int i = 0; i < cities.size(); i++) {
            JSONObject jGroup = new JSONObject();
            try {
                jGroup.put("id", cities.get(i).getId());
                jGroup.put("name", cities.get(i).getName());
                jGroup.put("countryId", cities.get(i).getCountryId());
                jGroup.put("enName", null);
                mCities.put(jGroup);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonParams.put("cities", mCities);
        JSONArray mCountries = new JSONArray();
        for (int i = 0; i < countries.size(); i++) {
            JSONObject jGroup = new JSONObject();
            try {
                jGroup.put("id", countries.get(i).getId());
                jGroup.put("name", countries.get(i).getName());
                mCountries.put(jGroup);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jsonParams.put("countries", mCountries);
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
        return AccountManager.getApiConstant().getUPDATE_MY_PROFILE();
    }
}
