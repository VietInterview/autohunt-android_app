package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.CreateNewCVActivity;
import com.vietinterview.getbee.adapter.JobsAdapter;
import com.vietinterview.getbee.api.request.GetSearchJobsRequest;
import com.vietinterview.getbee.api.response.jobsresponse.JobList;
import com.vietinterview.getbee.api.response.jobsresponse.JobsResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.callback.OnRefreshHomeListener;
import com.vietinterview.getbee.constant.AppConstant;
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
    //    @BindView(R.id.llDatePub)
//    LinearLayout llDatePub;
    @BindView(R.id.titleHeader)
    TextView titleHeader;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.edtJobTitle)
    ClearableRegularEditText edtJobTitle;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean visibleSearch = false;
    private boolean visibleCondition = false;
    public JobsAdapter adapter;
    //    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetSearchJobsRequest getSearchJobsRequest;
    int mPage = 0;
    private boolean mIsCity = false;
    private String mCarrerId = "4";
    private String mCarrerName = "IT, Phần mềm";
    private String mCityId = "1";
    private String mCityName = "Hà Nội";
    private String strSearch = "";

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
        myOnClickListener = new MyOnClickListener(getActivity());
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
                    fab.hide();
                } else {
                    titleHeader.setVisibility(View.VISIBLE);
                    fab.show();
                }
            }
        });
        adapter = new JobsAdapter(recyclerView, jobsList, HomeFragment.this, getActivity());
        recyclerView.setAdapter(adapter);
//        this.registerForContextMenu(llDatePub);
        tvCityName.setText(mCityName);
        tvCarrerName.setText(mCarrerName);
        edtJobTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPage = 0;
                strSearch = charSequence.toString();
                getSearchJob(mCarrerId, mCityId, charSequence.toString(), mPage);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        adapter.setOnLoadMoreListener(HomeFragment.this);
    }

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
        if (!visibleSearch)
            getSearchJob(mCarrerId, mCityId, strSearch, mPage);
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        startActivity(new Intent(getActivity(), CreateNewCVActivity.class));
//        FragmentUtil.pushFragment(getActivity(), this, new CreateNewCVFragment(), null);
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
//                    jobsList.remove(jobsList.size() - 1);
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

    @OnClick(R.id.llCarrer)
    public void onllCarrerClick() {
        mIsCity = false;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(false), null);
    }

    @OnClick(R.id.llAdd)
    public void onllAddClick() {
        mIsCity = true;
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(true), null);
    }

    //    @OnClick(R.id.llDatePub)
//    public void onPubDateClick() {
//        this.registerForContextMenu(llDatePub);
//        getActivity().openContextMenu(llDatePub);
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstant.FRAGMENT_CODE) {
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

    final int CONTEXT_MENU_VIEW = 1;
    final int CONTEXT_MENU_EDIT = 2;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
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
    }

    @Override
    protected void onSaveState(Bundle bundle) {
        bundle.putBoolean(AppConstant.VISIBLE_SEARCH, visibleSearch);
        bundle.putBoolean(AppConstant.VISIBLE_CONDITION, visibleCondition);
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
        visibleCondition = bundle.getBoolean(AppConstant.VISIBLE_CONDITION);
        visibleSearch = bundle.getBoolean(AppConstant.VISIBLE_SEARCH);
    }


    @Override
    protected void onRestore() {
        DebugLog.showLogCat("onRestore");
    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() >= 10) {
            mPage++;
            getSearchJob(mCarrerId, mCityId, strSearch, mPage);
            adapter.setOnLoadMoreListener(HomeFragment.this);
        }
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final FragmentActivity fragmentActivity;

        private MyOnClickListener(FragmentActivity fragmentActivity) {
            this.fragmentActivity = fragmentActivity;
        }

        @Override
        public void onClick(View v) {
            DebugLog.showLogCat(v.getVerticalScrollbarPosition() + "");
        }
    }

    private Menu menu;
    MenuItem mItem;

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
                visibleCondition = true;
                llSearch.setVisibility(View.VISIBLE);
                llCondition.setVisibility(View.VISIBLE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_saveok));
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
}
