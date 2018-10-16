package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.JobsAdapter;
import com.vietinterview.getbee.api.request.GetSearchJobsRequest;
import com.vietinterview.getbee.api.response.jobsresponse.JobList;
import com.vietinterview.getbee.api.response.jobsresponse.JobsResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

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
    public JobsAdapter adapter;
    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetSearchJobsRequest getSearchJobsRequest;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    int mPage = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobs_applyed;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        myOnClickListener = new JobsApplyedFragment.MyOnClickListener(getActivity());
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    titleHeader.setVisibility(View.GONE);
//                    fab.hide();
                } else {
                    titleHeader.setVisibility(View.VISIBLE);
//                    fab.show();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        });
        adapter = new JobsAdapter(recyclerView, jobsList, this, getActivity());
        recyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        adapter.setOnLoadMoreListener(this);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
        getSearchJob("4", "", "", mPage);
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
        getSearchJob("4", "", "", mPage);
    }

    public void getSearchJob(String careerId, String cityId, String jobtile, final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();

        getSearchJobsRequest = new GetSearchJobsRequest(careerId, cityId, "10", jobtile, page);
        getSearchJobsRequest.callRequest(new ApiObjectCallBack<JobsResponse>() {

            @Override
            public void onSuccess(JobsResponse data, List<JobsResponse> dataArrayList, int status, String message) {
                jobsListServer.clear();
                jobsListServer.addAll(data.getJobList());
                mSwipeRefreshLayout.setRefreshing(false);
                hideCoverNetworkLoading();
                if (page == 0) jobsList.clear();
                else {
                    jobsList.remove(jobsList.size() - 1);
                    adapter.notifyItemRemoved(jobsList.size());
                }
                jobsList.addAll(data.getJobList());
                titleHeader.setText(data.getTotal() + " công việc được tìm thấy");
                adapter.notifyDataSetChanged();
                adapter.setLoaded();
            }

            @Override
            public void onFail(int failCode, JobsResponse data, List<JobsResponse> dataArrayList, String message) {
                if (mSwipeRefreshLayout != null)
                    mSwipeRefreshLayout.setRefreshing(false);
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), "Thông báo", message);
            }
        });
    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() > 0) {
            mPage++;
            getSearchJob("4", "", "", mPage);
            adapter.setOnLoadMoreListener(JobsApplyedFragment.this);
        } else {
            Toast.makeText(getActivity(), "Loading data completed", Toast.LENGTH_SHORT).show();
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
