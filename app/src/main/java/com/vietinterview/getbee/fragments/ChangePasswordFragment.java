package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.ChangePasswordRequest;
import com.vietinterview.getbee.api.response.ErrorChangePassResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordFragment extends BaseFragment {
    @BindView(R.id.edtOldPass)
    EditText edtOldPass;
    @BindView(R.id.edtNewPass)
    EditText edtNewPass;
    @BindView(R.id.edtRetype)
    EditText edtRetype;
    String nameFragment;
    @BindView(R.id.imgOldPass)
    ImageView imgOldPass;
    @BindView(R.id.imgNewPass)
    ImageView imgNewPass;
    @BindView(R.id.imgReTypePass)
    ImageView imgReTypePass;
    ChangePasswordRequest changePasswordRequest;

    public static ChangePasswordFragment newInstance(String nameFragment) {
        ChangePasswordFragment fm = new ChangePasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nameFragment", nameFragment);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changepass;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        edtOldPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                imgOldPass.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                imgNewPass.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtRetype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                imgReTypePass.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
        nameFragment = bundle.getString("nameFragment");
    }

    @Override
    protected void initData() {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.change_password));
    }

    public void changePassWord() {
        showCoverNetworkLoading();
        changePasswordRequest = new ChangePasswordRequest(edtOldPass.getText().toString().trim(), edtNewPass.getText().toString().trim());
        changePasswordRequest.callRequest(getActivity(), new ApiObjectCallBack<Object, ErrorChangePassResponse>() {
            @Override
            public void onSuccess(int status, Object dataSuccess, List<Object> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        myItemShouldBeEnabled = true;
                        FragmentUtil.popBackStack(ChangePasswordFragment.this);
                        View toastView = getLayoutInflater().inflate(R.layout.activity_toast_custom_view, null);
                        TextView textView = toastView.findViewById(R.id.customToastText);
                        textView.setText(getResources().getString(R.string.change_password_success));
                        Toast toast = new Toast(getActivity());
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorChangePassResponse dataFail, List<ErrorChangePassResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    if (status == 200) {
                        myItemShouldBeEnabled = true;
                        View toastView = getLayoutInflater().inflate(R.layout.activity_toast_custom_view, null);
                        TextView textView = toastView.findViewById(R.id.customToastText);
                        textView.setText(getResources().getString(R.string.change_password_success));
                        Toast toast = new Toast(getActivity());
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        FragmentUtil.popBackStack(ChangePasswordFragment.this);
                    } else {
                        myItemShouldBeEnabled = true;
                        View toastView = getLayoutInflater().inflate(R.layout.activity_toast_custom_view, null);
                        TextView textView = toastView.findViewById(R.id.customToastText);
                        if (dataFail.getTitle().equalsIgnoreCase("incorrect_password"))
                            textView.setText(getResources().getString(R.string.incorrect_password));
                        else textView.setText(dataFail.getTitle());
                        Toast toast = new Toast(getActivity());
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
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


//    @OnClick(R.id.btnFrgThird)
//    void onClickFrgThird() {
//        FragmentUtil.popEntireFragmentBackStack(ChangePasswordFragment.this);
//        FragmentUtil.pushFragment(getActivity(), new DetailJobFragment(), null);
//    }

    @Override
    protected void processOnBackPress() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }

    @Override
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.save);

        if (myItemShouldBeEnabled) {
            item.setEnabled(true);
            item.getIcon().setAlpha(255);
        } else {
            item.setEnabled(false);
            item.getIcon().setAlpha(130);
        }
    }

    boolean myItemShouldBeEnabled = true;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            String oldPass = edtOldPass.getText().toString();
            String newPass = edtNewPass.getText().toString();
            String retypePass = edtRetype.getText().toString();
            if (oldPass.length() < 6 || newPass.length() < 6 || retypePass.length() < 6) {
                Toast.makeText(getActivity(), getResources().getString(R.string.min_6_character), Toast.LENGTH_SHORT).show();
            } else if (StringUtils.isEmpty(edtOldPass.getText().toString().trim())) {
                edtOldPass.setHint(getResources().getString(R.string.please_input_old_pass));
                edtOldPass.setHintTextColor(getResources().getColor(R.color.red));
                imgOldPass.setVisibility(View.VISIBLE);
            } else if (StringUtils.isEmpty(edtNewPass.getText().toString().trim())) {
                edtNewPass.setHint(getResources().getString(R.string.please_input_new_pass));
                edtNewPass.setHintTextColor(getResources().getColor(R.color.red));
                imgNewPass.setVisibility(View.VISIBLE);
            } else if (StringUtils.isEmpty(edtRetype.getText().toString().trim())) {
                edtRetype.setHint(getResources().getString(R.string.please_input_retype_pass));
                edtRetype.setHintTextColor(getResources().getColor(R.color.red));
                imgReTypePass.setVisibility(View.VISIBLE);
            } else if (!StringUtils.isEquals(edtNewPass.getText().toString().trim(), edtRetype.getText().toString().trim())) {
                Toast.makeText(getActivity(), getResources().getString(R.string.compare_new_retype_pass), Toast.LENGTH_SHORT).show();
            } else if (StringUtils.isEquals(edtOldPass.getText().toString().trim(), edtNewPass.getText().toString().trim())) {
                Toast.makeText(getActivity(), getResources().getString(R.string.compare_old_new_pass), Toast.LENGTH_SHORT).show();
            } else {
                myItemShouldBeEnabled = false;
                changePassWord();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
