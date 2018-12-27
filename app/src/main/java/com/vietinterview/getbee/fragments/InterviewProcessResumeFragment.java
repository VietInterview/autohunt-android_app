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
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ListInterviewAdapter;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.OfferStatusRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.request.UpdateInterviewStatusRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.ViewEmailInterviewResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnSwitchToTwoListener;
import com.vietinterview.getbee.constant.AppConstant;
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
public class InterviewProcessResumeFragment extends BaseFragment {
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    @BindView(R.id.list)
    public ListView listView;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.cardReject)
    CardView cardReject;
    @BindView(R.id.tvAddRound)
    TextView tvAddRound;
    @BindView(R.id.btnNotAccept)
    Button btnNotAccept;
    @BindView(R.id.llBtn)
    LinearLayout llBtn;
    @BindView(R.id.tvReject)
    TextView tvReject;
    DetailProcessResumeResponse detailProcessResumeResponse;
    private Dialog mReasonNotAcceptDialog;
    private Dialog mRejectDialog;
    private LstInterviewHi lstInterviewHi;
    private ListInterviewAdapter listInterviewOfferAdapter;

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
        if (lstInterviewHi == null) {
            if (detailProcessResumeResponse.getLstInterviewHis().size() > 0)
                showListInterview();
            else {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6)
                    FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new CreateEditInterviewFragment().newInstance(null, detailProcessResumeResponse), null);
            }
        } else if (lstInterviewHi != null) {
            if (lstInterviewHi.getId() == -1) {

            }
        }
        if (detailProcessResumeResponse.getLstInterviewHis().size() > 0) {
            int count = detailProcessResumeResponse.getLstInterviewHis().size();
            for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
                if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == -1) {
                    count--;
                }
            }
            if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2)) {

                if (count > 0) {
                    if (detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getStatus() == 1) {
                        btnNext.setVisibility(View.VISIBLE);
                        btnNotAccept.setVisibility(View.GONE);
                    } else if (detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getStatus() == 2) {
                        btnNext.setVisibility(View.GONE);
                        btnNotAccept.setVisibility(View.VISIBLE);
                    } else if (detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getStatus() == 3) {
                        btnNext.setVisibility(View.VISIBLE);
                        btnNotAccept.setVisibility(View.VISIBLE);
                    } else {
                        btnNext.setVisibility(View.GONE);
                        btnNotAccept.setVisibility(View.GONE);
                        llBtn.setVisibility(View.GONE);
                    }
                }
            } else {
                btnNext.setVisibility(View.GONE);
                tvAddRound.setVisibility(View.GONE);
                llAdd.setVisibility(View.GONE);
                btnNotAccept.setVisibility(View.GONE);
            }
        }
        getEventBaseFragment().setOnSwitchToTwoListener(new OnSwitchToTwoListener() {
            @Override
            public void onSwitchToTwo() {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2)) {
                    llBtn.setVisibility(View.VISIBLE);
                    tvAddRound.setVisibility(View.VISIBLE);
                    llAdd.setVisibility(View.VISIBLE);
                    btnNext.setEnabled(true);
                    listView.setEnabled(true);
                    btnNotAccept.setEnabled(true);
                    tvAddRound.setEnabled(true);
                } else {
                    llBtn.setVisibility(View.GONE);
                    tvAddRound.setVisibility(View.GONE);
                    llAdd.setVisibility(View.GONE);
                    btnNext.setEnabled(false);
                    listView.setEnabled(true);
                    btnNotAccept.setEnabled(false);
                    tvAddRound.setEnabled(false);
                }
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                    if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        llBtn.setVisibility(View.GONE);
                        tvAddRound.setVisibility(View.GONE);
                        llAdd.setVisibility(View.GONE);
                        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2) {
                            cardReject.setVisibility(View.VISIBLE);
                            getEventBaseFragment().reject();
                            btnNext.setEnabled(false);
                            listView.setEnabled(true);
                            btnNotAccept.setEnabled(false);
                            tvAddRound.setEnabled(false);
                            tvAddRound.setVisibility(View.GONE);
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
                llBtn.setVisibility(View.GONE);
                tvAddRound.setVisibility(View.GONE);
                llAdd.setVisibility(View.GONE);
                if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2) {
                    cardReject.setVisibility(View.VISIBLE);
                    getEventBaseFragment().reject();
                    btnNext.setEnabled(false);
                    listView.setEnabled(true);
                    btnNotAccept.setEnabled(false);
                    tvAddRound.setEnabled(false);
                    tvAddRound.setVisibility(View.GONE);
                    llBtn.setVisibility(View.GONE);
                    llAdd.setVisibility(View.GONE);
                } else cardReject.setVisibility(View.GONE);
            } else cardReject.setVisibility(View.GONE);
        } else {
            cardReject.setVisibility(View.GONE);
        }
        if (detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName() != null ) {
            tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName());
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectNote() != null && !detailProcessResumeResponse.getCvProcessInfo().getRejectNote().equalsIgnoreCase(""))
                tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName() + "\nGhi chú: " + detailProcessResumeResponse.getCvProcessInfo().getRejectNote());

        }
    }

    @OnClick(R.id.tvAddRound)
    public void onAddRoundClick() {
        int count = detailProcessResumeResponse.getLstInterviewHis().size();
        for (int i = 0; i < count; i++) {
            if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == -1) {
                count--;
            }
        }
        if (count > 0) {
            if (detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getStatus() == 1) {
                FragmentUtil.pushFragment(getActivity(), this, new CreateEditInterviewFragment().newInstance(null, detailProcessResumeResponse), null);
            } else if (detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getStatus() == 0) {
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Bạn phải cập nhật trạng thái vòng phỏng vấn trước, trước khi tạo vòng phỏng vấn mới");
            } else if (detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getStatus() == 2) {
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Vòng phỏng vấn gần nhất ứng viên không đạt vì vậy bạn không thể thêm mới vòng phỏng vấn");
            } else {
                FragmentUtil.pushFragment(getActivity(), this, new CreateEditInterviewFragment().newInstance(null, detailProcessResumeResponse), null);
            }
        } else {
            FragmentUtil.pushFragment(getActivity(), this, new CreateEditInterviewFragment().newInstance(null, detailProcessResumeResponse), null);
        }
    }

    private Dialog mNotifyDialog;

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (detailProcessResumeResponse.getLstInterviewHis().get(detailProcessResumeResponse.getLstInterviewHis().size() - 1).getStatus() == 1) {
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
            tvContent.setText("Bạn có chắc chắn gửi offer tới ứng viên này không?");
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mNotifyDialog.dismiss();
                }
            });
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (detailProcessResumeResponse.getLstOfferHis().size() > 0) {
                        mNotifyDialog.dismiss();
                        getEventBaseFragment().changeStepExp(2);
                        detailProcessResumeResponse.getCvProcessInfo().setStatus(7);
                    } else {
                        new OfferStatusRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId()).callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {
                            @Override
                            public void onSuccess(int status, ErrorResponse dataSuccess, List<ErrorResponse> listDataSuccess, String message) {
                                if (isAdded()) {
                                    if (status == 200) {
                                        mNotifyDialog.dismiss();
                                        getEventBaseFragment().changeStepExp(2);
                                        detailProcessResumeResponse.getCvProcessInfo().setStatus(7);

                                    }
                                }
                            }

                            @Override
                            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                                if (isAdded()) {
                                    if (status == 200) {
                                        mNotifyDialog.dismiss();
                                        getEventBaseFragment().changeStepExp(2);
                                        detailProcessResumeResponse.getCvProcessInfo().setStatus(7);

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
        } else if (detailProcessResumeResponse.getLstInterviewHis().get(detailProcessResumeResponse.getLstInterviewHis().size() - 1).getStatus() == 0) {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Bạn phải cập nhật trạng thái vòng phỏng vấn trước, trước khi tạo vòng phỏng vấn mới");
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

    boolean isOther = false;
    private String rejectName;

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
                        if (listDataSuccess.get(i).getStep() == 2) {
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
                                edtReasonOther.setFocusableInTouchMode(true);
                                edtReasonOther.setClickable(true);
                            } else {
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
                            if (isOther) {
                                if (edtReasonOther.getText().toString().trim().equalsIgnoreCase("")) {
                                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Xin hãy nhập lý do từ chối");
                                } else {
                                    DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), "Bạn có chắc chắn muốn gửi email và cập nhật trạng thái từ chối cho hồ sơ này?", "Có", "Không", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            showCoverNetworkLoading();
                                            new RejectRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId(), ((RejectReasonResponse) spinner1.getSelectedItem()).getId(), edtReasonOther.getText().toString().trim(), ((RejectReasonResponse) spinner1.getSelectedItem()).getStep()).callRequest(getActivity(), new ApiObjectCallBack<RejectResponse, ErrorResponse>() {
                                                @Override
                                                public void onSuccess(int status, RejectResponse dataSuccess, List<RejectResponse> listDataSuccess, String message) {
                                                    if (isAdded()) {
                                                        if (status == 200) {
                                                            hideCoverNetworkLoading();
                                                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Gửi từ chối thành công", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    cardReject.setVisibility(View.VISIBLE);
                                                                    tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + rejectName + "\nGhi chú: " + dataSuccess.getReasonNote());
                                                                }
                                                            });
//                                                            int count = detailProcessResumeResponse.getLstInterviewHis().size();
//                                                            for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
//                                                                if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == -1) {
//                                                                    count--;
//                                                                }
//                                                            }
//                                                            if (count > 0) {
//                                                                updateInterviewStatus(count);
//                                                            }
                                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                            detailProcessResumeResponse.getCvProcessInfo().setRejectStep(2);
                                                            btnNext.setEnabled(false);
                                                            tvAddRound.setEnabled(false);
                                                            tvAddRound.setVisibility(View.GONE);
                                                            llAdd.setVisibility(View.GONE);
                                                            listView.setEnabled(true);
                                                            btnNotAccept.setEnabled(false);
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
                                DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), "Bạn có chắc chắn muốn gửi email và cập nhật trạng thái từ chối cho hồ sơ này?", "Có", "Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        showCoverNetworkLoading();
                                        new RejectRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId(), ((RejectReasonResponse) spinner1.getSelectedItem()).getId(), edtReasonOther.getText().toString().trim(), ((RejectReasonResponse) spinner1.getSelectedItem()).getStep()).callRequest(getActivity(), new ApiObjectCallBack<RejectResponse, ErrorResponse>() {
                                            @Override
                                            public void onSuccess(int status, RejectResponse dataSuccess, List<RejectResponse> listDataSuccess, String message) {
                                                if (isAdded()) {
                                                    if (status == 200) {
                                                        hideCoverNetworkLoading();
                                                        DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Gửi từ chối thành công", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                cardReject.setVisibility(View.VISIBLE);
                                                                tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + rejectName + "\nGhi chú: " + dataSuccess.getReasonNote());
                                                            }
                                                        });
//                                                        int count = detailProcessResumeResponse.getLstInterviewHis().size();
//                                                        for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
//                                                            if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == -1) {
//                                                                count--;
//                                                            }
//                                                        }
//                                                        if (count > 0) {
//                                                            updateInterviewStatus(count);
//                                                        }
                                                        detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                        detailProcessResumeResponse.getCvProcessInfo().setRejectStep(2);
                                                        cardReject.setVisibility(View.VISIBLE);
                                                        btnNext.setEnabled(false);
                                                        tvAddRound.setEnabled(false);
                                                        llAdd.setVisibility(View.GONE);
                                                        tvAddRound.setVisibility(View.GONE);
                                                        listView.setEnabled(true);
                                                        btnNotAccept.setEnabled(false);
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

    public void updateInterviewStatus(int count) {
        showCoverNetworkLoading();
        int cvId = detailProcessResumeResponse.getCvId();
        int id = detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getId();
        String placeINterview = detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getInterviewAddress();
        String date = detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getInterviewDate();
        int jobId = detailProcessResumeResponse.getJobId();
        String Note = detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getNote();
        String round = detailProcessResumeResponse.getLstInterviewHis().get(count - 1).getRound();
        new UpdateInterviewStatusRequest(cvId, id, placeINterview, date, jobId, Note, round, 2).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        detailProcessResumeResponse.getLstInterviewHis().get(count - 1).setStatus(2);
                        showListInterview();
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        detailProcessResumeResponse.getLstInterviewHis().get(count - 1).setStatus(2);
                        showListInterview();
                    }
                }
            }
        });
    }

    public void showListInterview() {
        List<LstInterviewHi> lstInterviewHis = new ArrayList<>();
        for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
            if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() != -1) {
                lstInterviewHis.add(detailProcessResumeResponse.getLstInterviewHis().get(i));
            }
        }
        listInterviewOfferAdapter = new ListInterviewAdapter(getActivity(), lstInterviewHis);
        listView.setAdapter(listInterviewOfferAdapter);
        LayoutUtils.setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2)) {
                    if (lstInterviewHis.get(position).getId() != -1) {
                        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                            FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new DetailInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position)), null);

                        } else {
                            if (position == lstInterviewHis.size() - 1) {
                                FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new CreateEditInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position), detailProcessResumeResponse), null);
                            } else {
                                FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new DetailInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position)), null);
                            }
                        }
                    }
                } else {
                    if (lstInterviewHis.get(position).getId() != -1) {
                        FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new DetailInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position)), null);
                    }
                }
            }
        });
