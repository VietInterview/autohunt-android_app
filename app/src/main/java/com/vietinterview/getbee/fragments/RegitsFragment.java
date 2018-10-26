package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.MainActivity;
import com.vietinterview.getbee.api.request.RegistRequest;
import com.vietinterview.getbee.api.response.RegistResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;
import com.vietinterview.getbee.customview.NunitoBoldTextView;
import com.vietinterview.getbee.customview.NunitoEditText;
import com.vietinterview.getbee.customview.NunitoTextView;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.llName)
    LinearLayout llName;
    @BindView(R.id.llEmail)
    LinearLayout llEmail;
    @BindView(R.id.llSDT)
    LinearLayout llSDT;
    @BindView(R.id.lineName)
    View lineName;
    @BindView(R.id.lineEmail)
    View lineEmail;
    @BindView(R.id.lineSDT)
    View lineSDT;
    @BindView(R.id.imgName)
    ImageView imgName;
    @BindView(R.id.imgMail)
    ImageView imgMail;
    @BindView(R.id.imgSDT)
    ImageView imgSDT;
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
        edtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    llName.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    lineName.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgName.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));
                } else {
                    llName.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    lineName.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgName.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_notfocus));
                }
            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    llEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    lineEmail.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgMail.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail));
                } else {
                    llEmail.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    lineEmail.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgMail.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail_notfocus));
                }
            }
        });
        edtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    llSDT.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                    lineSDT.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgSDT.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone));
                } else {
                    llSDT.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                    lineSDT.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgSDT.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone_notfocus));
                }
            }
        });
        edtAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    edtAdd.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                } else {
                    edtAdd.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                }
            }
        });
        edtJob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    edtJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackgroundfocus));
                } else {
                    edtJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittextbackground));
                }
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

    @OnClick(R.id.tvGoLogin)
    public void ontvGoLoginClick() {
        FragmentUtil.replaceFragment(getActivity(), new LoginFragment(), null);
    }

    @OnClick(R.id.btnSignup)
    public void onSignupClick() {
        if (edtEmail.getText().toString().trim().equalsIgnoreCase("")) {
            edtEmail.setText("");
            edtEmail.setHint(getResources().getString(R.string.input_email));
            edtEmail.setHintTextColor(Color.RED);
            icRightEmail.setVisibility(View.VISIBLE);
        } else if (edtName.getText().toString().trim().equalsIgnoreCase("")) {
            edtName.setText("");
            edtName.setHint(getResources().getString(R.string.input_name));
            edtName.setHintTextColor(Color.RED);
            icRightName.setVisibility(View.VISIBLE);
        } else if (edtPhone.getText().toString().trim().equalsIgnoreCase("")) {
            edtPhone.setText("");
            edtPhone.setHint(getResources().getString(R.string.input_phone));
            edtPhone.setHintTextColor(Color.RED);
            icRightPhone.setVisibility(View.VISIBLE);
        } else {
            if (StringUtils.isValidEmail(edtEmail.getText().toString().trim())) {
                showCoverNetworkLoading();
                registRequest = new RegistRequest(edtEmail.getText().toString().trim(), edtPhone.getText().toString().trim(), edtName.getText().toString().trim(), edtJob.getText().toString().trim(), edtAdd.getText().toString().trim());
                registRequest.callRequest(getActivity(), new ApiObjectCallBack<RegistResponse>() {

                    @Override
                    public void onSuccess(RegistResponse data, List<RegistResponse> dataArrayList, int status, String message) {
                        hideCoverNetworkLoading();
                        if (status == 201) {
                            mNotifydialog = new Dialog(getActivity());
                            mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            mNotifydialog.setContentView(R.layout.dialog_noti);
                            mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            Window window = mNotifydialog.getWindow();
                            WindowManager.LayoutParams wlp = window.getAttributes();
                            wlp.gravity = Gravity.TOP;
                            wlp.y = 250;
                            window.setAttributes(wlp);
                            NunitoTextView nunitoTextView = (NunitoTextView) mNotifydialog.findViewById(R.id.tvContent);
                            nunitoTextView.setText(getResources().getString(R.string.regist_succces));
                            Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
                            btnOK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FragmentUtil.replaceFragment(getActivity(), new LoginFragment(), null);
                                    mNotifydialog.dismiss();
                                }
                            });
                            mNotifydialog.show();
                        } else {
                            if (data != null)
                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), data.getMessage());
                        }
                    }

                    @Override
                    public void onFail(int failCode, RegistResponse data, List<RegistResponse> dataArrayList, String message) {
                        hideCoverNetworkLoading();
                        if (data != null) {
                            if (data.getErrorKey().equalsIgnoreCase("userexists"))
                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.email_esxit));
                        } else {
                            if (failCode == 201) {
                                mNotifydialog = new Dialog(getActivity());
                                mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                mNotifydialog.setContentView(R.layout.dialog_noti);
                                mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                                Window window = mNotifydialog.getWindow();
                                WindowManager.LayoutParams wlp = window.getAttributes();
                                wlp.gravity = Gravity.TOP;
                                wlp.y = 250;
                                window.setAttributes(wlp);
                                NunitoTextView nunitoTextView = (NunitoTextView) mNotifydialog.findViewById(R.id.tvContent);
                                nunitoTextView.setText(getResources().getString(R.string.regist_succces));
                                Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
                                btnOK.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        FragmentUtil.replaceFragment(getActivity(), new LoginFragment(), null);
                                        mNotifydialog.dismiss();
                                    }
                                });
                                mNotifydialog.show();
                            } else
                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                        }
                    }
                });
            } else {

            }

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
