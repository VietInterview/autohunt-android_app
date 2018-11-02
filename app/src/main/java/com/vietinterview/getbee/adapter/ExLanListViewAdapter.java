package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.LstLanguage;

import java.util.HashMap;
import java.util.List;

public class ExLanListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<LstLanguage> listDataGroup;
    private HashMap<String, List<LstLanguage>> listDataChild;
    private LinearLayout llInfoChild;
    private LinearLayout llInfoGroup;

    public ExLanListViewAdapter(Context context, List<LstLanguage> listDataGroup, HashMap<String, List<LstLanguage>> listChildData) {
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getLanguageName()).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final LstLanguage lstLanguage = (LstLanguage) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_lan_row_child, null);
        }
        llInfoChild = convertView.findViewById(R.id.llInfoChild);
        TextView tvListen = convertView.findViewById(R.id.tvListen);
        TextView tvSpeak = convertView.findViewById(R.id.tvSpeak);
        TextView tvRead = convertView.findViewById(R.id.tvRead);
        TextView tvWrite = convertView.findViewById(R.id.tvWrite);
        tvListen.setText(genString(lstLanguage.getListen()));
        tvSpeak.setText(genString(lstLanguage.getSpeak()));
        tvRead.setText(genString(lstLanguage.getRead()));
        tvWrite.setText(genString(lstLanguage.getWrite()));
        if (childPosition == this.listDataChild.get(this.listDataGroup.get(groupPosition).getLanguageName()).size() - 1) {
            convertView.setPadding(32, 0, 32, 32);
        } else
            convertView.setPadding(0, 0, 0, 0);
        return convertView;
    }

    public String genString(int value) {
        String valueString;
        switch (value) {
            case 1:
                valueString = context.getResources().getString(R.string.good);
                break;
            case 2:
                valueString = context.getResources().getString(R.string.rather);
                break;
            case 3:
                valueString = context.getResources().getString(R.string.medium);
                break;
            case 4:
                valueString = context.getResources().getString(R.string.least);
                break;

            default:
                valueString = "";
                break;
        }
        return valueString;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getLanguageName()).size();
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
        LstLanguage lstLanguage = (LstLanguage) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_lan_row_group, null);
        }
        llInfoGroup = convertView.findViewById(R.id.llInfoGroup);
        TextView tvLanguage = convertView.findViewById(R.id.tvLanguage);
        tvLanguage.setText(lstLanguage.getLanguageName());
        if (isExpanded)
            convertView.setPadding(32, 32, 32, 0);
        else
            convertView.setPadding(32, 32, 32, 32);
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

    public LinearLayout getLlInfoChild() {
        return llInfoChild;
    }

    public LinearLayout getLlInfoGroup() {
        return llInfoGroup;
    }
}
