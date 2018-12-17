package com.vietinterview.getbee.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.vietinterview.getbee.api.request.SendOfferRequest;
import com.vietinterview.getbee.api.request.UpdateInterviewStatusRequest;
import com.vietinterview.getbee.api.request.UpdateOfferStatusRequest;
import com.vietinterview.getbee.api.request.ViewEmailOfferRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.SendOfferResponse;
import com.vietinterview.getbee.api.response.ViewEmailInterviewResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.api.response.detailprocessresume.LstOfferHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;
import com.vietinterview.getbee.utils.UiUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CreateEditOfferFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.edtRound)
    EditText edtRound;
    @BindView(R.id.edtSalary)
    EditText edtSalary;
    @BindView(R.id.edtWorkTime)
    EditText edtWorkTime;
    @BindView(R.id.edtWorkAddress)
    EditText edtWorkAddress;
    @BindView(R.id.edtPosition)
    EditText edtPosition;
    @BindView(R.id.edtNote)
    EditText edtNote;
    @BindView(R.id.llResult)
    LinearLayout llResult;
    @BindView(R.id.tvAchieve)
    TextView tvAchieve;
    @BindView(R.id.tvCandidateNotCome)
    TextView tvCandidateNotCome;
    @BindView(R.id.btnSendMail)
    Button btnSendMail;
    @BindView(R.id.tvCurrency)
    TextView tvCurrency;
    private LstOfferHi lstOfferHi;
    private DetailProcessResumeResponse detailProcessResumeResponse;
    private static final String TIME_PATTERN = "hh:mm a";
    private Calendar calendar;
    private int statusOffer = 0;
    private int currency = 1;
    private SimpleDateFormat timeFormat;
    private Dialog mReasonNotAcceptDialog;

    public static CreateEditOfferFragment newInstance(LstOfferHi lstOfferHi, DetailProcessResumeResponse detailProcessResumeResponse) {
        CreateEditOfferFragment fm = new CreateEditOfferFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lstOfferHi", lstOfferHi);
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_edit_offer;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        lstOfferHi = bundle.getParcelable("lstOfferHi");
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().doFillBackground("Thông tin Offer");
        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.US);
        this.registerForContextMenu(tvCurrency);
    }

    @Override
    protected void initData() {
        edtRound.setFocusable(false);
        edtRound.setFocusableInTouchMode(false);
        edtRound.setClickable(false);
        if (lstOfferHi != null) {
            llResult.setVisibility(View.VISIBLE);
            edtRound.setText(lstOfferHi.getRound());
            edtWorkTime.setText(lstOfferHi.getWorkTime());
            edtNote.setText(lstOfferHi.getNote());
            edtWorkAddress.setText(lstOfferHi.getWorkAddress());
            edtPosition.setText(lstOfferHi.getPosition());
            edtSalary.setText(StringUtils.filterCurrencyString(lstOfferHi.getSalary()));
            tvCurrency.setText(StringUtils.genStringCurrency(lstOfferHi.getCurency()));
            switchResult(lstOfferHi.getStatus());
            if (lstOfferHi.getStatus() == 0) {
                btnSendMail.setVisibility(View.VISIBLE);
            } else {
                btnSendMail.setVisibility(View.GONE);
            }
        } else {
            llResult.setVisibility(View.GONE);
            edtRound.setText("Offer " + (detailProcessResumeResponse.getLstOfferHis().size() + 1));
        }
    }

    @OnClick(R.id.tvCurrency)
    public void onCurrencyClick() {
        this.registerForContextMenu(tvCurrency);
        getActivity().openContextMenu(tvCurrency);
    }

    final int CONTEXT_MENU_VIEW = 1;
    final int CONTEXT_MENU_EDIT = 2;
    final int CONTEXT_MENU_ARCHIVE = 3;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Chọn đơn vị tiền tệ");
        menu.add(Menu.NONE, CONTEXT_MENU_VIEW, Menu.NONE, "VND");
        menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "USD");
        menu.add(Menu.NONE, CONTEXT_MENU_ARCHIVE, Menu.NONE, "JPY");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CONTEXT_MENU_VIEW: {
                currency = 1;
                tvCurrency.setText("VND");
            }
            break;
            case CONTEXT_MENU_EDIT: {
                currency = 2;
                tvCurrency.setText("USD");
            }
            break;
            case CONTEXT_MENU_ARCHIVE: {
                currency = 3;
                tvCurrency.setText("JPY");
            }
            break;
        }

        return super.onContextItemSelected(item);
    }

    @OnClick(R.id.tvAchieve)
    public void onAchieveClick() {
        statusOffer = 1;
        updateOfferStatus();
    }

    @OnClick(R.id.tvCandidateNotCome)
    public void onCandidateNotComeClick() {
        statusOffer = 2;
        updateOfferStatus();
    }

    public void updateOfferStatus() {
        showCoverNetworkLoading();
        int cvId = detailProcessResumeResponse.getCvId();
        int mCurrency = this.currency;
        int id = lstOfferHi == null ? -1 : lstOfferHi.getId();
        String position = edtPosition.getText().toString().trim();
        int jobId = detailProcessResumeResponse.getJobId();
        String note = edtNote.getText().toString().trim();
        String round = edtRound.getText().toString().trim();
        int status = lstOfferHi == null ? 0 : statusOffer;
        int salary = lstOfferHi.getSalary();
        String workAddress = edtWorkAddress.getText().toString().trim();
        String workTime = edtWorkTime.getText().toString().trim();
        new UpdateOfferStatusRequest(cvId, id, mCurrency, position, jobId, note, round, status, salary, workAddress, workTime).callRequest(getActivity(), new ApiObjectCallBack<SendOfferResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, SendOfferResponse dataSuccess, List<SendOfferResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        switchResult(statusOffer);
                        btnSendMail.setVisibility(View.GONE);
                        lstOfferHi = new LstOfferHi(currency, dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getPosition(), dataSuccess.getRound(), dataSuccess.getSalary(), dataSuccess.getStatus(), dataSuccess.getWorkAddress(), dataSuccess.getWorkTime());
                        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                        intent.putExtra("lstOfferHi", lstOfferHi);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                        FragmentUtil.popBackStack(CreateEditOfferFragment.this);
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        switchResult(statusOffer);
                        btnSendMail.setVisibility(View.GONE);
                        lstOfferHi = new LstOfferHi(currency, lstOfferHi.getCvId(), lstOfferHi.getEmailTemplate(), lstOfferHi.getId(), lstOfferHi.getJobId(), lstOfferHi.getNote(), lstOfferHi.getPosition(), lstOfferHi.getRound(), lstOfferHi.getSalary(), statusOffer, lstOfferHi.getWorkAddress(), lstOfferHi.getWorkTime());
                        Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                        intent.putExtra("lstOfferHi", lstOfferHi);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                        FragmentUtil.popBackStack(CreateEditOfferFragment.this);
                    }
                }
            }
        });
    }

    public void switchResult(int status) {
        switch (status) {
            case 1:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.blue);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.not_like);
                break;
            case 2:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.blue);
                break;
            default:
                UiUtil.setTextViewDrawableColor(tvAchieve, R.color.not_like);
                UiUtil.setTextViewDrawableColor(tvCandidateNotCome, R.color.not_like);
                break;
        }
    }

    @OnClick(R.id.btnSendMail)
    public void onSendMailClick() {
        int cvId = detailProcessResumeResponse.getCvId();
        int mCurrency = this.currency;
        int id = lstOfferHi == null ? -1 : lstOfferHi.getId();
        String position = edtPosition.getText().toString().trim();
        int jobId = detailProcessResumeResponse.getJobId();
        String note = edtNote.getText().toString().trim();
        String round = edtRound.getText().toString().trim();
        int status = lstOfferHi == null ? 0 : statusOffer;
        int salary = Integer.parseInt(edtSalary.getText().toString());
        String workAddress = edtWorkAddress.getText().toString().trim();
        String workTime = edtWorkTime.getText().toString().trim();
        new SendOfferRequest(cvId, id, mCurrency, position, jobId, note, round, status, salary, workAddress, workTime).callRequest(getActivity(), new ApiObjectCallBack<SendOfferResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, SendOfferResponse dataSuccess, List<SendOfferResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    lstOfferHi = new LstOfferHi(dataSuccess.getCurency(), dataSuccess.getCvId(), dataSuccess.getEmailTemplate(), dataSuccess.getId(), dataSuccess.getJobId(), dataSuccess.getNote(), dataSuccess.getPosition(), dataSuccess.getRound(), dataSuccess.getSalary(), dataSuccess.getStatus(), dataSuccess.getWorkAddress(), dataSuccess.getWorkTime());
                    Intent intent = new Intent(getActivity(), CreateEditInterviewFragment.class);
                    intent.putExtra("lstOfferHi", lstOfferHi);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                    FragmentUtil.popBackStack(CreateEditOfferFragment.this);
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

    @OnClick(R.id.btnSeeInvite)
    public void onSeeOffer() {
        if (lstOfferHi != null) {
            if (lstOfferHi.getStatus() == 1 || lstOfferHi.getStatus() == 0) {
                int cvId = detailProcessResumeResponse.getCvId();
                int mCurrency = this.currency;
                int id = lstOfferHi == null ? -1 : lstOfferHi.getId();
                String position = edtPosition.getText().toString().trim();
                int jobId = detailProcessResumeResponse.getJobId();
                String note = edtNote.getText().toString().trim();
                String round = edtRound.getText().toString().trim();
                int status = lstOfferHi == null ? 0 : statusOffer;
                int salary = lstOfferHi.getSalary();
                String workAddress = edtWorkAddress.getText().toString().trim();
                String workTime = edtWorkTime.getText().toString().trim();
                new ViewEmailOfferRequest(cvId, id, mCurrency, position, jobId, note, round, status, salary, workAddress, workTime).callRequest(getActivity(), new ApiObjectCallBack<ViewEmailInterviewResponse, ErrorResponse>() {
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
                            Button btnOK = (Button) mReasonNotAcceptDialog.findViewById(R.id.btnOK);
                            TextView tvEmailContent = mReasonNotAcceptDialog.findViewById(R.id.tvEmailContent);
                            tvEmailContent.setText(Html.fromHtml(dataSuccess.getEmailTemplate()));
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
                        }
                    }
                });
            }
        }
    }

    @OnClick({R.id.edtWorkTime, R.id.imgChooseDate, R.id.llDate})
    public void DateInterviewClick() {
        new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

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
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }

    private void update() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edtWorkTime.setText(sdf.format(calendar.getTime()) + " " + timeFormat.format(calendar.getTime()));
        DebugLog.showLogCat(edtWorkTime.getText().toString());
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
}
