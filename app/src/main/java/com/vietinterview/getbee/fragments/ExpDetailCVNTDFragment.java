package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ExpDetailCVNTDAdapter;
import com.vietinterview.getbee.api.request.SearchMyCVRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 11/29/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ExpDetailCVNTDFragment extends BaseFragment implements OnLoadMoreListener {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    private ExpDetailCVNTDAdapter expDetailCVNTDAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_exp_cv_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        searchMyCV(0, 0, 0);
    }

    private SearchMyCVRequest searchMyCVRequest;
    private int mPage = 0;
    private DetailJobResponse detailJobResponse;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();

    public void searchMyCV(final int carrerId, final int cityId, final int page) {
        if (page == 0)
            showCoverNetworkLoading();
        searchMyCVRequest = new SearchMyCVRequest(page, carrerId, cityId);
        searchMyCVRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    cvListsServer.clear();
                    cvListsServer.addAll(data.getCvList());
                    if (page == 0) cvLists.clear();
                    else expDetailCVNTDAdapter.notifyItemRemoved(cvLists.size());
                    cvLists.addAll(data.getCvList());
                    if (page == 0) {
                        expDetailCVNTDAdapter = new ExpDetailCVNTDAdapter(recyclerView, getActivity(), data.getTotal(), detailJobResponse, cvLists, ExpDetailCVNTDFragment.this);
                        expDetailCVNTDAdapter.setOnLoadMoreListener(ExpDetailCVNTDFragment.this);
                        recyclerView.setAdapter(expDetailCVNTDAdapter);
                    }
                    expDetailCVNTDAdapter.notifyDataSetChanged();
                    expDetailCVNTDAdapter.setLoaded();
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                }
            }
        });
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
    protected void onRestore() {

    }

    @Override
    public void onLoadMore() {
        if (cvListsServer.size() >= ApiConstant.LIMIT) {
            mPage++;
            searchMyCV(0, 0, mPage);
            expDetailCVNTDAdapter.setOnLoadMoreListener(ExpDetailCVNTDFragment.this);
        }
    }
}
