package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 12/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SkillDetailResumeCustomerFragment extends BaseFragment {
    @BindView(R.id.tvSkillPrimary)
    TextView tvSkillPrimary;
    @BindView(R.id.tvSkillOther)
    TextView tvSkillOther;
    @BindView(R.id.tvHobby)
    TextView tvHobby;
    DetailCVCustomerResponse mDetailCVCustomerResponse;

    public static SkillDetailResumeCustomerFragment newInstance(DetailCVCustomerResponse detailCVCustomerResponse) {
        SkillDetailResumeCustomerFragment fm = new SkillDetailResumeCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVCustomerResponse", detailCVCustomerResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_skill_detailcv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mDetailCVCustomerResponse = bundle.getParcelable("detailCVCustomerResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        if (mDetailCVCustomerResponse.getCvSkill() != null) {
            tvSkillPrimary.setText(mDetailCVCustomerResponse.getCvSkill().getPrimarySkill());
            tvHobby.setText(mDetailCVCustomerResponse.getCvSkill().getHobby());
            StringBuffer otherSkillBuffer = new StringBuffer("");
            for (int i = 0; i < mDetailCVCustomerResponse.getCvSkill().getLstOtherSkillName().size(); i++) {
                otherSkillBuffer.append(mDetailCVCustomerResponse.getCvSkill().getLstOtherSkillName().get(i) + "\n");
            }
            tvSkillOther.setText(otherSkillBuffer.toString());
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
