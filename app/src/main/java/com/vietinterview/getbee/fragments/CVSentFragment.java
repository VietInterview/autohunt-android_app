package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.customview.NunitoTextView;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.LayoutUtils;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/11/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CVSentFragment extends BaseFragment {
    @BindView(R.id.container)
    FlowLayout mFlowLayout;
    @BindView(R.id.tvCount)
    TextView tvCount;
    DetailJobResponse detailJobResponse;

    public static CVSentFragment newInstance(DetailJobResponse detailJobResponse) {
        CVSentFragment fm = new CVSentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailJobResponse", detailJobResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cv_sent;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        if (detailJobResponse.getLstJobApply() != null) {
            tvCount.setText(detailJobResponse.getLstJobApply().size() + " " + getResources().getString(R.string.cv_submited_title));
            mFlowLayout.removeAllViews();
            for (int i = 0; i < detailJobResponse.getLstJobApply().size(); i++) {
                LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(mFlowLayout, R.layout.cv_sent_item_view, false);
                final int mPosition = i;
                TextView tvName = (TextView) linearLayout.findViewById(R.id.tvName);
                TextView tvDate = (TextView) linearLayout.findViewById(R.id.tvDate);
                NunitoTextView btnStatus = (NunitoTextView) linearLayout.findViewById(R.id.tvStatus);
                LinearLayout llStatus = (LinearLayout) linearLayout.findViewById(R.id.llStatus);
                tvName.setText(detailJobResponse.getLstJobApply().get(i).getFullName());
                tvDate.setText(DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(detailJobResponse.getLstJobApply().get(i).getCreatedDate()) + ""));
                if (detailJobResponse.getLstJobApply().get(i).getStatus() == 1) {
                    btnStatus.setText(getResources().getString(R.string.not_send));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_notyet_send));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 2) {
                    btnStatus.setText(getResources().getString(R.string.wait_approve));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_red));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 3) {
                    btnStatus.setText(getResources().getString(R.string.approved));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_yellow));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 4) {
                    btnStatus.setText(getResources().getString(R.string.seen));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_yellow));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 5) {
                    btnStatus.setText(getResources().getString(R.string.not_accept));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_notyet_send));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 6) {
                    btnStatus.setText(getResources().getString(R.string.invite_interview));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_red));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 7) {
                    btnStatus.setText(getResources().getString(R.string.interviewd));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_yellow));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 8) {
                    btnStatus.setText(getResources().getString(R.string.offered));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
                } else if (detailJobResponse.getLstJobApply().get(i).getStatus() == 9) {
                    btnStatus.setText(getResources().getString(R.string.accept_offer));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_yellow));
                } else {
                    btnStatus.setText(getResources().getString(R.string.cv_submited));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_yellow));
                }
                mFlowLayout.addView(linearLayout);
            }
        } else {
            tvCount.setText("0 " + getResources().getString(R.string.cv_submited_title));
        }
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailJobResponse = bundle.getParcelable("detailJobResponse");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRestore() {

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
}
