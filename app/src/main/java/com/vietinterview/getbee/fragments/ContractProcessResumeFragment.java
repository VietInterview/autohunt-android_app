package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.NothingSelectedSpinnerAdapter;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnSwitchToFiveListener;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ContractProcessResumeFragment extends BaseFragment {
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.btnReject)
    Button btnReject;
    @BindView(R.id.llBtn)
    LinearLayout llBtn;
    @BindView(R.id.tvReject)
    TextView tvReject;
    @BindView(R.id.cardContract)
    CardView cardContract;
    private DetailProcessResumeResponse detailProcessResumeResponse;
    private Dialog mRejectDialog;
    private boolean isOther = false;
    private String rejectName;
    private String rejectNote;

    public static ContractProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse) {
        ContractProcessResumeFragment fm = new ContractProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contract_process_resume;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().setOnSwitchToFiveListener(new OnSwitchToFiveListener() {
            @Override
            public void onSwitchToFive() {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 9 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 5)) {
                    llBtn.setVisibility(View.VISIBLE);
                    cardContract.setVisibility(View.VISIBLE);
                } else {
                    llBtn.setVisibility(View.GONE);
                    cardContract.setVisibility(View.GONE);
                }
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                    if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 5) {
                            cardReject.setVisibility(View.VISIBLE);
                            cardContract.setVisibility(View.GONE);
                            getEventBaseFragment().reject();
                            btnReject.setEnabled(false);
                            llBtn.setVisibility(View.GONE);
                        } else cardReject.setVisibility(View.GONE);
                    } else cardReject.setVisibility(View.GONE);
                } else {
                    cardReject.setVisibility(View.GONE);
                }
            }
        });
        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 9 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 5)) {
            llBtn.setVisibility(View.VISIBLE);
            cardContract.setVisibility(View.VISIBLE);
        } else {
            llBtn.setVisibility(View.GONE);
            cardContract.setVisibility(View.GONE);
        }
        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 5) {
                    cardReject.setVisibility(View.VISIBLE);
                    cardContract.setVisibility(View.GONE);
                    getEventBaseFragment().reject();
                    btnReject.setEnabled(false);
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
                        if (listDataSuccess.get(i).getStep() == 5) {
                            listReasonName.add(listDataSuccess.get(i));
                        }
                    }
                    listReasonName.add(new RejectReasonResponse(-1, "Lựa chọn lý do", "-1", 3));
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
                    spinner1.setAdapter(new NothingSelectedSpinnerAdapter(dataAdapter, R.layout.contact_spinner_row_nothing_selected, getActivity()));
                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position >= 1) {
                                rejectName = listReasonName.get(position - 1).getName();
                                if (listReasonName.get(position - 1).getCode().equalsIgnoreCase("other")) {
                                    isOther = true;
                                    edtReasonOther.setFocusable(true);
                                    edtReasonOther.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
                                    edtReasonOther.setClickable(true);
                                } else {
                                    isOther = false;
                                    edtReasonOther.setFocusable(false);
                                    edtReasonOther.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
                                    edtReasonOther.setClickable(false);
                                }
                            } else {
                                edtReasonOther.setText("");
                                edtReasonOther.setFocusable(false);
                                edtReasonOther.setFocusableInTouchMode(false);
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
                            if ((RejectReasonResponse) spinner1.getSelectedItem() == null) {
                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Bạn phải chọn lý do");
                            } else {
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
                                                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.send_reject_success));
                                                                cardReject.setVisibility(View.VISIBLE);
                                                                cardContract.setVisibility(View.GONE);
                                                                if (rejectName != null) {
                                                                    detailProcessResumeResponse.getCvProcessInfo().setReasonRejectName(rejectName);
                                                                    detailProcessResumeResponse.getCvProcessInfo().setRejectNote(dataSuccess.getReasonNote());
                                                                    tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName);
                                                                    if (dataSuccess.getReasonNote() != null && !dataSuccess.getReasonNote().equalsIgnoreCase("")) {
                                                                        tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName + "\n" + getResources().getString(R.string.reject_mess3) + ": " + dataSuccess.getReasonNote());
                                                                        rejectNote = dataSuccess.getReasonNote();
                                                                    }
                                                                }
                                                                detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                                detailProcessResumeResponse.getCvProcessInfo().setRejectStep(5);
                                                                btnReject.setEnabled(false);
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
                                                                    if (rejectName != null) {
                                                                        detailProcessResumeResponse.getCvProcessInfo().setReasonRejectName(rejectName);
                                                                        detailProcessResumeResponse.getCvProcessInfo().setRejectNote(dataSuccess.getReasonNote());
                                                                        tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName);
                                                                        if (dataSuccess.getReasonNote() != null && !dataSuccess.getReasonNote().equalsIgnoreCase("")) {
                                                                            rejectNote = dataSuccess.getReasonNote();
                                                                            tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName + "\n" + getResources().getString(R.string.reject_mess3) + ": " + dataSuccess.getReasonNote());
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                            detailProcessResumeResponse.getCvProcessInfo().setRejectStep(5);
                                                            cardReject.setVisibility(View.VISIBLE);
                                                            btnReject.setEnabled(false);
                                                            cardContract.setVisibility(View.GONE);
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
}
