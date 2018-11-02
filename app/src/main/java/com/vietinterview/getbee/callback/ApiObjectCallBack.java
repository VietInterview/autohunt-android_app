package com.vietinterview.getbee.callback;

import java.util.List;

/**
 * Created by User on 9/30/2015.
 */
public interface ApiObjectCallBack<T, V> {
    void onSuccess(int status, T dataSuccess, List<T> listDataSuccess, String message);

    void onFail(int status, V dataFail, List<V> listDataFail, String message);
}
