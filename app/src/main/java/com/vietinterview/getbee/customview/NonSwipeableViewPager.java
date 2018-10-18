package com.vietinterview.getbee.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.vietinterview.getbee.utils.DebugLog;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class NonSwipeableViewPager extends ViewPager {

    private boolean enabled;

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled && detectSwipeToRight(event)) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled && detectSwipeToRight(event)) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    // To enable/disable swipe
    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Detects the direction of swipe. Right or left.
// Returns true if swipe is in right direction
    public boolean detectSwipeToRight(MotionEvent event) {

        int initialXValue = 0; // as we have to detect swipe to right
        final int SWIPE_THRESHOLD = 100; // detect swipe
        boolean result = false;

        try {
            float diffX = event.getX() - initialXValue;

            if (Math.abs(diffX) > SWIPE_THRESHOLD) {
                if (diffX > 0) {
                    // swipe from left to right detected ie.SwipeRight
//                    DebugLog.showLogCat("Right");
                    result = false;
                } else {
                    // swipe from right to left detected ie.SwipeLeft
//                    DebugLog.showLogCat("Left");
                    result = true;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
}