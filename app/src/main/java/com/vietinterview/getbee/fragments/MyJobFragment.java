package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class MyJobFragment extends BaseFragment {
    @BindView(R.id.llCondition)
    LinearLayout llCondition;
    @BindView(R.id.tvCarrerName)
    TextView tvCarrerName;
    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private Menu menu;
    private boolean visibleFilter = false;
    private boolean mIsCity = false;
    private String mCarrerId = "0";
    private String mCarrerName = "";
    private String mCityId = "0";
    private String mCityName = "";
    private JobsSavedFragment jobsSavedFragment;
    private JobsApplyedFragment jobsApplyedFragment;
    private int mPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myjob;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.my_job));
        GlobalDefine.currentFragment = this;
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.underline_tablyaout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                } else if (position == 1) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        if (jobsSavedFragment == null && jobsApplyedFragment == null) {
        jobsSavedFragment = new JobsSavedFragment().newInstance(mCityId, mCarrerId);
        jobsApplyedFragment = new JobsApplyedFragment().newInstance(mCityId, mCarrerId);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
//        } else {
//            viewPager.setCurrentItem(0);
//            viewPager.setOffscreenPageLimit(2);
//            viewPager.setAdapter(adapter);
//            tabLayout.setupWithViewPager(viewPager);
//            setupTabIcons();
//            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.black));
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//                    ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.background_icon_not_focus));
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//
//                }
//            });
//        }
//        setupViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.black));
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.background_icon_not_focus));
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
    }

    @Override
    protected void initData() {

    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.job_saved_tit));
        tabOne.setTextColor(SharedPrefUtils.getString("menu", "").equalsIgnoreCase("CTV_JOB") ? getResources().getColor(R.color.black) : getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.job_applyed_tit));
        tabTwo.setTextColor(SharedPrefUtils.getString("menu", "").equalsIgnoreCase("CTV_JOB_SENT") ? getResources().getColor(R.color.black) : getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    ViewPagerAdapter adapter;

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(jobsSavedFragment, getResources().getString(R.string.job_saved));
        adapter.addFrag(jobsApplyedFragment, getResources().getString(R.string.job_applyed));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        if (SharedPrefUtils.getString("menu", "").equalsIgnoreCase("CTV_JOB"))
            viewPager.setCurrentItem(0);
        else if (SharedPrefUtils.getString("menu", "").equalsIgnoreCase("CTV_JOB_SENT"))
            viewPager.setCurrentItem(1);
    }

    @OnClick(R.id.llCarrer)
    public void onllCarrerClick() {
        mIsCity = false;
        visibleFilter = true;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(false, mCarrerId, mCarrerName, true), null);
    }

    @OnClick(R.id.llAdd)
    public void onllAddClick() {
        mIsCity = true;
        visibleFilter = true;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(true, mCityId, mCityName, true), null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                if (mIsCity) {
                    mCityId = String.valueOf(data.getIntExtra("cityId", 0));
                    mCityName = data.getStringExtra("cityName");
                    tvCityName.setText(mCityName);
                } else {
                    mCarrerId = String.valueOf(data.getIntExtra("carrerId", 0));
                    mCarrerName = data.getStringExtra("carrerName");
                    tvCarrerName.setText(mCarrerName);
                }
            }
        }
    }

    @Override
    protected void onRestore() {

    }

    @Override
    protected void initialize() {
        if (visibleFilter) {
            llCondition.setVisibility(View.VISIBLE);
        } else {
            llCondition.setVisibility(View.GONE);
        }
        mCityName = mCityName.equalsIgnoreCase("") ? getResources().getString(R.string.all_city) : mCityName;
        mCarrerName = mCarrerName.equalsIgnoreCase("") ? getResources().getString(R.string.all_carrer) : mCarrerName;
        tvCityName.setText(mCityName);
        tvCarrerName.setText(mCarrerName);
        tvCityName.setTextColor(mCityName.equalsIgnoreCase(getResources().getString(R.string.all_city)) ? getResources().getColor(R.color.background_icon_not_focus) : getResources().getColor(R.color.black));
        tvCarrerName.setTextColor(mCarrerName.equalsIgnoreCase(getResources().getString(R.string.all_carrer)) ? getResources().getColor(R.color.background_icon_not_focus) : getResources().getColor(R.color.black));

    }

    @Override
    protected void onSaveState(Bundle bundle) {
        bundle.putBoolean(AppConstant.VISIBLE_FILTER, visibleFilter);
        bundle.putString("mCityName", mCityName);
        bundle.putString("mCarrerName", mCarrerName);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        visibleFilter = bundle.getBoolean(AppConstant.VISIBLE_FILTER);
        mCarrerName = bundle.getString("mCarrerName");
        mCityName = bundle.getString("mCityName");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (visibleFilter)
            inflater.inflate(R.menu.menu_cv_job_saved, menu);
        else
            inflater.inflate(R.menu.menu_myjob, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.filter) {
            if (visibleFilter) {
                visibleFilter = false;
                llCondition.setVisibility(View.GONE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_filter_black));
            } else {
                visibleFilter = true;
                llCondition.setVisibility(View.VISIBLE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_close));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
}
