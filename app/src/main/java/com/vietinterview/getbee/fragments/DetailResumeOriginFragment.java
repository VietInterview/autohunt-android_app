package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.FragmentUtil;

/**
 * Created by hiepnguyennghia on 2/11/19.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailResumeOriginFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_resume_origin;
    }

    @Override
    protected void getArgument(Bundle bundle) {
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        customToolbar(true);
        setCustomToolbarVisible(true);
        GlobalDefine.currentFragment = this;
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
