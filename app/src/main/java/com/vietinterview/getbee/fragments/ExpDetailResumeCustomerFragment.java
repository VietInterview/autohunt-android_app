package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExpDetailResumeCustomerAdapter;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 11/29/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ExpDetailResumeCustomerFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    private ExpDetailResumeCustomerAdapter expDetailCVNTDAdapter;
    DetailCVCustomerResponse mDetailCVCustomerResponse;

    public static ExpDetailResumeCustomerFragment newInstance(DetailCVCustomerResponse detailCVCustomerResponse) {
        ExpDetailResumeCustomerFragment fm = new ExpDetailResumeCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVCustomerResponse", detailCVCustomerResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_exp_cv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mDetailCVCustomerResponse = bundle.getParcelable("detailCVCustomerResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
//        searchMyCV(0, 0, 0);
        expDetailCVNTDAdapter = new ExpDetailResumeCustomerAdapter(recyclerView, getActivity(), mDetailCVCustomerResponse.getLstEmploymentHis(), this);
        recyclerView.setAdapter(expDetailCVNTDAdapter);
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
