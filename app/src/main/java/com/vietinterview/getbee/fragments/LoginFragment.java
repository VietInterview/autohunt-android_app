package com.vietinterview.getbee.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.MainActivity;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.GetMyProfileRequest;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.login.ErrorLoginResponse;
import com.vietinterview.getbee.api.response.login.LoginResponse;
import com.vietinterview.getbee.api.response.myprofile.MyProfileResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.customview.RobotoEditText;

import java.util.ArrayList;
import java.util.List;

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
    RobotoEditText edtEmail;
    @BindView(R.id.edtPass)
    RobotoEditText edtPass;
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
                if (charSequence.toString().equalsIgnoreCase("")) {
                    edtEmail.setHint(getResources().getString(R.string.input_email));
                    icRightEmail.setVisibility(View.VISIBLE);
                } else {
                    edtEmail.setTextColor(Color.BLACK);
                    icRightEmail.setVisibility(View.GONE);
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
                    edtPass.setTextColor(Color.BLACK);
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    llPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    linePass.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgPass.setImageDrawable(getResources().getDrawable(R.drawable.ic_pass_notfocus));
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
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
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
        Window window = mForgotPassdialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.y = 300;
        window.setAttributes(wlp);
        ImageView imgCall = mForgotPassdialog.findViewById(R.id.imgCall);
        ImageView imgEmail = mForgotPassdialog.findViewById(R.id.imgEmail);
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent;
                String number = ("tel:02466629448");
                mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse(number));
// Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(mIntent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(emailIntent,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(),
                            "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnOK = (Button) mForgotPassdialog.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mForgotPassdialog.dismiss();
            }
        });
        mForgotPassdialog.show();
    }

    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    UserInfoBean userInfoBean;
    GetMyProfileRequest getMyProfileRequest;

    public void getMyProfile() {
        getMyProfileRequest = new GetMyProfileRequest();
        getMyProfileRequest.callRequest(getActivity(), new ApiObjectCallBack<MyProfileResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, MyProfileResponse dataSuccess, List<MyProfileResponse> listDataSuccess, String message) {
                if (dataSuccess.getFullNameColl() != null) {
                    userInfoBean.name = dataSuccess.getFullNameColl();
                } else {
                    userInfoBean.name = edtEmail.getText().toString().trim().split("[$&<@%*]")[0];
                }
                AccountManager.setUserInfoBean(userInfoBean);
                getEventBaseFragment().setTextGreeting(AccountManager.getUserInfoBean().name);
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {

            }
        });
    }

    @OnClick(R.id.btnLogin)
    public void onbtnLoginClick() {
        if (edtEmail.getText().toString().equalsIgnoreCase("")) {
            edtEmail.setText("");
            edtEmail.setHint(getResources().getString(R.string.input_email));
            edtEmail.setHintTextColor(Color.RED);
            icRightEmail.setVisibility(View.VISIBLE);
        } else if (edtPass.getText().toString().equalsIgnoreCase("")) {
            edtPass.setHint(getResources().getString(R.string.input_pass));
            edtPass.setHintTextColor(Color.RED);
            icRightPass.setVisibility(View.VISIBLE);
        } else {
            showCoverNetworkLoading();
            loginRequest = new LoginRequest(edtEmail.getText().toString().trim(), edtPass.getText().toString().trim());
            loginRequest.callRequest(getActivity(), new ApiObjectCallBack<LoginResponse, ErrorLoginResponse>() {
                @Override
                public void onSuccess(int status, LoginResponse data, List<LoginResponse> dataArrayList, String message) {
                    if (isAdded()) {
                        if (status == 200) {
                            userInfoBean = new UserInfoBean();
                            userInfoBean.nickname = edtEmail.getText().toString().trim();
                            userInfoBean.access_token = data.getApiToken();
                            AccountManager.setUserInfoBean(userInfoBean);
                            getMyProfile();
                            if (getActivity() instanceof MainActivity) {
                                ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                            }
                            getAct().navigationView.setCheckedItem(R.id.nav_home);
                            FragmentUtil.replaceFragment(getActivity(), new HomeFragment(), null);
                        }
                    }
                }

                @Override
                public void onFail(int failCode, ErrorLoginResponse errorResponse, List<ErrorLoginResponse> dataArrayList, String message) {
                    if (isAdded()) {
                        hideCoverNetworkLoading();
                        mNotifydialog = new Dialog(getActivity());
                        mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mNotifydialog.setContentView(R.layout.dialog_noti);
                        mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        Window window = mNotifydialog.getWindow();
                        WindowManager.LayoutParams wlp = window.getAttributes();
                        wlp.gravity = Gravity.TOP;
                        wlp.y = 300;
                        window.setAttributes(wlp);
                        TextView tvContent = mNotifydialog.findViewById(R.id.tvContent);
                        if (errorResponse.getDetail().equalsIgnoreCase("userLock")) {
                            tvContent.setText(getResources().getString(R.string.user_locked));
                        }
                        Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
                        btnOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mNotifydialog.dismiss();
                            }
                        });
                        mNotifydialog.show();
                    }
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

    @Override
    public ArrayList<BaseJsonRequest> getArrayJsonRequest() {
        ArrayList<BaseJsonRequest> baseJsonRequests = new ArrayList<>();
        baseJsonRequests.add(loginRequest);
        return baseJsonRequests;
    }
}
