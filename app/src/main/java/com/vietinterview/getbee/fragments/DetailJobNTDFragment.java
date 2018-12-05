package com.vietinterview.getbee.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.activities.MainActivity;
import com.vietinterview.getbee.api.response.detailjobcustomer.DetailJobCustomerResponse;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 11/27/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailJobNTDFragment extends BaseFragment {
    @BindView(R.id.tvJobTitle)
    TextView tvJobTitle;
    @BindView(R.id.tvLevel)
    TextView tvLevel;
    @BindView(R.id.tvworkingForm)
    TextView tvworkingForm;
    @BindView(R.id.tvCarrer)
    TextView tvCarrer;
    @BindView(R.id.tvRequireExp)
    TextView tvRequireExp;
    @BindView(R.id.tvWorkPlace)
    TextView tvWorkPlace;
    @BindView(R.id.tvQuantityHiring)
    TextView tvQuantityHiring;
    @BindView(R.id.tvCertificate)
    TextView tvCertificate;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvSalary)
    TextView tvSalary;
    @BindView(R.id.tvFee)
    TextView tvFee;
    @BindView(R.id.tvDatePublic)
    TextView tvDatePublic;
    @BindView(R.id.tvExpireDate)
    TextView tvExpireDate;
    @BindView(R.id.tvKeyWord)
    TextView tvKeyWord;
    @BindView(R.id.tvLanCV)
    TextView tvLanCV;
    @BindView(R.id.tvContact)
    TextView tvContact;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvJobDes)
    TextView tvJobDes;
    @BindView(R.id.tvjobRequirement)
    TextView tvjobRequirement;
    @BindView(R.id.btShowmore)
    Button btShowmore;
    @BindView(R.id.btShowmore1)
    Button btShowmore1;
    @BindView(R.id.gradientView)
    LinearLayout gradientView;
    @BindView(R.id.gradientView1)
    LinearLayout gradientView1;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private Menu collapsedMenu;
    private boolean appBarExpanded = true;
    private DetailJobCustomerResponse detailJobCustomerResponse;

    public static DetailJobNTDFragment newInstance(DetailJobCustomerResponse detailJobCustomerResponse) {
        DetailJobNTDFragment fm = new DetailJobNTDFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailJobCustomerResponse", detailJobCustomerResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_job_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailJobCustomerResponse = bundle.getParcelable("detailJobCustomerResponse");
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(false);
        setCustomToolbarVisible(false);
        appBarLayout = (AppBarLayout) root.findViewById(R.id.appbar);
        collapsingToolbar = (CollapsingToolbarLayout) root.findViewById(R.id.collapsing_toolbar);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_header_job);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    getActivity().invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    getActivity().invalidateOptionsMenu();
                }
            }
        });
        tvJobTitle.setText(detailJobCustomerResponse.getJobTitle());
        tvLevel.setText(detailJobCustomerResponse.getCurrentLevel().getName());//note
        tvworkingForm.setText(detailJobCustomerResponse.getWorkingForm().getName());

        StringBuilder s0 = new StringBuilder("");
        for (int i = 0; i < detailJobCustomerResponse.getLstCareer().size(); i++) {
            if (i == detailJobCustomerResponse.getLstCareer().size() - 1) {
                s0.append(detailJobCustomerResponse.getLstCareer().get(i).getName() + "");
            } else {
                s0.append(detailJobCustomerResponse.getLstCareer().get(i).getName() + ", ");
            }
        }
        tvCarrer.setText(s0.toString());
        tvRequireExp.setText(detailJobCustomerResponse.getWorkExperience());
        StringBuilder s1 = new StringBuilder("");
        for (int i = 0; i < detailJobCustomerResponse.getLstCareer().size(); i++) {
            if (i == detailJobCustomerResponse.getLstCareer().size() - 1) {
                s1.append(detailJobCustomerResponse.getLstCareer().get(i).getName() + "");
            } else {
                s1.append(detailJobCustomerResponse.getLstCareer().get(i).getName() + ", ");
            }
        }
        tvWorkPlace.setText(s1.toString());
        tvQuantityHiring.setText(detailJobCustomerResponse.getQuantity() + "");
        tvCertificate.setText(detailJobCustomerResponse.getEducationLevel().getName());
        tvAge.setText(detailJobCustomerResponse.getAge() + "");
        tvSalary.setText(StringUtils.filterCurrencyString(detailJobCustomerResponse.getFromSalary()) + " - " + StringUtils.filterCurrencyString(detailJobCustomerResponse.getToSalary()));
        tvFee.setText(StringUtils.filterCurrencyString(detailJobCustomerResponse.getFee()));
        tvDatePublic.setText(DateUtil.convertToMyFormatFull(detailJobCustomerResponse.getSubmitDate()));
        tvExpireDate.setText(DateUtil.convertToMyFormatFull(detailJobCustomerResponse.getExpireDate()));
        tvKeyWord.setText(detailJobCustomerResponse.getTag());
        tvLanCV.setText("Ngon ngu");
        tvContact.setText(detailJobCustomerResponse.getCustomers().getContactName());
        tvEmail.setText(detailJobCustomerResponse.getCustomers().getContactEmail());
        tvJobDes.setText(Html.fromHtml(detailJobCustomerResponse.getJobDescription()));
        tvjobRequirement.setText(Html.fromHtml(detailJobCustomerResponse.getJobRequirements()));
        tvJobDes.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
        btShowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btShowmore.getText().toString().equalsIgnoreCase("Xem thêm  ")) {
                    tvJobDes.setMaxLines(Integer.MAX_VALUE);//your TextView
                    btShowmore.setText("Rút gọn  ");
                    gradientView.setVisibility(View.GONE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
                } else {
                    tvJobDes.setMaxLines(6);
                    tvJobDes.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
                    btShowmore.setText("Xem thêm  ");
                    gradientView.setVisibility(View.VISIBLE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_blue, 0);
                }
            }
        });
        btShowmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btShowmore1.getText().toString().equalsIgnoreCase("Xem thêm  ")) {
                    tvjobRequirement.setMaxLines(Integer.MAX_VALUE);//your TextView
                    btShowmore1.setText("Rút gọn  ");
                    gradientView1.setVisibility(View.GONE);
                    btShowmore1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
                } else {
                    tvjobRequirement.setMaxLines(6);
                    tvjobRequirement.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
                    btShowmore1.setText("Xem thêm  ");
                    gradientView1.setVisibility(View.VISIBLE);
                    btShowmore1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_blue, 0);
                }
            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.imgBack)
    public void onBackClick() {
        FragmentUtil.popBackStack(this);
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

}
