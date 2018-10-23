package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ChoiceCVAdapter;
import com.vietinterview.getbee.adapter.NotChoiceCVAdapter;
import com.vietinterview.getbee.api.request.GetListCareerRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.jobsresponse.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.NunitoBoldButton;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 10/22/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class ChoiceCVFragment extends BaseFragment {
    @BindView(R.id.list)
    public ListView listView;
    @BindView(R.id.btnDelete)
    NunitoBoldButton btnDelete;
    private ChoiceCVAdapter choiceCVAdapter;
    private NotChoiceCVAdapter notChoiceCVAdapter;
    GetListCareerRequest getListCareerRequest;
    List<CareerResponse> careerResponses;
    private boolean isEdit = false;
    private Menu menu;
    private View mView;
    private JobList mJobList;

    public static ChoiceCVFragment newInstance(JobList jobList) {
        ChoiceCVFragment fm = new ChoiceCVFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobList", jobList);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choice_cv;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        mView = root;
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        setHasOptionsMenu(true);
        getEventBaseFragment().doFillBackground("Chọn CV của tôi");
        getListCarrer();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                updateMenuTitles();
            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mJobList = bundle.getParcelable("jobList");
    }

    @Override
    protected void initData() {

    }

    public void getListCarrer() {
        showCoverNetworkLoading();
        getListCareerRequest = new GetListCareerRequest();
        getListCareerRequest.callRequest(new ApiObjectCallBack<CareerResponse>() {

            @Override
            public void onSuccess(CareerResponse data, List<CareerResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                careerResponses = dataArrayList;
                choiceCVAdapter = new ChoiceCVAdapter(getActivity(), dataArrayList);
                notChoiceCVAdapter = new NotChoiceCVAdapter(ChoiceCVFragment.this, getActivity(), dataArrayList, mJobList);
                listView.setAdapter(notChoiceCVAdapter);
            }

            @Override
            public void onFail(int failCode, CareerResponse data, List<CareerResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
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
}
