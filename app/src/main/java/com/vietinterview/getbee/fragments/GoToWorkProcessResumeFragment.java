package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;

import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/18/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GoToWorkProcessResumeFragment extends BaseFragment {
    DetailProcessResumeResponse detailProcessResumeResponse;

    public static GoToWorkProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        GoToWorkProcessResumeFragment fm = new GoToWorkProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gotowork_process_resume;
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
    public void onNextClick() {
        getEventBaseFragment().changeStepExp(4);
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
