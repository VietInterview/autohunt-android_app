package com.vietinterview.getbee.fragments;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.GetNewListByPlace;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;

import butterknife.OnClick;


public class MyProfileFragment extends BaseFragment {
    //    @BindView(R.id.btnFrgSec)
//    Button btnFrgSec;
    String mNameFragment;
    LoginRequest loginRequest;
    GetNewListByPlace getNewListByPlace;

    public static MyProfileFragment newInstance(String nameFragment) {
        MyProfileFragment fm = new MyProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nameFragment", nameFragment);
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
        setCustomToolbarVisible(true);
//        btnFrgSec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(baseActivity, "First", Toast.LENGTH_SHORT).show();
//                FragmentUtil.pushFragment(baseActivity, new ChangePasswordFragment().newInstance("ChangePasswordFragment"), null);
//            }
//        });
        setHasOptionsMenu(true);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mNameFragment = bundle.getString("nameFragment");
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.imgChangePass)
    public void onChangePassClick() {
        FragmentUtil.pushFragment(getActivity(), new ChangePasswordFragment(), null);
    }

    @Override
    protected void onRestore() {
        getEventBaseFragment().doFillBackground(mNameFragment);
    }

    @Override
    protected void initialize() {
        getEventBaseFragment().doFillBackground("Thông tin cá nhân");
    }

    @Override
    protected void onSaveState(Bundle bundle) {
//        bundle.putString("nameFragment", "Test");
//        mNameFragment = bundle.getString("nameFragment");
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        mNameFragment = bundle.getString("nameFragment");
    }

    @OnClick(R.id.rlChooseJob)
    public void onChooseJobClick() {
        FragmentUtil.pushFragment(getActivity(), new JobsFragment(), null);
    }

    @Override
    public ArrayList<BaseJsonRequest> getArrayRequest() {
        ArrayList<BaseJsonRequest> baseRequests = new ArrayList<>();
        baseRequests.add(loginRequest);
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("01682253221", null, "Test", null, null);
                    Toast.makeText(getActivity(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
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
            Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
