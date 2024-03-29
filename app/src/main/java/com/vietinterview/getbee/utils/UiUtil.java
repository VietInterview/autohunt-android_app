package com.vietinterview.getbee.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Envy 15T on 6/5/2015.
 */
public class UiUtil {

    /**
     * Convert px to dp
     *
     * @param dp
     * @return
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Convert dp to px
     *
     * @param px
     * @return
     */
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getDpi(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int densityDpi = (int) (metrics.density * 160f);
        return densityDpi;
    }

    /**
     * Get device screen width in pixel
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenWidthInPixel(Activity activity) {
        Point size = new Point();
        WindowManager w = activity.getWindowManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            w.getDefaultDisplay().getSize(size);
            return size.x;
        } else {
            Display d = w.getDefaultDisplay();
            return d.getWidth();
        }
    }

    /**
     * Safety hide view
     *
     * @param view
     */
    public static void hideView(View view, boolean isGone) {
        if (view != null) {
            if (isGone) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * Safety show view
     *
     * @param view
     */
    public static void showView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Check EditText is empty
     *
     * @param etText
     * @return
     */
    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    /**
     * Check TextView is empty
     *
     * @param tvText
     * @return
     */
    public static boolean isEmpty(TextView tvText) {
        return tvText.getText().toString().trim().length() == 0;
    }

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }
}
