package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordFragment extends BaseFragment {
//    @BindView(R.id.btnFrgThird)
//    Button btnFrgThird;
//    @BindView(R.id.tv_hello)
//    TextView tv_hello;
    String nameFragment;

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
//        tv_hello.setText(nameFragment);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        nameFragment = bundle.getString("nameFragment");
    }

    @Override
    protected void initData() {
        getEventBaseFragment().doFillBackground("Thay đổi mật khẩu");
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
//        FragmentUtil.pushFragment(getActivity(), new ThirdFragment(), null);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
