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
import com.vietinterview.getbee.api.request.SearchCVSaveRequest;
import com.vietinterview.getbee.api.request.SearchMyCVRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.callback.OnRefreshMyCVSavedListener;
import com.vietinterview.getbee.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class MyCVSavedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.tvCountCV)
    TextView tvCountCV;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();
    private MyCVAdapter adapter;
    private SearchCVSaveRequest searchCVSaveRequest;
    private int mPage = 0;
    private SearchMyCVRequest searchMyCVRequest;
    private int carrerId = -1;
    private int cityId = -1;

    public static MyCVSavedFragment newInstance(int carrerId, int cityId) {
        MyCVSavedFragment fm = new MyCVSavedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("carrerId", carrerId);
        bundle.putInt("cityId", cityId);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycv_saved;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().setOnRefreshMyCVListener(new OnRefreshMyCVSavedListener() {
            @Override
            public void onRefreshMyCV() {
                mPage = 0;
                if (carrerId != -1 && cityId != -1) {
                    searchMyCV(carrerId, cityId, mPage);
                } else {
                    getCVSaved(mPage);
                }
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
        carrerId = bundle.getInt("carrerId");
        cityId = bundle.getInt("cityId");
    }

    @Override
    protected void initData() {
        if (carrerId != -1 && cityId != -1) {
            searchMyCV(carrerId, cityId, mPage);
        } else {
            getCVSaved(mPage);
        }
    }

    public void getCVSaved(final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        searchCVSaveRequest = new SearchCVSaveRequest(page);
        searchCVSaveRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse,ErrorResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                cvListsServer.clear();
                cvListsServer.addAll(data.getCvList());
                tvCountCV.setText(data.getTotal() + " " + getResources().getString(R.string.cv_found));
                if (page == 0) cvLists.clear();
                else {
////                    jobsList.remove(jobsList.size() - 1);
                    adapter.notifyItemRemoved(cvLists.size());
                }
                cvLists.addAll(data.getCvList());
                adapter = new MyCVAdapter(recyclerView, getActivity(), cvLists, MyCVSavedFragment.this);
                adapter.setOnLoadMoreListener(MyCVSavedFragment.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setLoaded();
            }

            @Override
            public void onFail(int failCode, CVResponse data,ErrorResponse errorResponse, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
    }

    public void searchMyCV(final int carrerId, final int cityId, final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        searchMyCVRequest = new SearchMyCVRequest(page, carrerId, cityId);
        searchMyCVRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse, ErrorResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                cvListsServer.clear();
                cvListsServer.addAll(data.getCvList());
                tvCountCV.setText(data.getTotal() + " " + getResources().getString(R.string.cv_saved_found));
                if (page == 0) cvLists.clear();
                else {
//                    jobsList.remove(jobsList.size() - 1);
                    adapter.notifyItemRemoved(cvLists.size());
                }
                cvLists.addAll(data.getCvList());
                adapter = new MyCVAdapter(recyclerView, getActivity(), cvLists, MyCVSavedFragment.this);
                adapter.setOnLoadMoreListener(MyCVSavedFragment.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setLoaded();
            }

            @Override
            public void onFail(int failCode, CVResponse data, ErrorResponse errorResponse, List<CVResponse> dataArrayList, String message) {
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
    protected void onSaveState(final Bundle bundle) {
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
    }

    @Override
    public void onRefresh() {
        mPage = 0;
        if (carrerId != -1 && cityId != -1) {
            searchMyCV(carrerId, cityId, mPage);
        } else {
            getCVSaved(mPage);
        }
    }

    @Override
    public void onLoadMore() {
        if (cvListsServer.size() >= 10) {
            mPage++;
            if (carrerId != -1 && cityId != -1) {
                searchMyCV(carrerId, cityId, mPage);
            } else {
                getCVSaved(mPage);
            }
            adapter.setOnLoadMoreListener(MyCVSavedFragment.this);
        }
    }
}
