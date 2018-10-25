package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.listcv.CvList;
import com.vietinterview.getbee.customview.CheckableLinearLayout;
import com.vietinterview.getbee.utils.DateUtil;

import java.util.List;

public class ChoiceCVAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    List<CvList> cvLists;

    public ChoiceCVAdapter(Context context, List<CvList> cvLists1) {
        cvLists = cvLists1;
        inflater = LayoutInflater.from(context);
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
        final CvList careerResponse = cvLists.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_choice_mycv, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.item_carrer = (TextView) view.findViewById(R.id.item_carrer);
            viewHolder.item_date = (TextView) view.findViewById(R.id.item_date);
            viewHolder.name.setText(careerResponse.getFullName());
            viewHolder.item_carrer.setText(careerResponse.getCareerName());
            viewHolder.item_date.setText("Cập nhật: " + DateUtil.convertToMyFormat(DateUtil.convertToGMTDate(careerResponse.getUpdatedDate()) + ""));
            viewHolder.checkableLinearLayout = (CheckableLinearLayout) view.findViewById(R.id.llCheck);
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
