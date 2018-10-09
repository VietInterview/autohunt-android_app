package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.response.loginresponse.LoginResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.view.NunitoBoldTextView;
import com.vietinterview.getbee.view.NunitoTextView;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nguyennghiahiep on 10/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RegitsFragment extends BaseFragment {
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.tvGoLogin)
    NunitoBoldTextView tvGoLogin;
    LoginRequest loginRequest;
    private Dialog mNotifydialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_regits;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbarVisible(false);
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

    @OnClick(R.id.btnLogin)
    public void onLoginClick() {
        try {
            callLogin();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void callLogin() throws JSONException {
        showCoverNetworkLoading();
        loginRequest = new LoginRequest("admin", "admin");
        loginRequest.callRequest(getActivity(), new ApiObjectCallBack<LoginResponse>() {

            @Override
            public void onSuccess(LoginResponse data) {
                UserInfoBean userInfoBean = new UserInfoBean();
                userInfoBean.email = edtEmail.getText().toString().trim();
                userInfoBean.access_token = data.getApiToken();
//                AccountManager.setUserInfoBean(userInfoBean);
                FragmentUtil.replaceFragment(getActivity(), new FirstFragment().newInstance("FirstFragment"), null);
            }

            @Override
            public void onFail(int failCode, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), "Thông báo", message);
            }
        });
    }

    @OnClick(R.id.btnSignup)
    public void onSignupClick() {
        mNotifydialog = new Dialog(getActivity());
        mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mNotifydialog.setContentView(R.layout.dialog_noti);
        mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        NunitoTextView nunitoTextView = (NunitoTextView) mNotifydialog.findViewById(R.id.tvContent);
        nunitoTextView.setText("Bạn đã đăng ký tài khoản Cộng tác viên. Chúng tôi sẽ gửi thông tin email và mật khẩu qua hòm thư mà bạn đã đăng ký");
        Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mNotifydialog.show();
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
