package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.response.detailcv.LstEmploymentHi;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.StringUtils;

import java.util.HashMap;
import java.util.List;

public class ExExpListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<LstEmploymentHi> listDataGroup;
    private HashMap<String, List<LstEmploymentHi>> listDataChild;
    private LinearLayout llInfoChild;
    private LinearLayout llInfoGroup;
    private String mCurrencyName;

    public ExExpListViewAdapter(Context context, List<LstEmploymentHi> listDataGroup, HashMap<String, List<LstEmploymentHi>> listChildData, String currencyName) {
        this.context = context;
        this.listDataGroup = listDataGroup;
        this.listDataChild = listChildData;
        this.mCurrencyName = currencyName;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getCompanyName()).get(childPosititon);
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
        llInfoChild = convertView.findViewById(R.id.llInfoChild);
        TextView tvCompanyName = convertView.findViewById(R.id.tvCompanyName);
        TextView tvQuantityEmploy = convertView.findViewById(R.id.tvQuantityEmploy);
        TextView tvJobTitle = convertView.findViewById(R.id.tvJobTitle);
        TextView tvWorkTime = convertView.findViewById(R.id.tvWorkTime);
        TextView tvSalary = convertView.findViewById(R.id.tvSalary);
        TextView tvJobDes = convertView.findViewById(R.id.tvJobDes);
        TextView tvAchievements = convertView.findViewById(R.id.tvAchievements);
        TextView tvCurrentJob = convertView.findViewById(R.id.tvCurrentJob);
        if (lstEmploymentHi.getCurrentJob() == null || lstEmploymentHi.getCurrentJob() == 0) {
            tvCurrentJob.setVisibility(View.GONE);
        } else {
            tvCurrentJob.setVisibility(View.VISIBLE);
        }
        tvCompanyName.setText(lstEmploymentHi.getCompanyName());
        if (lstEmploymentHi.getHumanResources() != null)
            tvQuantityEmploy.setText(StringUtils.genString(lstEmploymentHi.getHumanResources(), context));
        tvJobTitle.setText(lstEmploymentHi.getTitle());
        tvWorkTime.setText(DateUtil.convertToMyFormatVacant(lstEmploymentHi.getFromMonth() + "") + " - " + DateUtil.convertToMyFormatVacant(lstEmploymentHi.getToMonth() + ""));
        if (lstEmploymentHi.getSalary() != null)
            tvSalary.setText(StringUtils.filterCurrencyString(lstEmploymentHi.getSalary()) + " " + genStringCurrency(lstEmploymentHi.getSalaryCurency()));
        tvJobDes.setText(lstEmploymentHi.getJobDescription());
        tvAchievements.setText(lstEmploymentHi.getAchievement());
        return convertView;
    }

    public String genStringCurrency(int value) {
        String valueString;
        switch (value) {
            case 1:
                valueString = "VND";
                break;
            case 2:
                valueString = "USD";
                break;
            case 3:
                valueString = "JPY";
                break;
            default:
                valueString = "";
                break;
        }
        return valueString;
    }

    public String genString(int value) {
        String valueString;
        switch (value) {
            case 1:
                valueString = context.getResources().getString(R.string.below_50);
                break;
            case 2:
                valueString = context.getResources().getString(R.string.from_50_to_100);
                break;
            case 3:
                valueString = context.getResources().getString(R.string.above_100);
                break;
            case 4:
                valueString = context.getResources().getString(R.string.above_500);
                break;
            case 5:
                valueString = context.getResources().getString(R.string.average);
                break;
            default:
                valueString = "";
                break;
        }
        return valueString;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataGroup.get(groupPosition).getCompanyName()).size();
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
        llInfoGroup = convertView.findViewById(R.id.llInfoGroup);
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

    public LinearLayout getLlInfoChild() {
        return llInfoChild;
    }

    public LinearLayout getLlInfoGroup() {
        return llInfoGroup;
    }
}
