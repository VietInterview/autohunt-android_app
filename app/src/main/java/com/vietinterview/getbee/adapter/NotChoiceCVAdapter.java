package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.jobsresponse.JobList;
import com.vietinterview.getbee.customview.CheckableLinearLayout;
import com.vietinterview.getbee.fragments.ChoiceCVFragment;
import com.vietinterview.getbee.fragments.DetailCVFragment;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

public class NotChoiceCVAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    List<CareerResponse> mCareerResponses;
    private ChoiceCVFragment mChoiceCVFragment;
    JobList mJobList;

    public NotChoiceCVAdapter(ChoiceCVFragment choiceCVFragment, Context context, List<CareerResponse> careerResponses, JobList jobList) {
        mCareerResponses = careerResponses;
        inflater = LayoutInflater.from(context);
        this.mJobList = jobList;
        this.mChoiceCVFragment = choiceCVFragment;
    }

    @Override
    public int getCount() {
        return mCareerResponses.size();
    }

    @Override
    public Object getItem(int i) {
        return mCareerResponses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final CareerResponse careerResponse = mCareerResponses.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_mycv, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.checkableLinearLayout = (CheckableLinearLayout) view.findViewById(R.id.llCheck);
            viewHolder.checkableLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentUtil.pushFragment(mChoiceCVFragment.getActivity(), mChoiceCVFragment, new DetailCVFragment().newInstance(mJobList), null);
                }
            });
            if (i % 2 == 0) {
                viewHolder.checkableLinearLayout.setBackgroundResource(R.color.browse);
            } else {
                viewHolder.checkableLinearLayout.setBackgroundResource(R.color.white);
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(careerResponse.getName());

        return view;
    }

    private class ViewHolder {
        TextView name;
        CheckableLinearLayout checkableLinearLayout;
    }
}
