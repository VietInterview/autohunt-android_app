package com.vietinterview.getbee.api.response;

/**
 * Created by User on 9/29/2015.
 */
public class BaseResponse {

    public int status;

    public String message;

//    public String errors;

    public boolean isSuccess() {
        return status == 1;
    }

}
