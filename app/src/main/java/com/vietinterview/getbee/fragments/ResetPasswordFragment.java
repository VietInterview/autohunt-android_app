package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.ResetPasswordRequest;
import com.vietinterview.getbee.api.response.ResetPasswordFailResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.ClearableRegularEditText;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 1/23/19.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ResetPasswordFragment extends DialogFragment {
    @BindView(R.id.edtEmail)
    ClearableRegularEditText edtEmail;
    @BindView(R.id.imgEmail)
    ImageView imgEmail;
    @BindView(R.id.icRightEmail)
    ImageView icRightEmail;
    @BindView(R.id.lineEmail)
    View lineEmail;
    @BindView(R.id.frParent)
    RelativeLayout frParent;
    ResetPasswordRequest resetPasswordRequest;
    Dialog mNotifyDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_resetpassword, container, false);
        ButterKnife.bind(this, v);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                icRightEmail.setVisibility(View.GONE);
                if (StringUtils.isValidEmail(edtEmail.getText().toString().trim())) {
                    icRightEmail.setVisibility(View.VISIBLE);
                    icRightEmail.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_tickok));
                } else {
                    icRightEmail.setVisibility(View.VISIBLE);
                    icRightEmail.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_note));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    lineEmail.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgEmail.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail));
                } else {
                    lineEmail.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgEmail.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail_notfocus));
                }
            }
        });

        return v;
    }

    @OnClick(R.id.btnClose)
    public void onClose() {
        ResetPasswordFragment.this.dismiss();
    }

    @OnClick(R.id.btnSendRequest)
    public void onSendRequestClick() {
        if (StringUtils.isEmpty(edtEmail.getText().toString().trim())) {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.input_email));
        } else {
            if (StringUtils.isValidEmail(edtEmail.getText().toString().trim())) {
                resetPasswordRequest = new ResetPasswordRequest(edtEmail.getText().toString().trim());
                resetPasswordRequest.callRequest(getActivity(), new ApiObjectCallBack<Object, ResetPasswordFailResponse>() {
                    @Override
                    public void onSuccess(int status, Object dataSuccess, List<Object> listDataSuccess, String message) {
                    }

                    @Override
                    public void onFail(int status, ResetPasswordFailResponse dataFail, List<ResetPasswordFailResponse> listDataFail, String message) {
                        if (isAdded()) {
                            if (status == 200) {
                                mNotifyDialog = new Dialog(getActivity());
                                mNotifyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                mNotifyDialog.setContentView(R.layout.dialog_noti_reset_pass);
                                mNotifyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                mNotifyDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                                Window window = mNotifyDialog.getWindow();
                                WindowManager.LayoutParams wlp = window.getAttributes();
                                wlp.gravity = Gravity.TOP;
                                wlp.y = 300;
                                window.setAttributes(wlp);
                                Button btnOK = (Button) mNotifyDialog.findViewById(R.id.btnOK);
                                btnOK.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mNotifyDialog.dismiss();
                                        ResetPasswordFragment.this.dismiss();
                                    }
                                });
                                mNotifyDialog.show();
                            } else {
                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), dataFail.getTitle().equalsIgnoreCase("email_not_found") ? "Không tìm thấy địa chỉ email này." : message);
                            }
                        }
                    }
                });
            }
        }
    }

}
