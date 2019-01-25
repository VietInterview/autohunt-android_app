package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.Html;
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
import android.widget.TextView;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.MainActivity;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.GetAccountRequest;
import com.vietinterview.getbee.api.request.GetCTVProfileRequest;
import com.vietinterview.getbee.api.request.GetCUSProfileRequest;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.account.AccountResponse;
import com.vietinterview.getbee.api.response.account.LstMenuAuthority;
import com.vietinterview.getbee.api.response.ctvprofile.MyProfileResponse;
import com.vietinterview.getbee.api.response.customerprofile.ProfileCustomerResponse;
import com.vietinterview.getbee.api.response.login.ErrorLoginResponse;
import com.vietinterview.getbee.api.response.login.LoginResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.customview.RobotoEditText;
import com.vietinterview.getbee.model.UserInfoBean;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.HashSet;
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
    @BindView(R.id.tvDevmode)
    TextView tvDevmode;
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.linePass)
    View linePass;
    @BindView(R.id.lineUserName)
    View lineUserName;
    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.imgPass)
    ImageView imgPass;
    private String edtMailData;
    @BindView(R.id.tvGoRegitsCus)
    TextView tvGoRegitsCus;
    @BindView(R.id.tvGoRegits)
    TextView tvGoRegits;
    private int dem = 0;
    ApiConstant ApiConstantTest = new ApiConstant();

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
                    icRightEmail.setVisibility(View.GONE);
                } else {
                    edtEmail.setTextColor(Color.BLACK);
                    icRightEmail.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (AccountManager.getApiConstant().getBASE_URL().equalsIgnoreCase(ApiConstantTest.REAL_URL)) {
            tvDevmode.setVisibility(View.GONE);
        } else {
            tvDevmode.setVisibility(View.VISIBLE);
        }
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dem < 7) {
                    dem++;
                    ApiConstantTest.setBASE_URL(ApiConstantTest.REAL_URL);
                    ApiConstantTest.setIMG_URL(ApiConstantTest.IMG_URL_REAL);
                    AccountManager.setApiConstant(ApiConstantTest);
                    tvDevmode.setVisibility(View.GONE);
                } else {
                    dem--;
                    ApiConstantTest.setBASE_URL(ApiConstantTest.DEV_URL);
                    ApiConstantTest.setIMG_URL(ApiConstantTest.IMG_URL_DEV);
                    AccountManager.setApiConstant(ApiConstantTest);
                    tvDevmode.setVisibility(View.VISIBLE);
                }
            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
//                    llUserName.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    lineUserName.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgUser.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));
                } else {
//                    llUserName.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    lineUserName.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgUser.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_notfocus));
                }
            }
        });
        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
