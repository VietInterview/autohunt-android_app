package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.LstEducationHi;

import java.util.HashMap;
import java.util.List;

public class ExLvListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<LstEducationHi> listDataGroup;
    private HashMap<String, List<LstEducationHi>> listDataChild;

    public ExLvListViewAdapter(Context context, List<LstEducationHi> listDataGroup, HashMap<String, List<LstEducationHi>> listChildData) {
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getSchool()).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final LstEducationHi lstEducationHi = (LstEducationHi) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_lv_row_child, null);
        }
        TextView tvCertificate = convertView.findViewById(R.id.tvCertificate);
        TextView tvSchoolName = convertView.findViewById(R.id.tvSchoolName);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        TextView tvSpecialize = convertView.findViewById(R.id.tvSpecialize);
        TextView tvGraduationType = convertView.findViewById(R.id.tvGraduationType);
        tvCertificate.setText(lstEducationHi.getSubject());
        tvSchoolName.setText(lstEducationHi.getSchool());
        tvTime.setText(lstEducationHi.getFromMonth() + " - " + lstEducationHi.getToMonth());
        tvSpecialize.setText(lstEducationHi.getCareer());
        tvGraduationType.setText(lstEducationHi.getGraduationTypeName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getSchool()).size();
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
        LstEducationHi lstEducationHi = (LstEducationHi) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_lv_row_group, null);
        }
        TextView tvSchoolName = convertView.findViewById(R.id.tvSchoolName);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        tvSchoolName.setText(lstEducationHi.getSchool());
        tvTime.setText(lstEducationHi.getFromMonth() + " - " + lstEducationHi.getToMonth());
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
