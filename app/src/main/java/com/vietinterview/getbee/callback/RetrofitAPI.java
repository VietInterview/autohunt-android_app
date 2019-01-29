package com.vietinterview.getbee.callback;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by hiepnguyennghia on 1/28/19.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public interface RetrofitAPI {
    @Multipart
    @POST("svccollaborator/api/saveProfileAvatar")
    Call<ResponseBody> addRecord(@Part MultipartBody.Part file);
}
