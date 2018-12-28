package com.vietinterview.getbee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.R;
import com.vietinterview.getbee.model.ImageCustomer;
import com.vietinterview.getbee.utils.ShowImageUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by hiepnguyennghia on 12/13/18.
 * Copyright © 2018 Vietinterview. All rights reserved.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


    List<ImageCustomer> horizontalList = Collections.emptyList();
    Context context;


    public HorizontalAdapter(List<ImageCustomer> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtview;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageview);
            txtview = (TextView) view.findViewById(R.id.txtview);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menu, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        holder.imageView.setImageResource(horizontalList.get(position).imageId);
        ShowImageUtils.showImage(context, horizontalList.get(position).imageId, R.drawable.ic_company_null, holder.imageView);
        holder.txtview.setText(horizontalList.get(position).txt);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String list = horizontalList.get(position).txt.toString();
            }

        });

    }


    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}