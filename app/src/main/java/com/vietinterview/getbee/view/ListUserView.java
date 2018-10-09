package com.vietinterview.getbee.view;


import com.vietinterview.getbee.model.User;

import java.util.List;

/**
 * Created by Allen on 01-Apr-16.
 */
public interface ListUserView {
    void reload();

    void showNoData();

    void showError();

    void displayListuser(List<User> listUser);

}
