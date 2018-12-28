package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.DeleteCVRequest;
import com.vietinterview.getbee.api.response.detailcvcustomer.LstLanguage;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * created by tindle
 * created time 16/8/12 下午10:55
 */
public class LanDetailResumeCustomerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT,
        SHOWING_SECONDARY_CONTENT
    }

    private DetailJobResponse detailJobResponse;
    private DeleteCVRequest deleteCVRequest;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<LstLanguage> lstLanguages;
    private List<SwipedState> mItemSwipedStates;
    private Context mContext;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private BaseFragment baseFragment;

    public LanDetailResumeCustomerAdapter(RecyclerView recyclerView, Context context, List<LstLanguage> lstLanguages, BaseFragment homeFragment) {
        this.lstLanguages = lstLanguages;
        this.mContext = context;
        this.baseFragment = homeFragment;
        mItemSwipedStates = new ArrayList<>();
        for (int i = 0; i < lstLanguages.size(); i++) {
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
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item_lan_detailcv_ntd, parent, false);
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
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvLanguageName)).setText(lstLanguages.get(position).getLanguageName());
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvListen)).setText(StringUtils.genStringLan(lstLanguages.get(position).getListen(), mContext));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvSpeak)).setText(StringUtils.genStringLan(lstLanguages.get(position).getSpeak(), mContext));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvRead)).setText(StringUtils.genStringLan(lstLanguages.get(position).getRead(), mContext));
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvWrite)).setText(StringUtils.genStringLan(lstLanguages.get(position).getWrite(), mContext));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return lstLanguages.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public MyViewHolder(View v) {
            super(v);
            mView = v;
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