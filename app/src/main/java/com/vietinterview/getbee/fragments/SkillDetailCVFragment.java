package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SkillDetailCVFragment extends BaseFragment {
    DetailCVResponse detailCVResponse;

    public static SkillDetailCVFragment newInstance(DetailCVResponse detailCVResponse) {
        SkillDetailCVFragment fm = new SkillDetailCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVResponse", detailCVResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_skill_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {

    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailCVResponse = bundle.getParcelable("detailCVResponse");
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
