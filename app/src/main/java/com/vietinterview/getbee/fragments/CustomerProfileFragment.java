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
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.HorizontalAdapter;
import com.vietinterview.getbee.model.ImageCustomer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class CustomerProfileFragment extends BaseFragment {
    HorizontalAdapter horizontalAdapter;
    RecyclerView horizontal_recycler_view;
    private VideoView vv;
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
        horizontal_recycler_view = (RecyclerView) root.findViewById(R.id.horizontal_recycler_view);
        progressBar = (ProgressBar) root.findViewById(R.id.progrss);
        data = fill_with_data();


        horizontalAdapter = new HorizontalAdapter(data, getActivity());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

        vv = (VideoView) root.findViewById(R.id.vv);

        mediacontroller = new MediaController(getActivity());
        mediacontroller.setAnchorView(vv);
        String uriPath = "http://www.demonuts.com/Demonuts/smallvideo.mp4"; //update package name
        uri = Uri.parse(uriPath);

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
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initData() {

    }

    public List<ImageCustomer> fill_with_data() {

        List<ImageCustomer> imageCustomers = new ArrayList<>();

        imageCustomers.add(new ImageCustomer(R.drawable.img1, "Image 1"));
        imageCustomers.add(new ImageCustomer(R.drawable.img1, "Image 2"));
        imageCustomers.add(new ImageCustomer(R.drawable.img1, "Image 3"));


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