//        flowListInterview.removeAllViews();
//        for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
//            LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(flowListInterview, R.layout.interview_item_view, false);
//            if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() != -1) {
//                final int position = i;
//                RelativeLayout rlRound = (RelativeLayout) linearLayout.findViewById(R.id.rlRound);
//                TextView tvRound = (TextView) linearLayout.findViewById(R.id.tvRound);
//                TextView tvDate = (TextView) linearLayout.findViewById(R.id.tvDate);
//                TextView tvContentReason = (TextView) linearLayout.findViewById(R.id.tvContentReason);
//                tvRound.setText(detailProcessResumeResponse.getLstInterviewHis().get(i).getRound());
//                tvDate.setText(DateUtil.convertToMyFormat3(detailProcessResumeResponse.getLstInterviewHis().get(i).getInterviewDate()));
//                tvContentReason.setText(switchResult(detailProcessResumeResponse.getLstInterviewHis().get(i).getStatus()));
//                rlRound.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int count = detailProcessResumeResponse.getLstInterviewHis().size();
//                        for (int i = 0; i < count; i++) {
//                            if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == -1) {
//                                count--;
//                            }
//                        }
//                        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 5 || detailProcessResumeResponse.getCvProcessInfo().getStatus() == 6 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 2)) {
//
//                            if (detailProcessResumeResponse.getLstInterviewHis().get(position).getId() != -1) {
//                                if (position == count - 1) {
//                                    FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new CreateEditInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position), detailProcessResumeResponse), null);
//                                } else {
//                                    FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new DetailInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position)), null);
//                                }
//                            }
//                        } else {
//                            if (detailProcessResumeResponse.getLstInterviewHis().get(position).getId() != -1) {
//                                FragmentUtil.pushFragment(getActivity(), InterviewProcessResumeFragment.this, new DetailInterviewFragment().newInstance(detailProcessResumeResponse.getLstInterviewHis().get(position)), null);
//                            }
//                        }
//                    }
//                });
//                flowListInterview.addView(linearLayout);
//            }
//        }
    }

    public String switchResult(int status) {
        String statusInterview = "";
        switch (status) {
            case 1:
                statusInterview = "Đạt";
                break;
            case 2:
                statusInterview = "Không đạt";
                break;
            case 3:
                statusInterview = "Ứng viên không đến";
                break;
            default:
                statusInterview = "Chưa có kết quả";
                break;
        }
        return statusInterview;
    }

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
                    if (isDuplicate == false) {
                        detailProcessResumeResponse.getLstInterviewHis().add(lstInterviewHi);
                    }
                    showListInterview();
                }
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
