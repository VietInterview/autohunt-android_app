package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExExpListViewAdapter;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcv.LstEmploymentHi;
import com.vietinterview.getbee.callback.OnSwitchToTwoListener;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.LayoutUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ExpDetailCVFragment extends BaseFragment {
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    private ExpandableListView expandableListView;
    private ExExpListViewAdapter exExpListViewAdapter;
    private List<LstEmploymentHi> listDataGroup;
    private int lastExpandedPosition = 0;
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
        if (detailCVResponse.getLstEmploymentHis().size() > 0) {
            getEventBaseFragment().setOnSwitchToTwoListener(new OnSwitchToTwoListener() {
                @Override
                public void onSwitchToTwo() {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoGroup()));
                }
            });
            tvNodata.setVisibility(View.GONE);
        } else {
            tvNodata.setVisibility(View.VISIBLE);
        }
        expandableListView = root.findViewById(R.id.expandableListView);
        expandableListView.setIndicatorBounds(expandableListView.getRight() + 800, expandableListView.getWidth());
        expandableListView.setDivider(getResources().getDrawable(R.color.white));
        expandableListView.setDividerHeight(0);
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        for (int i = 0; i < detailCVResponse.getLstEmploymentHis().size(); i++) {
            listDataGroup.add(detailCVResponse.getLstEmploymentHis().get(i));
            List<LstEmploymentHi> lstEmploymentHis = new ArrayList<>();
            lstEmploymentHis.add(detailCVResponse.getLstEmploymentHis().get(i));
            listDataChild.put(listDataGroup.get(i).getTitle(), lstEmploymentHis);
        }
        exExpListViewAdapter = new ExExpListViewAdapter(getActivity(), listDataGroup, listDataChild, detailCVResponse.getCurrencyName());
        expandableListView.setAdapter(exExpListViewAdapter);
        exExpListViewAdapter.notifyDataSetChanged();
        if (detailCVResponse.getLstEmploymentHis().size() > 0) {
            expandableListView.expandGroup(lastExpandedPosition);
            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                        expandableListView.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoGroup()));
                }
            });
            expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int i) {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exExpListViewAdapter.getLlInfoGroup()));
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
