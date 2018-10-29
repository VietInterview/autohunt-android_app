package com.vietinterview.getbee.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.SaveUnsaveJobRequest;
import com.vietinterview.getbee.api.response.AddRemoveJobResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.DetailJobFragment;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class MyJobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<JobList> dataSet;
    private BaseFragment mHomeFragment;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private FragmentActivity mActivity;
    private SaveUnsaveJobRequest saveUnsaveJobRequest;
    private boolean mIsJobSaved;


    public MyJobsAdapter(RecyclerView recyclerView, ArrayList<JobList> data, BaseFragment homeFragment, FragmentActivity activity, boolean isJobSaved) {
        this.dataSet = data;
        this.mHomeFragment = homeFragment;
        this.mActivity = activity;
        this.mIsJobSaved = isJobSaved;
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
            Glide.with(mActivity).load(dataSet.get(listPosition).getCompanyImg()).into(myViewHolder.imgBussiness);
            myViewHolder.tvjobTitle.setText(StringUtils.nullStrToEmpty(dataSet.get(listPosition).getJobTitle()));
            myViewHolder.tvCompanyName.setText(dataSet.get(listPosition).getCompanyName());
            myViewHolder.tvCarrer.setText(dataSet.get(listPosition).getCareerName());
            myViewHolder.tvListCity.setText(dataSet.get(listPosition).getListcityName());
            myViewHolder.tvExpireDate.setText(DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(dataSet.get(listPosition).getExpireDate()) + ""));
            myViewHolder.tvFee.setText(StringUtils.filterCurrencyString(dataSet.get(listPosition).getFee()) + " USD");
            myViewHolder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentUtil.pushFragment(mActivity, mHomeFragment, new DetailJobFragment().newInstance(dataSet.get(listPosition)), null);
                }
            });
            if (mIsJobSaved)
                myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_saved));
            else
                myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_tickok));

            if (dataSet.get(listPosition).getCollStatus() != null) {
                Integer collStatus = dataSet.get(listPosition).getCollStatus();
                if (collStatus == null || collStatus == 0) {
                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_save));
                } else {
                    myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_saved));
                }
            } else {
                myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_save));
            }
            if (mIsJobSaved) {
                myViewHolder.imgStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHomeFragment.showCoverNetworkLoading();
                        Integer collStatus = dataSet.get(listPosition).getCollStatus() == null ? 1 : (Integer) dataSet.get(listPosition).getCollStatus() == 0 ? 1 : 0;
                        saveUnsaveJobRequest = new SaveUnsaveJobRequest(dataSet.get(listPosition).getId(), collStatus);
                        saveUnsaveJobRequest.callRequest(mActivity, new ApiObjectCallBack<AddRemoveJobResponse>() {
                            @Override
                            public void onSuccess(AddRemoveJobResponse data, List<AddRemoveJobResponse> dataArrayList, int status, String message) {
                                mHomeFragment.hideCoverNetworkLoading();
                                if (status == 200) {
                                    mHomeFragment.getEventBaseFragment().refreshHome();
                                    if (data.getStatus() == 0) {
                                        Toast.makeText(mHomeFragment.getActivity(), "Hủy lưu", Toast.LENGTH_SHORT).show();
                                        myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_save));
                                    } else if (data.getStatus() == 1) {
                                        Toast.makeText(mHomeFragment.getActivity(), "Đã lưu", Toast.LENGTH_SHORT).show();
                                        myViewHolder.imgStatus.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.ic_saved));
                                    }
                                }
                            }

                            @Override
                            public void onFail(int failCode, AddRemoveJobResponse data, List<AddRemoveJobResponse> dataArrayList, String message) {
                                mHomeFragment.hideCoverNetworkLoading();
                            }
                        });
                    }
                });
            }
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
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
        }
    }
}
