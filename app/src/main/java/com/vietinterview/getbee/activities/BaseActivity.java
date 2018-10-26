package com.vietinterview.getbee.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.akexorcist.localizationactivity.LocalizationActivity;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.callback.DetectSwipeGestureListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.model.Event;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.EventBusHelper;
import com.vietinterview.getbee.utils.KeyboardUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import butterknife.ButterKnife;

/**
 * Created by Envy 15T on 6/4/2015.
 */
public abstract class BaseActivity extends LocalizationActivity {

    AlertDialog dialogErrorAPI;
    AlertDialog dialogTimeOutAPI;
    AlertDialog dialogNoConnection;
    BaseActivity baseActivity;
    boolean isUnregistEventBus = false;
    private Event eventBaseActivity;
    private GestureDetectorCompat gestureDetectorCompat = null;
    DetectSwipeGestureListener gestureListener = null;

    public DetectSwipeGestureListener getGestureListener() {
        return gestureListener;
    }

    public void setGestureListener(DetectSwipeGestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setLanguage(SharedPrefUtils.getString(AppConstant.LANGUAGE, "vi"));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        onPreSetContentView(savedInstanceState);
        super.onCreate(savedInstanceState);
        baseActivity = this;
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            handleDeepLinkData(intent.getData());
        }
        setContentView(setContentViewId());
        ButterKnife.bind(this);
        EventBusHelper.register(this);
        isUnregistEventBus = false;
        eventBaseActivity = new Event();
        initView();
        initData();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.yellow));
        // Create a common gesture listener object.
        gestureListener = new DetectSwipeGestureListener();

        // Set activity in the listener.
        gestureListener.setActivity(this);

        // Create the gesture detector with the gesture listener.
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public Event getEventBaseActivity() {
        return eventBaseActivity;
    }

    public GestureDetectorCompat getGestureDetectorCompat() {
        return gestureDetectorCompat;
    }


    protected boolean checkApiDialogIsShow() {
        return dialogErrorAPI.isShowing() || dialogTimeOutAPI.isShowing() || dialogNoConnection.isShowing();
    }

    @Override
    protected void onDestroy() {
        if (!isUnregistEventBus) {
            EventBusHelper.unregister(this);
        }
        super.onDestroy();
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(String event) {
//        switch (event.apiEventType) {
//            case SHOW_API_ERROR_DIALOG:
//                showApiDialog(dialogErrorAPI);
//                break;
//            case SHOW_API_TIMEOUT_DIALOG:
//                showApiDialog(dialogTimeOutAPI);
//                break;
//            case SHOW_API_NO_CONNECTION_DIALOG:
//                showApiDialog(dialogNoConnection);
//                break;
//        }
    }

    private void showApiDialog(AlertDialog alertDialog) {
        if (alertDialog != null && !checkApiDialogIsShow()) {
            alertDialog.show();
        }
    }

    /**
     * Handle data before setContentView call
     *
     * @param savedInstanceState
     */
    protected void onPreSetContentView(Bundle savedInstanceState) {

    }

    /**
     * Handle deep link data
     *
     * @param uri
     */
    protected void handleDeepLinkData(Uri uri) {
        DebugLog.i("uri: " + uri.toString());
    }

    /**
     * @return layout of activity
     */
    public abstract int setContentViewId();

    /**
     * Define your view
     */
    public abstract void initView();

    /**
     * Setup your data
     */
    public abstract void initData();


    @Override
    public void startActivity(Intent intent) {
        EventBusHelper.unregister(this);
        isUnregistEventBus = true;
        super.startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isUnregistEventBus) {
            EventBusHelper.register(this);
            isUnregistEventBus = false;
        }
    }

    public void hideKeyBoardWhenTouchOutside(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    KeyboardUtil.hideSoftKeyboard(BaseActivity.this);
                    return false;
                }
            });
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
