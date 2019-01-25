package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.CountryResponse;
import com.vietinterview.getbee.customview.CheckableLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    List<CountryResponse> countryResponses;
    List<CountryResponse> countryResponsesFilter;

    public CountryAdapter(Context context, List<CountryResponse> countryResponses1) {
        this.countryResponses = countryResponses1;
        countryResponsesFilter = countryResponses1;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return countryResponsesFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return countryResponsesFilter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final CountryResponse countryResponse = countryResponsesFilter.get(i);

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

        viewHolder.name.setText(countryResponse.getName());

        return view;
    }

    private class ViewHolder {
        TextView name;
        CheckableLinearLayout checkableLinearLayout;
    }

    public void setFilter(List<CountryResponse> countryResponses) {
        countryResponsesFilter = new ArrayList<>();
        countryResponsesFilter.addAll(countryResponses);
        notifyDataSetChanged();
    }
}
