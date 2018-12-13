package com.vietinterview.getbee.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.callback.OnFillBackgroundListener;
import com.vietinterview.getbee.callback.OnShowLogoListener;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 10/17/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CreateNewCVActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvAppName)
    TextView tvAppName;
    @BindView(R.id.imglogo)
    ImageView imglogo;

    @Override
    public int setContentViewId() {
        return R.layout.activity_create_new_cv;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(null);
        getEventBaseActivity().setOnFillBackgroundListener(new OnFillBackgroundListener() {
            @Override
            public void onFillBackground(String nameTable) {
                tvAppName.setVisibility(View.VISIBLE);
                tvAppName.setText(nameTable);
                imglogo.setVisibility(View.GONE);
            }
        });
        getEventBaseActivity().setOnShowLogoListener(new OnShowLogoListener() {
            @Override
            public void onShowLogo(boolean isShowLogo) {
                if (isShowLogo) {
                    imglogo.setVisibility(View.VISIBLE);
                    tvAppName.setVisibility(View.GONE);
                } else {
                    imglogo.setVisibility(View.GONE);
                    tvAppName.setVisibility(View.VISIBLE);
                }
            }
        });
//        FragmentUtil.replaceFragment(CreateNewCVActivity.this, new ProcessResumeFragment(), null);
    }

    @Override
    public void initData() {

    }
}
