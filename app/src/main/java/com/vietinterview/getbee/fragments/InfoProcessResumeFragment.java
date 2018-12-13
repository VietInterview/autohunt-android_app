package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.vietinterview.getbee.R;

import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoProcessResumeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_process_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btnNext)
    public void onClickNext() {
        mNotifyDialog = new Dialog(getActivity());
        mNotifyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mNotifyDialog.setContentView(R.layout.dialog_noti_interview);
        mNotifyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mNotifyDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Window window = mNotifyDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.y = 300;
        window.setAttributes(wlp);
        Button btnOK = (Button) mNotifyDialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEventBaseFragment().changeStepExp(1);
                mNotifyDialog.dismiss();
            }
        });
        mNotifyDialog.show();
    }

    private Dialog mReasonNotAcceptDialog;
    private Dialog mNotifyDialog;

    @OnClick(R.id.btnNotAccept)
    public void onNotAcceptClick() {
        mReasonNotAcceptDialog = new Dialog(getActivity());
        mReasonNotAcceptDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mReasonNotAcceptDialog.setContentView(R.layout.dialog_reason_not_accept);
        mReasonNotAcceptDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mReasonNotAcceptDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Window window = mReasonNotAcceptDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.y = 300;
        window.setAttributes(wlp);
        Button btnOK = (Button) mReasonNotAcceptDialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReasonNotAcceptDialog.dismiss();
            }
        });
        mReasonNotAcceptDialog.show();
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }

    @Override
    protected void onRestore() {

    }
}
