package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.response.loginresponse.LoginResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.view.NunitoEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/8/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class LoginFragment extends BaseFragment {
    private Dialog mNotifydialog;
    private Dialog mForgotPassdialog;
    LoginRequest loginRequest;
    @BindView(R.id.edtEmail)
    NunitoEditText edtEmail;
    @BindView(R.id.edtPass)
    NunitoEditText edtPass;
    @BindView(R.id.icRightEmail)
    ImageView icRightEmail;
    @BindView(R.id.icRightPass)
    ImageView icRightPass;
    @BindView(R.id.llPass)
    LinearLayout llPass;
    @BindView(R.id.llUserName)
    LinearLayout llUserName;
    @BindView(R.id.linePass)
    View linePass;
    @BindView(R.id.lineUserName)
    View lineUserName;
    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.imgPass)
    ImageView imgPass;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbarVisible(false);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edtEmail.setTextColor(Color.BLACK);
                icRightEmail.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    llUserName.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    lineUserName.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgUser.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));
                } else {
                    llUserName.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    lineUserName.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgUser.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_notfocus));
                }
            }
        });
        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    llPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    linePass.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgPass.setImageDrawable(getResources().getDrawable(R.drawable.ic_pass));
                    DebugLog.showLogCat("onFocusChange");
                    edtPass.setTextColor(Color.BLACK);
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    llPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    linePass.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgPass.setImageDrawable(getResources().getDrawable(R.drawable.ic_pass_notfocus));
                    DebugLog.showLogCat("onFocusChange False");
                }
            }
        });
        icRightEmail.setVisibility(View.GONE);
        icRightPass.setVisibility(View.GONE);
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                icRightPass.setVisibility(View.GONE);
                edtPass.setTextColor(Color.BLACK);
                edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
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

    @OnClick(R.id.tvGoRegits)
    public void ontvGoRegitsClick() {
        FragmentUtil.replaceFragment(getActivity(), new RegitsFragment(), null);
    }

    @OnClick(R.id.tvForgotPass)
    public void onForgotpassClick() {

        mForgotPassdialog = new Dialog(getActivity());
        mForgotPassdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mForgotPassdialog.setContentView(R.layout.dialog_forgotpass);
        mForgotPassdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mForgotPassdialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button btnOK = (Button) mForgotPassdialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mForgotPassdialog.dismiss();
            }
        });
        mForgotPassdialog.show();
    }

    @OnClick(R.id.btnLogin)
    public void onbtnLoginClick() {
        if (edtEmail.getText().toString().equalsIgnoreCase("")) {
            edtEmail.setText("");
            edtEmail.setHint("Xin hãy nhập tên đăng nhập");
            edtEmail.setHintTextColor(Color.RED);
            icRightEmail.setVisibility(View.VISIBLE);
        } else if (edtPass.getText().toString().equalsIgnoreCase("")) {
            edtPass.setHint("Xin hãy nhập mật khẩu");
            edtPass.setHintTextColor(Color.RED);
            icRightPass.setVisibility(View.VISIBLE);
        } else {
            showCoverNetworkLoading();
            loginRequest = new LoginRequest(edtEmail.getText().toString().trim(), edtPass.getText().toString().trim());
            loginRequest.callRequest(getActivity(), new ApiObjectCallBack<LoginResponse>() {

                @Override
                public void onSuccess(LoginResponse data, int status) {
                    if (status == 200) {
                        UserInfoBean userInfoBean = new UserInfoBean();
                        userInfoBean.email = edtEmail.getText().toString().trim();
                        userInfoBean.access_token = data.getApiToken();
                        AccountManager.setUserInfoBean(userInfoBean);
                        DebugLog.showLogCat(status + "");
                        FragmentUtil.replaceFragment(getActivity(), new MyProfileFragment().newInstance("MyProfileFragment"), null);
                    }
                }

                @Override
                public void onFail(int failCode, String message) {
                    hideCoverNetworkLoading();
                    mNotifydialog = new Dialog(getActivity());
                    mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    mNotifydialog.setContentView(R.layout.dialog_noti);
                    mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mNotifydialog.dismiss();
                        }
                    });
                    mNotifydialog.show();
                }

                @Override
                public void onFail(int failCode, LoginResponse data, String message) {

                }
            });
        }
    }

    @Override
    protected void onRestore() {

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
}
