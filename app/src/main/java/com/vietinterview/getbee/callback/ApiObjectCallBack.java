package com.vietinterview.getbee.callback;

import java.util.List;

/**
 * Created by User on 9/30/2015.
 */
public interface ApiObjectCallBack<T> {
    void onSuccess(T data, List<T> tArrayList, int status);
    void onFail(int failCode,T data, String message);
}
