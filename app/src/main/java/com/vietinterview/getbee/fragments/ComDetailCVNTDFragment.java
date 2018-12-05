package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 12/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ComDetailCVNTDFragment extends BaseFragment {
    @BindView(R.id.tvWord)
    TextView tvWord;
    @BindView(R.id.tvExcel)
    TextView tvExcel;
    @BindView(R.id.tvPowerPoint)
    TextView tvPowerPoint;
    @BindView(R.id.tvOutLook)
    TextView tvOutLook;
    @BindView(R.id.tvOtherSoft)
    TextView tvOtherSoft;
    DetailCVCustomerResponse mDetailCVCustomerResponse;

    public static ComDetailCVNTDFragment newInstance(DetailCVCustomerResponse detailCVCustomerResponse) {
        ComDetailCVNTDFragment fm = new ComDetailCVNTDFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVCustomerResponse", detailCVCustomerResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_com_detailcv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mDetailCVCustomerResponse = bundle.getParcelable("detailCVCustomerResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        if (mDetailCVCustomerResponse.getLstComputerSkill().size() > 0) {
            tvWord.setText(StringUtils.genStringLan(mDetailCVCustomerResponse.getLstComputerSkill().get(0).getMsWord(), getActivity()));
            tvPowerPoint.setText(StringUtils.genStringLan(mDetailCVCustomerResponse.getLstComputerSkill().get(0).getMsPowerPoint(), getActivity()));
            tvOutLook.setText(StringUtils.genStringLan(mDetailCVCustomerResponse.getLstComputerSkill().get(0).getMsOutlook(), getActivity()));
            tvExcel.setText(StringUtils.genStringLan(mDetailCVCustomerResponse.getLstComputerSkill().get(0).getMsExcel(), getActivity()));
            tvOtherSoft.setText(mDetailCVCustomerResponse.getLstComputerSkill().get(0).getOther());
        }

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
