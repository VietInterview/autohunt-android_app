package com.vietinterview.getbee.callback;


import com.vietinterview.getbee.model.User;

import java.util.List;

/**
 * Created by Allen on 01-Apr-16.
 */
public interface OnLoadUserResult {
    public void onLoadUserSuccess(List<User> userList);

    public void onLoadUserError();
}
