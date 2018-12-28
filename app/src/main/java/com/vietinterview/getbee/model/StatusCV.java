package com.vietinterview.getbee.model;

import android.graphics.drawable.Drawable;

/**
 * Created by hiepnguyennghia on 12/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class StatusCV {
    String status;
    Drawable drawable;
    int color;

    public StatusCV(String status, Drawable drawable, int color) {
        this.status = status;
        this.drawable = drawable;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
