package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badoualy.stepperindicator.StepperIndicator;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.customview.NonSwipeableViewPager;
import com.vietinterview.getbee.utils.DebugLog;

import net.skoumal.fragmentback.BackFragment;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CreateNewCVFragment extends BaseFragment implements BackFragment {
    @BindView(R.id.viewPager)
    NonSwipeableViewPager mViewPager;
    @BindView(R.id.stepper_indicator)
    StepperIndicator stepper_indicator;
    int mStep;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_new_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        customToolbar(true);
        setMenuVisibility(true);
        getEventBaseFragment().doFillBackground("Tạo CV mới");
        setupViewPager(mViewPager);
    }

    private void setupViewPager(final NonSwipeableViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new InfoCreateCVFragment(), "Thông tin");
        adapter.addFrag(new StatisticalFragment(), "Thống kê");
        adapter.addFrag(new CVSentFragment(), "CV đã nộp");
        adapter.addFrag(new MyCVFragment(), "CV của tôi");
        assert viewPager != null;
        viewPager.setAdapter(adapter);
        stepper_indicator.setViewPager(viewPager, false);
        stepper_indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                mStep = step;
                viewPager.setCurrentItem(step, true);
            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {

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

//    @Override
//    protected void processOnBackPress() {
//        DebugLog.showLogCat("onBackPress");
//
//    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_close);
    }

    @Override
    protected void processCustomToolbar() {
        getActivity().finish();
    }

    @Override
    public boolean onBackPressed() {
        DebugLog.showLogCat("onBackPressed");
        if (mStep > 0) {
            mStep = mStep - 1;
            mViewPager.setCurrentItem(mStep);
        } else {
            getActivity().finish();
        }
        return true;
    }

    @Override
    public int getBackPriority() {
        return NORMAL_BACK_PRIORITY;
    }
}
