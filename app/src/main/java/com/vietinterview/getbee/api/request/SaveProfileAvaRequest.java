package com.vietinterview.getbee.api.request;

import com.loopj.android.http.RequestParams;
import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.api.response.SaveProfileAvaResponse;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiepnguyennghia on 1/25/19.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SaveProfileAvaRequest extends BaseRequest<SaveProfileAvaResponse, SaveProfileAvaResponse> {
    private String filePath;

    public SaveProfileAvaRequest(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Class<SaveProfileAvaResponse> getResponseSuccessClass() {
        return SaveProfileAvaResponse.class;
    }

    @Override
    public Class<SaveProfileAvaResponse> getResponseFailClass() {
        return SaveProfileAvaResponse.class;
    }

    @Override
    public List<SaveProfileAvaResponse> getListResponseSuccessClass() {
        return new ArrayList<>();
    }

    @Override
    public List<SaveProfileAvaResponse> getListResponseFailClass() {
        return new ArrayList<>();
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    protected RequestParams putParams() {
//        File myFile = new File(filePath);
//        byte[] myByteArray = new byte[0];
//        try {
//            myByteArray = FileUtils.readFile(myFile);
//        } catch (IOException e) {
//            DebugLog.showLogCat(e.toString());
//        }
//        RequestParams params = new RequestParams();
//        params.put("file", new ByteArrayInputStream(myByteArray), "she-wolf.pbg");
        File myFile = new File(filePath);
        RequestParams params = new RequestParams();
        try {
            params.put("file", myFile);
        } catch (FileNotFoundException e) {
            DebugLog.showLogCat(e.toString());
        }
        return params;
    }

    @Override
    protected String getAccessToken() {
        return AccountManager.getAccessToken();
    }

    @Override
    public int getMethod() {
        return ApiConstant.POST;
    }

    @Override
    protected String getAbsoluteUrl() {
        return AccountManager.getApiConstant().getSAVE_PROFILE_AVATAR();
    }
}
