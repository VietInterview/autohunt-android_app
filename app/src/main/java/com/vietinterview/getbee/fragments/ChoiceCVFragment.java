package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyCVChoiceAdapter;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.SearchMyCVRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.customview.NunitoBoldButton;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/22/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ChoiceCVFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.btnDelete)
    NunitoBoldButton btnDelete;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MyCVChoiceAdapter myCVChoiceAdapter;
    private SearchMyCVRequest searchMyCVRequest;
    private CVResponse mCvResponse;
    private DetailJobResponse detailJobResponse;
    private int mPage = 0;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();

    public static ChoiceCVFragment newInstance(DetailJobResponse detailJobResponse, CVResponse cvResponse) {
        ChoiceCVFragment fm = new ChoiceCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailJobResponse", detailJobResponse);
        bundle.putParcelable("cvResponse", cvResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailJobResponse = bundle.getParcelable("detailJobResponse");
        mCvResponse = bundle.getParcelable("cvResponse");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choice_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.choice_cv));
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mPage = 0;
//        searchMyCV(0, 0, mPage);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }


    @Override
    protected void initData() {
        cvListsServer.clear();
        cvListsServer.addAll(mCvResponse.getCvList());
        cvLists.clear();
        cvLists.addAll(mCvResponse.getCvList());
        myCVChoiceAdapter = new MyCVChoiceAdapter(recyclerView, getActivity(), mCvResponse.getTotal(), detailJobResponse, cvLists, ChoiceCVFragment.this);
        myCVChoiceAdapter.setOnLoadMoreListener(ChoiceCVFragment.this);
        recyclerView.setAdapter(myCVChoiceAdapter);
    }

    public void searchMyCV(final int carrerId, final int cityId, final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        searchMyCVRequest = new SearchMyCVRequest(page, carrerId, cityId);
        searchMyCVRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    cvListsServer.clear();
                    cvListsServer.addAll(data.getCvList());
                    if (page == 0) cvLists.clear();
                    else myCVChoiceAdapter.notifyItemRemoved(cvLists.size());
                    cvLists.addAll(data.getCvList());
                    if (page == 0) {
                        myCVChoiceAdapter = new MyCVChoiceAdapter(recyclerView, getActivity(), data.getTotal(), detailJobResponse, cvLists, ChoiceCVFragment.this);
                        myCVChoiceAdapter.setOnLoadMoreListener(ChoiceCVFragment.this);
                        recyclerView.setAdapter(myCVChoiceAdapter);
                    }
                    myCVChoiceAdapter.notifyDataSetChanged();
                    myCVChoiceAdapter.setLoaded();
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
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processOnBackPress() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }

    @Override
    public void onRefresh() {
        mPage = 0;
        searchMyCV(0, 0, mPage);
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        baseRequests.add(searchMyCVRequest);
        return baseRequests;
    }

    @Override
    public void onLoadMore() {
        if (cvListsServer.size() >= ApiConstant.LIMIT) {
            mPage++;
            searchMyCV(0, 0, mPage);
            myCVChoiceAdapter.setOnLoadMoreListener(ChoiceCVFragment.this);
        }
    }
}
