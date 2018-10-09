package com.vietinterview.getbee.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Created by phuongnd23 on 6/8/2017.
 */

public class RobotoRegularRadioButton extends android.support.v7.widget.AppCompatRadioButton {
    public  Typeface FONT_NAME;
    public RobotoRegularRadioButton(Context context) {
        super(context);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        this.setTypeface(FONT_NAME);
    }

    public RobotoRegularRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        this.setTypeface(FONT_NAME);
    }

    public RobotoRegularRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        this.setTypeface(FONT_NAME);
    }
}
