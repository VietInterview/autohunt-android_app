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
import com.vietinterview.getbee.adapter.CarrerAdapter;
import com.vietinterview.getbee.adapter.CityAdapter;
import com.vietinterview.getbee.api.request.GetListCareerRequest;
import com.vietinterview.getbee.api.request.GetListCityRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.NunitoEditText;
import com.vietinterview.getbee.customview.NunitoTextView;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
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
    @BindView(R.id.edtSearchJob)
    NunitoEditText edtSearchJob;
    GetListCareerRequest getListCareerRequest;
    GetListCityRequest getListCityRequest;
    CarrerAdapter carrerAdapter;
    CityAdapter cityAdapter;
    private Menu mMenu;
    private MenuItem mMenuItem;
    boolean mIsCity;
    String mId;
    String mName;
    List<CareerResponse> careerResponses;
    List<CityResponse> cityResponses;
    List<CareerResponse> careerResponsesFilter;
    List<CityResponse> cityResponsesFilter;

    public static CarrerOrCityFragment newInstance(boolean isCity, String Id, String Name) {
        CarrerOrCityFragment fm = new CarrerOrCityFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isCity", isCity);
        bundle.putString(isCity ? "cityId" : "carrerId", Id);
        bundle.putString(isCity ? "cityName" : "carrerName", Name);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_carrers_cities;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground(!mIsCity ? getResources().getString(R.string.carrer) : getResources().getString(R.string.city));
        tvHeader.setText(mIsCity ? getResources().getString(R.string.all_city) : getResources().getString(R.string.all_carrer));
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
                if (mIsCity) {
                    if (cityResponses != null) {
                        cityResponsesFilter = filterCity(cityResponses, charSequence.toString().trim());
                        if (cityResponsesFilter.size() == 0) {
                            listView.setVisibility(View.GONE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                        }
                        cityAdapter.setFilter(cityResponsesFilter);
                    }
                } else {
                    if (careerResponses != null) {
                        careerResponsesFilter = filter(careerResponses, charSequence.toString().trim());
                        if (careerResponsesFilter.size() == 0) {
                            listView.setVisibility(View.GONE);
                        } else {
                            listView.setVisibility(View.VISIBLE);
                        }
                        carrerAdapter.setFilter(careerResponsesFilter);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<CareerResponse> filter(List<CareerResponse> careerResponses, String query) {
        final List<CareerResponse> careerResponses1 = new ArrayList<>();
        for (CareerResponse careerResponse : careerResponses) {
            final String name = careerResponse.getName().toLowerCase();
            if (name.contains(query.toLowerCase())) {
                careerResponses1.add(careerResponse);
            }
        }
        return careerResponses1;
    }

    private List<CityResponse> filterCity(List<CityResponse> cityResponses, String query) {
        final List<CityResponse> cityResponses1 = new ArrayList<>();
        for (CityResponse cityResponse : cityResponses) {
            final String name = cityResponse.getName().toLowerCase();
            if (name.contains(query.toLowerCase())) {
                cityResponses1.add(cityResponse);
            }
        }
        return cityResponses1;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mIsCity = bundle.getBoolean("isCity");
        mId = bundle.getString(mIsCity ? "cityId" : "carrerId");
        mName = bundle.getString(mIsCity ? "cityName" : "carrerName");
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
        getListCareerRequest.callRequest(getActivity(), new ApiObjectCallBack<CareerResponse>() {

            @Override
            public void onSuccess(CareerResponse data, List<CareerResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                careerResponses = dataArrayList;
                carrerAdapter = new CarrerAdapter(getActivity(), dataArrayList);
                listView.setAdapter(carrerAdapter);
            }

            @Override
            public void onFail(int failCode, CareerResponse data, List<CareerResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
    }

    private CareerResponse getSelectedItemsCarrer() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        CareerResponse careerResponse = null;
        if (checkedItems.size() > 0)
            careerResponse = (CareerResponse) listView.getItemAtPosition(checkedItems.keyAt(0));
        return careerResponse;
    }

    private CityResponse getSelectedItemsCity() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        CityResponse cityResponse = null;
        if (checkedItems.size() > 0)
            cityResponse = (CityResponse) listView.getItemAtPosition(checkedItems.keyAt(0));
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
            textView.setText(getResources().getString(R.string.choose));
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
                        if (getSelectedItemsCity() != null) {
                            intent.putExtra("cityId", getSelectedItemsCity().getId());
                            intent.putExtra("cityName", getSelectedItemsCity().getName());
                            getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                            FragmentUtil.popBackStack(CarrerOrCityFragment.this);
                        }
                    } else {
                        if (getSelectedItemsCarrer() != null) {
                            intent.putExtra("carrerId", getSelectedItemsCarrer().getId());
                            intent.putExtra("carrerName", getSelectedItemsCarrer().getName());
                            getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                            FragmentUtil.popBackStack(CarrerOrCityFragment.this);
                        }
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

    public void getListCity() {
        showCoverNetworkLoading();
        getListCityRequest = new GetListCityRequest();
        getListCityRequest.callRequest(getActivity(), new ApiObjectCallBack<CityResponse>() {
            @Override
            public void onSuccess(CityResponse data, List<CityResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                cityResponses = dataArrayList;
                cityAdapter = new CityAdapter(getActivity(), dataArrayList);
                listView.setAdapter(cityAdapter);
            }

            @Override
            public void onFail(int failCode, CityResponse data, List<CityResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
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
        Intent intent = new Intent(getActivity(), CarrerOrCityFragment.class);
        intent.putExtra(mIsCity ? "cityId" : "carrerId", mId);
        intent.putExtra(mIsCity ? "cityName" : "carrerName", mName);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processOnBackPress() {
        Intent intent = new Intent(getActivity(), CarrerOrCityFragment.class);
        intent.putExtra(mIsCity ? "cityId" : "carrerId", mId);
        intent.putExtra(mIsCity ? "cityName" : "carrerName", mName);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }
}
