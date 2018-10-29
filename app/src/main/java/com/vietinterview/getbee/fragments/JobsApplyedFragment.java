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
import com.vietinterview.getbee.adapter.MyJobsAdapter;
import com.vietinterview.getbee.api.request.GetApplyedSearchJobsRequest;
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
public class JobsApplyedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    //    @BindView(R.id.fab)
//    FloatingActionButton fab;
    @BindView(R.id.titleHeader)
    TextView titleHeader;
    @BindView(R.id.rcvApplyedJob)
    RecyclerView rcvApplyedJob;
    public MyJobsAdapter adapter;
    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;
    //    private static RecyclerView rcvApplyedJob;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetApplyedSearchJobsRequest getApplyedSearchJobsRequest;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    int mPage = 0;
    private String mCarrerId = "4";
    private String mCityId = "1";

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
        myOnClickListener = new JobsApplyedFragment.MyOnClickListener(getActivity());
        rcvApplyedJob.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        rcvApplyedJob.setLayoutManager(layoutManager);
        rcvApplyedJob.setItemAnimator(new DefaultItemAnimator());
        rcvApplyedJob.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView rcvApplyedJob, int dx, int dy) {
                super.onScrolled(rcvApplyedJob, dx, dy);
                if (dy > 0) {
//                    titleHeader.setVisibility(View.GONE);
//                    fab.hide();
                } else {
//                    titleHeader.setVisibility(View.VISIBLE);
//                    fab.show();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView rcvApplyedJob, int newState) {
                super.onScrollStateChanged(rcvApplyedJob, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        });
        adapter = new MyJobsAdapter(rcvApplyedJob, jobsList, this, getActivity(), false);
        rcvApplyedJob.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        adapter.setOnLoadMoreListener(this);
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
        getApplyedSearchJobsRequest.callRequest(getActivity(), new ApiObjectCallBack<JobsResponse>() {

            @Override
            public void onSuccess(JobsResponse data, List<JobsResponse> dataArrayList, int status, String message) {
                jobsListServer.clear();
                jobsListServer.addAll(data.getJobList());
                mSwipeRefreshLayout.setRefreshing(false);
                hideCoverNetworkLoading();
                if (page == 0) jobsList.clear();
                else {
//                    jobsList.remove(jobsList.size() - 1);
                    adapter.notifyItemRemoved(jobsList.size());
                }
                jobsList.addAll(data.getJobList());
                titleHeader.setText(data.getTotal() + " " + getResources().getString(R.string.job_applyed));
                adapter.notifyDataSetChanged();
                adapter.setLoaded();
            }

            @Override
            public void onFail(int failCode, JobsResponse data, List<JobsResponse> dataArrayList, String message) {
                if (mSwipeRefreshLayout != null)
                    mSwipeRefreshLayout.setRefreshing(false);
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() >= 10) {
            mPage++;
            getApplyedSearchJob(mCarrerId, mCityId, "", mPage);
            adapter.setOnLoadMoreListener(JobsApplyedFragment.this);
        }
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final FragmentActivity fragmentActivity;

        private MyOnClickListener(FragmentActivity fragmentActivity) {
            this.fragmentActivity = fragmentActivity;
        }

        @Override
        public void onClick(View v) {
//            FragmentUtil.pushFragment(fragmentActivity,JobsApplyedFragment.this, new DetailJobFragment(), null);
        }
    }
}
