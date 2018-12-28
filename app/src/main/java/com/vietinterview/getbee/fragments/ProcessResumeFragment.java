package com.vietinterview.getbee.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shuhart.stepview.StepView;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.api.request.GetDetailProcessResumeRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.JobCvGotoWorkDto;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.api.response.detailprocessresume.LstOfferHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnChangeStepExpListener;
import com.vietinterview.getbee.callback.OnRejectListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.NonSwipeableViewPager;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ProcessResumeFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    NonSwipeableViewPager mViewPager;
    @BindView(R.id.step_view_2)
    StepView stepView2;
    @BindView(R.id.tvNameStep)
    TextView tvNameStep;
    @BindView(R.id.tvStep)
    TextView tvStep;
    boolean isDetail;
    GetDetailProcessResumeRequest getDetailProcessResumeRequest;
    int mStep = 0;
    int mStepClick = 0;
    int cvId;
    int jobId;
    DetailProcessResumeResponse detailProcessResumeResponse;

    public static ProcessResumeFragment newInstance(int cvId, int jobId, boolean isDetail) {
        ProcessResumeFragment fm = new ProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cvId", cvId);
        bundle.putInt("jobId", jobId);
        bundle.putBoolean("isDetail", isDetail);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_process_resume;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        this.cvId = bundle.getInt("cvId");
        this.jobId = bundle.getInt("jobId");
        this.isDetail = bundle.getBoolean("isDetail");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        customToolbar(true);
        setMenuVisibility(true);
        GlobalDefine.currentFragment = this;
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.process_cv));
        getEventBaseFragment().setOnRejectListener(new OnRejectListener() {
            @Override
            public void onReject() {
//                stepView2.setEnabled(false);
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        SharedPrefUtils.putInt("step", i);
                        getEventBaseFragment().setSwitchToOne();
                        break;
                    case 1:
                        SharedPrefUtils.putInt("step", i);
                        getEventBaseFragment().setSwitchToTwo();
                        break;
                    case 2:
                        SharedPrefUtils.putInt("step", i);
                        getEventBaseFragment().setSwitchToThree();
                        break;
                    case 3:
                        SharedPrefUtils.putInt("step", i);
                        getEventBaseFragment().setSwitchToFour();
                        break;
                    case 4:
                        SharedPrefUtils.putInt("step", i);
                        getEventBaseFragment().setSwitchToFive();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        stepView2.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                    if (step <= (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() - 1)) {
                        stepView2.go(step, true);
                        mStep = step;
                        tvNameStep.setText(switchStepName(step));
                        mViewPager.setCurrentItem(step, true);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                    }
                } else {
                    if (step <= mStepClick) {
                        stepView2.go(step, true);
                        mStep = step;
                        tvNameStep.setText(switchStepName(step));
                        mViewPager.setCurrentItem(step, true);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                    }
                }
            }
        });
        getEventBaseFragment().setOnChangeStepExpListener(new OnChangeStepExpListener() {
            @Override
            public void onChangeStepExp(int step) {
                if (step == 1) {
                    if (detailProcessResumeResponse.getLstInterviewHis().size() == 0) {
                        FragmentUtil.pushFragment(getActivity(), ProcessResumeFragment.this, new CreateEditInterviewFragment().newInstance(null, detailProcessResumeResponse), null);
                    }
                } else if (step == 2) {
                    if (detailProcessResumeResponse.getLstOfferHis().size() == 0) {
                        FragmentUtil.pushFragment(getActivity(), ProcessResumeFragment.this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
                    }
                } else if (step == 3) {
                    if (detailProcessResumeResponse.getJobCvGotoWorkDto() != null) {
                        if (detailProcessResumeResponse.getJobCvGotoWorkDto().getId() == null)
                            FragmentUtil.pushFragment(getActivity(), ProcessResumeFragment.this, new CreateGoToWorkFragment().newInstance(null, detailProcessResumeResponse), null);
                        else {
                            FragmentUtil.pushFragment(getActivity(), ProcessResumeFragment.this, new CreateGoToWorkFragment().newInstance(detailProcessResumeResponse.getJobCvGotoWorkDto(), detailProcessResumeResponse), null);
                        }
                    } else {
                        FragmentUtil.pushFragment(getActivity(), ProcessResumeFragment.this, new CreateGoToWorkFragment().newInstance(null, detailProcessResumeResponse), null);
                    }
                }
                mStep = step;
                mStepClick = step;
                stepView2.go(mStep, true);
                SharedPrefUtils.putInt("step", mStep);
                mViewPager.setCurrentItem(mStep, true);
                tvNameStep.setText(switchStepName(mStep));
                tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
            }
        });
        tvNameStep.setText(switchStepName(mStep));
        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
    }

    @Override
    protected void initData() {
        if (detailProcessResumeResponse == null)
            getDetailProcessResume(this.cvId, this.jobId);
        else {
            setupViewPager(mViewPager);
            stepView2.go(mStep, true);
            mViewPager.setCurrentItem(mStep, true);
        }
    }

    public void getDetailProcessResume(int cvId, int jobId) {
        showCoverNetworkLoading();
        getDetailProcessResumeRequest = new GetDetailProcessResumeRequest(cvId, jobId);
        getDetailProcessResumeRequest.callRequest(getActivity(), new ApiObjectCallBack<DetailProcessResumeResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailProcessResumeResponse dataSuccess, List<DetailProcessResumeResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    detailProcessResumeResponse = dataSuccess;
                    if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 3 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 1)) {
                        mStep = 0;
                        mStepClick = 0;
                        SharedPrefUtils.putInt("step", mStep);
                        stepView2.go(mStep, true);
                        setupViewPager(mViewPager);
                        mViewPager.setCurrentItem(mStep);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                        tvNameStep.setText(switchStepName(mStep));
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2)) {
                        mStep = 1;
                        mStepClick = 1;
                        SharedPrefUtils.putInt("step", mStep);
                        stepView2.go(mStep, true);
                        setupViewPager(mViewPager);
                        mViewPager.setCurrentItem(mStep);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                        tvNameStep.setText(switchStepName(mStep));
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 7 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3)) {
                        mStep = 2;
                        mStepClick = 2;
                        SharedPrefUtils.putInt("step", mStep);
                        stepView2.go(mStep, true);
                        setupViewPager(mViewPager);
                        mViewPager.setCurrentItem(mStep);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                        tvNameStep.setText(switchStepName(mStep));
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 8 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 4)) {
                        mStep = 3;
                        mStepClick = 3;
                        SharedPrefUtils.putInt("step", mStep);
                        stepView2.go(mStep, true);
                        setupViewPager(mViewPager);
                        mViewPager.setCurrentItem(mStep);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                        tvNameStep.setText(switchStepName(mStep));
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 9 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 5)) {
                        mStep = 4;
                        mStepClick = 4;
                        SharedPrefUtils.putInt("step", mStep);
                        stepView2.go(mStep, true);
                        setupViewPager(mViewPager);
                        mViewPager.setCurrentItem(mStep);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep + 1) + "/5");
                        tvNameStep.setText(switchStepName(mStep));
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        mStep = detailProcessResumeResponse.getCvProcessInfo().getRejectStep();
                        mStepClick = detailProcessResumeResponse.getCvProcessInfo().getRejectStep();
                        stepView2.go(mStep - 1, true);
                        setupViewPager(mViewPager);
                        mViewPager.setCurrentItem(mStep - 1);
                        tvStep.setText(getResources().getString(R.string.step) + " " + (mStep) + "/5");
                        tvNameStep.setText(switchStepName(mStep - 1));
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.error_please_try), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FragmentUtil.popBackStack(ProcessResumeFragment.this);
                        }
                    });
                }
            }
        });
    }

    private void setupViewPager(final NonSwipeableViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new InfoProcessResumeFragment().newInstance(detailProcessResumeResponse, isDetail), getResources().getString(R.string.info));
        adapter.addFrag(new InterviewProcessResumeFragment().newInstance(detailProcessResumeResponse), getResources().getString(R.string.interview));
        adapter.addFrag(new OfferProcessResumeFragment().newInstance(detailProcessResumeResponse), getResources().getString(R.string.offered));
        adapter.addFrag(new GoToWorkProcessResumeFragment().newInstance(detailProcessResumeResponse), getResources().getString(R.string.go_to_work));
        adapter.addFrag(new ContractProcessResumeFragment().newInstance(detailProcessResumeResponse), getResources().getString(R.string.contract));
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
    }

    public String switchStepName(int Step) {
        String stepName;
        switch (Step) {
            case 0:
                stepName = getResources().getString(R.string.info);
                break;
            case 1:
                stepName = getResources().getString(R.string.interview);
                break;
            case 2:
                stepName = getResources().getString(R.string.offered);
                break;
            case 3:
                stepName = getResources().getString(R.string.go_to_work);
                break;
            case 4:
                stepName = getResources().getString(R.string.contract);
                break;
            default:
                stepName = getResources().getString(R.string.info);
        }
        return stepName;
    }

    private LstInterviewHi lstInterviewHi;
    private LstOfferHi lstOfferHi;
    private JobCvGotoWorkDto jobCvGotoWorkDto;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                lstInterviewHi = data.getParcelableExtra("lstInterviewHi");
                if (lstInterviewHi != null) {
                    boolean isDuplicate = false;
                    for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
                        if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == lstInterviewHi.getId()) {
                            isDuplicate = true;
                            detailProcessResumeResponse.getLstInterviewHis().set(i, lstInterviewHi);
                            break;
                        }
                    }
                    if (isDuplicate == false)
                        detailProcessResumeResponse.getLstInterviewHis().add(lstInterviewHi);
                }
                lstOfferHi = data.getParcelableExtra("lstOfferHi");
                if (lstOfferHi != null) {
                    boolean isDuplicateOffer = false;
                    for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
                        if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() == lstOfferHi.getId()) {
                            isDuplicateOffer = true;
                            detailProcessResumeResponse.getLstOfferHis().set(i, lstOfferHi);
                            break;
                        }
                    }
                    if (isDuplicateOffer == false)
                        detailProcessResumeResponse.getLstOfferHis().add(lstOfferHi);
                }
                jobCvGotoWorkDto = data.getParcelableExtra("jobCvGotoWorkDto");
                if (jobCvGotoWorkDto != null)
                    detailProcessResumeResponse.setJobCvGotoWorkDto(jobCvGotoWorkDto);

                setupViewPager(mViewPager);
                stepView2.go(mStep, true);
                mViewPager.setCurrentItem(mStep, true);
            }
        }
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
