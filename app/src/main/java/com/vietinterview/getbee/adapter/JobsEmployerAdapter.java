package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.GetDetailJobCustomerRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailjobcustomer.DetailJobCustomerResponse;
import com.vietinterview.getbee.api.response.jobcustomer.JobList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.customview.RobotoRegularButton;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.ResumesEmployerFragment;
import com.vietinterview.getbee.fragments.DetailJobCustomerFragment;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

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
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

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
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setText(this.mTotal == 0 ? mContext.getResources().getString(R.string.no_cv_upload) : this.mTotal + " " + mContext.getResources().getString(R.string.cv_found));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvname)).setText(jobLists.get(position).getJobTitle());
            if (jobLists.get(position).getStatus() == 1 && jobLists.get(position).getLimited() > 0 && jobLists.get(position).getLimited() < 8) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText("Sắp hết hạn");
            } else if (jobLists.get(position).getStatus() == 1 && jobLists.get(position).getLimited() > 7) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText("Còn " + jobLists.get(position).getLimited() + "ngày");
            } else if (jobLists.get(position).getStatus() == 1 && jobLists.get(position).getLimited() == 0) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText("Đã hết hạn");
            } else if (jobLists.get(position).getStatus() == 0) {
                ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText("Nháp");
            }
//            ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.llStatus)).setVisibility(View.GONE);
//            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvDate)).setText(mContext.getResources().getString(R.string.update) + " " + DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(jobLists.get(position).getUpdatedDate()) + ""));
            ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    getDetailCV(jobLists.get(position).getId());
                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new ResumesEmployerFragment().newInstance(jobLists.get(position)), null);
                }
            });
            binderHelper.bind((SwipeRevealLayout) ((MyViewHolder) holder).swipeLayout, jobLists.get(position).getId().toString());
//            if (position % 2 == 0) {
//                ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setBackgroundColor(mContext.getResources().getColor(R.color.row_not_white));
//            } else {
//                ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setBackgroundColor(mContext.getResources().getColor(R.color.white));
//            }
            ((RobotoRegularButton) ((MyViewHolder) holder).mView.findViewById(R.id.btnShowDetailJob)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    baseFragment.showCoverNetworkLoading();
                    getDetailCV(jobLists.get(position).getId());
                }
            });
//            baseFragment.getArrayBaseRequest().add(deleteCVRequest);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    @Override
    public int getItemCount() {
        return jobLists.size();
    }

    public void getDetailCV(int id) {
        baseFragment.showCoverNetworkLoading();
        getDetailJobCustomerRequest = new GetDetailJobCustomerRequest(id);
        getDetailJobCustomerRequest.callRequest(baseFragment.getActivity(), new ApiObjectCallBack<DetailJobCustomerResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailJobCustomerResponse data, List<DetailJobCustomerResponse> dataArrayList, String message) {
                baseFragment.hideCoverNetworkLoading();
                if (baseFragment.isAdded()) {
                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new DetailJobCustomerFragment().newInstance(data), null);
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
        private SwipeRevealLayout swipeLayout;

        public MyViewHolder(View v) {
            super(v);
            mView = v;
            swipeLayout = (SwipeRevealLayout) v.findViewById(R.id.swipe_layout);
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