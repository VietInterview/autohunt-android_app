package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailprocessresume.LstInterviewHi;
import com.vietinterview.getbee.api.response.detailprocessresume.LstOfferHi;
import com.vietinterview.getbee.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class ListOfferAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    List<LstOfferHi> lstInterviewHis;
    List<LstOfferHi> lstInterviewHisFilter;

    public ListOfferAdapter(Context context, List<LstOfferHi> cvStatuses1) {
        this.lstInterviewHis = cvStatuses1;
        lstInterviewHisFilter = cvStatuses1;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstInterviewHisFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return lstInterviewHisFilter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final LstOfferHi lstInterviewHi = lstInterviewHisFilter.get(i);

        if (view == null) {
            view = inflater.inflate(R.layout.interview_item_view, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.tvRound = (TextView) view.findViewById(R.id.tvRound);
            viewHolder.tvDate = (TextView) view.findViewById(R.id.tvDate);
            viewHolder.tvContentReason = (TextView) view.findViewById(R.id.tvContentReason);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvRound.setText(lstInterviewHi.getRound());
        viewHolder.tvDate.setText(DateUtil.convertToMyFormat3(lstInterviewHi.getWorkTime()));
        viewHolder.tvContentReason.setText(switchResult(lstInterviewHi.getStatus()));
        return view;
    }

    public String switchResult(int status) {
        String statusInterview = "";
        switch (status) {
            case 1:
                statusInterview = "Đạt";
                break;
            case 2:
                statusInterview = "Không đạt";
                break;
            case 3:
                statusInterview = "Ứng viên không đến";
                break;
            default:
                statusInterview = "Chưa có kết quả";
                break;
        }
        return statusInterview;
    }

    private class ViewHolder {
        TextView tvRound;
        TextView tvDate;
        TextView tvContentReason;
    }

    public void setFilter(List<LstOfferHi> lstInterviewHis) {
        lstInterviewHisFilter = new ArrayList<>();
        lstInterviewHisFilter.addAll(lstInterviewHis);
        notifyDataSetChanged();
    }
}
