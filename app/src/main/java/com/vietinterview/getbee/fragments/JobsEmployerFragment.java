package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.JobsEmployerAdapter;
import com.vietinterview.getbee.api.request.SearchCVSaveRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 11/27/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class JobsEmployerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    private SearchCVSaveRequest searchCVSaveRequest;
    private JobsEmployerAdapter jobsEmployerAdapter;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();

    public static JobsEmployerFragment newInstance(int carrerId, int cityId) {
        JobsEmployerFragment fm = new JobsEmployerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("carrerId", carrerId);
        bundle.putInt("cityId", cityId);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_employer;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().doFillBackground("Danh sách công việc");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    @Override
    protected void initData() {
        getCVSaved(0);
    }

    public void getCVSaved(final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        searchCVSaveRequest = new SearchCVSaveRequest(page);
        searchCVSaveRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    cvListsServer.clear();
                    cvListsServer.addAll(data.getCvList());
                    tvNodata.setVisibility(data.getTotal() == 0 ? View.VISIBLE : View.GONE);
                    if (page == 0) cvLists.clear();
                    else {
                        jobsEmployerAdapter.notifyItemRemoved(cvLists.size());
                    }
                    cvLists.addAll(data.getCvList());
                    if (page == 0) {
                        jobsEmployerAdapter = new JobsEmployerAdapter(recyclerView, getActivity(), data.getTotal(), cvLists, JobsEmployerFragment.this);
                        recyclerView.setAdapter(jobsEmployerAdapter);
                    }
                    jobsEmployerAdapter.setOnLoadMoreListener(JobsEmployerFragment.this);
                    jobsEmployerAdapter.notifyDataSetChanged();
                    jobsEmployerAdapter.setLoaded();
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                }
            }
        });
    }

    @Override
    protected void initialize() {
        if (visibleSearch) {
            llSearch.setVisibility(View.VISIBLE);
        } else llSearch.setVisibility(View.GONE);
        mStatusName = mStatusName.equalsIgnoreCase("") ? getResources().getString(R.string.default_key) : mStatusName;
        tvStatus.setText(mStatusName);
        tvStatus.setTextColor(mStatusName.equalsIgnoreCase(getResources().getString(R.string.default_key)) ? getResources().getColor(R.color.background_icon_not_focus) : getResources().getColor(R.color.black));

    }

    @Override
    protected void onSaveState(Bundle bundle) {
        bundle.putBoolean(AppConstant.VISIBLE_SEARCH, visibleSearch);
        bundle.putString("mStatusName", mStatusName);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        visibleSearch = bundle.getBoolean(AppConstant.VISIBLE_SEARCH);
        mStatusName = bundle.getString("mStatusName");
    }

    @Override
    protected void onRestore() {

    }

    private String mStatusId = "11";
    private String mStatusName = "";

    @OnClick(R.id.llStatus)
    public void onllStatusClick() {
        visibleSearch = true;
        FragmentUtil.pushFragment(getActivity(), this, new StatusFragment().newInstance(mStatusId, mStatusName), null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                mStatusId = String.valueOf(data.getIntExtra("statusId", Integer.parseInt(mStatusId)));
                mStatusName = data.getStringExtra("statusName");
                tvStatus.setText(mStatusName);
            }
        }
    }

    private boolean visibleSearch = false;
    private Menu menu;
    private MenuItem mItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (visibleSearch)
            inflater.inflate(R.menu.menu_main, menu);
        else inflater.inflate(R.menu.menu_home_search, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mItem = item;
        int id = mItem.getItemId();
        if (id == R.id.search) {
            if (visibleSearch) {
                visibleSearch = false;
                llSearch.setVisibility(View.GONE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search));
            } else {
                visibleSearch = true;
                llSearch.setVisibility(View.VISIBLE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_saveok));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

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