//                    llPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    linePass.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgPass.setImageDrawable(getResources().getDrawable(R.drawable.ic_pass));
                    edtPass.setTextColor(Color.BLACK);
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
//                    llPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
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
        tvGoRegits.setText(Html.fromHtml("<u><font color='black'>Headhunter</font></u>"));
        tvGoRegitsCus.setText(Html.fromHtml("<u><font color='black'>Doanh nghiệp</font></u>"));
    }

    @Override
    protected void getArgument(Bundle bundle) {
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.tvGoRegits)
    public void ontvGoRegitsClick() {
        FragmentUtil.replaceFragment(getActivity(), new RegitsCollFragment(), null);
    }
    @OnClick(R.id.tvGoRegitsCus)
    public void ontvGoRegitsCusClick() {
        FragmentUtil.replaceFragment(getActivity(), new RegitsCustomerFragment(), null);
    }
    @OnClick(R.id.tvForgotPass)
    public void onForgotpassClick() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new ResetPasswordFragment();
        dialogFragment.show(ft, "dialog");
    }

    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }

    UserInfoBean userInfoBean;
    GetCTVProfileRequest getMyProfileRequest;
    GetCUSProfileRequest getCUSProfileRequest;

    public void getMyProfile() {
        if (AccountManager.getUserInfoBean().getType() == 2) {
            getCUSProfileRequest = new GetCUSProfileRequest();
            getCUSProfileRequest.callRequest(getActivity(), new ApiObjectCallBack<ProfileCustomerResponse, ErrorResponse>() {
                @Override
                public void onSuccess(int status, ProfileCustomerResponse dataSuccess, List<ProfileCustomerResponse> listDataSuccess, String message) {
                    AccountManager.setUserInfoBean(userInfoBean);
                    getEventBaseFragment().setTextGreeting(AccountManager.getUserInfoBean().name);
                }

                @Override
                public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {

                }
            });
        } else {
            getMyProfileRequest = new GetCTVProfileRequest();
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
                            getAccount();
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
                        if (errorResponse.getDetail() != null) {
                            if (errorResponse.getDetail().equalsIgnoreCase("userLock")) {
                                tvContent.setText(getResources().getString(R.string.user_locked));
                            }
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

    private GetAccountRequest getAccountRequest;

    public void getAccount() {
        getAccountRequest = new GetAccountRequest();
        getAccountRequest.callRequest(getActivity(), new ApiObjectCallBack<AccountResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, AccountResponse dataSuccess, List<AccountResponse> listDataSuccess, String message) {
                if (status == 200) {
                    hideCoverNetworkLoading();
                    List<LstMenuAuthority> lstMenuAuthorities = null;
                    ArrayList<String> listCode = new ArrayList<>();
                    ArrayList<String> listName = new ArrayList<>();
                    ArrayList<Integer> listId = new ArrayList<>();
                    for (int i = 0; i < dataSuccess.getLstMenuAuthority().size(); i++) {
                        listCode.add(dataSuccess.getLstMenuAuthority().get(i).getCode());
                        listName.add(dataSuccess.getLstMenuAuthority().get(i).getName());
                        listId.add(dataSuccess.getLstMenuAuthority().get(i).getId());
                    }
                    listCode = removeDuplicates(listCode);
                    listName = removeDuplicates(listName);
                    listId = removeDuplicatesInt(listId);
                    lstMenuAuthorities = new ArrayList<>();
                    for (int i = 0; i < listCode.size(); i++) {
                        if (listCode.get(i).equalsIgnoreCase("CTV_JOB_SAVE")) {
                            lstMenuAuthorities.add(new LstMenuAuthority(listId.get(i), getResources().getString(R.string.my_job), "CTV_JOB"));
                        } else if (listCode.get(i).equalsIgnoreCase("CTV_JOB_SENT")) {

                        } else if (listCode.get(i).equalsIgnoreCase("CTV_CV_SAVE")) {
                            lstMenuAuthorities.add(new LstMenuAuthority(listId.get(i), getResources().getString(R.string.mycv), "CTV_CV"));
                        } else if (listCode.get(i).equalsIgnoreCase("CTV_CV_SEND")) {

                        } else if (listCode.get(i).equalsIgnoreCase("CUSTOMER_HOME_PAGE")) {
                            lstMenuAuthorities.add(new LstMenuAuthority(listId.get(i), getResources().getString(R.string.manager_jobs), listCode.get(i)));
                        } else
                            lstMenuAuthorities.add(new LstMenuAuthority(listId.get(i), listName.get(i), listCode.get(i)));
                    }
                    for (int i = 0; i < lstMenuAuthorities.size(); i++) {
                        if (lstMenuAuthorities.get(i).getName().equalsIgnoreCase("API"))
                            lstMenuAuthorities.remove(lstMenuAuthorities.get(i));
                    }
                    userInfoBean.setLstMenuAuthority(lstMenuAuthorities);
                    if (dataSuccess.getType() == 2) {
                        userInfoBean.getLstMenuAuthority().add(new LstMenuAuthority(0, getResources().getString(R.string.info_customer), "PROFILE"));

                    } else {
                        userInfoBean.getLstMenuAuthority().add(new LstMenuAuthority(0, getResources().getString(R.string.info_acc_tit), "PROFILE"));
                    }
                    userInfoBean.setLstFunctionAuthority(dataSuccess.getLstFunctionAuthority());
                    userInfoBean.setType(dataSuccess.getType());
                    if (dataSuccess.getFirstName() != null) {
                        userInfoBean.name = dataSuccess.getFirstName();
                    } else {
                        userInfoBean.name = edtEmail.getText().toString().trim().split("[$&<@%*]")[0];
                    }
                    getMyProfile();
                    AccountManager.setUserInfoBean(userInfoBean);
                    getEventBaseFragment().setMenu();
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    }
                    getMainActivity().navigationView.getMenu().getItem(0).setChecked(true);
                    if (AccountManager.getUserInfoBean().getType() == 7 || AccountManager.getUserInfoBean().getType() == 5)
                        FragmentUtil.replaceFragment(getActivity(), new HomeFragment(), null);
                    else if (AccountManager.getUserInfoBean().getType() == 2)
                        FragmentUtil.replaceFragment(getActivity(), new JobsEmployerFragment(), null);
                    else FragmentUtil.replaceFragment(getActivity(), new HomeFragment(), null);
                }
            }

            @Override
            public void onFail(int status, ErrorResponse
                    dataFail, List<ErrorResponse> listDataFail, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
    }

    static ArrayList<String> removeDuplicates(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String item : list) {
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    static ArrayList<Integer> removeDuplicatesInt(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (Integer item : list) {
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    @Override
    protected void onRestore() {
    }

    @Override
    protected void initialize() {
        edtEmail.clearFocus();
        edtEmail.setText(SharedPrefUtils.getString("email", "").equalsIgnoreCase("") ? "" : SharedPrefUtils.getString("email", ""));
    }

    @Override
    protected void onSaveState(Bundle bundle) {
        SharedPrefUtils.putString("email", edtEmail.getText().toString().trim());
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
