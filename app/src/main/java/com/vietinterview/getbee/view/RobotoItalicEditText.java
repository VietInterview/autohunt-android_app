package com.vietinterview.getbee.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by V4-OS01 on 25/10/2016.
 */

public class RobotoItalicEditText extends EditText {
    public  Typeface FONT_NAME;


    public RobotoItalicEditText(Context context) {
        super(context);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Italic.ttf");
        this.setTypeface(FONT_NAME);
    }

    public RobotoItalicEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Italic.ttf");
        this.setTypeface(FONT_NAME);
    }

    public RobotoItalicEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (FONT_NAME == null)
            FONT_NAME = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Italic.ttf");
        this.setTypeface(FONT_NAME);
    }
}