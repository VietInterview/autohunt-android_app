package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rafakob.floatingedittext.FloatingEditText;
import com.vietinterview.getbee.R;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoCreateCVFragment extends BaseFragment {
    @BindView(R.id.fedtName)
    FloatingEditText fedtName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_create_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        fedtName.setHint("Họ và tên" + Html.fromHtml("<font color='red'>*</font>"));
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

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
