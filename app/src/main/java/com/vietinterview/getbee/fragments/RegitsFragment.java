package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.request.RegistRequest;
import com.vietinterview.getbee.api.response.RegistResponse;
import com.vietinterview.getbee.api.response.loginresponse.LoginResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.view.NunitoBoldTextView;
import com.vietinterview.getbee.view.NunitoEditText;
import com.vietinterview.getbee.view.NunitoTextView;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nguyennghiahiep on 10/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RegitsFragment extends BaseFragment {
    @BindView(R.id.edtName)
    NunitoEditText edtName;
    @BindView(R.id.edtJob)
    NunitoEditText edtJob;
    @BindView(R.id.edtAdd)
    NunitoEditText edtAdd;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.tvGoLogin)
    NunitoBoldTextView tvGoLogin;
    @BindView(R.id.icRightName)
    ImageView icRightName;
    @BindView(R.id.icRightEmail)
    ImageView icRightEmail;
    @BindView(R.id.icRightPhone)
    ImageView icRightPhone;
    RegistRequest registRequest;
    private Dialog mNotifydialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_regits;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbarVisible(false);
        icRightEmail.setVisibility(View.GONE);
        icRightName.setVisibility(View.GONE);
        icRightPhone.setVisibility(View.GONE);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                icRightEmail.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                icRightName.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                icRightPhone.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tvGoLogin)
    public void ontvGoLoginClick() {
        FragmentUtil.replaceFragment(getActivity(), new LoginFragment(), null);
    }

    @OnClick(R.id.btnSignup)
    public void onSignupClick() {
        if (edtEmail.getText().toString().trim().equalsIgnoreCase("")) {
            icRightEmail.setVisibility(View.VISIBLE);
            edtEmail.setText("");
            edtEmail.setHint("Xin hãy nhập địa chỉ Email");
            edtEmail.setHintTextColor(Color.RED);
        } else if (edtName.getText().toString().trim().equalsIgnoreCase("")) {
            icRightName.setVisibility(View.VISIBLE);
            edtName.setText("");
            edtName.setHint("Xin hãy nhập họ và tên");
            edtName.setHintTextColor(Color.RED);
        } else if (edtPhone.getText().toString().trim().equalsIgnoreCase("")) {
            icRightPhone.setVisibility(View.VISIBLE);
            edtPhone.setText("");
            edtPhone.setHint("Xin hãy nhập số điện thoại");
            edtPhone.setHintTextColor(Color.RED);
        } else {
            showCoverNetworkLoading();
            registRequest = new RegistRequest(edtEmail.getText().toString().trim(), edtPhone.getText().toString().trim(), edtName.getText().toString().trim(), edtJob.getText().toString().trim(), edtAdd.getText().toString().trim());
            registRequest.callRequest(getActivity(), new ApiObjectCallBack<RegistResponse>() {
                @Override
                public void onSuccess(RegistResponse data, int status) {
                    hideCoverNetworkLoading();
                    mNotifydialog = new Dialog(getActivity());
                    mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    mNotifydialog.setContentView(R.layout.dialog_noti);
                    mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    NunitoTextView nunitoTextView = (NunitoTextView) mNotifydialog.findViewById(R.id.tvContent);
                    nunitoTextView.setText("Bạn đã đăng ký tài khoản Cộng tác viên. Chúng tôi sẽ gửi thông tin email và mật khẩu qua hòm thư mà bạn đã đăng ký");
                    Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FragmentUtil.replaceFragment(getActivity(), new LoginFragment(), null);
                            mNotifydialog.dismiss();
                        }
                    });
                    mNotifydialog.show();
                }

                @Override
                public void onFail(int failCode, String message) {
                    hideCoverNetworkLoading();
                }

                @Override
                public void onFail(int failCode, RegistResponse data, String message) {
                    hideCoverNetworkLoading();
                    mNotifydialog = new Dialog(getActivity());
                    mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    mNotifydialog.setContentView(R.layout.dialog_noti);
                    mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    NunitoTextView nunitoTextView = (NunitoTextView) mNotifydialog.findViewById(R.id.tvContent);
                    if (data.getErrorKey().equalsIgnoreCase("userexists"))
                        nunitoTextView.setText("Địa chỉ email đã tồn tại");
                    Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mNotifydialog.dismiss();
                        }
                    });
                    mNotifydialog.show();
                }
            });
        }
    }

    @Override
    protected void onRestore() {

    }

    @Override
    protected void initialize() {
        getEventBaseFragment().doFillBackground("Đăng nhập");
    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }
}
