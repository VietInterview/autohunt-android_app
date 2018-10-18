package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoCreateCVFragment extends BaseFragment {
    @BindView(R.id.edtFullName)
    TextInputEditText edtFullName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_create_cv;
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
        getEventBaseFragment().changeStepExp(1);
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
