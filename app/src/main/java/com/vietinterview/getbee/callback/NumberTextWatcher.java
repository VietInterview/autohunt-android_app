package com.vietinterview.getbee.callback;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by V4-OS01 on 30/11/2016.
 */

public class NumberTextWatcher implements TextWatcher {

    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;

    private EditText et;

    public NumberTextWatcher(EditText et) {
//        NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
        df = new DecimalFormat("###,###.###");
//        df = (DecimalFormat) nf;
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("###,###.###");
//        dfnd = (DecimalFormat) nf;
        this.et = et;
        hasFractionalPart = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";

    @Override
    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);
        try {
            int inilen, endlen;
            inilen = et.getText().length();

            String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = df.parse(v);
            int cp = et.getSelectionStart();
            if (hasFractionalPart) {
                et.setText(df.format(n));
            } else {
                et.setText(dfnd.format(n));
            }
            endlen = et.getText().length();
            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= et.getText().length()) {
                et.setSelection(sel);
            } else {
                et.setSelection(et.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
        } catch (ParseException e) {
        }

        et.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()))) {
            hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
    }

}