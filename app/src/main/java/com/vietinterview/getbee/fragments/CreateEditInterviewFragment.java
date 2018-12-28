package com.vietinterview.getbee.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.SendInterviewRequest;
import com.vietinterview.getbee.api.request.UpdateInterviewStatusRequest;
import com.vietinterview.getbee.api.request.ViewEmailInterviewRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.ViewEmailInterviewResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.UiUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 12/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CreateEditInterviewFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.edtRound)
    EditText edtRound;
    @BindView(R.id.edtDateInterview)
    EditText edtDateInterview;
    @BindView(R.id.edtPlaceInterview)
    EditText edtPlaceInterview;
    @BindView(R.id.edtNote)
    EditText edtNote;
    @BindView(R.id.llResult)
    LinearLayout llResult;
    @BindView(R.id.tvAchieve)
    TextView tvAchieve;
    @BindView(R.id.tvNotAchieved)
    TextView tvNotAchieved;
    @BindView(R.id.tvCandidateNotCome)
    TextView tvCandidateNotCome;
    @BindView(R.id.btnSendMail)
    Button btnSendMail;
    @BindView(R.id.llDate)
    LinearLayout llDate;
    private LstInterviewHi lstInterviewHi;
    private Dialog mReasonNotAcceptDialog;
    private Calendar calendar;
    private int statusInterview = 0;
    private SimpleDateFormat timeFormat;
    private DetailProcessResumeResponse detailProcessResumeResponse;
    private static final String TIME_PATTERN = "hh:mm a";

    public static CreateEditInterviewFragment newInstance(LstInterviewHi lstInterviewHi, DetailProcessResumeResponse detailProcessResumeResponse) {
        CreateEditInterviewFragment fm = new CreateEditInterviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lstInterviewHi", lstInterviewHi);
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_edit_interview;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        lstInterviewHi = bundle.getParcelable("lstInterviewHi");
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        GlobalDefine.currentFragment = this;
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.info_interview));
        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.US);
    }

    @Override
    protected void initData() {
        edtRound.setFocusable(false);
        edtRound.setFocusableInTouchMode(false);
        edtRound.setClickable(false);
        if (lstInterviewHi != null) {
            llResult.setVisibility(View.VISIBLE);
            edtRound.setText(lstInterviewHi.getRound());
            edtDateInterview.setText(lstInterviewHi.getInterviewDate());
            edtNote.setText(lstInterviewHi.getNote());
            edtPlaceInterview.setText(lstInterviewHi.getInterviewAddress());
            switchResult(lstInterviewHi.getStatus());
            if (lstInterviewHi.getStatus() == 0) {
                btnSendMail.setVisibility(View.VISIBLE);
            } else {
                btnSendMail.setVisibility(View.GONE);
            }
        } else {
            llResult.setVisibility(View.GONE);
            for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
                if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() == -1) {
                    detailProcessResumeResponse.getLstInterviewHis().remove(i);
                }
            }
            edtRound.setText(getResources().getString(R.string.round) + " " + (detailProcessResumeResponse.getLstInterviewHis().size() + 1));
        }
    }

    @OnClick(R.id.tvAchieve)
    public void onAchieveClick() {
        DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.ask_update_round_interview), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                statusInterview = 1;
                updateInterviewStatus();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @OnClick(R.id.tvNotAchieved)
    public void onNotAchieveClick() {
        DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.ask_update_round_interview), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                statusInterview = 2;
                updateInterviewStatus();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @OnClick(R.id.tvCandidateNotCome)
    public void onCandidateNotComeClick() {
        DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), "Bạn có chắc chắn muốn cập nhật kết quả cho vòng phỏng vấn này?", "Có", "Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                statusInterview = 3;
                updateInterviewStatus();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    public void updateInterviewStatus() {
        showCoverNetworkLoading();
        new UpdateInterviewStatusRequest(detailProcessResumeResponse.getCvId(), lstInterviewHi == null ? -1 : lstInterviewHi.getId(), edtPlaceInterview.getText().toString().trim(), edtDateInterview.getText().toString().trim(), detailProcessResumeResponse.getJobId(), edtNote.getText().toString().trim(), edtRound.getText().toString().trim(), statusInterview).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        switchResult(statusInterview);
                        btnSendMail.setVisibility(View.GONE);
                        lstInterviewHi = new LstInterviewHi(dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getInterviewAddress(), dataSuccess.getInterviewDate(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getRound(), dataSuccess.getStatus());
                        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                        intent.putExtra("lstInterviewHi", lstInterviewHi);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                        FragmentUtil.popBackStack(CreateEditInterviewFragment.this);
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        switchResult(statusInterview);
                        btnSendMail.setVisibility(View.GONE);
                        lstInterviewHi = new LstInterviewHi(lstInterviewHi.getCvId(), lstInterviewHi.getEmailTemplate(), lstInterviewHi.getId(), lstInterviewHi.getInterviewAddress(), lstInterviewHi.getInterviewDate(), lstInterviewHi.getJobId(), lstInterviewHi.getNote(), lstInterviewHi.getRound(), statusInterview);
                        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                        intent.putExtra("lstInterviewHi", lstInterviewHi);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                        FragmentUtil.popBackStack(CreateEditInterviewFragment.this);
                    }
                }
            }
        });
    }

    public void switchResult(int status) {
        switch (status) {
            case 1:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.blue);
                UiUtil.setTextViewDrawableColor(tvNotAchieved, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.not_like);
                break;
            case 2:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvNotAchieved, R.color.blue);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.not_like);
                break;
            case 3:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvNotAchieved, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.blue);
                break;
            default:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvNotAchieved, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.not_like);
                break;
        }
    }

    @OnClick(R.id.btnSendMail)
    public void onSendMailClick() {
        if (!edtDateInterview.getText().toString().equalsIgnoreCase("") && !edtPlaceInterview.getText().toString().equalsIgnoreCase("")) {
            List<LstInterviewHi> lstInterviewHis = new ArrayList<>();
            for (int i = 0; i < detailProcessResumeResponse.getLstInterviewHis().size(); i++) {
                if (detailProcessResumeResponse.getLstInterviewHis().get(i).getId() != -1) {
                    lstInterviewHis.add(detailProcessResumeResponse.getLstInterviewHis().get(i));
                }
            }
            if (lstInterviewHis.size() > 0) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date readDate = null;
                Date readDateChoose = null;
                try {
                    readDate = df.parse(DateUtil.convertToMyFormat3(lstInterviewHis.get(lstInterviewHis.size() - 1).getInterviewDate()));
                    readDateChoose = df.parse(DateUtil.convertToMyFormat3(edtDateInterview.getText().toString().trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar last = Calendar.getInstance();
                last.setTimeInMillis(readDate.getTime());
                last.set(Calendar.DAY_OF_MONTH, last.get(Calendar.DAY_OF_MONTH));
                last.set(Calendar.MONTH, last.get(Calendar.MONTH));
                last.set(Calendar.YEAR, last.get(Calendar.YEAR));
                Calendar choose = Calendar.getInstance();
                choose.setTimeInMillis(readDateChoose.getTime());
                choose.set(Calendar.DAY_OF_MONTH, choose.get(Calendar.DAY_OF_MONTH));
                choose.set(Calendar.MONTH, choose.get(Calendar.MONTH));
                choose.set(Calendar.YEAR, choose.get(Calendar.YEAR));
                long lastWarrantyDate = last.getTimeInMillis();
                long chooseTime = choose.getTimeInMillis();
                long difference = chooseTime - lastWarrantyDate;
                if (lstInterviewHi == null || lstInterviewHi.getStatus() == 0) {
                    DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.confirm_invite_interview), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showCoverNetworkLoading();
                            new SendInterviewRequest(detailProcessResumeResponse.getCvId(), lstInterviewHi == null ? -1 : lstInterviewHi.getId(), edtPlaceInterview.getText().toString().trim(), edtDateInterview.getText().toString().trim(), detailProcessResumeResponse.getJobId(), edtNote.getText().toString().trim(), edtRound.getText().toString().trim(), lstInterviewHi == null ? 0 : statusInterview).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
                                @Override
                                public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                                    if (isAdded()) {
                                        hideCoverNetworkLoading();
                                        lstInterviewHi = new LstInterviewHi(dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getInterviewAddress(), dataSuccess.getInterviewDate(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getRound(), dataSuccess.getStatus());
                                        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                                        intent.putExtra("lstInterviewHi", lstInterviewHi);
                                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                                        FragmentUtil.popBackStack(CreateEditInterviewFragment.this);
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
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                } else {
                    if (difference > 0) {
                        DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.confirm_invite_interview), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showCoverNetworkLoading();
                                new SendInterviewRequest(detailProcessResumeResponse.getCvId(), lstInterviewHi == null ? -1 : lstInterviewHi.getId(), edtPlaceInterview.getText().toString().trim(), edtDateInterview.getText().toString().trim(), detailProcessResumeResponse.getJobId(), edtNote.getText().toString().trim(), edtRound.getText().toString().trim(), lstInterviewHi == null ? 0 : statusInterview).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
                                    @Override
                                    public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                                        if (isAdded()) {
                                            hideCoverNetworkLoading();
                                            lstInterviewHi = new LstInterviewHi(dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getInterviewAddress(), dataSuccess.getInterviewDate(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getRound(), dataSuccess.getStatus());
                                            Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                                            intent.putExtra("lstInterviewHi", lstInterviewHi);
                                            getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                                            FragmentUtil.popBackStack(CreateEditInterviewFragment.this);
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
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                    } else {
                        DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.warning_date_interview));
                    }
                }

            } else {
                DialogUtil.showDialogFull(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.confirm_invite_interview), getResources().getString(R.string.yes), getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showCoverNetworkLoading();
                        new SendInterviewRequest(detailProcessResumeResponse.getCvId(), lstInterviewHi == null ? -1 : lstInterviewHi.getId(), edtPlaceInterview.getText().toString().trim(), edtDateInterview.getText().toString().trim(), detailProcessResumeResponse.getJobId(), edtNote.getText().toString().trim(), edtRound.getText().toString().trim(), lstInterviewHi == null ? 0 : statusInterview).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
                            @Override
                            public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                                if (isAdded()) {
                                    hideCoverNetworkLoading();
                                    lstInterviewHi = new LstInterviewHi(dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getInterviewAddress(), dataSuccess.getInterviewDate(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getRound(), dataSuccess.getStatus());
                                    Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                                    intent.putExtra("lstInterviewHi", lstInterviewHi);
                                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                                    FragmentUtil.popBackStack(CreateEditInterviewFragment.this);
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
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        } else {
            if (edtDateInterview.getText().toString().equalsIgnoreCase("")) {
                llDate.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_red_5));
            } else {
                llDate.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_gray));
            }
            if (edtPlaceInterview.getText().toString().equalsIgnoreCase("")) {
                edtPlaceInterview.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_red_5));
            } else {
                edtPlaceInterview.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_gray));
            }
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.pleaseinputdata));
        }
    }

    @OnClick(R.id.btnSeeInvite)
    public void onSeeOffer() {
        if (lstInterviewHi != null) {
            if (lstInterviewHi.getStatus() == 1 || lstInterviewHi.getStatus() == 0) {
                showCoverNetworkLoading();
                new ViewEmailInterviewRequest(lstInterviewHi.getCvId(), lstInterviewHi.getId(), lstInterviewHi.getInterviewAddress(), lstInterviewHi.getInterviewDate(), lstInterviewHi.getJobId(), lstInterviewHi.getNote(), lstInterviewHi.getRound(), lstInterviewHi.getStatus()).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
                    @Override
                    public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                        if (isAdded()) {
                            hideCoverNetworkLoading();
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
                            TextView tvEmailContent = mReasonNotAcceptDialog.findViewById(R.id.tvEmailContent);
                            tvEmailContent.setText(Html.fromHtml(dataSuccess.getEmailTemplate()));
                            Button btnOK = (Button) mReasonNotAcceptDialog.findViewById(R.id.btnOK);
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mReasonNotAcceptDialog.dismiss();
                                }
                            });
                            mReasonNotAcceptDialog.show();
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
        } else {
            showCoverNetworkLoading();
            new ViewEmailInterviewRequest(detailProcessResumeResponse.getCvId(), -1, edtPlaceInterview.getText().toString().trim(), edtDateInterview.getText().toString().trim(), detailProcessResumeResponse.getJobId(), edtNote.getText().toString().trim(), edtRound.getText().toString().trim(), statusInterview).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
                @Override
                public void onSuccess(int status, ViewEmailInterviewResponse dataSuccess, List<ViewEmailInterviewResponse> listDataSuccess, String message) {
                    if (isAdded()) {
                        hideCoverNetworkLoading();
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
                        TextView tvEmailContent = mReasonNotAcceptDialog.findViewById(R.id.tvEmailContent);
                        tvEmailContent.setText(Html.fromHtml(dataSuccess.getEmailTemplate()));
                        Button btnOK = (Button) mReasonNotAcceptDialog.findViewById(R.id.btnOK);
                        btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mReasonNotAcceptDialog.dismiss();
                            }
                        });
                        mReasonNotAcceptDialog.show();
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
    }

    @OnClick({R.id.edtDateInterview, R.id.imgChooseDate, R.id.llDate})
    public void DateInterviewClick() {
        new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void update() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edtDateInterview.setText(sdf.format(calendar.getTime()) + " " + timeFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        new TimePickerDialog(getActivity(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        update();
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
        lstInterviewHi = new LstInterviewHi(0, "", -1, "", "", 0, "", "", 1);
        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
        intent.putExtra("lstInterviewHi", lstInterviewHi);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processCustomToolbar() {
        lstInterviewHi = new LstInterviewHi(0, "", -1, "", "", 0, "", "", 1);
        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
        intent.putExtra("lstInterviewHi", lstInterviewHi);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }
}
