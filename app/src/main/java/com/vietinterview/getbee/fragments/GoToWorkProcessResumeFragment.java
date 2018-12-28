package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.format.DateFormat;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.ContractStatusRequest;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.GoToWorkStatusRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.JobCvGotoWorkDto;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnSwitchToFourListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/18/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class GoToWorkProcessResumeFragment extends BaseFragment {
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnReject)
    Button btnReject;
    @BindView(R.id.rlGotoworkDate)
    RelativeLayout rlGotoworkDate;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvWarranty)
    TextView tvWarranty;
    @BindView(R.id.tvRound)
    TextView tvRound;
    @BindView(R.id.llBtn)
    LinearLayout llBtn;
    @BindView(R.id.tvReject)
    TextView tvReject;
    @BindView(R.id.imgArrow)
    ImageView imgArrow;
    DetailProcessResumeResponse detailProcessResumeResponse;

    public static GoToWorkProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        GoToWorkProcessResumeFragment fm = new GoToWorkProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gotowork_process_resume;
    }


    @Override
    protected void getArgument(Bundle bundle) {
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().setOnSwitchToFourListener(new OnSwitchToFourListener() {
            @Override
            public void onSwitchToFour() {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 8 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 4)) {
                    btnNext.setEnabled(true);
                    rlGotoworkDate.setEnabled(true);
                    tvDate.setEnabled(true);
                    btnReject.setEnabled(true);
                    tvRound.setEnabled(true);
                    llBtn.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setEnabled(false);
                    rlGotoworkDate.setEnabled(false);
                    tvDate.setEnabled(false);
                    btnReject.setEnabled(false);
                    tvRound.setEnabled(false);
                    llBtn.setVisibility(View.GONE);
                }
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                    if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 4) {
                            cardReject.setVisibility(View.VISIBLE);
                            getEventBaseFragment().reject();
                            btnNext.setEnabled(false);
                            rlGotoworkDate.setEnabled(false);
                            tvDate.setEnabled(false);
                            btnReject.setEnabled(false);
                            tvRound.setEnabled(false);
                            llBtn.setVisibility(View.GONE);
                        } else cardReject.setVisibility(View.GONE);
                    } else cardReject.setVisibility(View.GONE);
                } else {
                    cardReject.setVisibility(View.GONE);
                }
                if (detailProcessResumeResponse.getJobCvGotoWorkDto() != null) {
                    if (detailProcessResumeResponse.getJobCvGotoWorkDto().getCountUpdate() != null) {
                        if (detailProcessResumeResponse.getJobCvGotoWorkDto().getCountUpdate() < 3) {
                            imgArrow.setVisibility(View.VISIBLE);
                        } else {
                            imgArrow.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });
        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 4) {
                    cardReject.setVisibility(View.VISIBLE);
                    getEventBaseFragment().reject();
                    btnNext.setEnabled(false);
                    rlGotoworkDate.setEnabled(false);
                    tvDate.setEnabled(false);
                    btnReject.setEnabled(false);
                    tvRound.setEnabled(false);
                    llBtn.setVisibility(View.GONE);
                } else cardReject.setVisibility(View.GONE);
            } else cardReject.setVisibility(View.GONE);
        } else {
            cardReject.setVisibility(View.GONE);
        }
        if (detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName() != null) {
            tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName());
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectNote() != null && !detailProcessResumeResponse.getCvProcessInfo().getRejectNote().equalsIgnoreCase(""))
                tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName() + "\n" + getResources().getString(R.string.reject_mess3) + ": " + detailProcessResumeResponse.getCvProcessInfo().getRejectNote());
        }
        if (detailProcessResumeResponse.getJobCvGotoWorkDto() != null) {
            if (detailProcessResumeResponse.getJobCvGotoWorkDto().getCountUpdate() != null) {
                if (detailProcessResumeResponse.getJobCvGotoWorkDto().getCountUpdate() < 3) {
                    imgArrow.setVisibility(View.VISIBLE);
                } else {
                    imgArrow.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    protected void initData() {
        if (detailProcessResumeResponse.getJobCvGotoWorkDto().getStartWorkDate() != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date readDate = null;
            try {
                readDate = df.parse(DateUtil.convertToMyFormat3(detailProcessResumeResponse.getJobCvGotoWorkDto().getStartWorkDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(readDate.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            Calendar today = Calendar.getInstance();
            calendar.add(Calendar.DATE, 60);
            long lastWarrantyDate = calendar.getTimeInMillis();
            long difference = lastWarrantyDate - today.getTimeInMillis();
            int days = (int) (difference / (1000 * 60 * 60 * 24));
            tvDate.setText(detailProcessResumeResponse.getJobCvGotoWorkDto().getStartWorkDate());
            tvWarranty.setText(days + " " + getResources().getString(R.string.dayleft));
        } else {
            tvDate.setText("");
            tvWarranty.setText(0 + " " + getResources().getString(R.string.dayleft));
        }
    }

    private Dialog mRejectDialog;
    private boolean isOther = false;
    private String rejectName;

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
                        if (listDataSuccess.get(i).getStep() == 4) {
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
                    rejectName = ((RejectReasonResponse) spinner1.getSelectedItem()).getName();
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            rejectName = listReasonName.get(position).getName();
                            if (listReasonName.get(position).getCode().equalsIgnoreCase("other")) {
                                isOther = true;
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
                            if (isOther) {
                                if (edtReasonOther.getText().toString().trim().equalsIgnoreCase("")) {
                                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.input_reason_reject));
                                } else {
                                    DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.ask_reject), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            showCoverNetworkLoading();
                                            new RejectRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId(), ((RejectReasonResponse) spinner1.getSelectedItem()).getId(), edtReasonOther.getText().toString().trim(), ((RejectReasonResponse) spinner1.getSelectedItem()).getStep()).callRequest(getActivity(), new ApiObjectCallBack<RejectResponse, ErrorResponse>() {
                                                @Override
                                                public void onSuccess(int status, RejectResponse dataSuccess, List<RejectResponse> listDataSuccess, String message) {
                                                    if (isAdded()) {
                                                        if (status == 200) {
                                                            hideCoverNetworkLoading();
                                                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.send_reject_success), new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    cardReject.setVisibility(View.VISIBLE);
                                                                    tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName + "\n" + getResources().getString(R.string.reject_mess3) + ": " + dataSuccess.getReasonNote());
                                                                }
                                                            });
                                                            btnNext.setEnabled(false);
                                                            btnReject.setEnabled(false);
                                                            rlGotoworkDate.setEnabled(false);
                                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                            detailProcessResumeResponse.getCvProcessInfo().setRejectStep(4);
                                                            tvRound.setEnabled(false);
                                                            tvDate.setEnabled(false);
                                                            llBtn.setVisibility(View.GONE);
                                                            getEventBaseFragment().reject();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                                                    if (isAdded()) {
                                                        hideCoverNetworkLoading();
                                                        DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                                                    }
                                                }
                                            });
                                            mRejectDialog.dismiss();
                                        }
                                    }, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });

                                }
                            } else {
                                DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.ask_reject), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        showCoverNetworkLoading();
                                        new RejectRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId(), ((RejectReasonResponse) spinner1.getSelectedItem()).getId(), edtReasonOther.getText().toString().trim(), ((RejectReasonResponse) spinner1.getSelectedItem()).getStep()).callRequest(getActivity(), new ApiObjectCallBack<RejectResponse, ErrorResponse>() {
                                            @Override
                                            public void onSuccess(int status, RejectResponse dataSuccess, List<RejectResponse> listDataSuccess, String message) {
                                                if (isAdded()) {
                                                    if (status == 200) {
                                                        hideCoverNetworkLoading();
                                                        DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.send_reject_success), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                cardReject.setVisibility(View.VISIBLE);
                                                                tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName + "\n" + getResources().getString(R.string.reject_mess3) + ": " + dataSuccess.getReasonNote());
                                                            }
                                                        });
                                                        cardReject.setVisibility(View.VISIBLE);
                                                        btnNext.setEnabled(false);
                                                        btnReject.setEnabled(false);
                                                        rlGotoworkDate.setEnabled(false);
                                                        tvRound.setEnabled(false);
                                                        detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                        detailProcessResumeResponse.getCvProcessInfo().setRejectStep(4);
                                                        tvDate.setEnabled(false);
                                                        llBtn.setVisibility(View.GONE);
                                                        getEventBaseFragment().reject();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                                                if (isAdded()) {
                                                    hideCoverNetworkLoading();
                                                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                                                }
                                            }
                                        });
                                        mRejectDialog.dismiss();
                                    }
                                }, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                            }
                        }
                    });
                    mRejectDialog.show();
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                }
            }
        });
    }

    @OnClick({R.id.rlGotoworkDate, R.id.tvRound, R.id.tvDate})
    public void onGotoWorkDate() {
        if (detailProcessResumeResponse.getJobCvGotoWorkDto().getCountUpdate() != null) {
            if (detailProcessResumeResponse.getJobCvGotoWorkDto().getCountUpdate() < 3) {
                if (detailProcessResumeResponse.getJobCvGotoWorkDto() != null) {
                    if (detailProcessResumeResponse.getJobCvGotoWorkDto().getId() != null)
                        FragmentUtil.pushFragment(getActivity(), GoToWorkProcessResumeFragment.this, new CreateGoToWorkFragment().newInstance(detailProcessResumeResponse.getJobCvGotoWorkDto(), detailProcessResumeResponse), null);
                    else
                        FragmentUtil.pushFragment(getActivity(), GoToWorkProcessResumeFragment.this, new CreateGoToWorkFragment().newInstance(null, detailProcessResumeResponse), null);

                }
            }
        } else {
            FragmentUtil.pushFragment(getActivity(), GoToWorkProcessResumeFragment.this, new CreateGoToWorkFragment().newInstance(null, detailProcessResumeResponse), null);

        }
    }

    private Dialog mNotifyDialog;

    @OnClick(R.id.btnNext)
    public void onNextClick() {
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
        tvContent.setText(getResources().getString(R.string.ask_update_status_contract));
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotifyDialog.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailProcessResumeResponse.getJobCvGotoWorkDto().getStartWorkDate() == null) {
                    mNotifyDialog.dismiss();
                    detailProcessResumeResponse.getCvProcessInfo().setStatus(9);
                    getEventBaseFragment().changeStepExp(4);
                } else {
                    new ContractStatusRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId()).callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {
                        @Override
                        public void onSuccess(int status, ErrorResponse dataSuccess, List<ErrorResponse> listDataSuccess, String message) {
                            if (isAdded()) {
                                if (status == 200) {
                                    mNotifyDialog.dismiss();
                                    detailProcessResumeResponse.getCvProcessInfo().setStatus(9);
                                    getEventBaseFragment().changeStepExp(4);
                                }
                            }
                        }

                        @Override
                        public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                            if (isAdded()) {
                                if (status == 200) {
                                    mNotifyDialog.dismiss();
                                    detailProcessResumeResponse.getCvProcessInfo().setStatus(9);
                                    getEventBaseFragment().changeStepExp(4);

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

    private JobCvGotoWorkDto jobCvGotoWorkDto;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                jobCvGotoWorkDto = data.getParcelableExtra("jobCvGotoWorkDto");
                detailProcessResumeResponse.setJobCvGotoWorkDto(jobCvGotoWorkDto);
                tvDate.setText(detailProcessResumeResponse.getJobCvGotoWorkDto().getStartWorkDate());
                tvWarranty.setText(detailProcessResumeResponse.getJobCvGotoWorkDto().getNumDayWarranty() + "");
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
