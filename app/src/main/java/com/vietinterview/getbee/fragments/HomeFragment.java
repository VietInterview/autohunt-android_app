package com.vietinterview.getbee.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.JobsAdapter;
import com.vietinterview.getbee.model.JobModel;
import com.vietinterview.getbee.model.MyJob;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nguyennghiahiep on 10/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.llCondition)
    LinearLayout llCondition;
    @BindView(R.id.imgFilter)
    ImageView imgFilter;
    @BindView(R.id.llDatePub)
    LinearLayout llDatePub;
    private boolean visibleSearch = false;
    private boolean visibleCondition = false;
    private static RecyclerView.Adapter adapter;
    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<JobModel> data;
    private static ArrayList<Integer> removedItems;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        myOnClickListener = new MyOnClickListener(getActivity());
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<JobModel>();
        for (int i = 0; i < MyJob.nameArray.length; i++) {
            data.add(new JobModel(
                    MyJob.nameArray[i],
                    MyJob.versionArray[i],
                    MyJob.id_[i],
                    MyJob.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();
        this.registerForContextMenu(llDatePub);
        adapter = new JobsAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.llCarrer)
    public void onllCarrerClick() {
        FragmentUtil.pushFragment(getActivity(), new CarrerFragment(), null);
    }

    @OnClick(R.id.llDatePub)
    public void onPubDateClick() {
        this.registerForContextMenu(llDatePub);
        getActivity().openContextMenu(llDatePub);
    }

    @OnClick(R.id.imgFilter)
    public void onimgFilter() {
        if (visibleCondition) {
            visibleCondition = false;
            llCondition.setVisibility(View.GONE);
        } else {
            visibleCondition = true;
            llCondition.setVisibility(View.VISIBLE);
        }
    }

    final int CONTEXT_MENU_VIEW = 1;
    final int CONTEXT_MENU_EDIT = 2;
    final int CONTEXT_MENU_ARCHIVE = 3;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Chọn ngày đăng tuyển");
        menu.add(Menu.NONE, CONTEXT_MENU_VIEW, Menu.NONE, "Mới nhất - Cũ nhất");
        menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Cũ nhất - Mới nhất");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case CONTEXT_MENU_VIEW: {

            }
            break;
            case CONTEXT_MENU_EDIT: {
                // Edit Action

            }
            break;
            case CONTEXT_MENU_ARCHIVE: {

            }
            break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onRestore() {

    }

    @Override
    protected void initialize() {
        getEventBaseFragment().showLogo(true);
    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
        }
    }

    private Menu menu;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_second, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            if (visibleSearch) {
                visibleSearch = false;
                llSearch.setVisibility(View.GONE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search_black));
            } else {
                visibleSearch = true;
                llSearch.setVisibility(View.VISIBLE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_saveok));
            }
            Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void processOnBackPress() {
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_menu);
    }

    @Override
    protected void processCustomToolbar() {
        loadMenuLeft();
    }
}
