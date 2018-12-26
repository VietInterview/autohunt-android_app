package com.vietinterview.getbee.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.utils.ViewUtils;

/**
 * Created by hiepnguyennghia on 12/26/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RoundLinerLayoutNormal extends LinearLayout {
    public RoundLinerLayoutNormal(Context context) {
        super(context);
        initBackground();
    }

    public RoundLinerLayoutNormal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBackground();
    }

    public RoundLinerLayoutNormal(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBackground();
    }

    private void initBackground() {
        setBackground(ViewUtils.generateBackgroundWithShadow(this, R.color.white,
                R.dimen.radius_corner, R.color.shadowColor, R.dimen.elevation, Gravity.BOTTOM));
    }
}