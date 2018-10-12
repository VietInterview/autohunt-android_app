package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class MyCVFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().doFillBackground("CV của tôi");
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

    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }

    @Override
    protected void processOnBackPress() {
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_menu);
    }

    @Override
    protected void processCustomToolbar() {
        loadMenuLeft();
    }
}
