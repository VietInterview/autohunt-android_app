package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.callback.OnSwitchToSixListener;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.utils.DebugLog;
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
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    @BindView(R.id.tvHobby)
    TextView tvHobby;
    @BindView(R.id.llContent)
    LinearLayout llContent;
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
        getEventBaseFragment().setOnSwitchToSixListener(new OnSwitchToSixListener() {
            @Override
            public void onSwitchToSix() {
                getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(llInfo));
            }
        });
        if (detailCVResponse.getCvSkill() != null) {
            tvSkillPrimary.setText(detailCVResponse.getCvSkill().getPrimarySkill());
            for (int i = 0; i < detailCVResponse.getCvSkill().getLstOtherSkillName().size(); i++) {
                LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(mFlowLayout, R.layout.other_skill_item_view, false);
                TextView tvSkillOther = (TextView) linearLayout.findViewById(R.id.tvSkillOther);
                tvSkillOther.setText(detailCVResponse.getCvSkill().getLstOtherSkillName().get(i));
                mFlowLayout.addView(linearLayout);
            }
            tvNodata.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);
            tvHobby.setText(detailCVResponse.getCvSkill().getHobby());
        } else {
            tvNodata.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.GONE);
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
