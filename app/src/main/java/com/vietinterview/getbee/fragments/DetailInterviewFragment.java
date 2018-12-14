package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.utils.FragmentUtil;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 12/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailInterviewFragment extends BaseFragment {
    @BindView(R.id.tvRound)
    TextView tvRound;
    @BindView(R.id.tvDateInterview)
    TextView tvDateInterview;
    @BindView(R.id.tvPlaceInterview)
    TextView tvPlaceInterview;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.tvResult)
    TextView tvResult;
    LstInterviewHi lstInterviewHi;

    public static DetailInterviewFragment newInstance(LstInterviewHi lstInterviewHi) {
        DetailInterviewFragment fm = new DetailInterviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lstInterviewHi", lstInterviewHi);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_interview;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        lstInterviewHi = bundle.getParcelable("lstInterviewHi");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
    }

    @Override
    protected void initData() {
        tvRound.setText(lstInterviewHi.getRound());
        tvDateInterview.setText(lstInterviewHi.getInterviewDate());
        tvNote.setText(lstInterviewHi.getNote());
        tvResult.setText(switchResult(lstInterviewHi.getStatus()));
        tvPlaceInterview.setText(lstInterviewHi.getInterviewAddress());
    }

    public String switchResult(int status) {
        String statusINterview = "";
        switch (status) {
            case 1:
                statusINterview = "Đạt";
                break;
            case 2:
                statusINterview = "Không đạt";
                break;
            case 3:
                statusINterview = "Ứng viên không đến";
                break;
            default:
                statusINterview = "Chưa có kết quả";
                break;
        }
        return statusINterview;
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

    @Override
    protected void processOnBackPress() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }
}
