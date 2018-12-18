package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.vietinterview.getbee.api.request.GetJobCustomerRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobcustomer.JobCustomerResponse;
import com.vietinterview.getbee.api.response.jobcustomer.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.customview.ClearableRegularEditText;
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
    @BindView(R.id.edtJobTitle)
    ClearableRegularEditText edtJobTitle;
    private int page = 0;
    private GetJobCustomerRequest getJobCustomerRequest;
    private JobsEmployerAdapter jobsEmployerAdapter;
    private ArrayList<JobList> jobLists = new ArrayList<>();
    private ArrayList<JobList> jobListsServer = new ArrayList<>();

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
        edtJobTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page = 0;
                getJobCustomer(page);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void initData() {
        getJobCustomer(0);
    }

    public void getJobCustomer(final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        getJobCustomerRequest = new GetJobCustomerRequest(page, edtJobTitle.getText().toString().trim(), Integer.parseInt(mStatusId));
        getJobCustomerRequest.callRequest(getActivity(), new ApiObjectCallBack<JobCustomerResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, JobCustomerResponse data, List<JobCustomerResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    jobListsServer.clear();
                    jobListsServer.addAll(data.getJobList());
                    tvNodata.setVisibility(data.getTotal() == 0 ? View.VISIBLE : View.GONE);
                    if (page == 0) {
                        jobLists.clear();
                    } else {
                        jobsEmployerAdapter.notifyItemRemoved(jobLists.size());
                    }
                    jobLists.addAll(data.getJobList());
                    if (page == 0) {
                        jobsEmployerAdapter = new JobsEmployerAdapter(recyclerView, getActivity(), data.getTotal(), jobLists, JobsEmployerFragment.this);
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

    private String mStatusId = "-1";
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
        page = 0;
        getJobCustomer(page);
    }

    @Override
    public void onLoadMore() {
        if (jobListsServer.size() >= ApiConstant.LIMIT) {
            page++;
            getJobCustomer(page);
            jobsEmployerAdapter.setOnLoadMoreListener(JobsEmployerFragment.this);
        }
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
