package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExComSkillListViewAdapter;
import com.vietinterview.getbee.adapter.ExLanListViewAdapter;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcv.LstComputerSkill;
import com.vietinterview.getbee.api.response.detailcv.LstLanguage;
import com.vietinterview.getbee.callback.OnSwitchToFiveListener;
import com.vietinterview.getbee.utils.LayoutUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ComputerSkillDetailCVFragment extends BaseFragment {
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    private ExComSkillListViewAdapter exComSkillListViewAdapter;
    private ExpandableListView exLvLanguage;
    private int lastExpandedPositionlangeuage = 0;
    private List<String> listDataGroup;
    private HashMap<String, List<LstComputerSkill>> listDataChild;
    DetailCVResponse detailCVResponse;

    public static ComputerSkillDetailCVFragment newInstance(DetailCVResponse detailCVResponse) {
        ComputerSkillDetailCVFragment fm = new ComputerSkillDetailCVFragment();
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
        if (detailCVResponse.getLstComputerSkill().size() > 0) {
            getEventBaseFragment().setOnSwitchToFiveListener(new OnSwitchToFiveListener() {
                @Override
                public void onSwitchToFive() {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoGroup()));
                    exLvLanguage.collapseGroup(lastExpandedPositionlangeuage);
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
        exComSkillListViewAdapter = new ExComSkillListViewAdapter(getActivity(), listDataGroup, listDataChild);
        exLvLanguage.setAdapter(exComSkillListViewAdapter);
        if (detailCVResponse.getLstComputerSkill().size() > 0) {
            listDataGroup.add(getResources().getString(R.string.office_information));
            List<LstComputerSkill> lstComputerSkills = new ArrayList<>();
            lstComputerSkills.add(detailCVResponse.getLstComputerSkill().get(0));
            listDataChild.put(listDataGroup.get(0), lstComputerSkills);

            listDataGroup.add(getResources().getString(R.string.other_soft));
            List<LstComputerSkill> otherSoftList = new ArrayList<>();
            otherSoftList.add(detailCVResponse.getLstComputerSkill().get(0));
            listDataChild.put(listDataGroup.get(1), otherSoftList);

            exComSkillListViewAdapter.notifyDataSetChanged();
        }
        if (detailCVResponse.getLstComputerSkill().size() > 0) {
            exLvLanguage.expandGroup(lastExpandedPositionlangeuage);
            exLvLanguage.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPositionlangeuage != -1 && groupPosition != lastExpandedPositionlangeuage) {
                        exLvLanguage.collapseGroup(lastExpandedPositionlangeuage);
                    }
                    lastExpandedPositionlangeuage = groupPosition;
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoChild()) + LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoGroup()));
                }
            });
            exLvLanguage.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int i) {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoGroup()) * detailCVResponse.getLstEmploymentHis().size() + LayoutUtils.getViewHeight(exComSkillListViewAdapter.getLlInfoGroup()));

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
