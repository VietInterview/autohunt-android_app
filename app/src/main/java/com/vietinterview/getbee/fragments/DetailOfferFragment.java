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
import com.vietinterview.getbee.api.response.detailprocessresume.LstOfferHi;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailOfferFragment extends BaseFragment {
    @BindView(R.id.tvRound)
    TextView tvRound;
    @BindView(R.id.tvSalary)
    TextView tvSalary;
    @BindView(R.id.tvWorkTime)
    TextView tvWorkTime;
    @BindView(R.id.tvWorkAddress)
    TextView tvWorkAddress;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.tvResult)
    TextView tvResult;
    LstOfferHi lstOfferHi;

    public static DetailOfferFragment newInstance(LstOfferHi lstOfferHi) {
        DetailOfferFragment fm = new DetailOfferFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lstOfferHi", lstOfferHi);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_offer;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        lstOfferHi = bundle.getParcelable("lstOfferHi");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.info_offer));
        GlobalDefine.currentFragment = this;
        tvRound.setText(lstOfferHi.getRound());
        tvSalary.setText(StringUtils.filterCurrencyString(lstOfferHi.getSalary()) + " " + StringUtils.genStringCurrency(lstOfferHi.getCurency()));
        tvWorkTime.setText(lstOfferHi.getWorkTime());
        tvWorkAddress.setText(lstOfferHi.getWorkAddress());
        tvPosition.setText(lstOfferHi.getPosition());
        tvNote.setText(lstOfferHi.getNote());
        tvResult.setText(switchResult(lstOfferHi.getStatus()));
    }

    public String switchResult(int status) {
        String statusInterview = "";
        switch (status) {
            case 1:
                statusInterview = getResources().getString(R.string.accept);
                break;
            case 2:
                statusInterview = getResources().getString(R.string.not_accept);
                break;
            default:
                statusInterview = getResources().getString(R.string.no_results);
                break;
        }
        return statusInterview;
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
