package com.vietinterview.getbee.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by V4-OS01 on 25/10/2016.
 */

public class RobotoTextView extends TextView {
    public Typeface FONT_NAME;


    public RobotoTextView(Context context) {
        super(context);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(FONT_NAME);
//        this.setTextSize(getResources().getDimensionPixelSize(R.dimen.sixe_text));
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(FONT_NAME);
//        this.setTextSize(getResources().getDimensionPixelSize(R.dimen.sixe_text));
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        this.setTypeface(FONT_NAME);
//        this.setTextSize(getResources().getDimensionPixelSize(R.dimen.sixe_text));
    }
}