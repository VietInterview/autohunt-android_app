package com.vietinterview.getbee.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.callback.OnSwitchToOneListener;
import com.vietinterview.getbee.utils.LayoutUtils;

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
    @BindView(R.id.llPhone)
    LinearLayout llPhone;
    @BindView(R.id.llEmail)
    LinearLayout llEmail;
    @BindView(R.id.llSex)
    LinearLayout llSex;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    @BindView(R.id.llCity)
    LinearLayout llCity;
    @BindView(R.id.llSignle)
    LinearLayout llSignle;
    @BindView(R.id.llPositionWish)
    LinearLayout llPositionWish;
    @BindView(R.id.llLevelNow)
    LinearLayout llLevelNow;
    @BindView(R.id.llLevelWish)
    LinearLayout llLevelWish;
    @BindView(R.id.llCarrer)
    LinearLayout llCarrer;
    @BindView(R.id.llAddWork)
    LinearLayout llAddWork;
    @BindView(R.id.llEducation)
    LinearLayout llEducation;
    @BindView(R.id.llExp)
    LinearLayout llExp;
    @BindView(R.id.llSalary)
    LinearLayout llSalary;
    @BindView(R.id.llTarget)
    LinearLayout llTarget;
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    DetailCVResponse detailCVResponse;
    ViewTreeObserver vto;

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
        vto = llInfo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    llInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    llInfo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int height = llInfo.getMeasuredHeight();
                getEventBaseFragment().setHeightView(height);
            }
        });
        getEventBaseFragment().setOnSwitchToOneListener(new OnSwitchToOneListener() {
            @Override
            public void onSwitchToOne() {
                getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(llInfo));
            }
        });
        llPhone.setVisibility(detailCVResponse.getPhone() != null ? View.VISIBLE : View.GONE);
        llEmail.setVisibility(detailCVResponse.getEmail() != null ? View.VISIBLE : View.GONE);
        llSex.setVisibility(detailCVResponse.getSex() != null ? View.VISIBLE : View.GONE);
        llAdd.setVisibility(detailCVResponse.getAddress() != null ? View.VISIBLE : View.GONE);
        llCity.setVisibility(detailCVResponse.getCity() != null ? View.VISIBLE : View.GONE);
        llSignle.setVisibility(detailCVResponse.getMaritalStatus() != null ? View.VISIBLE : View.GONE);
        llPositionWish.setVisibility(detailCVResponse.getCvTitle() != null ? View.VISIBLE : View.GONE);
        llLevelNow.setVisibility(detailCVResponse.getCurrentLevel() != null ? View.VISIBLE : View.GONE);
        llLevelWish.setVisibility(detailCVResponse.getDesiredLevel() != null ? View.VISIBLE : View.GONE);
        llCarrer.setVisibility(detailCVResponse.getCareerName() != null ? View.VISIBLE : View.GONE);
        llAddWork.setVisibility(detailCVResponse.getJobListcityName() != null ? View.VISIBLE : View.GONE);
        llEducation.setVisibility(detailCVResponse.getEducationLevel() != null ? View.VISIBLE : View.GONE);
        llExp.setVisibility(detailCVResponse.getExperienceYear() != null ? View.VISIBLE : View.GONE);
        llSalary.setVisibility(detailCVResponse.getDesiredSalary() != null ? View.VISIBLE : View.GONE);
        llTarget.setVisibility(detailCVResponse.getCareerObjectives() != null ? View.VISIBLE : View.GONE);
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
        if (detailCVResponse.getMaritalStatus() != null) {
            if (detailCVResponse.getMaritalStatus() == 1) {
                tvSignle.setText(getResources().getString(R.string.not_have_married));
            } else {
                tvSignle.setText(getResources().getString(R.string.have_married));
            }
        }
        tvPositionWish.setText(detailCVResponse.getCvTitle());
        tvLevelNow.setText(detailCVResponse.getCurrentLevel().getName());
        tvLevelWish.setText(detailCVResponse.getDesiredLevel().getName());
        tvCarrer.setText(detailCVResponse.getCareerName());
        tvAddWork.setText(detailCVResponse.getJobListcityName());
        tvEducation.setText(detailCVResponse.getEducationLevel().getName());
        tvExp.setText(detailCVResponse.getExperienceYear().getName());
        tvSalary.setText(detailCVResponse.getDesiredSalary() + " USD");
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
