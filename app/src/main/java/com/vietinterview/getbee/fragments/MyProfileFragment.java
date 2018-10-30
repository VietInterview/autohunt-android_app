package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.LoginRequest;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.customview.TouchDetectableScrollView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class MyProfileFragment extends BaseFragment {
    @BindView(R.id.scrollView)
    TouchDetectableScrollView scrollView;
    //    @BindView(R.id.fab)
//    FloatingActionButton fab;
    String mNameFragment;
    LoginRequest loginRequest;

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
        setHasOptionsMenu(true);
        scrollView.setMyScrollChangeListener(new TouchDetectableScrollView.OnMyScrollChangeListener() {
            @Override
            public void onScrollUp() {
//                fab.show();
            }

            @Override
            public void onScrollDown() {
//                fab.hide();
            }
        });
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
        FragmentUtil.pushFragment(getActivity(), MyProfileFragment.this, new ChangePasswordFragment(), null);
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
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        mNameFragment = bundle.getString("nameFragment");
    }

    @OnClick(R.id.rlChooseJob)
    public void onChooseJobClick() {
        FragmentUtil.pushFragment(getActivity(), MyProfileFragment.this, new CarrerOrCityFragment().newInstance(false, "4", "IT, Phần mềm"), null);
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
