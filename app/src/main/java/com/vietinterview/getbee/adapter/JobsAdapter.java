package com.vietinterview.getbee.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.jobsresponse.JobList;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.HomeFragment;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;


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

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvjobTitle;
        TextView tvCompanyName;
        TextView tvCarrer;
        TextView tvListCity;
        TextView tvExpireDate;
        TextView tvFee;
        ImageView imgBussiness;
        ImageView imgStatus;

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
        }
    }

    public JobsAdapter(RecyclerView recyclerView, ArrayList<JobList> data, BaseFragment homeFragment, FragmentActivity activity) {
        this.dataSet = data;
        this.mHomeFragment = homeFragment;
        this.mActivity = activity;
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

            view.setOnClickListener(mHomeFragment.myOnClickListener);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int listPosition) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.tvjobTitle.setText(StringUtils.nullStrToEmpty(dataSet.get(listPosition).getJobTitle()));
            myViewHolder.tvCompanyName.setText("Cannon Viêt Nam");
            myViewHolder.tvCarrer.setText("IT, Phần mềm");
            myViewHolder.tvListCity.setText("Hà Nội");
            myViewHolder.tvExpireDate.setText(DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(dataSet.get(listPosition).getExpireDate()) + ""));
            myViewHolder.tvFee.setText(dataSet.get(listPosition).getFee() + "");
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
}
