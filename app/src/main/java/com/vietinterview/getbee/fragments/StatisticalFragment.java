package com.vietinterview.getbee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.callback.OnSwitchToOneListener;
import com.vietinterview.getbee.callback.OnSwitchToTwoListener;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.LayoutUtils;

import butterknife.BindView;

/**
 * Created by hiepn on 23/03/2017.
 */

public class StatisticalFragment extends BaseFragment {
    @BindView(R.id.tvcountCv)
    TextView tvcountCv;
    @BindView(R.id.tvcountInterview)
    TextView tvcountInterview;
    @BindView(R.id.tvcountOffer)
    TextView tvcountOffer;
    @BindView(R.id.tvcountGotoWork)
    TextView tvcountGotoWork;
    @BindView(R.id.tvmyCountCv)
    TextView tvmyCountCv;
    @BindView(R.id.tvmyCountInviteInterview)
    TextView tvmyCountInviteInterview;
    @BindView(R.id.tvmyCountInterview)
    TextView tvmyCountInterview;
    @BindView(R.id.tvmyCountOffer)
    TextView tvmyCountOffer;
    @BindView(R.id.tvmyCountGotoWork)
    TextView tvmyCountGotoWork;
    @BindView(R.id.tvcountCared)
    TextView tvcountCared;
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    DetailJobResponse detailJobResponse;
    ViewTreeObserver vto;

    public static StatisticalFragment newInstance(DetailJobResponse detailJobResponse) {
        StatisticalFragment fm = new StatisticalFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailJobResponse", detailJobResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistical;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().setOnSwitchToTwoListener(new OnSwitchToTwoListener() {
            @Override
            public void onSwitchToTwo(int position) {
                DebugLog.showLogCat(position + "");
                if (position == 1) {
                    getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(llInfo));
                }
            }
        });
        tvcountCv.setText(detailJobResponse.getCountCv() != null ? detailJobResponse.getCountCv() + "" : "0");
        tvcountInterview.setText(detailJobResponse.getCountInterview() != null ? detailJobResponse.getCountInterview() + "" : "0");
        tvcountOffer.setText(detailJobResponse.getCountOffer() != null ? detailJobResponse.getCountOffer() + "" : "0");
        tvcountGotoWork.setText(detailJobResponse.getCountGotoWork() != null ? detailJobResponse.getCountGotoWork() + "" : "0");
        tvmyCountCv.setText(detailJobResponse.getMyCountCv() != null ? detailJobResponse.getMyCountCv() + "" : "0");
        tvmyCountInviteInterview.setText(detailJobResponse.getMyCountInviteInterview() != null ? detailJobResponse.getMyCountInviteInterview() + "" : "0");
        tvmyCountInterview.setText(detailJobResponse.getMyCountInterview() != null ? detailJobResponse.getMyCountInterview() + "" : "0");
        tvmyCountOffer.setText(detailJobResponse.getMyCountOffer() != null ? detailJobResponse.getMyCountOffer() + "" : "0");
        tvmyCountGotoWork.setText(detailJobResponse.getMyCountGotoWork() != null ? detailJobResponse.getMyCountGotoWork() + "" : "0");
        tvcountCared.setText(detailJobResponse.getCountColl() != null ? detailJobResponse.getCountColl() + "" : "0");
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailJobResponse = bundle.getParcelable("detailJobResponse");
    }

    @Override
    protected void initData() {

    }

    public LinearLayout getLlInfo() {
        return llInfo;
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


}
