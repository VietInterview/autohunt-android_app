package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyJobsSavedAdapter;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetSavedSearchJobsRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.api.response.jobs.JobsResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class JobsSavedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.rcvSavedJob)
    RecyclerView rcvSavedJob;
    private RecyclerView.LayoutManager layoutManager;
    public MyJobsSavedAdapter adapter;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetSavedSearchJobsRequest getSavedSearchJobsRequest;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    int mPage = 0;
    private String mCarrerId = "0";
    private String mCityId = "0";
    private View mView;

    public static JobsSavedFragment newInstance(String cityId, String carrerId) {
        JobsSavedFragment fm = new JobsSavedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cityId", cityId);
        bundle.putString("carrerId", carrerId);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobs_saved;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        mView = root;
        rcvSavedJob.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rcvSavedJob.setLayoutManager(layoutManager);
        rcvSavedJob.setItemAnimator(new DefaultItemAnimator());
        rcvSavedJob.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView rcvSavedJob, int dx, int dy) {
                super.onScrolled(rcvSavedJob, dx, dy);
                if (dy > 0) {
//                    titleHeader.setVisibility(View.GONE);
//                    fab.hide();
                } else {
//                    titleHeader.setVisibility(View.VISIBLE);
//                    fab.show();
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mCityId = bundle.getString("cityId");
        mCarrerId = bundle.getString("carrerId");
    }

    @Override
    protected void initData() {
        getSavedSearchJob(mCarrerId, mCityId, "", mPage);
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

    @Override
    public void onRefresh() {
        mPage = 0;
        getSavedSearchJob(mCarrerId, mCityId, "", mPage);
    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() >= ApiConstant.LIMIT) {
            mPage++;
            getSavedSearchJob(mCarrerId, mCityId, "", mPage);
            adapter.setOnLoadMoreListener(JobsSavedFragment.this);
        }
    }

    public void getSavedSearchJob(String careerId, String cityId, String jobtile, final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();

        getSavedSearchJobsRequest = new GetSavedSearchJobsRequest(careerId, cityId, String.valueOf(ApiConstant.LIMIT), jobtile, page);
        getSavedSearchJobsRequest.callRequest(getActivity(), new ApiObjectCallBack<JobsResponse, ErrorResponse>() {

            @Override
            public void onSuccess(int status, JobsResponse data, List<JobsResponse> dataArrayList, String message) {
                if (isAdded()) {
                    jobsListServer.clear();
                    jobsListServer.addAll(data.getJobList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    hideCoverNetworkLoading();
                    if (page == 0) jobsList.clear();
                    else {
//                    jobsList.remove(jobsList.size() - 1);
                        adapter.notifyItemRemoved(jobsList.size());
                    }
                    if (page == 0) {
                        adapter = new MyJobsSavedAdapter(rcvSavedJob, jobsList, data.getTotal(), JobsSavedFragment.this, getActivity(), true);
                        rcvSavedJob.setAdapter(adapter);
                    }
                    adapter.setOnLoadMoreListener(JobsSavedFragment.this);
                    jobsList.addAll(data.getJobList());
                    adapter.notifyDataSetChanged();
                    adapter.setLoaded();
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse dataFail, List<ErrorResponse> dataArrayList, String message) {
                if (isAdded()) {
                    if (mSwipeRefreshLayout != null)
                        mSwipeRefreshLayout.setRefreshing(false);
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), "Thông báo", message);
                }
            }

        });
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        baseRequests.add(getSavedSearchJobsRequest);
        return baseRequests;
    }
}
