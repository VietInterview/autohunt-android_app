package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetMyProfileRequest;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.api.request.SaveMyProfileRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.myprofile.DesideratedCareer;
import com.vietinterview.getbee.api.response.myprofile.MyProfileResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.NunitoTextInputEditText;
import com.vietinterview.getbee.model.Carrer;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.customview.TouchDetectableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class MyProfileFragment extends BaseFragment {
    @BindView(R.id.scrollView)
    TouchDetectableScrollView scrollView;
    @BindView(R.id.edtFullName)
    NunitoTextInputEditText edtFullName;
    @BindView(R.id.edtPhone)
    NunitoTextInputEditText edtPhone;
    @BindView(R.id.edtEmail)
    NunitoTextInputEditText edtEmail;
    @BindView(R.id.edtAdd)
    NunitoTextInputEditText edtAdd;
    @BindView(R.id.edtCarrer)
    NunitoTextInputEditText edtCarrer;
    @BindView(R.id.tvHunt)
    TextView tvHunt;
    private String mCarrerId = "0";
    private String mCarrerName = "";
    MyProfileResponse mMyProfileResponse;
    SaveMyProfileRequest saveMyProfileRequest;

    public static MyProfileFragment newInstance(MyProfileResponse myProfileResponse) {
        MyProfileFragment fm = new MyProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("myProfileResponse", myProfileResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myprofile;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        GlobalDefine.currentFragment = this;
        setCustomToolbarVisible(true);
        setHasOptionsMenu(true);
        edtFullName.setText(mMyProfileResponse.getFullNameColl());
        edtPhone.setText(mMyProfileResponse.getPhoneColl());
        edtEmail.setText(mMyProfileResponse.getEmailColl());
        edtAdd.setText(mMyProfileResponse.getAddressColl());
        edtCarrer.setText(mMyProfileResponse.getCareerColl());
        if (mMyProfileResponse.getDesideratedCareer() != null) {
            if (mMyProfileResponse.getDesideratedCareer().size() > 0) {
                mCarrerName = mMyProfileResponse.getDesideratedCareer().get(0).getName();
                mCarrerId = String.valueOf(mMyProfileResponse.getDesideratedCareer().get(0).getId());
                tvHunt.setText(mCarrerName);
            }
        } else {
            tvHunt.setText(mCarrerName);
        }
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mMyProfileResponse = bundle.getParcelable("myProfileResponse");
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.rlChangePass)
    public void onChangePassClick() {
        FragmentUtil.pushFragment(getActivity(), MyProfileFragment.this, new ChangePasswordFragment(), null);
    }

    public void saveProfile() {
        Carrer carrer = new Carrer(Integer.parseInt(mCarrerId), mCarrerName);
        ArrayList<Carrer> carrers = new ArrayList<>();
        carrers.add(carrer);
        saveMyProfileRequest = new SaveMyProfileRequest(edtFullName.getText().toString().trim(), edtAdd.getText().toString().trim(), edtCarrer.getText().toString().trim(), carrers, edtEmail.getText().toString().trim(), edtPhone.getText().toString().trim());
        saveMyProfileRequest.callRequest(getActivity(), new ApiObjectCallBack<MyProfileResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, MyProfileResponse dataSuccess, List<MyProfileResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    mMyProfileResponse.setFullNameColl(dataSuccess.getFullNameColl());
                    mMyProfileResponse.setAddressColl(dataSuccess.getAddressColl());
                    mMyProfileResponse.setPhoneColl(dataSuccess.getPhoneColl());
                    mMyProfileResponse.setCareerColl(dataSuccess.getCareerColl());
                    mMyProfileResponse.setDesideratedCareer(dataSuccess.getDesideratedCareer());
                    mMyProfileResponse.setEmailColl(dataSuccess.getEmailColl());
                    edtFullName.setText(mMyProfileResponse.getFullNameColl());
                    edtPhone.setText(mMyProfileResponse.getPhoneColl());
                    edtEmail.setText(mMyProfileResponse.getEmailColl());
                    edtAdd.setText(mMyProfileResponse.getAddressColl());
                    edtCarrer.setText(mMyProfileResponse.getCareerColl());
                    if (mMyProfileResponse.getDesideratedCareer() != null) {
                        if (mMyProfileResponse.getDesideratedCareer().size() > 0) {
                            mCarrerName = mMyProfileResponse.getDesideratedCareer().get(0).getName();
                            mCarrerId = String.valueOf(mMyProfileResponse.getDesideratedCareer().get(0).getId());
                            tvHunt.setText(mCarrerName);
                        }
                    } else {
                        tvHunt.setText(mCarrerName);
                    }
                    Toast.makeText(getActivity(), getResources().getString(R.string.save_profile_success), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.change_password_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onRestore() {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.info_acc_tit));
    }

    @Override
    protected void initialize() {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.info_acc_tit));
    }

    @Override
    protected void onSaveState(Bundle bundle) {
//        bundle.putString("mCarrerName", mCarrerName);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
//        mCarrerName = bundle.getString("mCarrerName");
    }

    @OnClick(R.id.rlChooseJob)
    public void onChooseJobClick() {
        FragmentUtil.pushFragment(getActivity(), MyProfileFragment.this, new CarrerOrCityFragment().newInstance(false, mCarrerId, mCarrerName), null);
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        return baseRequests;
    }

    @Override
    protected void processOnBackPress() {
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_menu);
    }

    @Override
    protected void processCustomToolbar() {
        loadMenuLeft();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            saveProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                mCarrerId = String.valueOf(data.getIntExtra("carrerId", 0));
                mCarrerName = data.getStringExtra("carrerName");
                if (mMyProfileResponse.getDesideratedCareer() != null) {
                    if (mMyProfileResponse.getDesideratedCareer().size() > 0) {
                        mMyProfileResponse.getDesideratedCareer().get(0).setId(Integer.parseInt(mCarrerId));
                        mMyProfileResponse.getDesideratedCareer().get(0).setName(mCarrerName);
                    } else {
                        List<DesideratedCareer> desideratedCareers = new ArrayList<>();
                        desideratedCareers.add(new DesideratedCareer(Integer.parseInt(mCarrerId), mCarrerName));
                        mMyProfileResponse.setDesideratedCareer(desideratedCareers);
                    }
                } else {
                    List<DesideratedCareer> desideratedCareers = new ArrayList<>();
                    desideratedCareers.add(new DesideratedCareer(Integer.parseInt(mCarrerId), mCarrerName));
                    mMyProfileResponse.setDesideratedCareer(desideratedCareers);
                }
                tvHunt.setText(mCarrerName);
            }
        }
    }
}
