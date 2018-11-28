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
import com.vietinterview.getbee.utils.FragmentUtil;

import butterknife.OnClick;

/**
 * Created by hiepnguyennghia on 11/27/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class DetailJobNTDFragment extends BaseFragment {
    TextView tvSongDes;
    Button btShowmore;
    LinearLayout gradientView;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private Menu collapsedMenu;
    private boolean appBarExpanded = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_job_ntd;
    }

    @Override
    protected void getArgument(Bundle bundle) {

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
        tvSongDes = (TextView) root.findViewById(R.id.tvSongDes);
        btShowmore = (Button) root.findViewById(R.id.btShowmore);
        gradientView = root.findViewById(R.id.gradientView);
        tvSongDes.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
        btShowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btShowmore.getText().toString().equalsIgnoreCase("Xem thêm  ")) {
                    tvSongDes.setMaxLines(Integer.MAX_VALUE);//your TextView
                    btShowmore.setText("Rút gọn  ");
                    gradientView.setVisibility(View.GONE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
                } else {
                    tvSongDes.setMaxLines(6);
                    tvSongDes.setBackgroundDrawable(getResources().getDrawable(R.drawable.main_header_selector));
                    btShowmore.setText("Xem thêm  ");
                    gradientView.setVisibility(View.VISIBLE);
                    btShowmore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_blue, 0);
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
