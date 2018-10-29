package com.vietinterview.getbee.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.api.request.GetDetailJobRequest;
import com.vietinterview.getbee.api.request.SaveUnsaveJobRequest;
import com.vietinterview.getbee.api.response.AddRemoveJobResponse;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hiepn on 23/03/2017.
 */

public class DetailJobFragment extends BaseFragment {
    @BindView(R.id.saveUnsaveJob)
    Button saveUnsaveJob;
    @BindView(R.id.tvcompanyName)
    TextView tvcompanyName;
    @BindView(R.id.tvjobTitle)
    TextView tvjobTitle;
    @BindView(R.id.tvstatus)
    TextView tvstatus;
    @BindView(R.id.llStatus)
    LinearLayout llStatus;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.imgCompany)
    ImageView imgCompany;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Dialog mNotifydialog;
    private JobList mJobList;
    private SaveUnsaveJobRequest saveUnsaveJobRequest;
    private GetDetailJobRequest getDetailJobRequest;
    private DetailJobResponse detailJobResponse;

    public static DetailJobFragment newInstance(JobList jobList) {
        DetailJobFragment fm = new DetailJobFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobList", jobList);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_job;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.detail_job));
        setCustomToolbar(true);
//        setHasOptionsMenu(true);
        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.transparent));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                } else if (position == 1) {
                } else if (position == 2) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mJobList = bundle.getParcelable("jobList");
    }

    @Override
    protected void initData() {
        if (mJobList.getCollStatus() != null) {
            Integer collStatus = mJobList.getCollStatus();
            if (collStatus == 0) {
                Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
                img.setBounds(0, 0, 40, 50);
                saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                saveUnsaveJob.setText(getResources().getString(R.string.save_job));
                saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
                saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
            } else {
                Drawable img = getContext().getResources().getDrawable(R.drawable.ic_saved);
                img.setBounds(0, 0, 40, 50);
                saveUnsaveJob.setText(getResources().getString(R.string.saved_job));
                saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                saveUnsaveJob.setTextColor(getResources().getColor(R.color.red));
                saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_save_button));
            }
        } else {
            Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
            img.setBounds(0, 0, 40, 50);
            saveUnsaveJob.setCompoundDrawables(img, null, null, null);
            saveUnsaveJob.setText(getResources().getString(R.string.save_job));
            saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
            saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
        }
        getDetailJob(mJobList.getId());
    }

    public void getDetailJob(int jobId) {
        showCoverNetworkLoading();
        getDetailJobRequest = new GetDetailJobRequest(jobId);
        getDetailJobRequest.callRequest(getActivity(), new ApiObjectCallBack<DetailJobResponse>() {
            @Override
            public void onSuccess(DetailJobResponse data, List<DetailJobResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                detailJobResponse = data;
                tvcompanyName.setText(data.getCompanyName());
                tvjobTitle.setText(data.getJobTitle());
                Glide.with(getActivity()).load(data.getCompanyImg()).into(imgCompany);
                if (data.getStatus() == 1) {
                    tvstatus.setText(getResources().getString(R.string.hiring));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
                } else {
                    tvstatus.setText(getResources().getString(R.string.closed));
                    llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_notyet_send));
                }
                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.black));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(R.color.background_icon_not_focus));
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            @Override
            public void onFail(int failCode, DetailJobResponse data, List<DetailJobResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentUtil.popBackStack(DetailJobFragment.this);
                    }
                });
            }
        });
    }

    @OnClick(R.id.saveUnsaveJob)
    public void onSaveUnsaveJobClick() {
        showCoverNetworkLoading();
        int collStatus = mJobList.getCollStatus() == null ? 1 : (int) mJobList.getCollStatus() != 0 ? 0 : 1;
        saveUnsaveJobRequest = new SaveUnsaveJobRequest(mJobList.getId(), collStatus);
        saveUnsaveJobRequest.callRequest(getActivity(), new ApiObjectCallBack<AddRemoveJobResponse>() {
            @Override
            public void onSuccess(AddRemoveJobResponse data, List<AddRemoveJobResponse> dataArrayList, int status, String message) {
                hideCoverNetworkLoading();
                if (status == 200) {
                    mJobList.setCollStatus(data.getStatus());
                    if (data.getStatus() == 0) {
                        Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
                        img.setBounds(0, 0, 40, 50);
                        saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                        saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
                        saveUnsaveJob.setText(getResources().getString(R.string.save_job));
                        saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
                    } else if (data.getStatus() == 1) {
                        Drawable img = getContext().getResources().getDrawable(R.drawable.ic_saved);
                        img.setBounds(0, 0, 40, 50);
                        saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                        saveUnsaveJob.setText(getResources().getString(R.string.saved_job));
                        saveUnsaveJob.setTextColor(getResources().getColor(R.color.red));
                        saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_save_button));
                    }
                }
            }

            @Override
            public void onFail(int failCode, AddRemoveJobResponse data, List<AddRemoveJobResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
            }
        });
    }


    @OnClick(R.id.btnApplyCV)
    public void onApplyCVClick() {
        FragmentUtil.pushFragment(getActivity(), this, new ChoiceCVFragment().newInstance(mJobList), null);
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

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.info));
        tabOne.setTextColor(getResources().getColor(R.color.black));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.statistic));
        tabTwo.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabThree.setText(getResources().getString(R.string.cv_submited));
        tabThree.setTextColor(getResources().getColor(R.color.background_icon_not_focus));
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new InfoFragment().newInstance(detailJobResponse), getResources().getString(R.string.info));
        adapter.addFrag(new StatisticalFragment().newInstance(detailJobResponse), getResources().getString(R.string.statistic));
        adapter.addFrag(new CVSentFragment().newInstance(detailJobResponse), getResources().getString(R.string.cv_submited));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail_job, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share) {

            mNotifydialog = new Dialog(getActivity());
            mNotifydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mNotifydialog.setContentView(R.layout.dialog_share);
            mNotifydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mNotifydialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            Button btnOK = (Button) mNotifydialog.findViewById(R.id.btnOK);
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNotifydialog.dismiss();
                }
            });
            mNotifydialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
