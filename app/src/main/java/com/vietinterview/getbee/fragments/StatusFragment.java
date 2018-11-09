package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.StatusAdapter;
import com.vietinterview.getbee.customview.NunitoEditText;
import com.vietinterview.getbee.customview.NunitoTextView;
import com.vietinterview.getbee.model.CVStatus;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/9/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class StatusFragment extends BaseFragment {
    @BindView(R.id.list)
    public ListView listView;
    @BindView(R.id.tvHeader)
    NunitoTextView tvHeader;
    @BindView(R.id.edtSearchJob)
    NunitoEditText edtSearchJob;
    StatusAdapter statusAdapter;
    private Menu mMenu;
    private MenuItem mMenuItem;
    String mId;
    String mName;
    List<CVStatus> cvStatuses;
    List<CVStatus> cvStatusesFilter;

    public static StatusFragment newInstance(String Id, String Name) {
        StatusFragment fm = new StatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString("statusId", Id);
        bundle.putString("statusName", Name);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_carrers_cities;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.status));
        tvHeader.setText(getResources().getString(R.string.all_status));
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateMenuTitles();
            }
        });
        edtSearchJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (cvStatuses != null) {
                    cvStatusesFilter = filter(cvStatuses, charSequence.toString().trim());
                    if (cvStatusesFilter.size() == 0) {
                        listView.setVisibility(View.GONE);
                    } else {
                        listView.setVisibility(View.VISIBLE);
                    }
                    statusAdapter.setFilter(cvStatusesFilter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<CVStatus> filter(List<CVStatus> cvStatuses, String query) {
        final List<CVStatus> cvStatuses1 = new ArrayList<>();
        for (CVStatus cvStatus : cvStatuses) {
            final String name = cvStatus.getStatusName().toLowerCase();
            if (name.contains(query.toLowerCase())) {
                cvStatuses1.add(cvStatus);
            }
        }
        return cvStatuses1;
    }


    @Override
    protected void getArgument(Bundle bundle) {
        mId = bundle.getString("statusId");
        mName = bundle.getString("statusName");
    }

    @Override
    protected void initData() {
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        cvStatuses = new ArrayList<>();
//        cvStatuses.add(new CVStatus(0, getResources().getString(R.string.not_save)));
//        cvStatuses.add(new CVStatus(1, getResources().getString(R.string.saved)));
        cvStatuses.add(new CVStatus(2, getResources().getString(R.string.sent)));
        cvStatuses.add(new CVStatus(3, getResources().getString(R.string.seen)));
        cvStatuses.add(new CVStatus(4, getResources().getString(R.string.not_accept)));
        cvStatuses.add(new CVStatus(5, getResources().getString(R.string.invite_interview)));
        cvStatuses.add(new CVStatus(6, getResources().getString(R.string.interviewed)));
        cvStatuses.add(new CVStatus(7, getResources().getString(R.string.offered)));
        cvStatuses.add(new CVStatus(8, getResources().getString(R.string.go_to_work)));
        cvStatuses.add(new CVStatus(9, getResources().getString(R.string.contract)));
        cvStatuses.add(new CVStatus(11, getResources().getString(R.string.default_key)));
        cvStatusesFilter = cvStatuses;
        statusAdapter = new StatusAdapter(getActivity(), cvStatuses);
        listView.setAdapter(statusAdapter);
    }


    private CVStatus getSelectedItemsStatus() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        CVStatus cvStatus = null;
        if (checkedItems.size() > 0)
            cvStatus = (CVStatus) listView.getItemAtPosition(checkedItems.keyAt(0));
        return cvStatus;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.mMenu = menu;
        inflater.inflate(R.menu.menu_jobs, mMenu);
        MenuItem menuItem = mMenu.findItem(R.id.choose);
        mMenuItem = menuItem;
        if (menuItem != null) {
            TextView textView = (TextView) menuItem.getActionView();
            textView.setText(getResources().getString(R.string.choose));
            textView.setPadding(0, 0, 16, 0);
            textView.setTextSize(18);
            textView.setTextColor(Color.BLACK);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Nunito-Bold.ttf");
            textView.setTypeface(font);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getSelectedItemsStatus() != null) {
                        Intent intent = new Intent(getActivity(), StatusFragment.class);
                        intent.putExtra("statusId", getSelectedItemsStatus().getStatusId());
                        intent.putExtra("statusName", getSelectedItemsStatus().getStatusName());
                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                        FragmentUtil.popBackStack(StatusFragment.this);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item = mMenuItem;
        int id = item.getItemId();
        if (id == R.id.choose) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateMenuTitles() {
        MenuItem menuItem = mMenu.findItem(R.id.choose);
        if (menuItem != null) {
            TextView textView = (TextView) menuItem.getActionView();
            textView.setText(getResources().getString(R.string.choose));
            textView.setTextSize(18);
            textView.setTextColor(Color.BLACK);
            textView.setPadding(0, 0, 16, 0);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Nunito-Bold.ttf");
            textView.setTypeface(font);
        }
    }

    @Override
    protected void processCustomToolbar() {
        Intent intent = new Intent(getActivity(), StatusFragment.class);
        intent.putExtra("statusId", mId);
        intent.putExtra("statusName", mName);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processOnBackPress() {
        Intent intent = new Intent(getActivity(), StatusFragment.class);
        intent.putExtra("statusId", mId);
        intent.putExtra("statusName", mName);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }
}
