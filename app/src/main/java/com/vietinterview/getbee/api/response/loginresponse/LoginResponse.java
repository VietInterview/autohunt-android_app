
package com.vietinterview.getbee.api.response.loginresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("id_token")
    @Expose
    private String id_token;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginResponse() {
    }

    /**
     *
     * @param id_token
     */
    public LoginResponse(String id_token) {
        super();
        this.id_token = id_token;
    }


    public String getApiToken() {
        return id_token;
    }

    public void setApiToken(String id_token) {
        this.id_token = id_token;
    }

}
