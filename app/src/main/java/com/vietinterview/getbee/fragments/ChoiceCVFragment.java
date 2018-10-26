package com.vietinterview.getbee.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ChoiceCVAdapter;
import com.vietinterview.getbee.adapter.MyCVAdapter;
import com.vietinterview.getbee.adapter.NotChoiceCVAdapter;
import com.vietinterview.getbee.api.request.GetListCareerRequest;
import com.vietinterview.getbee.api.request.SearchCVSaveRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.customview.NunitoBoldButton;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/22/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ChoiceCVFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.list)
    public ListView listView;
    @BindView(R.id.btnDelete)
    NunitoBoldButton btnDelete;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tvCount)
    TextView tvCount;
    private ChoiceCVAdapter choiceCVAdapter;
    private NotChoiceCVAdapter notChoiceCVAdapter;
    private SearchCVSaveRequest searchCVSaveRequest;
    private boolean isEdit = false;
    private Menu menu;
    private JobList mJobList;
    private int mPage = 0;
    private ProgressBar progressBar;
    private List<CvList> cvLists = new ArrayList<>();
    private ArrayList<CvList> cvListsServer = new ArrayList<>();
    CVResponse mCvResponse;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            addMoreItems();
//            Log.d("asyncstatus", "status = " + asyncTaskWait.getStatus().name());
        }
    };

    public static ChoiceCVFragment newInstance(JobList jobList) {
        ChoiceCVFragment fm = new ChoiceCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobList", jobList);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mJobList = bundle.getParcelable("jobList");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choice_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
//        setHasOptionsMenu(true);
        getEventBaseFragment().doFillBackground("Chọn CV của tôi");
        getCVSaved(mPage);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        setListViewFooter();
        setListOnScrollListener();
    }

    private void setListViewFooter() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.footer_listview_progressbar, null);
        progressBar = view.findViewById(R.id.progressBar);
        listView.addFooterView(progressBar);
    }

    private void setListOnScrollListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                DebugLog.showLogCat(listView.getLastVisiblePosition() + "");
                if (scrollState == SCROLL_STATE_IDLE && listView.getLastVisiblePosition() == cvLists.size()) {
                    if (cvListsServer.size() >= 10) {
                        mPage++;
                        getCVSaved(mPage);
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override
    protected void initData() {

    }

    public void getCVSaved(final int page) {
        if (page == 0 && !mSwipeRefreshLayout.isRefreshing())
            showCoverNetworkLoading();
        searchCVSaveRequest = new SearchCVSaveRequest(page);
        searchCVSaveRequest.callRequest(new ApiObjectCallBack<CVResponse>() {
            @Override
            public void onSuccess(CVResponse data, List<CVResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                tvCount.setText(data.getTotal() + " CV được tìm thấy");
                mSwipeRefreshLayout.setRefreshing(false);
                mCvResponse = data;
                cvListsServer.clear();
                cvListsServer.addAll(data.getCvList());
                if (page == 0) cvLists.clear();
                cvLists.addAll(data.getCvList());
                choiceCVAdapter = new ChoiceCVAdapter(getActivity(), cvLists);
                notChoiceCVAdapter = new NotChoiceCVAdapter(ChoiceCVFragment.this, getActivity(), cvLists, mJobList);
                if (isEdit) {
                    listView.setAdapter(choiceCVAdapter);
                } else {
                    listView.setAdapter(notChoiceCVAdapter);
                }
                choiceCVAdapter.notifyDataSetChanged();
                notChoiceCVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(int failCode, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                DialogUtil.showDialog(getActivity(), "Thông báo", message);
            }
        });
    }

    private List<CareerResponse> getSelectedItems() {
        List<CareerResponse> result = new ArrayList<CareerResponse>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < listView.getCount(); i++) {
            if (checkedItems.valueAt(i)) {
                result.add((CareerResponse) listView.getItemAtPosition(checkedItems.keyAt(i)));
            }
        }

        return result;
    }

    @OnClick(R.id.btnDelete)
    public void onDeleteClick() {
        DebugLog.showLogCat(getSelectedItems().size() + "");
        for (int i = 0; i < getSelectedItems().size(); i++)
            DebugLog.showLogCat(getSelectedItems().get(i).getName());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_choice_cv, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit) {
            if (isEdit) {
                isEdit = false;
                for (int i = 0; i < listView.getCount(); i++) {
                    listView.setItemChecked(i, false);
                }
                listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
                listView.setAdapter(notChoiceCVAdapter);
                btnDelete.setVisibility(View.GONE);
                menu.getItem(0).setTitle("Sửa");
            } else {
                isEdit = true;
                listView.setAdapter(choiceCVAdapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                btnDelete.setVisibility(View.VISIBLE);
                menu.getItem(0).setTitle("Hủy");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        getCVSaved(mPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("result");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }
}
