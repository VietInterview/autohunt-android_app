package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyJobsAppliedAdapter;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetApplyedSearchJobsRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.api.response.jobs.JobsResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DialogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class JobsApplyedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, Serializable {
    @BindView(R.id.rcvApplyedJob)
    RecyclerView rcvApplyedJob;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MyJobsAppliedAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetApplyedSearchJobsRequest getApplyedSearchJobsRequest;
    private int mPage = 0;
    private String mCarrerId = "0";
    private String mCityId = "0";

    public static JobsApplyedFragment newInstance(String cityId, String carrerId) {
        JobsApplyedFragment fm = new JobsApplyedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cityId", cityId);
        bundle.putString("carrerId", carrerId);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobs_applyed;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        rcvApplyedJob.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rcvApplyedJob.setLayoutManager(layoutManager);
        rcvApplyedJob.setItemAnimator(new DefaultItemAnimator());
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
        getApplyedSearchJob(mCarrerId, mCityId, "", mPage);
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
        getApplyedSearchJob(mCarrerId, mCityId, "", mPage);
    }

    public void getApplyedSearchJob(String careerId, String cityId, String jobtile, final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        getApplyedSearchJobsRequest = new GetApplyedSearchJobsRequest(careerId, cityId, String.valueOf(ApiConstant.LIMIT), jobtile, page);
        getApplyedSearchJobsRequest.callRequest(getActivity(), new ApiObjectCallBack<JobsResponse, ErrorResponse>() {

            @Override
            public void onSuccess(int status, JobsResponse data, List<JobsResponse> dataArrayList, String message) {
                if (isAdded()) {
                    jobsListServer.clear();
                    jobsListServer.addAll(data.getJobList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    hideCoverNetworkLoading();
                    if (page == 0) jobsList.clear();
                    else adapter.notifyItemRemoved(jobsList.size());
                    jobsList.addAll(data.getJobList());
                    if (page == 0) {
                        adapter = new MyJobsAppliedAdapter(rcvApplyedJob, jobsList, data.getTotal(), JobsApplyedFragment.this, getActivity(), false);
                        rcvApplyedJob.setAdapter(adapter);
                    }
                    adapter.setOnLoadMoreListener(JobsApplyedFragment.this);
                    adapter.notifyDataSetChanged();
                    adapter.setLoaded();
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse dataFail, List<ErrorResponse> dataArrayList, String message) {
                if (isAdded()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() >= ApiConstant.LIMIT) {
            mPage++;
            getApplyedSearchJob(mCarrerId, mCityId, "", mPage);
        }
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        baseRequests.add(getApplyedSearchJobsRequest);
        return baseRequests;
    }
}
