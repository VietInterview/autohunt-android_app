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

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.response.loginresponse.LoginResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.DialogUtil;
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
    private Dialog mSuccessdialog;
    LoginRequest loginRequest;
    @BindView(R.id.edtEmail)
    NunitoEditText edtEmail;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {

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

    @OnClick(R.id.btnLogin)
    public void onbtnLoginClick() {
//        mNotifydialog = new Dialog(getActivity());
//        mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mNotifydialog.setContentView(R.layout.dialog_noti);
//        mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//
//        Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
//        btnOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        mNotifydialog.show();
        mSuccessdialog = new Dialog(getActivity());
        mSuccessdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSuccessdialog.setContentView(R.layout.dialog_success);
        mSuccessdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mSuccessdialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button btnOK = (Button) mSuccessdialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        mSuccessdialog.show();
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
