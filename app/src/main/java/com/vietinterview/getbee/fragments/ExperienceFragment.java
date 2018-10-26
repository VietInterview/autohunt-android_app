package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExExpListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/18/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ExperienceFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private ExExpListViewAdapter exExpListViewAdapter;
    private List<String> listDataGroup;
    private int lastExpandedPosition = -1;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_experience;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
//        expandableListView = root.findViewById(R.id.expandableListView);
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                return false;
//            }
//        });
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
//                    expandableListView.collapseGroup(lastExpandedPosition);
//                }
//                lastExpandedPosition = groupPosition;
//            }
//        });
//        expandableListView.setIndicatorBounds(expandableListView.getRight() + 900, expandableListView.getWidth());
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//
//            }
//        });
//        listDataGroup = new ArrayList<>();
//        listDataChild = new HashMap<>();
//        exExpListViewAdapter = new ExExpListViewAdapter(getActivity(), listDataGroup, listDataChild);
//        expandableListView.setAdapter(exExpListViewAdapter);
//        listDataGroup.add(getString(R.string.text_alcohol));
//        listDataGroup.add(getString(R.string.text_coffee));
//        listDataGroup.add(getString(R.string.text_pasta));
//        listDataGroup.add(getString(R.string.text_cold_drinks));
//        String[] array;
//        List<String> alcoholList = new ArrayList<>();
//        array = getResources().getStringArray(R.array.string_array_alcohol);
//        for (String item : array) {
//            alcoholList.add(item);
//        }
//        List<String> coffeeList = new ArrayList<>();
//        array = getResources().getStringArray(R.array.string_array_coffee);
//        for (String item : array) {
//            coffeeList.add(item);
//        }
//        List<String> pastaList = new ArrayList<>();
//        array = getResources().getStringArray(R.array.string_array_pasta);
//        for (String item : array) {
//            pastaList.add(item);
//        }
//        List<String> coldDrinkList = new ArrayList<>();
//        array = getResources().getStringArray(R.array.string_array_cold_drinks);
//        for (String item : array) {
//            coldDrinkList.add(item);
//        }
//        listDataChild.put(listDataGroup.get(0), alcoholList);
//        listDataChild.put(listDataGroup.get(1), coffeeList);
//        listDataChild.put(listDataGroup.get(2), pastaList);
//        listDataChild.put(listDataGroup.get(3), coldDrinkList);
//        exExpListViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        getEventBaseFragment().changeStepExp(2);
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
