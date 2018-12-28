package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ListOfferAdapter;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.GoToWorkStatusRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.request.UpdateOfferStatusRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.SendOfferResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstOfferHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnSwitchToThreeListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.utils.DateUtil;
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
    @BindView(R.id.list)
    public ListView listView;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.tvAddOffer)
    TextView tvAddOffer;
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.btnReject)
    Button btnReject;
    @BindView(R.id.llBtn)
    LinearLayout llBtn;
    @BindView(R.id.tvReject)
    TextView tvReject;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    DetailProcessResumeResponse detailProcessResumeResponse;
    LstOfferHi lstOfferHi;
    ListOfferAdapter listOfferAdapter;

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
        if (lstOfferHi == null) {
            if (detailProcessResumeResponse.getLstOfferHis().size() > 0) {
                showListOffer();
            } else {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 7)
                    FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
            }
        } else if (lstOfferHi != null) {
            if (lstOfferHi.getId() == -1) {

            }
        }
        if (detailProcessResumeResponse.getLstOfferHis().size() > 0) {
            int count = detailProcessResumeResponse.getLstOfferHis().size();
            for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
                if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() == -1) {
                    count--;
                }
            }
            if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 7 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3)) {
                if (count > 0) {
                    if (detailProcessResumeResponse.getLstOfferHis().get(count - 1).getStatus() == 1) {
                        btnNext.setVisibility(View.VISIBLE);
                        btnReject.setVisibility(View.VISIBLE);
                    } else if (detailProcessResumeResponse.getLstOfferHis().get(count - 1).getStatus() == 2) {
                        btnNext.setVisibility(View.GONE);
                        btnReject.setVisibility(View.VISIBLE);
                    } else {
                        btnNext.setVisibility(View.GONE);
                        btnReject.setVisibility(View.GONE);
                        llBtn.setVisibility(View.GONE);
                    }
                }
            } else {
                btnNext.setVisibility(View.GONE);
                btnReject.setVisibility(View.GONE);
                tvAddOffer.setVisibility(View.GONE);
                llAdd.setVisibility(View.GONE);
            }
        }
        getEventBaseFragment().setOnSwitchToThreeListener(new OnSwitchToThreeListener() {
            @Override
            public void onSwitchToThree() {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 7 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3)) {
                    btnNext.setEnabled(true);
                    listView.setEnabled(true);
                    btnReject.setEnabled(true);
                    tvAddOffer.setEnabled(true);
                    tvAddOffer.setVisibility(View.VISIBLE);
                    llAdd.setVisibility(View.VISIBLE);
                    llBtn.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setEnabled(false);
                    listView.setEnabled(true);
                    btnReject.setEnabled(false);
                    tvAddOffer.setEnabled(false);
                    tvAddOffer.setVisibility(View.GONE);
                    llAdd.setVisibility(View.GONE);
                    llBtn.setVisibility(View.GONE);
                }
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                    if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3) {
                            cardReject.setVisibility(View.VISIBLE);
                            getEventBaseFragment().reject();
                            btnNext.setEnabled(false);
                            listView.setEnabled(true);
                            btnReject.setEnabled(false);
                            tvAddOffer.setEnabled(false);
                            tvAddOffer.setVisibility(View.GONE);
                            llAdd.setVisibility(View.GONE);
                            llBtn.setVisibility(View.GONE);
                        } else cardReject.setVisibility(View.GONE);
                    } else cardReject.setVisibility(View.GONE);
                } else {
                    cardReject.setVisibility(View.GONE);
                }
            }
        });
        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3) {
                    cardReject.setVisibility(View.VISIBLE);
                    getEventBaseFragment().reject();
                    btnNext.setEnabled(false);
                    listView.setEnabled(true);
                    btnReject.setEnabled(false);
                    tvAddOffer.setEnabled(false);
                    tvAddOffer.setVisibility(View.GONE);
                    llAdd.setVisibility(View.GONE);
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

    @Override
    protected void getArgument(Bundle bundle) {
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initData() {

    }

    public void showListOffer() {
        List<LstOfferHi> lstInterviewHis = new ArrayList<>();
        for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
            if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() != -1) {
                lstInterviewHis.add(detailProcessResumeResponse.getLstOfferHis().get(i));
            }
        }
        listOfferAdapter = new ListOfferAdapter(getActivity(), lstInterviewHis);
        listView.setAdapter(listOfferAdapter);
        LayoutUtils.setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 7 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 3)) {
                    if (lstInterviewHis.get(position).getId() != -1) {
                        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                            FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new DetailOfferFragment().newInstance(detailProcessResumeResponse.getLstOfferHis().get(position)), null);

                        } else {
                            if (position == lstInterviewHis.size() - 1) {
                                FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new CreateEditOfferFragment().newInstance(detailProcessResumeResponse.getLstOfferHis().get(position), detailProcessResumeResponse), null);
                            } else {
                                FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new DetailOfferFragment().newInstance(detailProcessResumeResponse.getLstOfferHis().get(position)), null);
                            }
                        }
                    }
                } else {
                    if (lstInterviewHis.get(position).getId() != -1) {
                        FragmentUtil.pushFragment(getActivity(), OfferProcessResumeFragment.this, new DetailOfferFragment().newInstance(detailProcessResumeResponse.getLstOfferHis().get(position)), null);
                    }
                }
            }
        });
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
                tvContent.setText(getResources().getString(R.string.ask_update_gotowork));
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
                            detailProcessResumeResponse.getCvProcessInfo().setStatus(8);
                        } else {
                            new GoToWorkStatusRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId()).callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {
                                @Override
                                public void onSuccess(int status, ErrorResponse dataSuccess, List<ErrorResponse> listDataSuccess, String message) {
                                    if (isAdded()) {
                                        if (status == 200) {
                                            mNotifyDialog.dismiss();
                                            getEventBaseFragment().changeStepExp(3);
                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(8);
                                        }
                                    }
                                }

                                @Override
                                public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                                    if (isAdded()) {
                                        if (status == 200) {
                                            mNotifyDialog.dismiss();
                                            getEventBaseFragment().changeStepExp(3);
                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(8);

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
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.offer_rejected));
            }
        } else {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.warning_add_offer));
        }
    }

    @OnClick(R.id.tvAddOffer)
    public void onAddOfferClick() {
        int count = detailProcessResumeResponse.getLstOfferHis().size();
        for (int i = 0; i < count; i++) {
            if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() == -1) {
                count--;
            }
        }
        if (count > 0) {
            if (detailProcessResumeResponse.getLstOfferHis().get(count - 1).getStatus() == 2) {
                FragmentUtil.pushFragment(getActivity(), this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
            } else if (detailProcessResumeResponse.getLstOfferHis().get(count - 1).getStatus() == 1) {
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.warning_add_new_offer));
            } else if (detailProcessResumeResponse.getLstOfferHis().get(count - 1).getStatus() == 0) {
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.warning_update_offer));
            } else {
                FragmentUtil.pushFragment(getActivity(), this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
            }
        } else {
            FragmentUtil.pushFragment(getActivity(), this, new CreateEditOfferFragment().newInstance(null, detailProcessResumeResponse), null);
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
                                                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.send_reject_success));
                                                            cardReject.setVisibility(View.VISIBLE);
                                                            tvReject.setText(getResources().getString(R.string.reject_mess) + "\n" + getResources().getString(R.string.reject_mess2) + ": " + rejectName + "\n" + getResources().getString(R.string.reject_mess3) + ": " + dataSuccess.getReasonNote());
