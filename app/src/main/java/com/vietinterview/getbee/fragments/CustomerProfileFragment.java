package com.vietinterview.getbee.fragments;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.HorizontalAdapter;
import com.vietinterview.getbee.api.request.GetCUSProfileRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.customerprofile.ProfileCustomerResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.customview.FlowLayout;
import com.vietinterview.getbee.model.ImageCustomer;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.LayoutUtils;
import com.vietinterview.getbee.utils.ShowImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CustomerProfileFragment extends BaseFragment {
    @BindView(R.id.imgCompany)
    ImageView imgCompany;
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.horizontal_recycler_view)
    RecyclerView horizontal_recycler_view;
    @BindView(R.id.vv)
    VideoView vv;
    @BindView(R.id.tvHumanResource)
    TextView tvHumanResource;
    @BindView(R.id.tvCarrer)
    TextView tvCarrer;
    @BindView(R.id.tvCompanyAddress)
    TextView tvCompanyAddress;
    @BindView(R.id.tvWorkTime)
    TextView tvWorkTime;
    @BindView(R.id.tvInfoCompany)
    TextView tvInfoCompany;
    @BindView(R.id.btShowmore)
    Button btShowmore;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.flowListWelfare)
    FlowLayout flowListWelfare;
    @BindView(R.id.gradientView)
    LinearLayout gradientView;
    HorizontalAdapter horizontalAdapter;
    private MediaController mediacontroller;
    private Uri uri;
    private List<ImageCustomer> data;
    private ProgressBar progressBar;
    @BindView(R.id.relVideo)
    RelativeLayout relVideo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_customer;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        progressBar = (ProgressBar) root.findViewById(R.id.progrss);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        mediacontroller = new MediaController(getActivity());
        mediacontroller.setAnchorView(vv);
        getEventBaseFragment().doFillBackground("Thông tin nhà tuyển dụng");
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vv.start();
            }
        });
        relVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                vv.setMediaController(mediacontroller);
                vv.setVideoURI(uri);
                vv.requestFocus();
                vv.start();
            }
        });
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });
        btShowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btShowmore.getText().toString().equalsIgnoreCase("Xem thêm  ")) {
                    tvInfoCompany.setMaxLines(Integer.MAX_VALUE);//your TextView
                    btShowmore.setText("Rút gọn  ");
                    gradientView.setVisibility(View.GONE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
                } else {
                    tvInfoCompany.setMaxLines(6);
                    tvInfoCompany.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
                    btShowmore.setText("Xem thêm  ");
                    gradientView.setVisibility(View.VISIBLE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_blue, 0);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getCustomerProfile();
    }

    GetCUSProfileRequest getCUSProfileRequest;
    ProfileCustomerResponse profileCustomerResponse;

    public void getCustomerProfile() {
        showCoverNetworkLoading();
        getCUSProfileRequest = new GetCUSProfileRequest();
        getCUSProfileRequest.callRequest(getActivity(), new ApiObjectCallBack<ProfileCustomerResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, ProfileCustomerResponse dataSuccess, List<ProfileCustomerResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    profileCustomerResponse = dataSuccess;
                    ShowImageUtils.showImage(getActivity(), profileCustomerResponse.getLogoImg(), R.drawable.ic_company_null, imgCompany);
                    tvCompanyName.setText(profileCustomerResponse.getCompanyName());
                    if (profileCustomerResponse.getHumanResources().size() > 0) {
                        tvHumanResource.setText(profileCustomerResponse.getHumanResources().get(0).getName());
                    }
                    if (profileCustomerResponse.getCareerSelectedItems() != null) {
                        StringBuffer carrerBuffer = new StringBuffer("");
                        for (int i = 0; i < profileCustomerResponse.getCareerSelectedItems().size(); i++) {
                            if (i == profileCustomerResponse.getCareerSelectedItems().size() - 1) {
                                carrerBuffer.append(profileCustomerResponse.getCareerSelectedItems().get(i).getName() + "");
                            } else {
                                carrerBuffer.append(profileCustomerResponse.getCareerSelectedItems().get(i).getName() + ", ");
                            }
                        }
                        tvCarrer.setText(carrerBuffer.toString());
                    }
                    tvCompanyAddress.setText(profileCustomerResponse.getAddress());
                    StringBuffer timeBuffer = new StringBuffer("");
                    for (int i = 0; i < profileCustomerResponse.getTimeservingSelectedItems().size(); i++) {
                        if (i == profileCustomerResponse.getTimeservingSelectedItems().size() - 1) {
                            timeBuffer.append(profileCustomerResponse.getTimeservingSelectedItems().get(i).getName() + "");
                        } else {
                            timeBuffer.append(profileCustomerResponse.getTimeservingSelectedItems().get(i).getName() + ", ");
                        }
                    }
                    tvWorkTime.setText(timeBuffer.toString());
                    tvInfoCompany.setText(profileCustomerResponse.getDescripstion());
                    if (profileCustomerResponse.getVideoLink() != null) {
                        String uriPath = profileCustomerResponse.getVideoLink();
                        uri = Uri.parse(uriPath);
                    }
                    if (dataSuccess.getCustomerImg() != null) {
                        data = fill_with_data(dataSuccess);
                        horizontalAdapter = new HorizontalAdapter(data, getActivity());
                        horizontal_recycler_view.setAdapter(horizontalAdapter);
                    }
                    tvEmail.setText(profileCustomerResponse.getContactEmail());
                    showListWelfare();
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.error_please_try));
                }
            }
        });
    }

    public void showListWelfare() {
        flowListWelfare.removeAllViews();
        if (profileCustomerResponse.getCustomerWelfare() != null) {
            for (int i = 0; i < profileCustomerResponse.getCustomerWelfare().size(); i++) {
                LinearLayout linearLayout = (LinearLayout) LayoutUtils.inflate(flowListWelfare, R.layout.welfare_item_view, false);
                TextView tvWelfare = (TextView) linearLayout.findViewById(R.id.tvWelfare);
                tvWelfare.setText(profileCustomerResponse.getCustomerWelfare().get(i).getName() + " - " + profileCustomerResponse.getCustomerWelfare().get(i).getNote());
                flowListWelfare.addView(linearLayout);
            }
        }
    }

    public List<ImageCustomer> fill_with_data(ProfileCustomerResponse dataSuccess) {
        List<ImageCustomer> imageCustomers = new ArrayList<>();
        for (int i = 0; i < dataSuccess.getCustomerImg().size(); i++) {
            imageCustomers.add(new ImageCustomer(dataSuccess.getCustomerImg().get(i).getImageUrl(), "Image" + i));
        }
        return imageCustomers;
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
