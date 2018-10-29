package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyCVAdapter;
import com.vietinterview.getbee.adapter.MyCVSubmitAdapter;
import com.vietinterview.getbee.api.request.SearchCVSubmitRequest;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSavedListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSubmitListener;
import com.vietinterview.getbee.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class MyCVApplyedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.tvCountCV)
    TextView tvCountCV;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();
    private MyCVSubmitAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SearchCVSubmitRequest searchCVSubmitRequest;
    private int mPage = 0;
    private int mStatus = 11;
    CVResponse cvResponse;
    private int mCareerId;
    private int mCityId;

    public static MyCVApplyedFragment newInstance(int status, int carrerId, int cityId) {
        MyCVApplyedFragment fm = new MyCVApplyedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        bundle.putInt("carrerId", carrerId);
        bundle.putInt("cityId", cityId);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycv_applyed;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().setOnRefreshMyCVSubmitListener(new OnRefreshMyCVSubmitListener() {
            @Override
            public void onRefreshMyCVSubmit() {
                mPage = 0;
                getCVSubmit(mPage, mStatus, mCareerId, mCityId);
            }
        });
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
    protected void getArgument(Bundle bundle) {
        mStatus = bundle.getInt("status");
        mCareerId = bundle.getInt("carrerId");
        mCityId = bundle.getInt("cityId");
    }

    @Override
    protected void initData() {
        getCVSubmit(mPage, mStatus, mCareerId, mCityId);
    }

    public void getCVSubmit(final int page, int status, int carrerId, int cityId) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        searchCVSubmitRequest = new SearchCVSubmitRequest(page, status, carrerId, cityId);
        searchCVSubmitRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                cvResponse = data;
                cvListsServer.clear();
                cvListsServer.addAll(data.getCvList());
                tvCountCV.setText(data.getTotal() + " " + getResources().getString(R.string.cv_found));
                if (page == 0) cvLists.clear();
                else {
////                    jobsList.remove(jobsList.size() - 1);
                    adapter.notifyItemRemoved(cvLists.size());
                }
                cvLists.addAll(data.getCvList());
                adapter = new MyCVSubmitAdapter(recyclerView, getActivity(), cvLists, MyCVApplyedFragment.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setLoaded();
            }

            @Override
            public void onFail(int failCode, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
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
        getCVSubmit(mPage, mStatus, mCareerId, mCityId);
    }

    @Override
    public void onLoadMore() {
        if (cvListsServer.size() >= 10) {
            mPage++;
            getCVSubmit(mPage, mStatus, mCareerId, mCityId);
            adapter.setOnLoadMoreListener(MyCVApplyedFragment.this);
        }
    }
}
