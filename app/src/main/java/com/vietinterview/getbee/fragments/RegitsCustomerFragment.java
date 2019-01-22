package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.Html;
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
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.MainActivity;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.RegistRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.RobotoBoldTextView;
import com.vietinterview.getbee.customview.RobotoEditText;
import com.vietinterview.getbee.customview.RobotoTextView;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nguyennghiahiep on 10/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class RegitsCustomerFragment extends BaseFragment {
    @BindView(R.id.edtName)
    RobotoEditText edtName;
    @BindView(R.id.edtContact)
    RobotoEditText edtContact;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.tvGoLogin)
    RobotoBoldTextView tvGoLogin;
    @BindView(R.id.icRightName)
    ImageView icRightName;
    @BindView(R.id.icRightEmail)
    ImageView icRightEmail;
    @BindView(R.id.icRightPhone)
    ImageView icRightPhone;
    @BindView(R.id.lineName)
    View lineName;
    @BindView(R.id.lineEmail)
    View lineEmail;
    @BindView(R.id.lineSDT)
    View lineSDT;
    @BindView(R.id.lineContact)
    View lineContact;
    @BindView(R.id.imgName)
    ImageView imgName;
    @BindView(R.id.imgMail)
    ImageView imgMail;
    @BindView(R.id.imgSDT)
    ImageView imgSDT;
    @BindView(R.id.imgPolicy)
    ImageView imgPolicy;
    @BindView(R.id.imgContact)
    ImageView imgContact;
    @BindView(R.id.tvPolicy)
    TextView tvPolicy;
    RegistRequest registRequest;
    private Dialog mNotifydialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_regits_cus;
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
                    lineName.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgName.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));
                } else {
                    lineName.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgName.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_notfocus));
                }
            }
        });
        edtContact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    lineContact.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgContact.setImageDrawable(getResources().getDrawable(R.drawable.ic_contact_focus));
                } else {
                    lineContact.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgContact.setImageDrawable(getResources().getDrawable(R.drawable.ic_contact));
                }
            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    lineEmail.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgMail.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail));
                } else {
                    lineEmail.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgMail.setImageDrawable(getResources().getDrawable(R.drawable.ic_mail_notfocus));
                }
            }
        });
        edtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    lineSDT.setBackgroundColor(getResources().getColor(R.color.yellow_highlight));
                    imgSDT.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone));
                } else {
                    lineSDT.setBackgroundColor(getResources().getColor(R.color.gray_not_focus));
                    imgSDT.setImageDrawable(getResources().getDrawable(R.drawable.ic_phone_notfocus));
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
        tvPolicy.setText(Html.fromHtml("Đồng ý với <u><font color='blue'>điều khoản</font></u> sử dụng của GetBee"));
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

    private boolean isAgree = false;

    @OnClick(R.id.llPolicy)
    public void onPolicyClick() {
        if (isAgree) {
            isAgree = false;
            imgPolicy.setImageDrawable(getResources().getDrawable(R.drawable.ic_oval));
        } else {
            isAgree = true;
            imgPolicy.setImageDrawable(getResources().getDrawable(R.drawable.ic_tickok));
        }
    }

    @OnClick(R.id.btnSignup)
    public void onSignupClick() {
        if (StringUtils.isEmpty(edtName.getText().toString().trim()) && StringUtils.isEmpty(edtEmail.getText().toString().trim()) && StringUtils.isEmpty(edtPhone.getText().toString().trim())) {
            edtName.setText("");
            edtName.setHint(getResources().getString(R.string.input_name));
            edtName.setHintTextColor(Color.RED);
            icRightName.setVisibility(View.VISIBLE);
            edtEmail.setText("");
            edtEmail.setHint(getResources().getString(R.string.input_email));
            edtEmail.setHintTextColor(Color.RED);
            icRightEmail.setVisibility(View.VISIBLE);
            edtPhone.setText("");
            edtPhone.setHint(getResources().getString(R.string.input_phone));
            edtPhone.setHintTextColor(Color.RED);
            icRightPhone.setVisibility(View.VISIBLE);
        } else if (edtName.getText().toString().trim().equalsIgnoreCase("")) {
            edtName.setText("");
            edtName.setHint(getResources().getString(R.string.input_name));
            edtName.setHintTextColor(Color.RED);
            icRightName.setVisibility(View.VISIBLE);
        } else if (edtEmail.getText().toString().trim().equalsIgnoreCase("")) {
            edtEmail.setText("");
            edtEmail.setHint(getResources().getString(R.string.input_email));
            edtEmail.setHintTextColor(Color.RED);
            icRightEmail.setVisibility(View.VISIBLE);
        } else if (edtPhone.getText().toString().trim().equalsIgnoreCase("")) {
            edtPhone.setText("");
            edtPhone.setHint(getResources().getString(R.string.input_phone));
            edtPhone.setHintTextColor(Color.RED);
            icRightPhone.setVisibility(View.VISIBLE);
        } else if (!isAgree) {
            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Bạn phải đồng ý với điều khoản sử dụng của GetBee");
        } else {
            if (StringUtils.isValidEmail(edtEmail.getText().toString().trim())) {
                showCoverNetworkLoading();
                registRequest = new RegistRequest(edtEmail.getText().toString().trim(), edtPhone.getText().toString().trim(), edtName.getText().toString().trim(), "", edtName.getText().toString().trim(), edtContact.getText().toString().trim(), 0, 2);
                registRequest.callRequest(getActivity(), new ApiObjectCallBack<ErrorResponse, ErrorResponse>() {

                    @Override
                    public void onSuccess(int status, ErrorResponse data, List<ErrorResponse> dataArrayList, String message) {
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
                            RobotoTextView nunitoTextView = (RobotoTextView) mNotifydialog.findViewById(R.id.tvContent);
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
                    public void onFail(int failCode, ErrorResponse dataFail, List<ErrorResponse> dataArrayList, String message) {
                        hideCoverNetworkLoading();
                        if (dataFail != null) {
                            if (dataFail.getErrorKey() != null) {
                                if (dataFail.getErrorKey().equalsIgnoreCase("userexists"))
                                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.email_esxit));
                            } else {
                                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                            }
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
                                RobotoTextView nunitoTextView = (RobotoTextView) mNotifydialog.findViewById(R.id.tvContent);
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

    @Override
    public ArrayList<BaseJsonRequest> getArrayJsonRequest() {
        ArrayList<BaseJsonRequest> baseJsonRequests = new ArrayList<>();
        baseJsonRequests.add(registRequest);
        return baseJsonRequests;
    }
}
