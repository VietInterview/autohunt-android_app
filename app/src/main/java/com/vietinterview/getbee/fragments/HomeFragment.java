package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.JobsAdapter;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetSearchJobsRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.api.response.jobs.JobsResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.callback.OnRefreshHomeListener;
import com.vietinterview.getbee.constant.ApiConstant;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.ClearableRegularEditText;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by nguyennghiahiep on 10/5/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.llCondition)
    LinearLayout llCondition;
    @BindView(R.id.imgFilter)
    ImageView imgFilter;
    @BindView(R.id.tvCarrerName)
    TextView tvCarrerName;
    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.edtJobTitle)
    ClearableRegularEditText edtJobTitle;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean visibleSearch = false;
    private boolean visibleCondition = false;
    private JobsAdapter jobsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetSearchJobsRequest getSearchJobsRequest;
    private int mPage = 0;
    private int mTotal;
    private boolean mIsCity = false;
    private String mCarrerId = "0";
    private String mCarrerName = "";
    private String mCityId = "0";
    private String mCityName = "";
    private String strSearch = "";
    private Menu menu;
    private MenuItem mItem;
    final int CONTEXT_MENU_VIEW = 1;
    final int CONTEXT_MENU_EDIT = 2;

    public static HomeFragment newInstance(String nameFragment) {
        HomeFragment fm = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nameFragment", nameFragment);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().setOnRefreshHomeListener(new OnRefreshHomeListener() {
            @Override
            public void onRefresh() {
                mPage = 0;
                getSearchJob(mCarrerId, mCityId, strSearch, mPage);
            }
        });
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
                } else {
                }
            }
        });
        edtJobTitle.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edtJobTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getSearchJob(mCarrerId, mCityId, strSearch, mPage);
                }
                return false;
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPage = 0;
            strSearch = s.toString();
            getSearchJob(mCarrerId, mCityId, strSearch, mPage);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void onRefresh() {
        mPage = 0;
        getSearchJob(mCarrerId, mCityId, strSearch, mPage);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalDefine.currentFragment = this;
        edtJobTitle.addTextChangedListener(textWatcher);
        if (jobsList.size() == 0)
            getSearchJob(mCarrerId, mCityId, strSearch, mPage);
        else {
            recyclerView.setAdapter(jobsAdapter);
        }
    }

    public void getSearchJob(String careerId, String cityId, String jobtile, final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();

        getSearchJobsRequest = new GetSearchJobsRequest(careerId, cityId, ApiConstant.LIMIT, jobtile, page);
        getSearchJobsRequest.callRequest(getActivity(), new ApiObjectCallBack<JobsResponse, ErrorResponse>() {

            @Override
            public void onSuccess(int status, JobsResponse data, List<JobsResponse> dataArrayList, String message) {
                if (isAdded()) {
                    jobsListServer.clear();
                    jobsListServer.addAll(data.getJobList());
                    mSwipeRefreshLayout.setRefreshing(false);
                    hideCoverNetworkLoading();
                    if (page == 0) jobsList.clear();
                    else jobsAdapter.notifyItemRemoved(jobsList.size());
                    mTotal = data.getTotal();
                    jobsList.addAll(data.getJobList());
                    if (page == 0) {
                        jobsAdapter = new JobsAdapter(recyclerView, jobsList, mTotal, HomeFragment.this, getActivity());
                        recyclerView.setAdapter(jobsAdapter);
                    }
                    jobsAdapter.setOnLoadMoreListener(HomeFragment.this);
                    jobsAdapter.notifyDataSetChanged();
                    jobsAdapter.setLoaded();
                } else {
                    if (page == 0) jobsList.clear();
                    mTotal = data.getTotal();
                    jobsList.addAll(data.getJobList());
                    if (jobsAdapter == null) {
                        jobsAdapter = new JobsAdapter(recyclerView, jobsList, mTotal, HomeFragment.this, getActivity());
                        recyclerView.setAdapter(jobsAdapter);
                    }
                    jobsAdapter.setOnLoadMoreListener(HomeFragment.this);
                    jobsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                if (isAdded()) {
                    if (mSwipeRefreshLayout != null)
                        mSwipeRefreshLayout.setRefreshing(false);
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                }
            }
        });
    }

    @OnClick(R.id.llCarrer)
    public void onllCarrerClick() {
        mIsCity = false;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(false, mCarrerId, mCarrerName), null);
    }

    @OnClick(R.id.llAdd)
    public void onllAddClick() {
        mIsCity = true;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(true, mCityId, mCityName), null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
                jobsList.clear();
                if (mIsCity) {
                    mCityId = String.valueOf(data.getIntExtra("cityId", 0));
                    mCityName = data.getStringExtra("cityName");
                    tvCityName.setText(mCityName);
                } else {
                    mCarrerId = String.valueOf(data.getIntExtra("carrerId", 0));
                    mCarrerName = data.getStringExtra("carrerName");
                    tvCarrerName.setText(mCarrerName);
                }
            }
        }
    }

    @OnClick(R.id.imgFilter)
    public void onimgFilter() {
        if (visibleCondition) {
            visibleCondition = false;
            llCondition.setVisibility(View.GONE);
            imgFilter.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter));
        } else {
            visibleCondition = true;
            llCondition.setVisibility(View.VISIBLE);
            imgFilter.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter_black));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Chọn ngày đăng tuyển");
        menu.add(Menu.NONE, CONTEXT_MENU_VIEW, Menu.NONE, "Mới nhất - Cũ nhất");
        menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Cũ nhất - Mới nhất");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CONTEXT_MENU_VIEW: {

            }
            break;
            case CONTEXT_MENU_EDIT: {

            }
            break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void initialize() {
        getEventBaseFragment().showLogo(true);
        if (visibleSearch) {
            llSearch.setVisibility(View.VISIBLE);
        } else {
            llSearch.setVisibility(View.GONE);
        }
        if (visibleCondition) {
            llCondition.setVisibility(View.VISIBLE);
        } else {
            llCondition.setVisibility(View.GONE);
        }
        mCityName = mCityName.equalsIgnoreCase("") ? getResources().getString(R.string.all_city) : mCityName;
        mCarrerName = mCarrerName.equalsIgnoreCase("") ? getResources().getString(R.string.all_carrer) : mCarrerName;
        tvCityName.setText(mCityName);
        tvCarrerName.setText(mCarrerName);
        tvCityName.setTextColor(mCityName.equalsIgnoreCase(getResources().getString(R.string.all_city)) ? getResources().getColor(R.color.background_icon_not_focus) : getResources().getColor(R.color.black));
        tvCarrerName.setTextColor(mCarrerName.equalsIgnoreCase(getResources().getString(R.string.all_carrer)) ? getResources().getColor(R.color.background_icon_not_focus) : getResources().getColor(R.color.black));
    }

    @Override
    protected void onSaveState(Bundle bundle) {
        bundle.putBoolean(AppConstant.VISIBLE_SEARCH, visibleSearch);
        bundle.putBoolean(AppConstant.VISIBLE_CONDITION, visibleCondition);
        bundle.putString("mCityName", mCityName);
        bundle.putString("mCarrerName", mCarrerName);
        bundle.putParcelableArrayList("jobsList", jobsList);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        visibleCondition = bundle.getBoolean(AppConstant.VISIBLE_CONDITION);
        visibleSearch = bundle.getBoolean(AppConstant.VISIBLE_SEARCH);
        mCarrerName = bundle.getString("mCarrerName");
        mCityName = bundle.getString("mCityName");
        jobsList = bundle.getParcelableArrayList("jobsList");
    }


    @Override
    protected void onRestore() {
        DebugLog.showLogCat("onRestore");
        getEventBaseFragment().showLogo(true);
    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() >= ApiConstant.LIMIT) {
            mPage++;
            getSearchJob(mCarrerId, mCityId, strSearch, mPage);
            jobsAdapter.setOnLoadMoreListener(HomeFragment.this);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (visibleSearch)
            inflater.inflate(R.menu.menu_home_saved, menu);
        else inflater.inflate(R.menu.menu_home_search, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mItem = item;
        int id = mItem.getItemId();
        if (id == R.id.search) {
            if (visibleSearch) {
                visibleSearch = false;
                visibleCondition = false;
                llSearch.setVisibility(View.GONE);
                llCondition.setVisibility(View.GONE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search_black));
            } else {
                visibleSearch = true;
                visibleCondition = false;
                llSearch.setVisibility(View.VISIBLE);
                llCondition.setVisibility(View.GONE);
                imgFilter.setImageDrawable(getResources().getDrawable(R.drawable.ic_filter));
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_close));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void processOnBackPress() {
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_menu);
    }

    @Override
    protected void processCustomToolbar() {
        loadMenuLeft();
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        baseRequests.add(getSearchJobsRequest);
        return baseRequests;
    }
}
