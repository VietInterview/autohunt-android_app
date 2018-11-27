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

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.CarrerAdapter;
import com.vietinterview.getbee.adapter.CityAdapter;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetListCareerRequest;
import com.vietinterview.getbee.api.request.GetListCityRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.RobotoEditText;
import com.vietinterview.getbee.customview.RobotoTextView;
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
    RobotoTextView tvHeader;
    @BindView(R.id.edtSearchJob)
    RobotoEditText edtSearchJob;
    GetListCareerRequest getListCareerRequest;
    GetListCityRequest getListCityRequest;
    CarrerAdapter carrerAdapter;
    CityAdapter cityAdapter;
    private Menu mMenu;
    private MenuItem mMenuItem;
    boolean mIsCity;
    String mId;
    String mName;
    List<CareerResponse> careerResponses = new ArrayList<>();
    List<CityResponse> cityResponses = new ArrayList<>();
    List<CareerResponse> careerResponsesFilter;
    List<CityResponse> cityResponsesFilter;
    boolean mIsSignleChoice;

    public static CarrerOrCityFragment newInstance(boolean isCity, String Id, String Name, boolean isSignleChoice) {
        CarrerOrCityFragment fm = new CarrerOrCityFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isCity", isCity);
        bundle.putString(isCity ? "cityId" : "carrerId", Id);
        bundle.putString(isCity ? "cityName" : "carrerName", Name);
        bundle.putBoolean("isSignleChoice", isSignleChoice);
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
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (i == 0) {
                    listView.setItemChecked(0, true);
                    for (int j = 1; j < listView.getCount(); j++) {
                        listView.setItemChecked(j, false);
                    }
                } else {
                    listView.setItemChecked(0, false);
//                    for (int k = 1; k < listView.getCount(); k++) {
//                        listView.setItemChecked(k, listView.getItemAtPosition(k).);
//                    }
                }
                updateMenuTitles();
            }
        });
//        getEventBaseFragment().setOnClearCheckedListener(new OnClearCheckedListener() {
//            @Override
//            public void onClearCheck() {
//                for (int i = 0; i < careerResponses.size(); i++) {
//                    listView.setItemChecked(i, false);
//                }
//            }
//        });
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
                        careerResponsesFilter = filterCarrer(careerResponses, charSequence.toString().trim());
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

    private List<CareerResponse> filterCarrer(List<CareerResponse> careerResponses, String query) {
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
        mIsSignleChoice = bundle.getBoolean("isSignleChoice");
    }

    @Override
    protected void initData() {
        if (mIsCity) {
            getListCity();
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        } else {
            if (mIsSignleChoice)
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            else listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            getListCarrer();
        }
    }

    public void getListCarrer() {
        showCoverNetworkLoading();
        getListCareerRequest = new GetListCareerRequest();
        getListCareerRequest.callRequest(getActivity(), new ApiObjectCallBack<CareerResponse, ErrorResponse>() {

            @Override
            public void onSuccess(int status, CareerResponse data, List<CareerResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                careerResponses.add(new CareerResponse(0, getResources().getString(R.string.all_carrer)));
                careerResponses.addAll(dataArrayList);
                carrerAdapter = new CarrerAdapter(getActivity(), careerResponses, CarrerOrCityFragment.this);
                listView.setAdapter(carrerAdapter);
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
            }
        });
    }

    private CareerResponse getSelectedItemCarrer() {
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        CareerResponse careerResponse = null;
        if (checkedItems.size() > 0)
            careerResponse = (CareerResponse) listView.getItemAtPosition(checkedItems.keyAt(0));
        return careerResponse;
    }

    private ArrayList<CareerResponse> getSelectedItemsCarrer() {
        ArrayList<CareerResponse> result = new ArrayList<>();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

        for (int i = 0; i < checkedItems.size(); i++) {
            if (checkedItems.size() > 0) {
                DebugLog.showLogCat(checkedItems.size() + " - " + listView.getCount() + " - " + i);
                if (checkedItems.valueAt(i)) {
                    result.add((CareerResponse) listView.getItemAtPosition(checkedItems.keyAt(i)));
                }
            }
        }

        return result;
    }

    private CityResponse getSelectedItemCity() {
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
            textView.setTextColor(getResources().getColor(R.color.iconcolor));
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Roboto-Bold.ttf");
            textView.setTypeface(font);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), CarrerOrCityFragment.class);
                    if (mIsCity) {
                        if (getSelectedItemCity() != null) {
                            intent.putExtra("cityId", getSelectedItemCity().getId());
                            intent.putExtra("cityName", getSelectedItemCity().getName());
                            getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                            FragmentUtil.popBackStack(CarrerOrCityFragment.this);
                        }
                    } else {
                        if (!mIsSignleChoice) {
                            if (getSelectedItemsCarrer() != null) {
                                if (getSelectedItemsCarrer().get(0).getId() == 0) {
                                    listView.setItemChecked(0, false);
                                    for (int i = 1; i < careerResponses.size(); i++) {
                                        listView.setItemChecked(i, true);
                                    }
                                    intent.putParcelableArrayListExtra("arrListCarrer", getSelectedItemsCarrer());
                                } else {
                                    intent.putParcelableArrayListExtra("arrListCarrer", getSelectedItemsCarrer());
                                }
                                getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                                FragmentUtil.popBackStack(CarrerOrCityFragment.this);
                            }
                        } else {
                            if (getSelectedItemCarrer() != null) {
                                intent.putExtra("carrerId", getSelectedItemCarrer().getId());
                                intent.putExtra("carrerName", getSelectedItemCarrer().getName());
                                getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                                FragmentUtil.popBackStack(CarrerOrCityFragment.this);
                            }
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
        getListCityRequest.callRequest(getActivity(), new ApiObjectCallBack<CityResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, CityResponse data, List<CityResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                cityResponses.add(new CityResponse(0, getResources().getString(R.string.all_city)));
                cityResponses.addAll(dataArrayList);
                cityAdapter = new CityAdapter(getActivity(), cityResponses);
                listView.setAdapter(cityAdapter);
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
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
            textView.setTextColor(getResources().getColor(R.color.iconcolor));
            textView.setPadding(0, 0, 16, 0);
            Typeface font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), "fonts/Roboto-Bold.ttf");
            textView.setTypeface(font);
        }
    }

    @Override
    protected void processCustomToolbar() {
//        Intent intent = new Intent(getActivity(), CarrerOrCityFragment.class);
//        intent.putExtra(mIsCity ? "cityId" : "carrerId", mId);
//        intent.putExtra(mIsCity ? "cityName" : "carrerName", mName);
//        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processOnBackPress() {
//        Intent intent = new Intent(getActivity(), CarrerOrCityFragment.class);
//        intent.putExtra(mIsCity ? "cityId" : "carrerId", mId);
//        intent.putExtra(mIsCity ? "cityName" : "carrerName", mName);
//        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_back_svg);
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        baseRequests.add(getListCareerRequest);
        baseRequests.add(getListCityRequest);
        return baseRequests;
    }
}
