package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.api.request.GetDetailCVRequest;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailCVFragment extends BaseFragment {
    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.tvBirthDay)
    TextView tvBirthDay;
    @BindView(R.id.imgAva)
    ImageView imgAva;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private JobList mJobList;
    private Dialog mNotifydialog;
    private GetDetailCVRequest getDetailCVRequest;
    private int mCvId;

    public static DetailCVFragment newInstance(JobList jobList, int cvId) {
        DetailCVFragment fm = new DetailCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobList", jobList);
        bundle.putInt("cvId", cvId);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbarVisible(false);
        getEventBaseFragment().doFillBackground("");
        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow_highlight));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                } else if (position == 1) {
                } else if (position == 2) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mJobList = bundle.getParcelable("jobList");
        mCvId = bundle.getInt("cvId");
    }

    @Override
    protected void initData() {
        getDetailCV(mCvId);
    }

    @OnClick(R.id.imglogo)
    public void onBackClick() {
        FragmentUtil.popBackStack(this);
    }

    @OnClick(R.id.btnApplyCV)
    public void onApplyCVClick() {
        mNotifydialog = new Dialog(getActivity());
        mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mNotifydialog.setContentView(R.layout.dialog_choose_send_cv);
        mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ImageView imgInfoHunt = (ImageView) mNotifydialog.findViewById(R.id.imgInfoHunt);
        ImageView imgInfoSentCV = (ImageView) mNotifydialog.findViewById(R.id.imgInfoSentCV);
        final TextView tvContent = (TextView) mNotifydialog.findViewById(R.id.tvContent);
        final TextView tvContentSendCV = (TextView) mNotifydialog.findViewById(R.id.tvContentSendCV);
        final TextView tvHunt = (TextView) mNotifydialog.findViewById(R.id.tvHunt);
        final TextView tvSentCV = (TextView) mNotifydialog.findViewById(R.id.tvSentCV);
        imgInfoHunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvContent.setVisibility(View.VISIBLE);
                tvContentSendCV.setVisibility(View.GONE);
            }
        });
        imgInfoSentCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvContent.setVisibility(View.GONE);
                tvContentSendCV.setVisibility(View.VISIBLE);
            }
        });
        tvHunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotifydialog.dismiss();
                FragmentUtil.popEntireFragmentBackStack(DetailCVFragment.this);
                FragmentUtil.pushFragment(getActivity(), DetailCVFragment.this, new DetailJobFragment().newInstance(mJobList), null);
            }
        });
        tvSentCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotifydialog.dismiss();
                FragmentUtil.popEntireFragmentBackStack(DetailCVFragment.this);
                FragmentUtil.pushFragment(getActivity(), DetailCVFragment.this, new DetailJobFragment().newInstance(mJobList), null);
            }
        });
        mNotifydialog.show();
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.info));
        tabOne.setTextColor(getResources().getColor(R.color.black));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.exp));
        tabTwo.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabThree.setText(getResources().getString(R.string.level));
        tabThree.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabFour.setText(getResources().getString(R.string.language));
        tabFour.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(3).setCustomView(tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabFive.setText(getResources().getString(R.string.skill));
        tabFive.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(4).setCustomView(tabFive);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new InfoDetailCVFragment().newInstance(detailCVResponse), getResources().getString(R.string.info));
        adapter.addFrag(new ExpDetailCVFragment().newInstance(detailCVResponse), getResources().getString(R.string.exp));
        adapter.addFrag(new LevelDetailCVFragment().newInstance(detailCVResponse), getResources().getString(R.string.level));
        adapter.addFrag(new LanDetailCVFragment().newInstance(detailCVResponse), getResources().getString(R.string.language));
        adapter.addFrag(new SkillDetailCVFragment().newInstance(detailCVResponse), getResources().getString(R.string.skill));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
    }

    DetailCVResponse detailCVResponse;

    public void getDetailCV(int id) {
        getDetailCVRequest = new GetDetailCVRequest(id);
        getDetailCVRequest.callRequest(new ApiObjectCallBack<DetailCVResponse>() {
            @Override
            public void onSuccess(DetailCVResponse data, List<DetailCVResponse> dataArrayList, int status, String message) {
                detailCVResponse = data;
                tvFullName.setText(detailCVResponse.getFullName());
                tvBirthDay.setText(detailCVResponse.getBirthday() + "");
                Glide.with(getActivity()).load(detailCVResponse.getPictureUrl()).into(imgAva);
                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.black));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.background_icon_not_focus));
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            @Override
            public void onFail(int failCode, DetailCVResponse data, List<DetailCVResponse> dataArrayList, String message) {

            }
        });
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
}
