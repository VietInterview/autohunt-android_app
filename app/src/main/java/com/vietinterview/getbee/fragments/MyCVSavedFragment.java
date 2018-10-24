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
import com.vietinterview.getbee.api.request.SearchCVSaveRequest;
import com.vietinterview.getbee.api.request.SearchMyCVRequest;
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
public class MyCVSavedFragment extends BaseFragment {
    @BindView(R.id.tvCountCV)
    TextView tvCountCV;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<CvList> cvLists = new ArrayList<>();
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
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        carrerId = bundle.getInt("carrerId");
        cityId = bundle.getInt("cityId");
    }

    @Override
    protected void initData() {
        if (carrerId != -1 && cityId != -1) {
            searchMyCV(carrerId, cityId);
        } else {
            getCVSaved();
        }
    }

    public void getCVSaved() {
        showCoverNetworkLoading();
        searchCVSaveRequest = new SearchCVSaveRequest(mPage);
        searchCVSaveRequest.callRequest(new ApiObjectCallBack<CVResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                tvCountCV.setText(data.getTotal() + " CV đã lưu được tìm thấy");
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

    public void searchMyCV(final int carrerId, final int cityId) {
        showCoverNetworkLoading();
        searchMyCVRequest = new SearchMyCVRequest(0, carrerId, cityId);
        searchMyCVRequest.callRequest(new ApiObjectCallBack<CVResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                tvCountCV.setText(data.getTotal() + " CV đã lưu được tìm thấy");
                if (cvLists.size() > 0) cvLists.clear();
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
    protected void onSaveState(final Bundle bundle) {
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
    }

}
