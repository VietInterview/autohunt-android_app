package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.LstEmploymentHi;

import java.util.HashMap;
import java.util.List;

public class ExExpListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<LstEmploymentHi> listDataGroup;
    private HashMap<String, List<LstEmploymentHi>> listDataChild;

    public ExExpListViewAdapter(Context context, List<LstEmploymentHi> listDataGroup, HashMap<String, List<LstEmploymentHi>> listChildData) {
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getTitle()).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final LstEmploymentHi lstEmploymentHi = (LstEmploymentHi) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_ex_row_child, null);
        }
        TextView tvCompanyName = convertView.findViewById(R.id.tvCompanyName);
        TextView tvQuantityEmploy = convertView.findViewById(R.id.tvQuantityEmploy);
        TextView tvJobTitle = convertView.findViewById(R.id.tvJobTitle);
        TextView tvWorkTime = convertView.findViewById(R.id.tvWorkTime);
        TextView tvSalary = convertView.findViewById(R.id.tvSalary);
        TextView tvJobDes = convertView.findViewById(R.id.tvJobDes);
        TextView tvAchievements = convertView.findViewById(R.id.tvAchievements);
        tvCompanyName.setText(lstEmploymentHi.getCompanyName());
        tvQuantityEmploy.setText("unknown");
        tvJobTitle.setText(lstEmploymentHi.getTitle());
        tvWorkTime.setText(lstEmploymentHi.getFromMonth() + " - " + lstEmploymentHi.getToMonth());
        tvSalary.setText(lstEmploymentHi.getSalary() + "");
        tvJobDes.setText(lstEmploymentHi.getJobDescription());
        tvAchievements.setText(lstEmploymentHi.getAchievement());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getTitle()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataGroup.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataGroup.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LstEmploymentHi lstEmploymentHi = (LstEmploymentHi) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_ex_row_group, null);
        }
        TextView tvJob = convertView.findViewById(R.id.tvJob);
        TextView tvCompany = convertView.findViewById(R.id.tvCompany);
        tvJob.setText(lstEmploymentHi.getTitle());
        tvCompany.setText(lstEmploymentHi.getCompanyName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
