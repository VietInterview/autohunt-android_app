package com.vietinterview.getbee.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


/**
 * Created by V4-OS01 on 25/10/2016.
 */

public class RobotoRegularButton extends AppCompatButton {
    public Typeface FONT_NAME;


    public RobotoRegularButton(Context context) {
        super(context);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(FONT_NAME);
//        this.setText(getText().toString().toLowerCase());
        setSupportAllCaps(false);
    }

    public RobotoRegularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(FONT_NAME);
//        this.setText(getText().toString().toLowerCase());
        setSupportAllCaps(false);
    }

    public RobotoRegularButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(FONT_NAME);
//        this.setText(getText().toString().toLowerCase());
        setSupportAllCaps(false);
    }
}