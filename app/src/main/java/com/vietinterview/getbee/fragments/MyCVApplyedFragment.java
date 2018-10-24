package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyCVAdapter;
import com.vietinterview.getbee.api.request.SearchCVSubmitRequest;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class MyCVApplyedFragment extends BaseFragment {
    @BindView(R.id.tvCountCV)
    TextView tvCountCV;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private MyCVAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SearchCVSubmitRequest searchCVSubmitRequest;
    private int mPage = 0;
    private int mStatus = 11;
    CVResponse cvResponse;
    boolean isSearching = false;

    public static MyCVApplyedFragment newInstance(int status) {
        MyCVApplyedFragment fm = new MyCVApplyedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycv_applyed;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mStatus = bundle.getInt("status");
    }

    @Override
    protected void initData() {
        getCVSubmit(mPage, mStatus);
    }

    public void getCVSubmit(int page, int status) {
        showCoverNetworkLoading();
        searchCVSubmitRequest = new SearchCVSubmitRequest(page, status);
        searchCVSubmitRequest.callRequest(new ApiObjectCallBack<CVResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                cvResponse = data;
                tvCountCV.setText(data.getTotal() + " CV đã nộp được tìm thấy");
                cvLists.addAll(data.getCvList());
                adapter = new MyCVAdapter(getActivity(), cvLists);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(int failCode, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), "Thông báo", message);
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


}
