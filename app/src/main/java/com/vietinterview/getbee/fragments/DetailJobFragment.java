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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyCVChoiceAdapter;
import com.vietinterview.getbee.adapter.ViewPagerAdapter;
import com.vietinterview.getbee.api.request.BaseJsonRequest;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetDetailJobRequest;
import com.vietinterview.getbee.api.request.SaveUnsaveJobRequest;
import com.vietinterview.getbee.api.request.SearchMyCVRequest;
import com.vietinterview.getbee.api.response.AddRemoveJobResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.api.response.listcv.CVResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnSetHeightViewListener;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
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
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.llParent)
    LinearLayout llParent;
    private TabLayout tabLayout;
    private Dialog mNotifydialog;
    //    private JobList mJobList;
    private SaveUnsaveJobRequest saveUnsaveJobRequest;
    private SearchMyCVRequest searchMyCVRequest;
    InfoFragment infoFragment;
    StatisticalFragment statisticalFragment;
    CVSentFragment cvSentFragment;
    DetailJobResponse mDetailJobResponse;
    private int jobID;

    public static DetailJobFragment newInstance(int jobID) {
        DetailJobFragment fm = new DetailJobFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("jobId", jobID);
        fm.setArguments(bundle);
        return fm;
    }

    public static DetailJobFragment newInstance(DetailJobResponse detailJobResponse) {
        DetailJobFragment fm = new DetailJobFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailJobResponse", detailJobResponse);
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
        GlobalDefine.currentFragment = this;
        setCustomToolbar(true);
        setCustomToolbarVisible(true);
        getEventBaseFragment().setOnSetHeightViewListener(new OnSetHeightViewListener() {
            @Override
            public void onSetHeightView(int height) {
                ViewGroup.LayoutParams params = viewPager.getLayoutParams();
                params.height = height + 300;
                viewPager.requestLayout();
            }
        });
        tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.transparent));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    getEventBaseFragment().setSwitchToOne();
                } else if (position == 1) {
                    getEventBaseFragment().setSwitchToTwo();
                } else if (position == 2) {
                    getEventBaseFragment().setSwitchToThree();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {
        jobID = bundle.getInt("jobId");
        mDetailJobResponse = bundle.getParcelable("detailJobResponse");
    }

    @Override
    protected void initData() {
        if (jobID != 0) {
            getDetailJob(jobID);
        } else {
            llParent.setVisibility(View.VISIBLE);
            tvcompanyName.setText(mDetailJobResponse.getCompanyName());
            tvjobTitle.setText(mDetailJobResponse.getJobTitle());
            RequestOptions options = new RequestOptions().fitCenter().error(R.drawable.ic_company_null).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH);
            Glide.with(getActivity()).load(mDetailJobResponse.getCompanyImg()).apply(options).into(imgCompany);
            if (mDetailJobResponse.getStatus() == 1) {
                tvstatus.setText(getResources().getString(R.string.hiring));
                llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
            } else {
                tvstatus.setText(getResources().getString(R.string.closed));
                llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_notyet_send));
            }

            if (mDetailJobResponse.getCollStatus() != null) {
                Integer collStatus = mDetailJobResponse.getCollStatus();
                if (collStatus == 0) {
                    Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
                    img.setBounds(0, 0, 40, 50);
                    saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                    saveUnsaveJob.setText(getResources().getString(R.string.save_job));
                    saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
                    saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
                } else if (collStatus == 1) {
                    Drawable img = getContext().getResources().getDrawable(R.drawable.ic_saved);
                    img.setBounds(0, 0, 40, 50);
                    saveUnsaveJob.setText(getResources().getString(R.string.saved_job));
                    saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                    saveUnsaveJob.setTextColor(getResources().getColor(R.color.red));
                    saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_save_button));
                } else {
                    Drawable img = getContext().getResources().getDrawable(R.drawable.ic_tick_white);
                    img.setBounds(0, 0, 50, 50);
                    saveUnsaveJob.setText(getResources().getString(R.string.cv_applyed_tit));
                    saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                    saveUnsaveJob.setTextColor(getResources().getColor(R.color.white));
                    saveUnsaveJob.setEnabled(false);
                    saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
                }
            } else {
                Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
                img.setBounds(0, 0, 40, 50);
                saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                saveUnsaveJob.setText(getResources().getString(R.string.save_job));
                saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
                saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
            }
            infoFragment = new InfoFragment().newInstance(mDetailJobResponse);
            statisticalFragment = new StatisticalFragment().newInstance(mDetailJobResponse);
            cvSentFragment = new CVSentFragment().newInstance(mDetailJobResponse);
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
    }


    @OnClick(R.id.saveUnsaveJob)
    public void onSaveUnsaveJobClick() {
        showCoverNetworkLoading();
        int collStatus = mDetailJobResponse.getCollStatus() == null ? 1 : (int) mDetailJobResponse.getCollStatus() != 0 ? 0 : 1;
        saveUnsaveJobRequest = new SaveUnsaveJobRequest(mDetailJobResponse.getId(), collStatus);
        saveUnsaveJobRequest.callRequest(getActivity(), new ApiObjectCallBack<AddRemoveJobResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, AddRemoveJobResponse data, List<AddRemoveJobResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    if (status == 200) {
                        getEventBaseFragment().refreshHome();
                        mDetailJobResponse.setCollStatus(data.getStatus());
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
                        } else {
                            Drawable img = getContext().getResources().getDrawable(R.drawable.ic_tick_white);
                            img.setBounds(0, 0, 40, 50);
                            saveUnsaveJob.setText(getResources().getString(R.string.cv_applyed_tit));
                            saveUnsaveJob.setEnabled(false);
                            saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                            saveUnsaveJob.setTextColor(getResources().getColor(R.color.white));
                            saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
                        }
                    }
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
            }
        });
    }


    @OnClick(R.id.btnApplyCV)
    public void onApplyCVClick() {
//        FragmentUtil.pushFragment(getActivity(), this, new ChoiceCVFragment().newInstance(mDetailJobResponse), null);
        searchMyCV(0, 0, 0);
    }

    public void searchMyCV(final int carrerId, final int cityId, final int page) {
        showCoverNetworkLoading();
        searchMyCVRequest = new SearchMyCVRequest(page, carrerId, cityId);
        searchMyCVRequest.callRequest(getActivity(), new ApiObjectCallBack<CVResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, CVResponse data, List<CVResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    if (data.getCvList().size() > 0)
                        FragmentUtil.pushFragment(getActivity(), DetailJobFragment.this, new ChoiceCVFragment().newInstance(mDetailJobResponse, data), null);
                    else {
                        DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.no_choice_cv));
                    }
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                if (isAdded()) {
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message);
                }
            }
        });
    }

    private GetDetailJobRequest getDetailJobRequest;

    public void getDetailJob(int jobId) {
        llParent.setVisibility(View.GONE);
        showCoverNetworkLoading();
        getDetailJobRequest = new GetDetailJobRequest(jobId);
        getDetailJobRequest.callRequest(getActivity(), new ApiObjectCallBack<DetailJobResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailJobResponse data, List<DetailJobResponse> dataArrayList, String message) {
                hideCoverNetworkLoading();
                mDetailJobResponse = data;
                if (isAdded()) {
                    llParent.setVisibility(View.VISIBLE);
                    tvcompanyName.setText(data.getCompanyName());
                    tvjobTitle.setText(data.getJobTitle());
                    RequestOptions options = new RequestOptions().fitCenter().error(R.drawable.ic_company_null).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH);
                    Glide.with(getActivity()).load(data.getCompanyImg()).apply(options).into(imgCompany);
                    if (data.getStatus() == 1) {
                        tvstatus.setText(getResources().getString(R.string.hiring));
                        llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
                    } else {
                        tvstatus.setText(getResources().getString(R.string.closed));
                        llStatus.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_notyet_send));
                    }

                    if (data.getCollStatus() != null) {
                        Integer collStatus = data.getCollStatus();
                        if (collStatus == 0) {
                            Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
                            img.setBounds(0, 0, 40, 50);
                            saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                            saveUnsaveJob.setText(getResources().getString(R.string.save_job));
                            saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
                            saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
                        } else if (collStatus == 1) {
                            Drawable img = getContext().getResources().getDrawable(R.drawable.ic_saved);
                            img.setBounds(0, 0, 40, 50);
                            saveUnsaveJob.setText(getResources().getString(R.string.saved_job));
                            saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                            saveUnsaveJob.setTextColor(getResources().getColor(R.color.red));
                            saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_save_button));
                        } else {
                            Drawable img = getContext().getResources().getDrawable(R.drawable.ic_tick_white);
                            img.setBounds(0, 0, 50, 50);
                            saveUnsaveJob.setText(getResources().getString(R.string.cv_applyed_tit));
                            saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                            saveUnsaveJob.setTextColor(getResources().getColor(R.color.white));
                            saveUnsaveJob.setEnabled(false);
                            saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.borderbutton_green));
                        }
                    } else {
                        Drawable img = getContext().getResources().getDrawable(R.drawable.ic_save);
                        img.setBounds(0, 0, 40, 50);
                        saveUnsaveJob.setCompoundDrawables(img, null, null, null);
                        saveUnsaveJob.setText(getResources().getString(R.string.save_job));
                        saveUnsaveJob.setTextColor(getResources().getColor(R.color.gray_not_focus));
                        saveUnsaveJob.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_radius_unsave_button));
                    }
                    infoFragment = new InfoFragment().newInstance(mDetailJobResponse);
                    statisticalFragment = new StatisticalFragment().newInstance(mDetailJobResponse);
                    cvSentFragment = new CVSentFragment().newInstance(mDetailJobResponse);
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
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                if (isAdded()) {
                    llParent.setVisibility(View.GONE);
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FragmentUtil.popBackStack(DetailJobFragment.this);
                        }
                    });
                }
            }
        });
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
        adapter.addFrag(infoFragment, getResources().getString(R.string.info));
        adapter.addFrag(statisticalFragment, getResources().getString(R.string.statistic));
        adapter.addFrag(cvSentFragment, getResources().getString(R.string.cv_submited));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void processOnBackPress() {
        FragmentUtil.popBackStack(this);
    }

    @Override
    protected void processCustomToolbar() {
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

    @Override
    public ArrayList<BaseJsonRequest> getArrayJsonRequest() {
        ArrayList<BaseJsonRequest> baseJsonRequests = new ArrayList<>();
        baseJsonRequests.add(saveUnsaveJobRequest);
        return baseJsonRequests;
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        baseRequests.add(searchMyCVRequest);
        return baseRequests;
    }
}
