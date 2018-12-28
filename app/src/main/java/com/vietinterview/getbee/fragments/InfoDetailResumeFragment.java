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
import com.vietinterview.getbee.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoDetailResumeFragment extends BaseFragment {
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
    @BindView(R.id.tvworkingForm)
    TextView tvworkingForm;
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
    @BindView(R.id.llworkingForm)
    LinearLayout llworkingForm;
    DetailCVResponse detailCVResponse;
    ViewTreeObserver vto;

    public static InfoDetailResumeFragment newInstance(DetailCVResponse detailCVResponse) {
        InfoDetailResumeFragment fm = new InfoDetailResumeFragment();
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
        llPositionWish.setVisibility(detailCVResponse.getDesiredPosition() != null ? View.VISIBLE : View.GONE);
        llLevelNow.setVisibility(detailCVResponse.getCurrentLevel() != null ? View.VISIBLE : View.GONE);
        llLevelWish.setVisibility(detailCVResponse.getDesiredLevel() != null ? View.VISIBLE : View.GONE);
        llCarrer.setVisibility(detailCVResponse.getLstCareer().size() > 0 ? View.VISIBLE : View.GONE);
        llAddWork.setVisibility(detailCVResponse.getLstJobCity().size() > 0 ? View.VISIBLE : View.GONE);
        llEducation.setVisibility(detailCVResponse.getEducationLevel() != null ? View.VISIBLE : View.GONE);
        llExp.setVisibility(detailCVResponse.getExperienceYear() != null ? View.VISIBLE : View.GONE);
        llSalary.setVisibility(detailCVResponse.getDesiredSalary() != null ? View.VISIBLE : View.GONE);
        llTarget.setVisibility(detailCVResponse.getCareerObjectives() != null ? View.VISIBLE : View.GONE);
        llworkingForm.setVisibility(detailCVResponse.getWorkingForm() != null ? View.VISIBLE : View.GONE);
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
        tvPositionWish.setText(detailCVResponse.getDesiredPosition());
        tvLevelNow.setText(detailCVResponse.getCurrentLevel().getName());
        tvLevelWish.setText(detailCVResponse.getDesiredLevel().getName());
        StringBuilder s0 = new StringBuilder("");
        for (int i = 0; i < detailCVResponse.getLstCareer().size(); i++) {
            if (i != detailCVResponse.getLstCareer().size() - 1) {
                s0.append(detailCVResponse.getLstCareer().get(i).getName() + ", ");
            } else {
                s0.append(detailCVResponse.getLstCareer().get(i).getName() + "");
            }
        }
        tvCarrer.setText(s0.toString());
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < detailCVResponse.getLstJobCity().size(); i++) {
            if (i != detailCVResponse.getLstJobCity().size() - 1) {
                s.append(detailCVResponse.getLstJobCity().get(i).getName() + ", ");
            } else {
                s.append(detailCVResponse.getLstJobCity().get(i).getName() + "");
            }
        }
        tvAddWork.setText(s.toString());
        tvEducation.setText(detailCVResponse.getEducationLevel().getName());
        tvExp.setText(detailCVResponse.getExperienceYear().getName());
        tvSalary.setText(StringUtils.filterCurrencyString(detailCVResponse.getDesiredSalary()) + " " + detailCVResponse.getCurrencyName());
        tvTarget.setText(detailCVResponse.getCareerObjectives());
        tvworkingForm.setText(detailCVResponse.getWorkingForm().getName());
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