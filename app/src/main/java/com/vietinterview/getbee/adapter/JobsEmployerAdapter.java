package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetDetailJobCustomerRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailjobcustomer.DetailJobCustomerResponse;
import com.vietinterview.getbee.api.response.jobcustomer.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.customview.RobotoRegularButton;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.DetailJobCustomerFragment;
import com.vietinterview.getbee.fragments.ResumesEmployerFragment;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * created by tindle
 * created time 16/8/12 下午10:55
 */
public class JobsEmployerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT,
        SHOWING_SECONDARY_CONTENT
    }

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<JobList> jobLists;
    private List<SwipedState> mItemSwipedStates;
    private Context mContext;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private GetDetailJobCustomerRequest getDetailJobCustomerRequest;
    private boolean isLoading;
    private BaseFragment baseFragment;
    private int mTotal;

    public JobsEmployerAdapter(RecyclerView recyclerView, Context context, int total, ArrayList<JobList> cvLists, BaseFragment homeFragment) {
        this.jobLists = cvLists;
        this.mContext = context;
        this.mTotal = total;
        this.baseFragment = homeFragment;
        mItemSwipedStates = new ArrayList<>();
        for (int i = 0; i < cvLists.size(); i++) {
            mItemSwipedStates.add(i, SwipedState.SHOWING_PRIMARY_CONTENT);
        }
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
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_job_employer, parent, false);
            DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            v.getLayoutParams().width = displayMetrics.widthPixels;
            v.requestLayout();
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvcountJobOffer)).setText(jobLists.get(position).getCountOffer() + "");
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvsumJob)).setText("/" + jobLists.get(position).getCountCv() + "");
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setText(this.mTotal == 0 ? mContext.getResources().getString(R.string.no_data) : this.mTotal + " " + mContext.getResources().getString(R.string.job_found));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvname)).setText(jobLists.get(position).getJobTitle());
            if (jobLists.get(position).getStatus() == 1 && jobLists.get(position).getLimited() > 0 && jobLists.get(position).getLimited() < 8) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(mContext.getResources().getString(R.string.expiring_soon));
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(mContext.getResources().getColor(R.color.red));
                ((ImageView) ((MyViewHolder) holder).mView.findViewById(R.id.imgDeadline)).setColorFilter(mContext.getResources().getColor(R.color.red));
            } else if (jobLists.get(position).getStatus() == 1 && jobLists.get(position).getLimited() > 7) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(jobLists.get(position).getLimited() + " " + mContext.getResources().getString(R.string.dayleft));
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(mContext.getResources().getColor(R.color.not_like));
                ((ImageView) ((MyViewHolder) holder).mView.findViewById(R.id.imgDeadline)).setColorFilter(mContext.getResources().getColor(R.color.not_like));
            } else if (jobLists.get(position).getStatus() == 2) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(mContext.getResources().getString(R.string.expired));
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(mContext.getResources().getColor(R.color.not_like));
                ((ImageView) ((MyViewHolder) holder).mView.findViewById(R.id.imgDeadline)).setColorFilter(mContext.getResources().getColor(R.color.not_like));
            } else if (jobLists.get(position).getStatus() == 0) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(mContext.getResources().getString(R.string.draft));
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(mContext.getResources().getColor(R.color.not_like));
                ((ImageView) ((MyViewHolder) holder).mView.findViewById(R.id.imgDeadline)).setColorFilter(mContext.getResources().getColor(R.color.not_like));
            } else if (jobLists.get(position).getStatus() == 3) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(mContext.getResources().getString(R.string.closed));
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(mContext.getResources().getColor(R.color.not_like));
                ((ImageView) ((MyViewHolder) holder).mView.findViewById(R.id.imgDeadline)).setColorFilter(mContext.getResources().getColor(R.color.not_like));
            } else if (jobLists.get(position).getStatus() == 5) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(mContext.getResources().getString(R.string.locked));
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(mContext.getResources().getColor(R.color.not_like));
                ((ImageView) ((MyViewHolder) holder).mView.findViewById(R.id.imgDeadline)).setColorFilter(mContext.getResources().getColor(R.color.not_like));
            }
            ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPrefUtils.putInt("jobIdCus", jobLists.get(position).getId());
                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new ResumesEmployerFragment(), null);
                }
            });
//            binderHelper.bind((SwipeLayout) ((MyViewHolder) holder).swipeLayout, jobLists.get(position).getId().toString());
            ((RobotoRegularButton) ((MyViewHolder) holder).mView.findViewById(R.id.btnShowDetailJob)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    baseFragment.showCoverNetworkLoading();
                    getDetailCV(jobLists.get(position).getId(), jobLists.get(position).getLimited());
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return jobLists.size();
    }

    public void getDetailCV(int id, final int limited) {
        baseFragment.showCoverNetworkLoading();
        getDetailJobCustomerRequest = new GetDetailJobCustomerRequest(id);
        getDetailJobCustomerRequest.callRequest(baseFragment.getActivity(), new ApiObjectCallBack<DetailJobCustomerResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailJobCustomerResponse data, List<DetailJobCustomerResponse> dataArrayList, String message) {
                baseFragment.hideCoverNetworkLoading();
                if (baseFragment.isAdded()) {
                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new DetailJobCustomerFragment().newInstance(data, limited), null);
                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                baseFragment.hideCoverNetworkLoading();
                if (baseFragment.isAdded()) {
                    DialogUtil.showDialog(baseFragment.getActivity(), baseFragment.getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                }
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        private CardView card_view1;
        private SwipeLayout swipeLayout;

        public MyViewHolder(View v) {
            super(v);
            mView = v;
            swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe_layout);
            card_view1 = v.findViewById(R.id.card_view1);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
}