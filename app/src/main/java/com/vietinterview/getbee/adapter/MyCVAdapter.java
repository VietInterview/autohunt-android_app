package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * created by tindle
 * created time 16/8/12 下午10:55
 */
public class MyCVAdapter extends RecyclerView.Adapter<MyCVAdapter.ViewHolder> {

    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT,
        SHOWING_SECONDARY_CONTENT
    }

    private ArrayList<CvList> cvLists;
    private List<SwipedState> mItemSwipedStates;
    private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public MyCVAdapter(Context context, ArrayList<CvList> cvLists) {
        this.cvLists = cvLists;
        this.mContext = context;
        mItemSwipedStates = new ArrayList<>();
        for (int i = 0; i < cvLists.size(); i++) {
            mItemSwipedStates.add(i, SwipedState.SHOWING_PRIMARY_CONTENT);
        }
    }

    @Override
    public MyCVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewPager v = (ViewPager) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_mycv, parent, false);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        ((ViewPager) v.findViewById(R.id.viewPager)).setAdapter(adapter);

        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        v.getLayoutParams().width = displayMetrics.widthPixels;
        v.requestLayout();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ((TextView) holder.mView.findViewById(R.id.tvName)).setText(cvLists.get(position).getFullName());
        ((TextView) holder.mView.findViewById(R.id.tvCarrer)).setText(cvLists.get(position).getCareerName());
        ((TextView) holder.mView.findViewById(R.id.tvDate)).setText("Cập nhật: " + DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(cvLists.get(position).getUpdatedDate())+""));
        ((ViewPager) holder.mView).setCurrentItem(mItemSwipedStates.get(position).ordinal());
        if (position % 2 == 0) {
            ((RelativeLayout) holder.mView.findViewById(R.id.primaryContentCardView)).setBackgroundColor(mContext.getResources().getColor(R.color.row_not_white));
        } else {
            ((RelativeLayout) holder.mView.findViewById(R.id.primaryContentCardView)).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        ((ViewPager) holder.mView).setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int previousPagePosition = 0;

            @Override
            public void onPageScrolled(int pagePosition, float positionOffset, int positionOffsetPixels) {
                if (pagePosition == previousPagePosition)
                    return;

                switch (pagePosition) {
                    case 0:
                        mItemSwipedStates.set(position, SwipedState.SHOWING_PRIMARY_CONTENT);
                        break;
                    case 1:
                        mItemSwipedStates.set(position, SwipedState.SHOWING_SECONDARY_CONTENT);
                        break;

                }
                previousPagePosition = pagePosition;

                Log.i("MyAdapter", "PagePosition " + position + " set to " + mItemSwipedStates.get(position).ordinal());
            }

            @Override
            public void onPageSelected(int pagePosition) {
                //This method keep incorrectly firing as the RecyclerView scrolls.
                //Use the one above instead
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
}