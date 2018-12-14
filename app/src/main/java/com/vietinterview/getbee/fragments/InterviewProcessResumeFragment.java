package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.ctvprofile.DesideratedCareer;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.LayoutUtils;
import com.vietinterview.getbee.utils.UiUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/18/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InterviewProcessResumeFragment extends BaseFragment {
    @BindView(R.id.flowListInterview)
    FlowLayout flowListInterview;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnSendMail)
    Button btnSendMail;
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.tvAddRound)
    TextView tvAddRound;
    @BindView(R.id.btnNotAccept)
    Button btnNotAccept;
    @BindView(R.id.scrInterview)
    ScrollView scrInterview;
    DetailProcessResumeResponse detailProcessResumeResponse;
    private Dialog mReasonNotAcceptDialog;
    private Dialog mRejectDialog;
    private LstInterviewHi lstInterviewHi;

    public static InterviewProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        InterviewProcessResumeFragment fm = new InterviewProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_interview_process_resume;
    }


    @Override
    protected void getArgument(Bundle bundle) {
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
    }

    @Override
    protected void initData() {
        showListInterview();
        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 1) {
                cardReject.setVisibility(View.VISIBLE);
                getEventBaseFragment().reject();
                btnNext.setEnabled(false);
                btnSendMail.setEnabled(false);
                tvAddRound.setEnabled(false);
            } else cardReject.setVisibility(View.GONE);
        } else cardReject.setVisibility(View.GONE);
        if (detailProcessResumeResponse.getLstInterviewHis().size() > 0) {
            if (detailProcessResumeResponse.getLstInterviewHis().get(detailProcessResumeResponse.getLstInterviewHis().size() - 1).getStatus() == 1) {
                btnNotAccept.setVisibility(View.GONE);
            } else {
                btnNotAccept.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.tvAddRound)
    public void onAddRoundClick() {
        if (detailProcessResumeResponse.getLstInterviewHis().get(detailProcessResumeResponse.getLstInterviewHis().size() - 1).getStatus() == 1) {
            FragmentUtil.pushFragment(getActivity(), this, new CreateEditInterviewFragment().newInstance(null, detailProcessResumeResponse), null);
        } else {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Vòng phỏng vấn gần nhất ứng viên không đạt vì vậy bạn không thể thêm mới vòng phỏng vấn");
        }
    }

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (detailProcessResumeResponse.getLstInterviewHis().get(detailProcessResumeResponse.getLstInterviewHis().size() - 1).getStatus() == 1) {
            getEventBaseFragment().changeStepExp(2);
        } else {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Vòng phỏng vấn gần nhất ứng viên không đạt vì vậy bạn không thể thêm mới vòng phỏng vấn");
        }
    }

    @OnClick(R.id.btnSeeInvite)
    public void onSeeOffer() {
        mReasonNotAcceptDialog = new Dialog(getActivity());
        mReasonNotAcceptDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mReasonNotAcceptDialog.setContentView(R.layout.dialog_see_invite);
        mReasonNotAcceptDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mReasonNotAcceptDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Window window = mReasonNotAcceptDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.y = 300;
        window.setAttributes(wlp);
        Button btnOK = (Button) mReasonNotAcceptDialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReasonNotAcceptDialog.dismiss();
            }
        });
        mReasonNotAcceptDialog.show();
    }

    @OnClick(R.id.btnSendMail)
    public void onSendMailClick() {

    }

    @OnClick(R.id.btnNotAccept)
    public void onNotAcceptClick() {
        showCoverNetworkLoading();
        new GetListRejectReasonRequest().callRequest(getActivity(), new ApiObjectCallBack<RejectReasonResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, RejectReasonResponse dataSuccess, final List<RejectReasonResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    final List<RejectReasonResponse> listReasonName = new ArrayList<RejectReasonResponse>();
                    for (int i = 0; i < listDataSuccess.size(); i++) {
                        if (listDataSuccess.get(i).getStep() == 1) {
                            listReasonName.add(listDataSuccess.get(i));
                        }
                    }
                    ArrayAdapter<RejectReasonResponse> dataAdapter = new ArrayAdapter<RejectReasonResponse>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listReasonName);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mRejectDialog = new Dialog(getActivity());
                    mRejectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    mRejectDialog.setContentView(R.layout.dialog_reject);
                    mRejectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mRejectDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    Window window = mRejectDialog.getWindow();
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.TOP;
                    wlp.y = 300;
                    window.setAttributes(wlp);
                    final Spinner spinner1 = (Spinner) mRejectDialog.findViewById(R.id.spinner1);
                    final EditText edtReasonOther = mRejectDialog.findViewById(R.id.edtReasonOther);
                    spinner1.setAdapter(dataAdapter);
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            DebugLog.showLogCat(listReasonName.get(position).getName());
                            if (listReasonName.get(position).getCode().equalsIgnoreCase("other")) {
                                edtReasonOther.setFocusable(true);
                                edtReasonOther.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
                                edtReasonOther.setClickable(true);
                            } else {
                                edtReasonOther.setFocusable(false);
                                edtReasonOther.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
                                edtReasonOther.setClickable(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    Button btnOK = (Button) mRejectDialog.findViewById(R.id.btnOK);
                    Button btnClose = mRejectDialog.findViewById(R.id.btnClose);
                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mRejectDialog.dismiss();
                        }
                    });
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showCoverNetworkLoading();
                            new RejectRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId(), ((RejectReasonResponse) spinner1.getSelectedItem()).getId(), edtReasonOther.getText().toString().trim(), ((RejectReasonResponse) spinner1.getSelectedItem()).getStep()).callRequest(getActivity(), new ApiObjectCallBack<RejectResponse, ErrorResponse>() {
                                @Override
                                public void onSuccess(int status, RejectResponse dataSuccess, List<RejectResponse> listDataSuccess, String message) {
                                    if (isAdded()) {
                                        if (status == 200) {
                                            hideCoverNetworkLoading();
                                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Gửi từ chối thành công");
                                            cardReject.setVisibility(View.VISIBLE);
                                            btnNext.setEnabled(false);
                                            btnSendMail.setEnabled(false);
                                            tvAddRound.setEnabled(false);
                                            flowListInterview.setEnabled(false);
                                            btnNotAccept.setEnabled(false);
                                            getEventBaseFragment().reject();
                                        }
                                    }
                                }

                                @Override
                                public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                                    if (isAdded()) {
                                        hideCoverNetworkLoading();
                                    }
                                }
                            });
                            mRejectDialog.dismiss();
                        }
                    });
                    mRejectDialog.show();
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                }
            }
        });
    }

    public void showListInterview() {
        flowListInterview.removeAllViews();
        for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
            LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(flowListInterview, R.layout.interview_item_view, false);
            final int position = i;
            RelativeLayout rlRound = (RelativeLayout) linearLayout.findViewById(R.id.rlRound);
            TextView tvRound = (TextView) linearLayout.findViewById(R.id.tvRound);
            TextView tvDate = (TextView) linearLayout.findViewById(R.id.tvDate);
            TextView tvContentReason = (TextView) linearLayout.findViewById(R.id.tvContentReason);
            tvRound.setText(detailProcessResumeResponse.getLstInterviewHis().get(i).getRound());
            tvDate.setText(DateUtil.convertToMyFormat3(detailProcessResumeResponse.getLstInterviewHis().get(i).getInterviewDate()));
            tvContentReason.setText(switchResult(detailProcessResumeResponse.getLstInterviewHis().get(i).getStatus()));
            rlRound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btnNext.isEnabled()) {
                        if (position == detailProcessResumeResponse.getLstInterviewHis().size() - 1) {
                            FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new CreateEditInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position), detailProcessResumeResponse), null);
                        } else {
                            FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new DetailInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position)), null);
                        }
                    }
                }
            });
            flowListInterview.addView(linearLayout);
        }
    }

    public String switchResult(int status) {
        String statusINterview = "";
        switch (status) {
            case 1:
                statusINterview = "Đạt";
                break;
            case 2:
                statusINterview = "Không đạt";
                break;
            case 3:
                statusINterview = "Ứng viên không đến";
                break;
            default:
                statusINterview = "Chưa có kết quả";
                break;
        }
        return statusINterview;
    }

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
                showListInterview();
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
}
