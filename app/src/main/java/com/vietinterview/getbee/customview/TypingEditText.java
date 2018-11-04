package com.vietinterview.getbee.customview;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Hiep Nguyen Nghia on 8/5/2015.
 */
public class TypingEditText extends ClearableRegularEditText implements TextWatcher {

    private static final int TypingInterval = 1000;


    public interface OnTypingChanged {
        public void onTyping(EditText view, boolean isTyping);
    }

    private OnTypingChanged t;
    private Handler handler;

    {
        handler = new Handler();
    }

    public TypingEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.addTextChangedListener(this);
    }

    public TypingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.addTextChangedListener(this);
    }

    public TypingEditText(Context context) {
        super(context);
        this.addTextChangedListener(this);
    }

    public void setOnTypingChanged(OnTypingChanged t) {
        this.t = t;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (t != null) {
            t.onTyping(this, true);
            handler.removeCallbacks(notifier);
            handler.postDelayed(notifier, TypingInterval);
        }

    }

    private Runnable notifier = new Runnable() {

        @Override
        public void run() {
            if (t != null)
                t.onTyping(TypingEditText.this, false);
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }


    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    }
}
