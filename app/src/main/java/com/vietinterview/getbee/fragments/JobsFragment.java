package com.vietinterview.getbee.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/9/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class JobsFragment extends BaseFragment {
    @BindView(R.id.list)
    public ListView listView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobs;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground("Ngành Nghề");
        setHasOptionsMenu(true);
        String[] fruit = getResources().getStringArray(R.array.fruit_array);
        CustomAdapter adapter = new CustomAdapter(getActivity(), fruit);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateMenuTitles();
            }
        });
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    private List<String> getSelectedItems() {
        List<String> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCount(); ++i) {
            if (checkedItems.size() > 0) {
                if (checkedItems.valueAt(i)) {
                    result.add((String) listView.getItemAtPosition(checkedItems.keyAt(i)));
                }
            }
        }

        return result;
    }

    @Override
    protected void onRestore() {

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

    private Menu menu;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_jobs, menu);
        MenuItem menuItem = menu.findItem(R.id.choose);
        if (menuItem != null) {
            String title = menuItem.getTitle().toString();
            TextView button_menu = (TextView) menuItem.getActionView();
            if (getSelectedItems().size() > 0)
                button_menu.setText("Chọn (" + getSelectedItems().size() + ")");
            else button_menu.setText("Chọn");
            button_menu.setPadding(0, 0, 16, 0);
            button_menu.setTextSize(18);
            button_menu.setTextColor(Color.BLACK);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Nunito-Bold.ttf");
            button_menu.setTypeface(font);
        }
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.choose) {
            Toast.makeText(getActivity(), "Choose", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateMenuTitles() {
        MenuItem menuItem = menu.findItem(R.id.choose);
        if (menuItem != null) {
            String title = menuItem.getTitle().toString();
            TextView button_menu = (TextView) menuItem.getActionView();
            if (getSelectedItems().size() > 0)
                button_menu.setText("Chọn (" + getSelectedItems().size() + ")");
            else button_menu.setText("Chọn");
            button_menu.setTextSize(18);
            button_menu.setTextColor(Color.BLACK);
            button_menu.setPadding(0, 0, 16, 0);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Nunito-Bold.ttf");
            button_menu.setTypeface(font);
        }
        menuItem.setTitle("Chọn (" + getSelectedItems().size() + ")");
    }
}
