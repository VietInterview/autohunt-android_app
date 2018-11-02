package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExLanListViewAdapter;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcv.LstLanguage;
import com.vietinterview.getbee.callback.OnSwitchToFourListener;
import com.vietinterview.getbee.utils.LayoutUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class LanDetailCVFragment extends BaseFragment {
    private ExLanListViewAdapter exStepLanListViewAdapter;
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    private ExpandableListView exLvLanguage;
    private int lastExpandedPositionlangeuage = 0;
    private List<LstLanguage> listDataGroup;
    private HashMap<String, List<LstLanguage>> listDataChild;
    DetailCVResponse detailCVResponse;

    public static LanDetailCVFragment newInstance(DetailCVResponse detailCVResponse) {
        LanDetailCVFragment fm = new LanDetailCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVResponse", detailCVResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lan_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        if (detailCVResponse.getLstLanguage().size() > 0) {
            getEventBaseFragment().setOnSwitchToFourListener(new OnSwitchToFourListener() {
                @Override
                public void onSwitchToFour() {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoGroup()));
                }
            });
            tvNodata.setVisibility(View.GONE);
        } else {
            tvNodata.setVisibility(View.VISIBLE);
        }
        exLvLanguage = root.findViewById(R.id.exLvLanguage);
        exLvLanguage.setIndicatorBounds(exLvLanguage.getRight() + 800, exLvLanguage.getWidth());
        exLvLanguage.setDivider(getResources().getDrawable(R.color.white));
        exLvLanguage.setDividerHeight(0);
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        exStepLanListViewAdapter = new ExLanListViewAdapter(getActivity(), listDataGroup, listDataChild);
        exLvLanguage.setAdapter(exStepLanListViewAdapter);
        for (int i = 0; i < detailCVResponse.getLstLanguage().size(); i++) {
            listDataGroup.add(detailCVResponse.getLstLanguage().get(i));
            List<LstLanguage> alcoholList = new ArrayList<>();
            alcoholList.add(detailCVResponse.getLstLanguage().get(i));
            listDataChild.put(listDataGroup.get(i).getLanguageName(), alcoholList);
        }
        if (detailCVResponse.getLstLanguage().size() > 0) {
            exStepLanListViewAdapter.notifyDataSetChanged();
            exLvLanguage.expandGroup(lastExpandedPositionlangeuage);
            exLvLanguage.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPositionlangeuage != -1 && groupPosition != lastExpandedPositionlangeuage) {
                        exLvLanguage.collapseGroup(lastExpandedPositionlangeuage);
                    }
                    lastExpandedPositionlangeuage = groupPosition;
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoGroup()));
                }
            });
            exLvLanguage.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int i) {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exStepLanListViewAdapter.getLlInfoGroup()));

                }
            });
        }
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailCVResponse = bundle.getParcelable("detailCVResponse");
    }

    @Override
    protected void initData() {

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
