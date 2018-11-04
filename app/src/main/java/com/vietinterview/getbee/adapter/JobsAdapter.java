package com.vietinterview.getbee.adapter;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetDetailJobRequest;
import com.vietinterview.getbee.api.request.SaveUnsaveJobRequest;
import com.vietinterview.getbee.api.response.AddRemoveJobResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.CVSentFragment;
import com.vietinterview.getbee.fragments.DetailJobFragment;
import com.vietinterview.getbee.fragments.InfoFragment;
import com.vietinterview.getbee.fragments.StatisticalFragment;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class JobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<JobList> dataSet;
    BaseFragment mHomeFragment;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    FragmentActivity mActivity;
    private SaveUnsaveJobRequest saveUnsaveJobRequest;
    private GetDetailJobRequest getDetailJobRequest;
    int total;

    public JobsAdapter(RecyclerView recyclerView, ArrayList<JobList> data, int total, BaseFragment homeFragment, FragmentActivity activity) {
        this.dataSet = data;
        this.mHomeFragment = homeFragment;
        this.mActivity = activity;
        this.total = total;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_item, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            RequestOptions options = new RequestOptions()
                    .fitCenter()
                    .error(R.drawable.ic_company_null)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH);
            Glide.with(mActivity).load(dataSet.get(listPosition).getCompanyImg()).apply(options).into(myViewHolder.imgBussiness);
            if (listPosition == 0) {
                myViewHolder.llHeader.setVisibility(View.VISIBLE);
                myViewHolder.titleHeader.setText(this.total + " " + mActivity.getResources().getString(R.string.job_found));
            } else {
                myViewHolder.llHeader.setVisibility(View.GONE);
            }
            myViewHolder.tvjobTitle.setText(StringUtils.nullStrToEmpty(dataSet.get(listPosition).getJobTitle()));
            myViewHolder.tvCompanyName.setText(dataSet.get(listPosition).getCompanyName());
            myViewHolder.tvCarrer.setText(dataSet.get(listPosition).getCareerName());
            myViewHolder.tvListCity.setText(dataSet.get(listPosition).getListcityName());
            myViewHolder.tvExpireDate.setText(DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(dataSet.get(listPosition).getExpireDate()) + ""));
            myViewHolder.tvFee.setText(StringUtils.filterCurrencyString(dataSet.get(listPosition).getFee()) + " USD");
            myViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHomeFragment.showCoverNetworkLoading();
                    getDetailJobRequest = new GetDetailJobRequest(dataSet.get(listPosition).getId());
                    getDetailJobRequest.callRequest(mHomeFragment.getActivity(), new ApiObjectCallBack<DetailJobResponse, ErrorResponse>() {
                        @Override
                        public void onSuccess(int status, DetailJobResponse data, List<DetailJobResponse> dataArrayList, String message) {
                            mHomeFragment.hideCoverNetworkLoading();
                            if (mHomeFragment.isAdded()) {
                                FragmentUtil.pushFragment(mActivity, mHomeFragment, new DetailJobFragment().newInstance(data), null);
                            }
                        }

                        @Override
                        public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                            if (mHomeFragment.isAdded()) {
                                mHomeFragment.hideCoverNetworkLoading();
                                DialogUtil.showDialog(mHomeFragment.getActivity(), mHomeFragment.getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FragmentUtil.popBackStack(mHomeFragment);
                                    }
                                });
                            }
                        }
                    });
                }
            });
            if (dataSet.get(listPosition).getCollStatus() != null) {
                Integer collStatus = dataSet.get(listPosition).getCollStatus();
                if (collStatus == null || collStatus == 0) {
                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_save));
                } else if (collStatus == 1) {
                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_saved));
                } else if (collStatus == 2) {
                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_tickok));
                    myViewHolder.imgStatus.setEnabled(false);
                }
            } else {
                myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_save));
            }
            myViewHolder.imgStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHomeFragment.showCoverNetworkLoading();
                    Integer collStatus = dataSet.get(listPosition).getCollStatus() == null ? 1 : (Integer) dataSet.get(listPosition).getCollStatus() == 0 ? 1 : 0;
                    saveUnsaveJobRequest = new SaveUnsaveJobRequest(dataSet.get(listPosition).getId(), collStatus);
                    saveUnsaveJobRequest.callRequest(mActivity, new ApiObjectCallBack<AddRemoveJobResponse, ErrorResponse>() {
                        @Override
                        public void onSuccess(int status, AddRemoveJobResponse data, List<AddRemoveJobResponse> dataArrayList, String message) {
                            mHomeFragment.hideCoverNetworkLoading();
                            if (status == 200) {
                                dataSet.get(listPosition).setCollStatus(data.getStatus());
                                if (data.getStatus() == 0) {
                                    Toast.makeText(mHomeFragment.getActivity(), mHomeFragment.getResources().getString(R.string.cancel_save_noti), Toast.LENGTH_SHORT).show();
                                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_save));
                                } else if (data.getStatus() == 1) {
                                    Toast.makeText(mHomeFragment.getActivity(), mHomeFragment.getResources().getString(R.string.saved_noti), Toast.LENGTH_SHORT).show();
                                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_saved));
                                }
                            }
                        }

                        @Override
                        public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                            mHomeFragment.hideCoverNetworkLoading();
                        }
                    });
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvjobTitle;
        TextView tvCompanyName;
        TextView tvCarrer;
        TextView tvListCity;
        TextView tvExpireDate;
        TextView tvFee;
        ImageView imgBussiness;
        ImageView imgStatus;
        CardView card_view;
        LinearLayout llHeader;
        TextView titleHeader;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvjobTitle = (TextView) itemView.findViewById(R.id.tvjobTitle);
            this.tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
            this.tvCarrer = (TextView) itemView.findViewById(R.id.tvCarrer);
            this.tvListCity = (TextView) itemView.findViewById(R.id.tvListCity);
            this.tvExpireDate = (TextView) itemView.findViewById(R.id.tvExpireDate);
            this.tvFee = (TextView) itemView.findViewById(R.id.tvFee);
            this.imgBussiness = (ImageView) itemView.findViewById(R.id.imgBussiness);
            this.imgStatus = (ImageView) itemView.findViewById(R.id.imgStatus);
            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            this.llHeader = itemView.findViewById(R.id.llHeader);
            this.titleHeader = itemView.findViewById(R.id.titleHeader);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
