package com.vietinterview.getbee.fragments;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.callback.OnSwitchToOneListener;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.LayoutUtils;
import com.vietinterview.getbee.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepn on 23/03/2017.
 */

public class InfoFragment extends BaseFragment {
    @BindView(R.id.tvCarrer)
    TextView tvCarrer;
    @BindView(R.id.tvjobLevel)
    TextView tvjobLevel;
    @BindView(R.id.tvlistcityName)
    TextView tvlistcityName;
    @BindView(R.id.tvsubmitDate)
    TextView tvsubmitDate;
    @BindView(R.id.tvexpireDate)
    TextView tvexpireDate;
    @BindView(R.id.tvstatus)
    TextView tvstatus;
    @BindView(R.id.tvSalaryFromTo)
    TextView tvSalaryFromTo;
    @BindView(R.id.tvRewardCTV)
    TextView tvRewardCTV;
    @BindView(R.id.tvquantity)
    TextView tvquantity;
    @BindView(R.id.tvcountCv)
    TextView tvcountCv;
    @BindView(R.id.tvjobDescription)
    TextView tvjobDescription;
    @BindView(R.id.llContentDes)
    LinearLayout llContentDes;
    @BindView(R.id.imgDes)
    ImageView imgDes;
    @BindView(R.id.llInfo)
    LinearLayout llInfo;
    DetailJobResponse detailJobResponse;
    boolean isVisibleDes = true;
    ViewTreeObserver vto;

    public static InfoFragment newInstance(DetailJobResponse detailJobResponse) {
        InfoFragment fm = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailJobResponse", detailJobResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        vto = llInfo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    llInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    llInfo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int height = llInfo.getMeasuredHeight();
                getEventBaseFragment().setHeightView(height);
            }
        });
        getEventBaseFragment().setOnSwitchToOneListener(new OnSwitchToOneListener() {
            @Override
            public void onSwitchToOne() {
                getEventBaseFragment().setHeightView(LayoutUtils.getViewHeight(llInfo));
            }
        });
        tvCarrer.setText(detailJobResponse.getCareerName());
        if (detailJobResponse.getJobLevel() == 1)
            tvjobLevel.setText("Trưởng phòng");
        else tvjobLevel.setText("Nhân viên");
        if (!StringUtils.isEmpty(detailJobResponse.getListcityName()))
            tvlistcityName.setText(detailJobResponse.getListcityName());
        tvsubmitDate.setText(DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(detailJobResponse.getSubmitDate()) + ""));
        tvexpireDate.setText(DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(detailJobResponse.getExpireDate()) + ""));
        if (detailJobResponse.getStatus() == 1) {
            tvstatus.setText(getResources().getString(R.string.hiring));
            tvstatus.setTextColor(getResources().getColor(R.color.step_complete));
        } else {
            tvstatus.setText(getResources().getString(R.string.closed));
            tvstatus.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        }
        tvSalaryFromTo.setText(StringUtils.filterCurrencyString(detailJobResponse.getFromSalary()) + " USD - " + StringUtils.filterCurrencyString(detailJobResponse.getToSalary()) + " USD");
        tvquantity.setText(detailJobResponse.getQuantity() + "");
        tvcountCv.setText(detailJobResponse.getCountCv() + "");
        tvRewardCTV.setText(StringUtils.filterCurrencyString(detailJobResponse.getFee()) + " USD");
        tvjobDescription.setText(Html.fromHtml(detailJobResponse.getJobDescription()));
    }

    @Override
    protected void getArgument(Bundle bundle) {
        detailJobResponse = bundle.getParcelable("detailJobResponse");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.llDes)
    public void onExpanDesClick() {
        if (isVisibleDes) {
            isVisibleDes = false;
            llContentDes.setVisibility(View.GONE);
            imgDes.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_right));
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        llInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        llInfo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    int height = llInfo.getMeasuredHeight();
                    getEventBaseFragment().setHeightView(height);
                }
            });
        } else {
            isVisibleDes = true;
            llContentDes.setVisibility(View.VISIBLE);
            imgDes.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down));
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        llInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        llInfo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    int height = llInfo.getMeasuredHeight();
                    getEventBaseFragment().setHeightView(height);
                }
            });
        }
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
