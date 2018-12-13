package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.utils.DateUtil;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 11/28/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoDetailResumeCustomerFragment extends BaseFragment {
    @BindView(R.id.tvBirthDay)
    TextView tvBirthDay;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvMarried)
    TextView tvMarried;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvPositionWish)
    TextView tvPositionWish;
    @BindView(R.id.tvCurrentLevel)
    TextView tvCurrentLevel;
    @BindView(R.id.tvLevelWish)
    TextView tvLevelWish;
    @BindView(R.id.tvCarrer)
    TextView tvCarrer;
    @BindView(R.id.tvWorkPLace)
    TextView tvWorkPLace;
    @BindView(R.id.tvCertificate)
    TextView tvCertificate;
    @BindView(R.id.tvExpYear)
    TextView tvExpYear;
    @BindView(R.id.tvSalaryWish)
    TextView tvSalaryWish;
    @BindView(R.id.tvTarget)
    TextView tvTarget;
    @BindView(R.id.btShowmore)
    Button btShowmore;
    @BindView(R.id.gradientView)
    LinearLayout gradientView;
    DetailCVCustomerResponse mDetailCVCustomerResponse;

    public static InfoDetailResumeCustomerFragment newInstance(DetailCVCustomerResponse detailCVCustomerResponse) {
        InfoDetailResumeCustomerFragment fm = new InfoDetailResumeCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVCustomerResponse", detailCVCustomerResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_detailcv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mDetailCVCustomerResponse = bundle.getParcelable("detailCVCustomerResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        tvBirthDay.setText(DateUtil.convertToMyFormatFull(mDetailCVCustomerResponse.getBirthday() + ""));
        if (mDetailCVCustomerResponse.getSex() == 1) {
            tvGender.setText(getResources().getString(R.string.male));
        } else if (mDetailCVCustomerResponse.getSex() == 2) {
            tvGender.setText(getResources().getString(R.string.female));
        } else tvGender.setText(getResources().getString(R.string.other_sex));
        tvMarried.setText(mDetailCVCustomerResponse.getMaritalStatus() == 1 ? getResources().getString(R.string.not_have_married) : getResources().getString(R.string.have_married));
        tvCity.setText(mDetailCVCustomerResponse.getCity().getName());
        tvPositionWish.setText(mDetailCVCustomerResponse.getDesiredPosition());
        tvCurrentLevel.setText(mDetailCVCustomerResponse.getCurrentLevel().getName());
        tvLevelWish.setText(mDetailCVCustomerResponse.getDesiredLevel().getName());
        StringBuffer carrerBuffer = new StringBuffer("");
        for (int i = 0; i < mDetailCVCustomerResponse.getLstCareer().size(); i++) {
            if (i == mDetailCVCustomerResponse.getLstCareer().size() - 1) {
                carrerBuffer.append(mDetailCVCustomerResponse.getLstCareer().get(i).getName());
            } else {
                carrerBuffer.append(mDetailCVCustomerResponse.getLstCareer().get(i).getName() + ", ");
            }
        }
        tvCarrer.setText(carrerBuffer.toString());
        tvWorkPLace.setText(mDetailCVCustomerResponse.getJobListcityName());
        tvCertificate.setText(mDetailCVCustomerResponse.getEducationLevel().getName());
        tvExpYear.setText(mDetailCVCustomerResponse.getExperienceYear().getName());
        tvSalaryWish.setText(mDetailCVCustomerResponse.getDesiredSalary() + "");
        tvTarget.setText(mDetailCVCustomerResponse.getCareerObjectives());
        btShowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btShowmore.getText().toString().equalsIgnoreCase("Xem thêm  ")) {
                    tvTarget.setMaxLines(Integer.MAX_VALUE);//your TextView
                    btShowmore.setText("Rút gọn  ");
                    gradientView.setVisibility(View.GONE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
                } else {
                    tvTarget.setMaxLines(6);
                    tvTarget.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
                    btShowmore.setText("Xem thêm  ");
                    gradientView.setVisibility(View.VISIBLE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_blue, 0);
                }
            }
        });
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
