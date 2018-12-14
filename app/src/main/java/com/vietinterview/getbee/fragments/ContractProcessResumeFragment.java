package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ContractProcessResumeFragment extends BaseFragment {
    DetailProcessResumeResponse detailProcessResumeResponse;

    public static ContractProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        ContractProcessResumeFragment fm = new ContractProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contract_process_resume;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {

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
