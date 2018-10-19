package com.vietinterview.getbee.model;

/**
 * Created by hiepnguyennghia on 10/19/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DropDownItems {
    String txt;
    int value;
    Boolean showValue;

    public DropDownItems(String txt, int value, Boolean showValue) {
        this.txt = txt;
        this.value = value;
        this.showValue = showValue;
    }

    public String getTxt() {
        return txt;
    }

    public int getValue() {
        return value;
    }

    public Boolean getShowValue() {
        return showValue;
    }
}