//                                                            int count = detailProcessResumeResponse.getLstOfferHis().size();
//                                                            for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
//                                                                if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() == -1) {
//                                                                    count--;
//                                                                }
//                                                            }
//                                                            if (count > 0) {
//                                                                updateOfferStatus(count);
//                                                            }
                                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                            detailProcessResumeResponse.getCvProcessInfo().setRejectStep(3);
                                                            btnNext.setEnabled(false);
                                                            tvAddOffer.setEnabled(false);
                                                            tvAddOffer.setVisibility(View.GONE);
                                                            llAdd.setVisibility(View.GONE);
                                                            listView.setEnabled(true);
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
                                DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title),  getResources().getString(R.string.ask_reject), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
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
//                                                        int count = detailProcessResumeResponse.getLstOfferHis().size();
//                                                        for (int i = 0; i < detailProcessResumeResponse.getLstOfferHis().size(); i++) {
//                                                            if (detailProcessResumeResponse.getLstOfferHis().get(i).getId() == -1) {
//                                                                count--;
//                                                            }
//                                                        }
//                                                        if (count > 0) {
//                                                            updateOfferStatus(count);
//                                                        }
                                                        detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                        detailProcessResumeResponse.getCvProcessInfo().setRejectStep(3);
                                                        cardReject.setVisibility(View.VISIBLE);
                                                        btnNext.setEnabled(false);
                                                        tvAddOffer.setEnabled(false);
                                                        tvAddOffer.setVisibility(View.GONE);
                                                        llAdd.setVisibility(View.GONE);
                                                        listView.setEnabled(true);
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

    public void updateOfferStatus(int count) {
        showCoverNetworkLoading();
        int cvId = detailProcessResumeResponse.getCvId();
        int mCurrency = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getCurency();
        int id = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getId();
        String position = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getPosition().trim();
        int jobId = detailProcessResumeResponse.getJobId();
        String note = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getNote().toString().trim();
        String round = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getRound().toString().trim();
        int status = 2;
        long salary = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getSalary();
        String workAddress = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getWorkAddress().trim();
        String workTime = detailProcessResumeResponse.getLstOfferHis().get(count - 1).getWorkTime().trim();
        new UpdateOfferStatusRequest(cvId, id, mCurrency, position, jobId, note, round, status, salary, workAddress, workTime).callRequest(getActivity(), new ApiObjectCallBack<SendOfferResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, SendOfferResponse dataSuccess, List<SendOfferResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        lstOfferHi = new LstOfferHi(mCurrency, dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getPosition(), dataSuccess.getRound(), dataSuccess.getSalary(), dataSuccess.getStatus(), dataSuccess.getWorkAddress(), dataSuccess.getWorkTime());
                        detailProcessResumeResponse.getLstOfferHis().set(count - 1, lstOfferHi);
                        showListOffer();
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        lstOfferHi = new LstOfferHi(mCurrency, cvId, detailProcessResumeResponse.getLstOfferHis().get(count - 1).getEmailTemplate(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getId(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getJobId(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getNote(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getPosition(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getRound(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getSalary(), 2, detailProcessResumeResponse.getLstOfferHis().get(count - 1).getWorkAddress(), detailProcessResumeResponse.getLstOfferHis().get(count - 1).getWorkTime());
                        detailProcessResumeResponse.getLstOfferHis().get(count - 1).setStatus(2);
                        showListOffer();
                    }
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
