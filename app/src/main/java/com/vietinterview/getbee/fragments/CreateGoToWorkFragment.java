package com.vietinterview.getbee.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GoToWorkStatusRequest;
import com.vietinterview.getbee.api.request.GoToWorkUpdateRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.GoToWorkUpdateResponse;
import com.vietinterview.getbee.api.response.SendOfferResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.JobCvGotoWorkDto;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 12/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CreateGoToWorkFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.edtWorkTime)
    EditText edtWorkTime;
    @BindView(R.id.edtWarranty)
    EditText edtWarranty;
    @BindView(R.id.btnUpdateWork)
    Button btnUpdateWork;
    JobCvGotoWorkDto jobCvGotoWorkDto;
    private DetailProcessResumeResponse detailProcessResumeResponse;
    GoToWorkUpdateRequest goToWorkUpdateRequest;
    private Calendar calendar;
    private SimpleDateFormat timeFormat;
    private static final String TIME_PATTERN = "hh:mm a";

    public static CreateGoToWorkFragment newInstance(JobCvGotoWorkDto jobCvGotoWorkDto, DetailProcessResumeResponse detailProcessResumeResponse) {
        CreateGoToWorkFragment fm = new CreateGoToWorkFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobCvGotoWorkDto", jobCvGotoWorkDto);
        bundle.putParcelable("detailProcessResumeResponse", detailProcessResumeResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_create_gotowork;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        jobCvGotoWorkDto = bundle.getParcelable("jobCvGotoWorkDto");
        detailProcessResumeResponse = bundle.getParcelable("detailProcessResumeResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().doFillBackground("Thông tin đi làm");
        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.US);
    }

    @Override
    protected void initData() {
        if (jobCvGotoWorkDto != null) {
            if (jobCvGotoWorkDto.getStartWorkDate() != null) {
                edtWorkTime.setText(jobCvGotoWorkDto.getStartWorkDate());
                edtWarranty.setText(jobCvGotoWorkDto.getNumDayWarranty() + "");
            }
        }
    }

    @OnClick(R.id.btnUpdateWork)
    public void onUpdateWorkClick() {
        if (!StringUtils.isEmpty(edtWorkTime.getText().toString())) {
            showCoverNetworkLoading();
            if (jobCvGotoWorkDto == null) {
                goToWorkUpdateRequest = new GoToWorkUpdateRequest(detailProcessResumeResponse.getCvId(), -1, 0, detailProcessResumeResponse.getJobId(), edtWorkTime.getText().toString().trim());
            } else {
                goToWorkUpdateRequest = new GoToWorkUpdateRequest(jobCvGotoWorkDto.getCvId(), jobCvGotoWorkDto.getId(), jobCvGotoWorkDto.getCountUpdate(), jobCvGotoWorkDto.getJobId(), edtWorkTime.getText().toString().trim());
            }
            goToWorkUpdateRequest.callRequest(getActivity(), new ApiObjectCallBack<GoToWorkUpdateResponse, ErrorResponse>() {
                @Override
                public void onSuccess(int status, GoToWorkUpdateResponse dataSuccess, List<GoToWorkUpdateResponse> listDataSuccess, String message) {
                    if (isAdded()) {
                        hideCoverNetworkLoading();
                        Integer countUpdate = dataSuccess.getCountUpdate();
                        Integer cvId = dataSuccess.getCvId();
                        Integer id = dataSuccess.getId();
                        Integer jobId = dataSuccess.getJobId();
                        String note = dataSuccess.getNote();
                        Integer numDayWarranty = dataSuccess.getNumDayWarranty();
                        String startWorkDate = dataSuccess.getStartWorkDate();
                        Integer updateBy = dataSuccess.getUpdateBy();
                        String updateDate = dataSuccess.getUpdateDate();
                        String warrantyExpireDate = dataSuccess.getWarrantyExpireDate();
                        jobCvGotoWorkDto = new JobCvGotoWorkDto(countUpdate, cvId, id, jobId, note, numDayWarranty, startWorkDate, updateBy, updateDate, warrantyExpireDate);
                        Intent intent = new Intent(getActivity(), CreateGoToWorkFragment.class);
                        intent.putExtra("jobCvGotoWorkDto", jobCvGotoWorkDto);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                        FragmentUtil.popBackStack(CreateGoToWorkFragment.this);
                    }
                }

                @Override
                public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                    if (isAdded()) {
                        hideCoverNetworkLoading();
                    }
                }
            });
        } else {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.pleaseinputdata));
        }
    }

    @OnClick({R.id.edtWorkTime, R.id.imgChooseDate, R.id.llDate})
    public void DateInterviewClick() {
        new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

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
}
