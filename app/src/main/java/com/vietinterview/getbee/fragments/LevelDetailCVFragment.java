package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExLvListViewAdapter;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcv.LstEducationHi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class LevelDetailCVFragment extends BaseFragment {
    private ExLvListViewAdapter exStepLvListViewAdapter;
    private ExpandableListView exLvLevel;
    private List<LstEducationHi> listDataGroup;
    private int lastExpandedPosition = -1;
    private HashMap<String, List<LstEducationHi>> listDataChild;
    DetailCVResponse detailCVResponse;

    public static LevelDetailCVFragment newInstance(DetailCVResponse detailCVResponse) {
        LevelDetailCVFragment fm = new LevelDetailCVFragment();
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
        exLvLevel.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    exLvLevel.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        exStepLvListViewAdapter = new ExLvListViewAdapter(getActivity(), listDataGroup, listDataChild);
        exLvLevel.setAdapter(exStepLvListViewAdapter);
        for (int i = 0; i < detailCVResponse.getLstEducationHis().size(); i++) {
            listDataGroup.add(detailCVResponse.getLstEducationHis().get(i));
            String[] array;
            List<LstEducationHi> lstEducationHis = new ArrayList<>();
            for (LstEducationHi item : detailCVResponse.getLstEducationHis()) {
                lstEducationHis.add(item);
            }
            listDataChild.put(listDataGroup.get(i).getSchool(), lstEducationHis);
        }
        exStepLvListViewAdapter.notifyDataSetChanged();
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
