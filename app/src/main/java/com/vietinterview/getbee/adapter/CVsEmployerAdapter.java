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
import com.vietinterview.getbee.api.request.GetDetailCVByJobCustomerRequest;
import com.vietinterview.getbee.api.request.GetDetailCVRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailcvcustomer.DetailCVCustomerResponse;
import com.vietinterview.getbee.api.response.listcvcustomer.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.customview.RobotoRegularButton;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.DetailCVFragment;
import com.vietinterview.getbee.fragments.DetailCVNTDFragment;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * created by tindle
 * created time 16/8/12 下午10:55
 */
public class CVsEmployerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT,
        SHOWING_SECONDARY_CONTENT
    }

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<CvList> cvLists;
    private List<SwipedState> mItemSwipedStates;
    private Context mContext;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private GetDetailCVByJobCustomerRequest getDetailCVByJobCustomerRequest;
    private boolean isLoading;
    private BaseFragment baseFragment;
    private int mTotal;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

    public CVsEmployerAdapter(RecyclerView recyclerView, Context context, int total, ArrayList<CvList> cvLists, BaseFragment homeFragment) {
        this.cvLists = cvLists;
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
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cv_employer, parent, false);
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
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvName)).setText(cvLists.get(position).getFullName());
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCarrer)).setText(cvLists.get(position).getCountDay() + " " + baseFragment.getResources().getString(R.string.before_day));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setText(this.mTotal == 0 ? mContext.getResources().getString(R.string.no_cv_upload) : this.mTotal + " " + mContext.getResources().getString(R.string.cv_found));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
//            ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.llStatus)).setVisibility(View.GONE);
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setText(StringUtils.genStringStatusCVNTD(cvLists.get(position).getStatus(), baseFragment.getActivity()).getStatus());
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setBackgroundDrawable(StringUtils.genStringStatusCVNTD(cvLists.get(position).getStatus(), baseFragment.getActivity()).getDrawable());
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvStatus)).setTextColor(StringUtils.genStringStatusCVNTD(cvLists.get(position).getStatus(), baseFragment.getActivity()).getColor());
            ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    getDetailCV(cvLists.get(position).getId());
//                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new CVsEmployerFragment(), null);
                }
            });
            binderHelper.bind((SwipeRevealLayout) ((MyViewHolder) holder).swipeLayout, cvLists.get(position).getId().toString());
            ((RobotoRegularButton) ((MyViewHolder) holder).mView.findViewById(R.id.btnShowDetailJob)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDetailCV(cvLists.get(position).getId());
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
        return cvLists.size();
    }

    public void getDetailCV(int id) {
        baseFragment.showCoverNetworkLoading();
        getDetailCVByJobCustomerRequest = new GetDetailCVByJobCustomerRequest(id);
        getDetailCVByJobCustomerRequest.callRequest(baseFragment.getActivity(), new ApiObjectCallBack<DetailCVCustomerResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailCVCustomerResponse data, List<DetailCVCustomerResponse> dataArrayList, String message) {
                baseFragment.hideCoverNetworkLoading();
                if (baseFragment.isAdded()) {
                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new DetailCVNTDFragment().newInstance(data), null);
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