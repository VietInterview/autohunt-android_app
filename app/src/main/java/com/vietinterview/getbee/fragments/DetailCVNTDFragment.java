package com.vietinterview.getbee.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.SimpleRecyclerAdapter;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.model.VersionModel;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 11/28/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailCVNTDFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_cv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(false);
        setCustomToolbarVisible(false);
        final ViewPager viewPager = (ViewPager) root.findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);

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
        adapter.addFrag(new InfoDetailCVNTDFragment(), "THÔNG TIN");
        adapter.addFrag(new DummyFragment(
                ContextCompat.getColor(getActivity(), R.color.white)), "KINH NGHIỆM");
        adapter.addFrag(new DummyFragment(
                ContextCompat.getColor(getActivity(), R.color.white)), "BẰNG CẤP");
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

    public static class DummyFragment extends Fragment {
        int color;
        SimpleRecyclerAdapter adapter;

        public DummyFragment() {
        }

        @SuppressLint("ValidFragment")
        public DummyFragment(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            List<String> list = new ArrayList<String>();
            for (int i = 0; i < VersionModel.data.length; i++) {
                list.add(VersionModel.data[i]);
            }

            adapter = new SimpleRecyclerAdapter(list);
            recyclerView.setAdapter(adapter);

            return view;
        }
    }
}
