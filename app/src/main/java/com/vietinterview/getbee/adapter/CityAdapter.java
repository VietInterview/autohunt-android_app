package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.customview.CheckableLinearLayout;

import java.util.List;

public class CityAdapter extends BaseAdapter {
//    private String[] data;
    private LayoutInflater inflater;
    List<CityResponse> cityResponses;

    public CityAdapter(Context context, List<CityResponse> cityResponses) {
        this.cityResponses = cityResponses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cityResponses.size();
    }

    @Override
    public Object getItem(int i) {
        return cityResponses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final CityResponse cityResponse = cityResponses.get(i);

        if (view == null) {
            view = inflater.inflate(R.layout.list_item, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
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

        viewHolder.name.setText(cityResponse.getName());

        return view;
    }

    private class ViewHolder {
        TextView name;
        CheckableLinearLayout checkableLinearLayout;
    }
}
