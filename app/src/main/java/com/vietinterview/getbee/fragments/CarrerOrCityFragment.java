package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.CarrerAdapter;
import com.vietinterview.getbee.adapter.CityAdapter;
import com.vietinterview.getbee.api.request.GetListCareerRequest;
import com.vietinterview.getbee.api.request.GetListCityRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.NunitoTextView;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hiepnguyennghia on 10/9/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CarrerOrCityFragment extends BaseFragment {
    @BindView(R.id.list)
    public ListView listView;
    @BindView(R.id.tvHeader)
    NunitoTextView tvHeader;
    GetListCareerRequest getListCareerRequest;
    GetListCityRequest getListCityRequest;
    CarrerAdapter carrerAdapter;
    CityAdapter cityAdapter;
    private Menu mMenu;
    private MenuItem mMenuItem;
    boolean mIsCity;

    public static CarrerOrCityFragment newInstance(boolean isCity) {
        CarrerOrCityFragment fm = new CarrerOrCityFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isCity", isCity);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_carrers_cities;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground(!mIsCity ? "Ngành Nghề" : "Thành Phố");
        tvHeader.setText(mIsCity ? "Tất cả thành phố" : "Tất cả ngành nghề");
        setCustomToolbar(true);
        setHasOptionsMenu(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateMenuTitles();
            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mIsCity = bundle.getBoolean("isCity");
    }

    @Override
    protected void initData() {
        if (mIsCity) {
            getListCity();
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        } else {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            getListCarrer();
        }
    }

    public void getListCarrer() {
        showCoverNetworkLoading();
        getListCareerRequest = new GetListCareerRequest();
        getListCareerRequest.callRequest(new ApiObjectCallBack<CareerResponse>() {

            @Override
            public void onSuccess(CareerResponse data, List<CareerResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                carrerAdapter = new CarrerAdapter(getActivity(), dataArrayList);
                listView.setAdapter(carrerAdapter);
            }

            @Override
            public void onFail(int failCode, CareerResponse data, List<CareerResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
            }
        });
    }

    private CareerResponse getSelectedItemsCarrer() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        CareerResponse careerResponse = (CareerResponse) listView.getItemAtPosition(checkedItems.keyAt(0));
        return careerResponse;
    }

    private CityResponse getSelectedItemsCity() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        CityResponse cityResponse = (CityResponse) listView.getItemAtPosition(checkedItems.keyAt(0));
        return cityResponse;
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
            textView.setText("Chọn");
            textView.setPadding(0, 0, 16, 0);
            textView.setTextSize(18);
            textView.setTextColor(Color.BLACK);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Nunito-Bold.ttf");
            textView.setTypeface(font);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), CarrerOrCityFragment.class);
                    if (mIsCity) {
                        intent.putExtra("cityId", getSelectedItemsCity().getId());
                        intent.putExtra("cityName", getSelectedItemsCity().getName());
                    } else {
                        intent.putExtra("carrerId", getSelectedItemsCarrer().getId());
                        intent.putExtra("carrerName", getSelectedItemsCarrer().getName());
                    }
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                    FragmentUtil.popBackStack(CarrerOrCityFragment.this);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item = mMenuItem;
        int id = item.getItemId();
        if (id == R.id.choose) {
            Toast.makeText(getActivity(), "Choose", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getListCity() {
        showCoverNetworkLoading();
        getListCityRequest = new GetListCityRequest();
        getListCityRequest.callRequest(new ApiObjectCallBack<CityResponse>() {
            @Override
            public void onSuccess(CityResponse data, List<CityResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                cityAdapter = new CityAdapter(getActivity(), dataArrayList);
                listView.setAdapter(cityAdapter);
            }

            @Override
            public void onFail(int failCode, CityResponse data, List<CityResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
            }
        });
    }

    private void updateMenuTitles() {
        MenuItem menuItem = mMenu.findItem(R.id.choose);
        if (menuItem != null) {
            TextView textView = (TextView) menuItem.getActionView();
            textView.setText("Chọn");
            textView.setTextSize(18);
            textView.setTextColor(Color.BLACK);
            textView.setPadding(0, 0, 16, 0);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Nunito-Bold.ttf");
            textView.setTypeface(font);
        }
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
