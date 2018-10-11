package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.JobsAdapter;
import com.vietinterview.getbee.api.request.GetSearchJobsRequest;
import com.vietinterview.getbee.api.response.jobsresponse.JobList;
import com.vietinterview.getbee.api.response.jobsresponse.JobsResponse;
import com.vietinterview.getbee.api.volley.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.model.MyJob;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.view.ClearableRegularEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.llDatePub)
    LinearLayout llDatePub;
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
    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private ArrayList<JobList> jobsList = new ArrayList<>();
    private ArrayList<JobList> jobsListServer = new ArrayList<>();
    private GetSearchJobsRequest getSearchJobsRequest;
    int mPage = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setHasOptionsMenu(true);
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
        adapter = new JobsAdapter(recyclerView, jobsList, HomeFragment.this, getActivity());
        recyclerView.setAdapter(adapter);
        this.registerForContextMenu(llDatePub);
        edtJobTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPage = 0;
                getSearchJob("4", "", charSequence.toString(), mPage);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
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
        getSearchJob("4", "", "", mPage);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
        getSearchJob("4", "", "", mPage);
    }

    public void getSearchJob(String careerId, String cityId, String jobtile, final int page) {
        if (page == 0)
            showCoverNetworkLoading();
        getSearchJobsRequest = new GetSearchJobsRequest(careerId, cityId, "10", jobtile, page);
        getSearchJobsRequest.callRequest(new ApiObjectCallBack<JobsResponse>() {
            @Override
            public void onSuccess(final JobsResponse data) {
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
            public void onFail(int failCode, String message) {
                mSwipeRefreshLayout.setRefreshing(false);
                hideCoverNetworkLoading();
            }
        });
    }

    @OnClick(R.id.llCarrer)
    public void onllCarrerClick() {
        FragmentUtil.pushFragment(getActivity(), new CarrerFragment(), null);
    }

    @OnClick(R.id.llDatePub)
    public void onPubDateClick() {
        this.registerForContextMenu(llDatePub);
        getActivity().openContextMenu(llDatePub);
    }

    @OnClick(R.id.imgFilter)
    public void onimgFilter() {
        if (visibleCondition) {
            visibleCondition = false;
            llCondition.setVisibility(View.GONE);
        } else {
            visibleCondition = true;
            llCondition.setVisibility(View.VISIBLE);
        }
    }

    final int CONTEXT_MENU_VIEW = 1;
    final int CONTEXT_MENU_EDIT = 2;
    final int CONTEXT_MENU_ARCHIVE = 3;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Chọn ngày đăng tuyển");
        menu.add(Menu.NONE, CONTEXT_MENU_VIEW, Menu.NONE, "Mới nhất - Cũ nhất");
        menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Cũ nhất - Mới nhất");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case CONTEXT_MENU_VIEW: {

            }
            break;
            case CONTEXT_MENU_EDIT: {
                // Edit Action

            }
            break;
            case CONTEXT_MENU_ARCHIVE: {

            }
            break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onRestore() {

    }

    @Override
    protected void initialize() {
        getEventBaseFragment().showLogo(true);
    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }

    @Override
    public void onLoadMore() {
        if (jobsListServer.size() <= 10) {
            mPage++;
            DebugLog.showLogCat(mPage + " MyPage");
            getSearchJob("4", "", "", mPage);
            adapter.setOnLoadMoreListener(HomeFragment.this);
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
            FragmentUtil.pushFragment(fragmentActivity, new DetailJobFragment(), null);
        }
    }

    private Menu menu;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_second, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            if (visibleSearch) {
                visibleSearch = false;
                llSearch.setVisibility(View.GONE);
                menu.getItem(0).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search_black));
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
