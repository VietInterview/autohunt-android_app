package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.utils.FragmentUtil;

/**
 * Created by nguyennghiahiep on 10/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class SignupFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signup;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRestore() {

    }

    @Override
    protected void initialize() {
        getEventBaseFragment().doFillBackground("Đăng ký");
    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }
    @Override
    protected void processOnBackPress() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back);
    }

    @Override
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }
}
