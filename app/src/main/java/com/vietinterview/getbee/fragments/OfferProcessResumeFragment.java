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
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.GoToWorkStatusRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstOfferHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.LayoutUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/18/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class OfferProcessResumeFragment extends BaseFragment {
    @BindView(R.id.flowListOffer)
    FlowLayout flowListOffer;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.tvAddOffer)
    TextView tvAddOffer;
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.btnReject)
    Button btnReject;
    DetailProcessResumeResponse detailProcessResumeResponse;
    LstOfferHi lstOfferHi;

    public static OfferProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        OfferProcessResumeFragment fm = new OfferProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_offer_process_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        showListOffer();
        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3) {
                cardReject.setVisibility(View.VISIBLE);
                getEventBaseFragment().reject();
                btnNext.setEnabled(false);
                tvAddOffer.setEnabled(false);
            } else cardReject.setVisibility(View.GONE);
        } else cardReject.setVisibility(View.GONE);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initData() {

    }

    public void showListOffer() {
        flowListOffer.removeAllViews();
        for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
            LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(flowListOffer, R.layout.offer_item_view, false);
            final int position = i;
            RelativeLayout rlRound = (RelativeLayout) linearLayout.findViewById(R.id.rlRound);
            TextView tvRound = (TextView) linearLayout.findViewById(R.id.tvRound);
            TextView tvDate = (TextView) linearLayout.findViewById(R.id.tvDate);
            tvRound.setText(detailProcessResumeResponse.getLstOfferHis().get(i).getRound());
            tvDate.setText(DateUtil.convertToMyFormat3(detailProcessResumeResponse.getLstOfferHis().get(i).getWorkTime()));
            rlRound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btnNext.isEnabled()) {
                        if (position == detailProcessResumeResponse.getLstOfferHis().size() - 1) {
                            FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new CreateEditOfferFragment().newInstance(detailProcessResumeResponse.getLstOfferHis().get(position), detailProcessResumeResponse), null);
                        } else {
                            FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new DetailOfferFragment().newInstance(detailProcessResumeResponse.getLstOfferHis().get(position)), null);
                        }
                    }
                }
            });
            flowListOffer.addView(linearLayout);
        }
    }

    private Dialog mNotifyDialog;

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (detailProcessResumeResponse.getLstOfferHis().size() > 0) {
            if (detailProcessResumeResponse.getLstOfferHis().get(detailProcessResumeResponse.getLstOfferHis().size() - 1).getStatus() == 1) {
                mNotifyDialog = new Dialog(getActivity());
                mNotifyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mNotifyDialog.setContentView(R.layout.dialog_noti_interview);
                mNotifyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mNotifyDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                Window window = mNotifyDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.TOP;
                wlp.y = 300;
                window.setAttributes(wlp);
                Button btnOK = (Button) mNotifyDialog.findViewById(R.id.btnOK);
                Button btnNo = mNotifyDialog.findViewById(R.id.btnNo);
                TextView tvContent = mNotifyDialog.findViewById(R.id.tvContent);
                tvContent.setText("Bạn có chắc chắn muốn gửi lịch đi làm tới ứng viên này?");
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mNotifyDialog.dismiss();
                    }
                });
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (detailProcessResumeResponse.getJobCvGotoWorkDto().getId() != null) {
                            mNotifyDialog.dismiss();
                            getEventBaseFragment().changeStepExp(3);
                        } else {
                            new GoToWorkStatusRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId()).callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {
                                @Override
                                public void onSuccess(int status, ErrorResponse dataSuccess, List<ErrorResponse> listDataSuccess, String message) {
                                    if (isAdded()) {
                                        if (status == 200) {
                                            mNotifyDialog.dismiss();
                                            getEventBaseFragment().changeStepExp(3);
                                        }
                                    }
                                }

                                @Override
                                public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                                    if (isAdded()) {
                                        if (status == 200) {
                                            mNotifyDialog.dismiss();
                                            getEventBaseFragment().changeStepExp(3);
                                        } else {
                                            mNotifyDialog.dismiss();
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
                mNotifyDialog.show();
            } else {
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Vòng Offer gần nhất ứng viên từ chối vì vậy bạn không thể mời ứng viên đi làm");
            }
        } else {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Chưa có Offer nào, hãy thêm offer");
        }
    }

    @OnClick(R.id.tvAddOffer)
    public void onAddOfferClick() {
        if (detailProcessResumeResponse.getLstOfferHis().size() > 0) {
            if (detailProcessResumeResponse.getLstOfferHis().get(detailProcessResumeResponse.getLstOfferHis().size() - 1).getStatus() == 2) {
                FragmentUtil.pushFragment(getActivity(), this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
            } else if (detailProcessResumeResponse.getLstOfferHis().get(detailProcessResumeResponse.getLstOfferHis().size() - 1).getStatus() == 1) {
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Vòng Offer gần nhất ứng viên đồng ý vì vậy bạn không thể thêm mới vòng phỏng vấn");
            }
        } else {
            FragmentUtil.pushFragment(getActivity(), this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
        }
    }

    private Dialog mRejectDialog;

    @OnClick(R.id.btnReject)
    public void onRejectClick() {
        showCoverNetworkLoading();
        new GetListRejectReasonRequest().callRequest(getActivity(), new ApiObjectCallBack<RejectReasonResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, RejectReasonResponse dataSuccess, final List<RejectReasonResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    final List<RejectReasonResponse> listReasonName = new ArrayList<RejectReasonResponse>();
                    for (int i = 0; i < listDataSuccess.size(); i++) {
                        if (listDataSuccess.get(i).getStep() == 3) {
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
                                            tvAddOffer.setEnabled(false);
                                            flowListOffer.setEnabled(false);
                                            btnReject.setEnabled(false);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                lstOfferHi = data.getParcelableExtra("lstOfferHi");
                boolean isDuplicate = false;
                for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
                    if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() == lstOfferHi.getId()) {
                        isDuplicate = true;
                        detailProcessResumeResponse.getLstOfferHis().set(i, lstOfferHi);
                        break;
                    }
                }
                if (isDuplicate == false)
                    detailProcessResumeResponse.getLstOfferHis().add(lstOfferHi);
                showListOffer();
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
