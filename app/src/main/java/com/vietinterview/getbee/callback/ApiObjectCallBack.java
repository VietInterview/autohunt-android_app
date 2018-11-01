package com.vietinterview.getbee.callback;

import java.util.List;

/**
 * Created by User on 9/30/2015.
 */
public interface ApiObjectCallBack<T, V> {
    void onSuccess(T data, List<T> dataArrayList, int status, String message);

    void onFail(int failCode, T dataSuccess, V dataFail, List<T> dataArrayList, String message);
}
