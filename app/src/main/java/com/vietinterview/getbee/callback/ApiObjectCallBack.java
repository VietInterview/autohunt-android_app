package com.vietinterview.getbee.callback;

/**
 * Created by User on 9/30/2015.
 */
public interface ApiObjectCallBack<T> {
    void onSuccess(T data, int status);

    void onFail(int failCode, String message);
    void onFail(int failCode,T data, String message);
}