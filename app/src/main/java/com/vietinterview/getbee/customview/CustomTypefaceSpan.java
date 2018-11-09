package com.vietinterview.getbee.customview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import com.vietinterview.getbee.R;

/**
 * Created by hiepnguyennghia on 10/10/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CustomTypefaceSpan extends TypefaceSpan {

    private final Typeface newType;
    private static int mFontSize;

    public CustomTypefaceSpan(int fontsize, String family, Typeface type) {
        super(family);
        newType = type;
        mFontSize = fontsize;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, newType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);

        }
        paint.setTextSize(mFontSize);
        paint.setTypeface(tf);
    }
}