package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuhart.stepview.StepView;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.callback.OnChangeStepExpListener;
import com.vietinterview.getbee.customview.NonSwipeableViewPager;
import com.vietinterview.getbee.utils.FragmentUtil;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ProcessResumeFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    NonSwipeableViewPager mViewPager;
    @BindView(R.id.step_view_2)
    StepView stepView2;
    int mStep;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_process_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        customToolbar(true);
        setMenuVisibility(true);
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.process_cv));
        setupViewPager(mViewPager);
        stepView2.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                stepView2.go(step, true);
                mViewPager.setCurrentItem(step, true);
            }
        });
        getEventBaseFragment().setOnChangeStepExpListener(new OnChangeStepExpListener() {
            @Override
            public void onChangeStepExp(int step) {
                mStep = step;
                stepView2.go(mStep, true);
                mViewPager.setCurrentItem(mStep, true);
            }
        });
    }

    private void setupViewPager(final NonSwipeableViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new InfoProcessResumeFragment(), "Thông tin");
        adapter.addFrag(new InterviewProcessResumeFragment(), "Phỏng vấn");
        adapter.addFrag(new OfferProcessResumeFragment(), "Offer");
        adapter.addFrag(new GoToWorkProcessResumeFragment(), "CV của tôi");
        adapter.addFrag(new ContractProcessResumeFragment(), "CV của tôi");
        assert viewPager != null;
        viewPager.setAdapter(adapter);
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

    @Override
    protected void processOnBackPress() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }

    @Override
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }
}
