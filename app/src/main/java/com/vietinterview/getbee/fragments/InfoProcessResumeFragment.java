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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetDetailCVByJobCustomerRequest;
import com.vietinterview.getbee.api.request.GetDetailJobCustomerRequest;
import com.vietinterview.getbee.api.request.GetListRejectReasonRequest;
import com.vietinterview.getbee.api.request.InviteInterviewRequest;
import com.vietinterview.getbee.api.request.RejectRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.RejectReasonResponse;
import com.vietinterview.getbee.api.response.RejectResponse;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.api.response.detailjobcustomer.DetailJobCustomerResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnSwitchToOneListener;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;
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
    @BindView(R.id.llButton)
    LinearLayout llButton;
    @BindView(R.id.tvReject)
    TextView tvReject;
    DetailProcessResumeResponse detailProcessResumeResponse;
    private Dialog mRejectDialog;
    private Dialog mNotifyDialog;
    private boolean isDetail;

    public static InfoProcessResumeFragment newInstance(DetailProcessResumeResponse detailProcessResumeResponse, boolean isDetail) {
        InfoProcessResumeFragment fm = new InfoProcessResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        bundle.putBoolean("isDetail", isDetail);
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
        isDetail = bundle.getBoolean("isDetail");
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
        getEventBaseFragment().setOnSwitchToOneListener(new OnSwitchToOneListener() {
            @Override
            public void onSwitchToOne() {
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 3 || (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4 && detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 1)) {
                    llButton.setVisibility(View.VISIBLE);
                } else {
                    llButton.setVisibility(View.GONE);
                }
                if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
                    if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                        if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 1) {
                            cardReject.setVisibility(View.VISIBLE);
                            getEventBaseFragment().reject();
//                    btnInviteInterview.setEnabled(false);
//                    btnReject.setEnabled(false);
                            llButton.setVisibility(View.GONE);
                        } else cardReject.setVisibility(View.GONE);
                    } else cardReject.setVisibility(View.GONE);
                } else {
                    cardReject.setVisibility(View.GONE);
                }
            }
        });
        if (detailProcessResumeResponse.getCvProcessInfo().getStatus() == 4) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() != null) {
                if (detailProcessResumeResponse.getCvProcessInfo().getRejectStep() == 1) {
                    cardReject.setVisibility(View.VISIBLE);
                    getEventBaseFragment().reject();
//                    btnInviteInterview.setEnabled(false);
//                    btnReject.setEnabled(false);
                    llButton.setVisibility(View.GONE);
                } else cardReject.setVisibility(View.GONE);
            } else cardReject.setVisibility(View.GONE);
        } else {
            cardReject.setVisibility(View.GONE);
        }
        if (detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName() != null && detailProcessResumeResponse.getCvProcessInfo().getRejectNote() != null) {
            if (detailProcessResumeResponse.getCvProcessInfo().getRejectNote() != null && !detailProcessResumeResponse.getCvProcessInfo().getRejectNote().equalsIgnoreCase(""))
                tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName() + "\nGhi chú: " + detailProcessResumeResponse.getCvProcessInfo().getRejectNote());
            else
                tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + detailProcessResumeResponse.getCvProcessInfo().getReasonRejectName());

        }
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
        tvContent.setText("Bạn có chắc chắn muốn mời ứng viên này đi phỏng vấn?");
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
                    detailProcessResumeResponse.getCvProcessInfo().setStatus(5);
                } else {
                    new InviteInterviewRequest(detailProcessResumeResponse.getCvId(), detailProcessResumeResponse.getJobId()).callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {
                        @Override
                        public void onSuccess(int status, ErrorResponse dataSuccess, List<ErrorResponse> listDataSuccess, String message) {
                            if (isAdded()) {
                                if (status == 200) {
                                    mNotifyDialog.dismiss();
                                    getEventBaseFragment().changeStepExp(1);
                                    detailProcessResumeResponse.getCvProcessInfo().setStatus(5);

                                }
                            }
                        }

                        @Override
                        public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                            if (isAdded()) {
                                if (status == 200) {
                                    mNotifyDialog.dismiss();
                                    getEventBaseFragment().changeStepExp(1);
                                    detailProcessResumeResponse.getCvProcessInfo().setStatus(5);

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

    private GetDetailCVByJobCustomerRequest getDetailCVByJobCustomerRequest;

    @OnClick(R.id.imgInfo)
    public void onImageClick() {
        if (isDetail)
            FragmentUtil.popBackStack(this);
        else {
            showCoverNetworkLoading();
            getDetailCVByJobCustomerRequest = new GetDetailCVByJobCustomerRequest(detailProcessResumeResponse.getCvId());
            getDetailCVByJobCustomerRequest.callRequest(getActivity(), new ApiObjectCallBack<DetailCVCustomerResponse, ErrorResponse>() {
                @Override
                public void onSuccess(int status, DetailCVCustomerResponse data, List<DetailCVCustomerResponse> dataArrayList, String message) {
                    hideCoverNetworkLoading();
                    if (isAdded()) {
                        FragmentUtil.pushFragment(getActivity(), InfoProcessResumeFragment.this, new DetailResumeCustomerFragment().newInstance(data), null);
                    }
                }

                @Override
                public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                    hideCoverNetworkLoading();
                    if (isAdded()) {
                        DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                    }
                }
            });
        }
    }

    boolean isOther = false;
    String rejectName = "";

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
                                                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Gửi từ chối thành công");
                                                            cardReject.setVisibility(View.VISIBLE);
                                                            tvReject.setText("Ứng viên này đã bị từ chối\nLý do: " + rejectName + "\nGhi chú: " + dataSuccess.getReasonNote());
                                                            detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                            detailProcessResumeResponse.getCvProcessInfo().setRejectStep(1);
                                                            btnInviteInterview.setEnabled(false);
                                                            btnReject.setEnabled(false);
                                                            llButton.setVisibility(View.GONE);
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
                                                        detailProcessResumeResponse.getCvProcessInfo().setStatus(4);
                                                        detailProcessResumeResponse.getCvProcessInfo().setRejectStep(1);
                                                        cardReject.setVisibility(View.VISIBLE);
                                                        btnInviteInterview.setEnabled(false);
                                                        btnReject.setEnabled(false);
                                                        llButton.setVisibility(View.GONE);
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
