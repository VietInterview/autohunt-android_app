package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.DeleteCVRequest;
import com.vietinterview.getbee.api.request.GetDetailCVRequest;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.detailcv.DetailCVResponse;
import com.vietinterview.getbee.api.response.detailjob.DetailJobResponse;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.OnLoadMoreListener;
import com.vietinterview.getbee.customview.RobotoRegularButton;
import com.vietinterview.getbee.fragments.BaseFragment;
import com.vietinterview.getbee.fragments.DetailResumeFragment;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * created by tindle
 * created time 16/8/12 下午10:55
 */
public class MyResumeChoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT,
        SHOWING_SECONDARY_CONTENT
    }

    private DetailJobResponse detailJobResponse;
    private DeleteCVRequest deleteCVRequest;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<CvList> cvLists;
    private List<SwipedState> mItemSwipedStates;
    private Context mContext;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private BaseFragment baseFragment;
    private int mTotal;
    private GetDetailCVRequest getDetailCVRequest;

    public MyResumeChoiceAdapter(RecyclerView recyclerView, Context context, int total, DetailJobResponse detailJobResponse, ArrayList<CvList> cvLists, BaseFragment homeFragment) {
        this.cvLists = cvLists;
        this.mContext = context;
        this.mTotal = total;
        this.baseFragment = homeFragment;
        this.detailJobResponse = detailJobResponse;
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
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item_mycv_applied, parent, false);
            ViewPagerAdapter adapter = new ViewPagerAdapter();
            ((ViewPager) v.findViewById(R.id.viewPager)).setAdapter(adapter);

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

    public void setmTotal(int mTotal) {
        this.mTotal = mTotal;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvName)).setText(cvLists.get(position).getFullName());
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCarrer)).setText(cvLists.get(position).getCareerName());
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvCountCV)).setText(this.mTotal + " " + mContext.getResources().getString(R.string.cv_found));
            ((LinearLayout) ((MyViewHolder) holder).mView.findViewById(R.id.llStatus)).setVisibility(View.GONE);
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvJobTitle)).setVisibility(View.GONE);
            ((TextView) ((MyViewHolder) holder).mView.findViewById(R.id.tvDate)).setText(mContext.getResources().getString(R.string.update) + " " + DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(cvLists.get(position).getUpdatedDate()) + ""));
            ((RelativeLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDetailCV(cvLists.get(position).getId());
                }
            });
//            ((ViewPager) ((MyViewHolder) holder).mView).setCurrentItem(mItemSwipedStates.get(position).ordinal());
            if (position % 2 == 0) {
                ((RelativeLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.background_press_2));
            } else {
                ((RelativeLayout) ((MyViewHolder) holder).mView.findViewById(R.id.primaryContentCardView)).setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.background_press));
            }
            ((RobotoRegularButton) ((MyViewHolder) holder).mView.findViewById(R.id.btnDeleteCV)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    baseFragment.showCoverNetworkLoading();
                    deleteCVRequest = new DeleteCVRequest(cvLists.get(position).getId());
                    deleteCVRequest.callRequest(mContext, new ApiObjectCallBack() {
                        @Override
                        public void onSuccess(int status, Object data, List dataArrayList, String message) {
                            baseFragment.hideCoverNetworkLoading();
                            baseFragment.getEventBaseFragment().refreshMyCV();
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFail(int status, Object dataFail, List listDataFail, String message) {
                            baseFragment.hideCoverNetworkLoading();
                        }
                    });
                }
            });
            baseFragment.getArrayBaseRequest().add(deleteCVRequest);
//            ((ViewPager) ((MyViewHolder) holder).mView).setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                int previousPagePosition = 0;
//
//                @Override
//                public void onPageScrolled(int pagePosition, float positionOffset, int positionOffsetPixels) {
//                    if (pagePosition == previousPagePosition)
//                        return;
//                    switch (pagePosition) {
//                        case 0:
//                            mItemSwipedStates.set(position, SwipedState.SHOWING_PRIMARY_CONTENT);
//                            break;
//                        case 1:
//                            mItemSwipedStates.set(position, SwipedState.SHOWING_SECONDARY_CONTENT);
//                            break;
//                    }
//                    previousPagePosition = pagePosition;
//                }
//
//                @Override
//                public void onPageSelected(int pagePosition) {
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                }
//            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return cvLists.size();
    }

    public void addItem(CvList cvList) {
        cvLists.add(cvList);
        mItemSwipedStates.add(SwipedState.SHOWING_PRIMARY_CONTENT);
        notifyItemInserted(cvLists.size());
    }

    public void getDetailCV(int id) {
        baseFragment.showCoverNetworkLoading();
        getDetailCVRequest = new GetDetailCVRequest(id);
        getDetailCVRequest.callRequest(baseFragment.getActivity(), new ApiObjectCallBack<DetailCVResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, DetailCVResponse data, List<DetailCVResponse> dataArrayList, String message) {
                baseFragment.hideCoverNetworkLoading();
                if (baseFragment.isAdded()) {
                    FragmentUtil.pushFragment(baseFragment.getActivity(), baseFragment, new DetailResumeFragment().newInstance(detailJobResponse, data), null);

                }
            }

            @Override
            public void onFail(int failCode, ErrorResponse errorResponse, List<ErrorResponse> dataArrayList, String message) {
                baseFragment.hideCoverNetworkLoading();
                if (baseFragment.isAdded()) {
                    DialogUtil.showDialog(baseFragment.getActivity(), baseFragment.getResources().getString(R.string.noti_title), message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FragmentUtil.popBackStack(baseFragment);
                        }
                    });
                }
            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter {

        public Object instantiateItem(ViewGroup collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.primaryContentCardView;
                    break;
                case 1:
                    resId = R.id.secondaryContentFrameLayout;
                    break;
            }
            return collection.findViewById(resId);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }
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