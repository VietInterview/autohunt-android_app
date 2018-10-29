package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.jobs.JobList;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.customview.CheckableLinearLayout;
import com.vietinterview.getbee.fragments.ChoiceCVFragment;
import com.vietinterview.getbee.fragments.DetailCVFragment;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.FragmentUtil;

import java.util.List;

public class NotChoiceCVAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    List<CvList> cvLists;
    private ChoiceCVFragment mChoiceCVFragment;
    JobList mJobList;

    public NotChoiceCVAdapter(ChoiceCVFragment choiceCVFragment, Context context, List<CvList> cvLists1, JobList jobList) {
        cvLists = cvLists1;
        inflater = LayoutInflater.from(context);
        this.mJobList = jobList;
        this.mChoiceCVFragment = choiceCVFragment;
    }

    @Override
    public int getCount() {
        return cvLists.size();
    }

    @Override
    public Object getItem(int i) {
        return cvLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final CvList cvList = cvLists.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_mycv, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.item_carrer = (TextView) view.findViewById(R.id.item_carrer);
            viewHolder.item_date = (TextView) view.findViewById(R.id.item_date);
            viewHolder.name.setText(cvList.getFullName());
            viewHolder.item_carrer.setText(cvList.getCareerName());
            viewHolder.item_date.setText(inflater.getContext().getResources().getString(R.string.update) + " " + DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(cvList.getUpdatedDate()) + ""));
            viewHolder.checkableLinearLayout = (CheckableLinearLayout) view.findViewById(R.id.llCheck);
            viewHolder.checkableLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentUtil.pushFragment(mChoiceCVFragment.getActivity(), mChoiceCVFragment, new DetailCVFragment().newInstance(mJobList, cvList.getId()), null);
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


        return view;
    }

    private class ViewHolder {
        TextView name;
        TextView item_carrer;
        TextView item_date;
        CheckableLinearLayout checkableLinearLayout;
    }
}
