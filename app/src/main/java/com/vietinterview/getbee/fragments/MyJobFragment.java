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
import com.vietinterview.getbee.utils.FragmentUtil;

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
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Menu menu;
    private boolean visibleFilter = false;
    private boolean mIsCity = false;
    private String mCarrerId = "4";
    private String mCarrerName = "IT, Phần mềm";
    private String mCityId = "1";
    private String mCityName = "Hà Nội";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myjob;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.my_job));
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        tvCityName.setText(mCityName);
        tvCarrerName.setText(mCarrerName);
        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
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
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.black));
        tab.select();
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
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.job_saved));
        tabOne.setTextColor(getResources().getColor(R.color.black));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.job_applyed));
        tabTwo.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new JobsSavedFragment().newInstance(mCityId, mCarrerId), getResources().getString(R.string.job_saved));
        adapter.addFrag(new JobsApplyedFragment().newInstance(mCityId, mCarrerId), getResources().getString(R.string.job_applyed));
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);
    }


    @OnClick(R.id.llCarrer)
    public void onllCarrerClick() {
        mIsCity = false;
        visibleFilter = true;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(false, mCarrerId, mCarrerName), null);
    }

    @OnClick(R.id.llAdd)
    public void onllAddClick() {
        mIsCity = true;
        visibleFilter = true;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(true, mCityId, mCityName), null);
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
    }

    @Override
    protected void onSaveState(Bundle bundle) {
        bundle.putBoolean(AppConstant.VISIBLE_FILTER, visibleFilter);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        visibleFilter = bundle.getBoolean(AppConstant.VISIBLE_FILTER);
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
