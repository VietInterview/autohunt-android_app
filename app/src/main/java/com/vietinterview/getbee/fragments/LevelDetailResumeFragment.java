package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExLvListViewAdapter;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcv.LstEducationHi;
import com.vietinterview.getbee.callback.OnSwitchToThreeListener;
import com.vietinterview.getbee.utils.LayoutUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class LevelDetailResumeFragment extends BaseFragment {
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    private ExLvListViewAdapter exStepLvListViewAdapter;
    private ExpandableListView exLvLevel;
    private List<LstEducationHi> listDataGroup;
    private int lastExpandedPosition = 0;
    private HashMap<String, List<LstEducationHi>> listDataChild;
    DetailCVResponse detailCVResponse;

    public static LevelDetailResumeFragment newInstance(DetailCVResponse detailCVResponse) {
        LevelDetailResumeFragment fm = new LevelDetailResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVResponse", detailCVResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_level_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        if (detailCVResponse.getLstEducationHis().size() > 0) {
            getEventBaseFragment().setOnSwitchToThreeListener(new OnSwitchToThreeListener() {
                @Override
                public void onSwitchToThree() {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoGroup()));
                    exLvLevel.collapseGroup(lastExpandedPosition);
                }
            });
            tvNodata.setVisibility(View.GONE);
        } else {
            tvNodata.setVisibility(View.VISIBLE);
        }
        exLvLevel = root.findViewById(R.id.exLvLevel);
        exLvLevel.setIndicatorBounds(exLvLevel.getRight() + 800, exLvLevel.getWidth());
        exLvLevel.setDivider(getResources().getDrawable(R.color.white));
        exLvLevel.setDividerHeight(0);
        exLvLevel.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        exStepLvListViewAdapter = new ExLvListViewAdapter(getActivity(), listDataGroup, listDataChild);
        exLvLevel.setAdapter(exStepLvListViewAdapter);
        for (int i = 0; i < detailCVResponse.getLstEducationHis().size(); i++) {
            listDataGroup.add(detailCVResponse.getLstEducationHis().get(i));
            List<LstEducationHi> lstEducationHis = new ArrayList<>();
            lstEducationHis.add(detailCVResponse.getLstEducationHis().get(i));
            listDataChild.put(listDataGroup.get(i).getSchool(), lstEducationHis);
        }
        exStepLvListViewAdapter.notifyDataSetChanged();
        if (detailCVResponse.getLstEducationHis().size() > 0) {
            exLvLevel.expandGroup(lastExpandedPosition);
            exLvLevel.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                        exLvLevel.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoGroup()));
                }
            });
            exLvLevel.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int i) {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exStepLvListViewAdapter.getLlInfoGroup()));
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
