package com.vietinterview.getbee.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnChangeStepExpListener;
import com.vietinterview.getbee.callback.OnRejectListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.customview.NonSwipeableViewPager;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

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
    GetDetailProcessResumeRequest getDetailProcessResumeRequest;
    int mStep = 0;
    int cvId;
    int jobId;
    DetailProcessResumeResponse detailProcessResumeResponse;

    public static ProcessResumeFragment newInstance(int cvId, int jobId) {
        ProcessResumeFragment fm = new ProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cvId", cvId);
        bundle.putInt("jobId", jobId);
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
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        customToolbar(true);
        setMenuVisibility(true);
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.process_cv));
        getEventBaseFragment().setOnRejectListener(new OnRejectListener() {
            @Override
            public void onReject() {
                stepView2.setEnabled(false);
            }
        });
        stepView2.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                if (step == 1) {
                    stepView2.go(step, true);
                    mStep = step;
                    tvNameStep.setText(switchStepName(step));
                    mViewPager.setCurrentItem(step, true);
                    tvStep.setText("Bước " + (mStep + 1) + "/5");
                } else if (step == 2) {
                    if (detailProcessResumeResponse.getLstInterviewHis().size() > 0) {
                        if (detailProcessResumeResponse.getLstInterviewHis().get(detailProcessResumeResponse.getLstInterviewHis().size() - 1).getStatus() == 1) {
                            stepView2.go(step, true);
                            mStep = step;
                            tvNameStep.setText(switchStepName(step));
                            mViewPager.setCurrentItem(step, true);
                            tvStep.setText("Bước " + (mStep + 1) + "/5");
                        }
                    }
                } else if (step == 3) {
                    if (detailProcessResumeResponse.getLstOfferHis().size() > 0) {
                        if (detailProcessResumeResponse.getLstOfferHis().get(detailProcessResumeResponse.getLstOfferHis().size() - 1).getStatus() == 1) {
                            stepView2.go(step, true);
                            mStep = step;
                            tvNameStep.setText(switchStepName(step));
                            mViewPager.setCurrentItem(step, true);
                            tvStep.setText("Bước " + (mStep + 1) + "/5");
                        }
                    }
                } else if (step == 4) {

                } else if (step == 0) {

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
                }
                mStep = step;
                stepView2.go(mStep, true);
                mViewPager.setCurrentItem(mStep, true);
                tvNameStep.setText(switchStepName(mStep));
                tvStep.setText("Bước " + (mStep + 1) + "/5");
            }
        });
        tvNameStep.setText(switchStepName(mStep));
        tvStep.setText("Bước " + (mStep + 1) + "/5");
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
                    setupViewPager(mViewPager);
                    if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        stepView2.setEnabled(false);
                        mStep = detailProcessResumeResponse.getCvProcessInfo().getRejectStep();
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 3 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                        mStep = 0;
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6) {
                        mStep = 1;
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 7) {
                        mStep = 2;
                    } else if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 8) {
                        mStep = 3;
                    } else {
                        mStep = 4;
                    }
                    stepView2.go(mStep, true);
                    mViewPager.setCurrentItem(mStep);
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
        adapter.addFrag(new InfoProcessResumeFragment().newInstance(detailProcessResumeResponse), "Thông tin");
        adapter.addFrag(new InterviewProcessResumeFragment().newInstance(detailProcessResumeResponse), "Phỏng vấn");
        adapter.addFrag(new OfferProcessResumeFragment().newInstance(detailProcessResumeResponse), "Offer");
        adapter.addFrag(new GoToWorkProcessResumeFragment().newInstance(detailProcessResumeResponse), "Đi làm");
        adapter.addFrag(new ContractProcessResumeFragment().newInstance(detailProcessResumeResponse), "Ký hợp đồng");
        assert viewPager != null;
        viewPager.setAdapter(adapter);
    }

    public String switchStepName(int Step) {
        String stepName;
        switch (Step) {
            case 0:
                stepName = getResources().getString(R.string.info);
                break;
            case 1:
                stepName = "Phỏng vấn";
                break;
            case 2:
                stepName = "Offer";
                break;
            case 3:
                stepName = "Đi làm";
                break;
            case 4:
                stepName = "Ký hợp đồng";
                break;
            default:
                stepName = getResources().getString(R.string.info);
        }
        return stepName;
    }

    private LstInterviewHi lstInterviewHi;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                lstInterviewHi = data.getParcelableExtra("lstInterviewHi");
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
                setupViewPager(mViewPager);
                stepView2.go(mStep, true);
                mViewPager.setCurrentItem(mStep, true);
//                showListInterview();
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
