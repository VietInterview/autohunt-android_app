package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExLanListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hiepnguyennghia on 10/23/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class LanDetailCVFragment extends BaseFragment {
    private ExLanListViewAdapter exStepLanListViewAdapter;
    private ExpandableListView exLvLanguage;
    private int lastExpandedPositionlangeuage = -1;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lan_detail_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {

        exLvLanguage = root.findViewById(R.id.exLvLanguage);
        exLvLanguage.setIndicatorBounds(exLvLanguage.getRight() + 900, exLvLanguage.getWidth());
        exLvLanguage.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPositionlangeuage != -1 && groupPosition != lastExpandedPositionlangeuage) {
                    exLvLanguage.collapseGroup(lastExpandedPositionlangeuage);
                }
                lastExpandedPositionlangeuage = groupPosition;
            }
        });
        listDataGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        exStepLanListViewAdapter = new ExLanListViewAdapter(getActivity(), listDataGroup, listDataChild);
        exLvLanguage.setAdapter(exStepLanListViewAdapter);
        listDataGroup.add(getString(R.string.text_alcohol));
        listDataGroup.add(getString(R.string.text_coffee));
        listDataGroup.add(getString(R.string.text_pasta));
        listDataGroup.add(getString(R.string.text_cold_drinks));
        String[] array;
        List<String> alcoholList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_alcohol);
        for (String item : array) {
            alcoholList.add(item);
        }
        List<String> coffeeList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_coffee);
        for (String item : array) {
            coffeeList.add(item);
        }
        List<String> pastaList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_pasta);
        for (String item : array) {
            pastaList.add(item);
        }
        List<String> coldDrinkList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_array_cold_drinks);
        for (String item : array) {
            coldDrinkList.add(item);
        }
        listDataChild.put(listDataGroup.get(0), alcoholList);
        listDataChild.put(listDataGroup.get(1), coffeeList);
        listDataChild.put(listDataGroup.get(2), pastaList);
        listDataChild.put(listDataGroup.get(3), coldDrinkList);
        exStepLanListViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void getArgument(Bundle bundle) {

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
