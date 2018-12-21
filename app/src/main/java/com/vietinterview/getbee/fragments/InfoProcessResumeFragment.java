package com.vietinterview.getbee.fragments;

import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.InviteInterviewRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.ShowImageUtils;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class InfoProcessResumeFragment extends BaseFragment {
    @BindView(R.id.tvNameResume)
    TextView tvNameResume;
    @BindView(R.id.tvBirthDayResume)
    TextView tvBirthDayResume;
    @BindView(R.id.tvCertificate)
    TextView tvCertificate;
    @BindView(R.id.tvPositionWish)
    TextView tvPositionWish;
    @BindView(R.id.tvWorkPLace)
    TextView tvWorkPLace;
    @BindView(R.id.tvExpYear)
    TextView tvExpYear;
    @BindView(R.id.tvSalary)
    TextView tvSalary;
    @BindView(R.id.tvCollName)
    TextView tvCollName;
    @BindView(R.id.tvPhoneColl)
    TextView tvPhoneColl;
    @BindView(R.id.tvEmailColl)
    TextView tvEmailColl;
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.imgAva)
    ImageView imgAva;
    @BindView(R.id.btnInviteInterview)
    Button btnInviteInterview;
    @BindView(R.id.imgInfo)
    ImageView imgInfo;
    @BindView(R.id.btnReject)
    Button btnReject;
    DetailProcessResumeResponse detailProcessResumeResponse;
    private Dialog mRejectDialog;
    private Dialog mNotifyDialog;

    public static InfoProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        InfoProcessResumeFragment fm = new InfoProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_process_cv;
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
        ShowImageUtils.showImage(getActivity(), detailProcessResumeResponse.getCvProcessInfo().getPictureUrl(), R.drawable.ic_ava_null, imgAva);
        tvNameResume.setText(detailProcessResumeResponse.getCvProcessInfo().getFullName());
        tvBirthDayResume.setText(DateUtil.convertToMyFormatFull(detailProcessResumeResponse.getCvProcessInfo().getBirthday() + ""));
        tvCertificate.setText(detailProcessResumeResponse.getCvProcessInfo().getEducationLevelName());
        tvWorkPLace.setText(detailProcessResumeResponse.getCvProcessInfo().getWorkAddress());
        tvExpYear.setText(detailProcessResumeResponse.getCvProcessInfo().getYearsExperienceName());
        tvPositionWish.setText(detailProcessResumeResponse.getCvProcessInfo().getPositionName());
        tvSalary.setText(StringUtils.filterCurrencyString(detailProcessResumeResponse.getCvProcessInfo().getSalary()) + " " + StringUtils.genStringCurrency(detailProcessResumeResponse.getCvProcessInfo().getCurrencyId()));
        tvCollName.setText(detailProcessResumeResponse.getCvProcessInfo().getCollName());
        tvEmailColl.setText(detailProcessResumeResponse.getCvProcessInfo().getCollEmail());
        tvPhoneColl.setText(detailProcessResumeResponse.getCvProcessInfo().getCollPhone());
        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 1) {
                cardReject.setVisibility(View.VISIBLE);
                getEventBaseFragment().reject();
                btnInviteInterview.setEnabled(false);
                btnReject.setEnabled(false);
            } else cardReject.setVisibility(View.GONE);
        } else cardReject.setVisibility(View.GONE);
    }


    @OnClick(R.id.btnInviteInterview)
    public void onClickNext() {
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
        tvContent.setText("Bạn có chắc chắn muốn gửi thư phỏng vấn tới ứng viên này?");
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotifyDialog.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailProcessResumeResponse.getLstInterviewHis().size() > 0) {
                    mNotifyDialog.dismiss();
                    getEventBaseFragment().changeStepExp(1);
                } else {
                    new InviteInterviewRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId()).callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {
                        @Override
                        public void onSuccess(int status, ErrorResponse dataSuccess, List<ErrorResponse> listDataSuccess, String message) {
                            if (isAdded()) {
                                if (status == 200) {
                                    mNotifyDialog.dismiss();
                                    getEventBaseFragment().changeStepExp(1);
                                }
                            }
                        }

                        @Override
                        public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                            if (isAdded()) {
                                if (status == 200) {
                                    mNotifyDialog.dismiss();
                                    getEventBaseFragment().changeStepExp(1);
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
    }

    @OnClick(R.id.imgInfo)
    public void onImageClick() {
        FragmentUtil.popBackStack(this);
    }

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
                                            btnInviteInterview.setEnabled(false);
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
