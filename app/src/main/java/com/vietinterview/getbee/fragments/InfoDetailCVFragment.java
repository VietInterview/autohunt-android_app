package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoDetailCVFragment extends BaseFragment {
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvAdd)
    TextView tvAdd;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvSignle)
    TextView tvSignle;
    @BindView(R.id.tvPositionWish)
    TextView tvPositionWish;
    @BindView(R.id.tvLevelNow)
    TextView tvLevelNow;
    @BindView(R.id.tvLevelWish)
    TextView tvLevelWish;
    @BindView(R.id.tvCarrer)
    TextView tvCarrer;
    @BindView(R.id.tvAddWork)
    TextView tvAddWork;
    @BindView(R.id.tvEducation)
    TextView tvEducation;
    @BindView(R.id.tvExp)
    TextView tvExp;
    @BindView(R.id.tvSalary)
    TextView tvSalary;
    @BindView(R.id.tvTarget)
    TextView tvTarget;
    DetailCVResponse detailCVResponse;

    public static InfoDetailCVFragment newInstance(DetailCVResponse detailCVResponse) {
        InfoDetailCVFragment fm = new InfoDetailCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVResponse", detailCVResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        tvPhone.setText(detailCVResponse.getPhone());
        tvEmail.setText(detailCVResponse.getEmail());
        if (detailCVResponse.getSex() != null) {
            if (detailCVResponse.getSex() == 1)
                tvGender.setText(getResources().getString(R.string.male));
            else if (detailCVResponse.getSex() == 0)
                tvGender.setText(getResources().getString(R.string.female));
            else tvGender.setText(getResources().getString(R.string.other_sex));
        }
        tvAdd.setText(detailCVResponse.getAddress());
        tvCity.setText(detailCVResponse.getCity().getName());
        if (detailCVResponse.getMaritalStatus() == 1) {
            tvSignle.setText(getResources().getString(R.string.not_have_married));
        } else {
            tvSignle.setText(getResources().getString(R.string.have_married));
        }
        tvPositionWish.setText(detailCVResponse.getCvTitle());
        tvLevelNow.setText(detailCVResponse.getCurrentLevel().getName());
        tvLevelWish.setText(detailCVResponse.getDesiredLevel().getName());
        tvCarrer.setText(detailCVResponse.getCareerName());
        tvAddWork.setText(detailCVResponse.getJobListcityName());
        tvEducation.setText(detailCVResponse.getEducationLevel().getName());
        tvExp.setText(detailCVResponse.getExperienceYear().getName());
        tvSalary.setText(detailCVResponse.getDesiredSalary() + "");
        tvTarget.setText(detailCVResponse.getCareerObjectives());
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
