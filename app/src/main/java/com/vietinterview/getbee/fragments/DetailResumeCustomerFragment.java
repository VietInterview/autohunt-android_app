package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.ShowImageUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hiepnguyennghia on 11/28/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailResumeCustomerFragment extends BaseFragment {
    @BindView(R.id.tvFullname)
    TextView tvFullname;
    @BindView(R.id.imgAva)
    CircleImageView imgAva;
    @BindView(R.id.btnProcessCV)
    Button btnProcessCV;
    DetailCVCustomerResponse mDetailCVCustomerResponse;

    public static DetailResumeCustomerFragment newInstance(DetailCVCustomerResponse detailCVCustomerResponse) {
        DetailResumeCustomerFragment fm = new DetailResumeCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVCustomerResponse", detailCVCustomerResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_cv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mDetailCVCustomerResponse = bundle.getParcelable("detailCVCustomerResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(false);
        setCustomToolbarVisible(false);
//        btnProcessCV.setVisibility(StringUtils.checkFunction("CUS_HOME_PAGE_VIEW_CV_DETAIL") ? View.VISIBLE : View.GONE);
        final ViewPager viewPager = (ViewPager) root.findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);
        tvFullname.setText(mDetailCVCustomerResponse.getFullName());
        ShowImageUtils.showImage(getActivity(), mDetailCVCustomerResponse.getPictureUrl(), R.drawable.ic_ava_null, imgAva);
        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.iconcolor));
        tabLayout.setTabTextColors(getResources().getColor(R.color.background_icon_not_focus), getResources().getColor(R.color.iconcolor));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFrag(new InfoDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), "THÔNG TIN");
        adapter.addFrag(new ExpDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), "KINH NGHIỆM");
        adapter.addFrag(new EduDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), "BẰNG CẤP");
        adapter.addFrag(new LanDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), "NGOẠI NGỮ");
        adapter.addFrag(new ComDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), "TIN HỌC");
        adapter.addFrag(new SkillDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), "KỸ NĂNG");
        viewPager.setAdapter(adapter);
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
    protected void onRestore() {

    }

    @OnClick(R.id.imgBack)
    public void onBackClick() {
        FragmentUtil.popBackStack(this);
    }

    @OnClick(R.id.btnProcessCV)
    public void onProcessClick() {
        FragmentUtil.pushFragment(getActivity(), this, new ProcessResumeFragment().newInstance(mDetailCVCustomerResponse.getId(), mDetailCVCustomerResponse.getJobId()), null);
    }
}
