package com.vietinterview.getbee.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.TypedValue;


/**
 * Created by V4-OS01 on 25/10/2016.
 */

public class NunitoTextInputEditText extends TextInputEditText {
    public Typeface FONT_NAME;


    public NunitoTextInputEditText(Context context) {
        super(context);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Nunito-Regular.ttf");
        this.setTypeface(FONT_NAME);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(255)});
    }

    public NunitoTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Nunito-Regular.ttf");
        this.setTypeface(FONT_NAME);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(255)});
    }

    public NunitoTextInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/Nunito-Regular.ttf");
        this.setTypeface(FONT_NAME);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(255)});
    }
}