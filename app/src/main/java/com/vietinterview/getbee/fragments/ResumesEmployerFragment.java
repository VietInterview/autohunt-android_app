package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ResumesEmployerAdapter;
import com.vietinterview.getbee.api.request.GetListCVByJobCustomerRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobcustomer.JobList;
import com.vietinterview.getbee.api.response.listcvcustomer.CvList;
import com.vietinterview.getbee.api.response.listcvcustomer.CvsCustomerResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 11/27/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ResumesEmployerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tvNodata)
    TextView tvNodata;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.edtJobTitle)
    EditText edtJobTitle;
    //    JobList mJobList;
    private int page = 0;
    private GetListCVByJobCustomerRequest getListCVByJobCustomerRequest;
    private ResumesEmployerAdapter cvsEmployerAdapter;
    private ArrayList<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();
    private int jobID;

    public static ResumesEmployerFragment newInstance(JobList jobList) {
        ResumesEmployerFragment fm = new ResumesEmployerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobList", jobList);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cv_employer;
    }

    @Override
    protected void getArgument(Bundle bundle) {
//        mJobList = bundle.getParcelable("jobList");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        setCustomToolbarVisible(true);
        GlobalDefine.currentFragment = this;
        jobID = SharedPrefUtils.getInt("jobIdCus", 0);
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.cv_list));
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (cvListsServer.size() >= ApiConstant.LIMIT) {
                        page++;
                        getResumeEmployer(page);
                        cvsEmployerAdapter.setOnLoadMoreListener(ResumesEmployerFragment.this);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        if (cvLists.size() == 0)
            getResumeEmployer(0);
        else {
            recyclerView.setAdapter(cvsEmployerAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        edtJobTitle.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            page = 0;
            getResumeEmployer(page);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void getResumeEmployer(final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        getListCVByJobCustomerRequest = new GetListCVByJobCustomerRequest(page, String.valueOf(jobID), Integer.parseInt(mStatusId), edtJobTitle.getText().toString().trim());
        getListCVByJobCustomerRequest.callRequest(getActivity(), new ApiObjectCallBack<CvsCustomerResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, CvsCustomerResponse data, List<CvsCustomerResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    cvListsServer.clear();
                    cvListsServer.addAll(data.getCvList());
                    tvNodata.setVisibility(data.getTotal() == 0 ? View.VISIBLE : View.GONE);
                    if (page == 0) cvLists.clear();
                    else {
                        cvsEmployerAdapter.notifyItemRemoved(cvLists.size());
                    }
                    cvLists.addAll(data.getCvList());
                    if (page == 0) {
                        cvsEmployerAdapter = new ResumesEmployerAdapter(recyclerView, getActivity(), data.getTotal(), cvLists, jobID, ResumesEmployerFragment.this);
                        recyclerView.setAdapter(cvsEmployerAdapter);
                    }
                    cvsEmployerAdapter.setOnLoadMoreListener(ResumesEmployerFragment.this);
                    cvsEmployerAdapter.notifyDataSetChanged();
                    cvsEmployerAdapter.setLoaded();
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
        if (visibleSearch) {
            llSearch.setVisibility(View.VISIBLE);
        } else llSearch.setVisibility(View.GONE);
        mStatusName = mStatusName.equalsIgnoreCase("") ? getResources().getString(R.string.default_key) : mStatusName;
        tvStatus.setText(mStatusName);
        tvStatus.setTextColor(mStatusName.equalsIgnoreCase(getResources().getString(R.string.default_key)) ? getResources().getColor(R.color.background_icon_not_focus) : getResources().getColor(R.color.black));

    }

    @Override
    protected void onSaveState(Bundle bundle) {
        bundle.putBoolean(AppConstant.VISIBLE_SEARCH, visibleSearch);
        bundle.putString("mStatusName", mStatusName);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        visibleSearch = bundle.getBoolean(AppConstant.VISIBLE_SEARCH);
        mStatusName = bundle.getString("mStatusName");
    }

    @Override
    protected void onRestore() {

    }

    private String mStatusId = "11";
    private String mStatusName = "";

    @OnClick(R.id.llStatus)
    public void onllStatusClick() {
        visibleSearch = true;
        FragmentUtil.pushFragment(getActivity(), this, new StatusFragment().newInstance(mStatusId, mStatusName), null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                mStatusId = String.valueOf(data.getIntExtra("statusId", Integer.parseInt(mStatusId)));
                mStatusName = data.getStringExtra("statusName");
                tvStatus.setText(mStatusName);
                page = 0;
                getResumeEmployer(page);
            }
        }
    }

    private boolean visibleSearch = false;
    private Menu menu;
    private MenuItem mItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (visibleSearch)
            inflater.inflate(R.menu.menu_main, menu);
        else inflater.inflate(R.menu.menu_home_search, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mItem = item;
        int id = mItem.getItemId();
        if (id == R.id.search || id == R.id.save) {
            if (visibleSearch) {
                visibleSearch = false;
                llSearch.setVisibility(View.GONE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search));
            } else {
                visibleSearch = true;
                llSearch.setVisibility(View.VISIBLE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_saveok));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        page = 0;
        getResumeEmployer(page);
    }

    @Override
    public void onLoadMore() {
        if (cvListsServer.size() >= ApiConstant.LIMIT) {
            page++;
            getResumeEmployer(page);
            cvsEmployerAdapter.setOnLoadMoreListener(this);
        }
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
    protected void processCustomToolbar() {
        FragmentUtil.popBackStack(this);
    }
}