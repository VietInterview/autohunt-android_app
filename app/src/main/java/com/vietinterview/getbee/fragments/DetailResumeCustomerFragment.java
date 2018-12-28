package com.vietinterview.getbee.fragments;

import android.content.DialogInterface;
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
import com.vietinterview.getbee.api.request.GetDetailProcessResumeRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.api.response.detailprocessresume.DetailProcessResumeResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.ShowImageUtils;

import java.util.List;

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
        GlobalDefine.currentFragment = this;
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
        adapter.addFrag(new InfoDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), getResources().getString(R.string.info));
        adapter.addFrag(new ExpDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), getResources().getString(R.string.exp));
        adapter.addFrag(new EduDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), getResources().getString(R.string.cer_tit));
        adapter.addFrag(new LanDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), getResources().getString(R.string.language));
        adapter.addFrag(new ComDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), getResources().getString(R.string.conputer_skill_tit));
        adapter.addFrag(new SkillDetailResumeCustomerFragment().newInstance(mDetailCVCustomerResponse), getResources().getString(R.string.skill));
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
        getDetailProcessResume(mDetailCVCustomerResponse.getId(), mDetailCVCustomerResponse.getJobId());
    }

    GetDetailProcessResumeRequest getDetailProcessResumeRequest;

    public void getDetailProcessResume(int cvId, int jobId) {
        showCoverNetworkLoading();
        getDetailProcessResumeRequest = new GetDetailProcessResumeRequest(cvId, jobId);
        getDetailProcessResumeRequest.callRequest(getActivity(), new ApiObjectCallBack<DetailProcessResumeResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailProcessResumeResponse dataSuccess, List<DetailProcessResumeResponse> listDataSuccess, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    FragmentUtil.pushFragment(getActivity(), DetailResumeCustomerFragment.this, new ProcessResumeFragment().newInstance(dataSuccess, true), null);

                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                }
            }
        });
    }
}
