package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExExpListViewAdapter;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcv.LstEmploymentHi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ExpDetailCVFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private ExExpListViewAdapter exExpListViewAdapter;
    private List<String> listDataGroup;
    private int lastExpandedPosition = -1;
    private HashMap<String, List<LstEmploymentHi>> listDataChild;
    DetailCVResponse detailCVResponse;

    public static ExpDetailCVFragment newInstance(DetailCVResponse detailCVResponse) {
        ExpDetailCVFragment fm = new ExpDetailCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailCVResponse", detailCVResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exp_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        expandableListView = root.findViewById(R.id.expandableListView);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
        expandableListView.setIndicatorBounds(expandableListView.getRight() + 900, expandableListView.getWidth());
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        exExpListViewAdapter = new ExExpListViewAdapter(getActivity(), listDataGroup, listDataChild);
        expandableListView.setAdapter(exExpListViewAdapter);
        for (int i = 0; i < detailCVResponse.getLstEmploymentHis().size(); i++) {
            listDataGroup.add(detailCVResponse.getLstEmploymentHis().get(i).getCompanyName());
            List<LstEmploymentHi> lstEmploymentHis = new ArrayList<>();
            for (LstEmploymentHi item : detailCVResponse.getLstEmploymentHis()) {
                lstEmploymentHis.add(item);
            }
            listDataChild.put(listDataGroup.get(i), lstEmploymentHis);
        }
        exExpListViewAdapter.notifyDataSetChanged();
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
