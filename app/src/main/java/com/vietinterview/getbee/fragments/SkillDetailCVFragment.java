package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.customview.NunitoTextView;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.LayoutUtils;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SkillDetailCVFragment extends BaseFragment {
    @BindView(R.id.tvSkillPrimary)
    TextView tvSkillPrimary;
    @BindView(R.id.container)
    FlowLayout mFlowLayout;
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
        if (detailCVResponse.getLstCvSkill().size() > 0) {
            tvSkillPrimary.setText(detailCVResponse.getLstCvSkill().get(0).getPrimarySkill());
            mFlowLayout.removeAllViews();
            for (int i = 0; i < detailCVResponse.getLstCvSkill().size(); i++) {
                LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(mFlowLayout, R.layout.other_skill_item_view, false);
                TextView tvSkillOther = (TextView) linearLayout.findViewById(R.id.tvSkillOther);
                tvSkillOther.setText(detailCVResponse.getLstCvSkill().get(i).getOtherSkill());
                mFlowLayout.addView(linearLayout);
            }
        }

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
