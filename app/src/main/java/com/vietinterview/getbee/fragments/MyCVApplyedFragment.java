package com.vietinterview.getbee.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.adapter.MyCVAdapter;
import com.vietinterview.getbee.model.DropDownItems;

import java.util.ArrayList;

/**
 * Created by hiepnguyennghia on 10/12/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class MyCVApplyedFragment extends BaseFragment {
    private ArrayList<String> titles = new ArrayList<>();
    private MyCVAdapter adapter;
    private RecyclerView recyclerView;
//    PopupWindow popupWindow;
//    private ArrayList<DropDownItems> items = new ArrayList<DropDownItems>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycv_applyed;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        getEventBaseFragment().doFillBackground("CV của tôi");
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        titles.add("Jon Snow");
        titles.add("Arya Stark");
        titles.add("Daenerys Targaryen");
        titles.add("Tyrion Lannister");
        titles.add("Bran Stark");
        adapter = new MyCVAdapter(getActivity(), titles);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        items.add(new DropDownItems("Settings", 0, false));
//        items.add(new DropDownItems("Likes", 10, true));
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRestore() {

    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void onSaveState(Bundle bundle) {

    }

    @Override
    protected void onRestoreState(Bundle bundle) {

    }

//    @Override
//    protected void processOnBackPress() {
//    }
//
//    @Override
//    protected Drawable getIconLeft() {
//        return getResources().getDrawable(R.drawable.ic_menu);
//    }
//
//    @Override
//    protected void processCustomToolbar() {
//        loadMenuLeft();
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_my_cv, menu);

        ImageButton item1 = (ImageButton) menu.findItem(R.id.more).getActionView().findViewById(R.id.dropDowmImageBtn);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPopUpWindow(v);
            }
        });

        return;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void showPopUpWindow(View v) {
//        DropDownAdapter adapter = new DropDownAdapter(v.getContext(), items);
//        popupWindow = new PopupWindow(getActivity());
//        int[] colors = {0, 0xFFFF0000, 0}; // red for the example
//        ListView listViewSort = new ListView(getActivity());
//        listViewSort.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
//        listViewSort.setDividerHeight(1);
//        listViewSort.setAdapter(adapter);
//        listViewSort.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        popupWindow.setFocusable(true);
//        popupWindow.setWidth(500);
//        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(listViewSort);
//
//        popupWindow.showAsDropDown(v, 0, 0);
//    }

    public class DropDownAdapter extends BaseAdapter {

        Context context;
        ArrayList<DropDownItems> items;

        public DropDownAdapter(Context context, ArrayList<DropDownItems> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public DropDownItems getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DropDownItems item = getItem(position);

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_option, null);
            }

            final TextView itemText = (TextView) convertView.findViewById(R.id.item_name);
//            final Button itemImage = (Button) convertView.findViewById(R.id.dropDownItemImage);
//            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.chkState);
            itemText.setText(item.getTxt());
            itemText.setTag(item.getTxt());
//            itemImage.setText(String.valueOf(item.getValue()));
//            itemImage.setTag(item.getTxt());

//            if (!item.getShowValue()) {
//                itemImage.setVisibility(View.GONE);
//            } else {
//                itemImage.setVisibility(View.VISIBLE);
//            }

//            itemText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Clicked " + itemText.getTag(), Toast.LENGTH_LONG).show();
//
//                    if (popupWindow != null && popupWindow.isShowing()) {
//                        popupWindow.dismiss();
//                        popupWindow = null;
//                    }
//
//                }
//            });

            return convertView;
        }
    }
}
