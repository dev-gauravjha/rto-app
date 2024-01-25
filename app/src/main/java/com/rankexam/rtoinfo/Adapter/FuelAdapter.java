package com.rankexam.rtoinfo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;


import com.rankexam.rtoinfo.Model.FuelModel;
import com.rankexam.rtoinfo.R;

import java.util.ArrayList;

public class FuelAdapter extends RecyclerView.Adapter<FuelAdapter.ViewHolder> {
    Context context;
    ArrayList<FuelModel> list;

    public FuelAdapter(Context context2, ArrayList<FuelModel> arrayList) {
        this.context = context2;
        this.list = arrayList;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay_fuel_price, viewGroup, false));
    }

    @SuppressLint("RestrictedApi")
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FuelModel fuelModel = this.list.get(i);
        viewHolder.fuelName.setText(fuelModel.getFuelName());
        viewHolder.currently.setText(fuelModel.getCurrently());
        viewHolder.day.setText(fuelModel.getDay());
        viewHolder.month.setText(fuelModel.getMonth());
        viewHolder.year.setText(fuelModel.getYear());
        viewHolder.currentPrice.setText(fuelModel.getCurrentPrice());
        viewHolder.previousPrice.setText(fuelModel.getPreviousPrice());
        if (fuelModel.getPreviousPrice().contains("+")) {
            viewHolder.scale.setImageTintList(ColorStateList.valueOf(SupportMenu.CATEGORY_MASK));
            viewHolder.scale.setRotation(-90.0f);
        }
        if (i == 1) {
            viewHolder.bg.setBackgroundResource(R.drawable.bg_fuel2);
            viewHolder.bg2.setBackgroundResource(R.drawable.bg_fuel2_bg);
        } else if (i == 2) {
            viewHolder.bg.setBackgroundResource(R.drawable.bg_fuel3);
            viewHolder.bg2.setBackgroundResource(R.drawable.bg_fuel3_bg);
        } else if (i == 3) {
            viewHolder.bg.setBackgroundResource(R.drawable.bg_fuel4);
            viewHolder.bg2.setBackgroundResource(R.drawable.bg_fuel4_bg);
        }
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout bg;
        ConstraintLayout bg2;
        TextView currentPrice;
        TextView currently;
        TextView day;
        TextView fuelName;
        TextView month;
        TextView previousPrice;
        ImageView scale;
        TextView year;

        public ViewHolder(View view) {
            super(view);
            this.fuelName = (TextView) view.findViewById(R.id.FuelName);
            this.currently = (TextView) view.findViewById(R.id.current);
            this.day = (TextView) view.findViewById(R.id.day);
            this.month = (TextView) view.findViewById(R.id.month);
            this.year = (TextView) view.findViewById(R.id.year);
            this.currentPrice = (TextView) view.findViewById(R.id.price);
            this.previousPrice = (TextView) view.findViewById(R.id.previousPrice);
            this.scale = (ImageView) view.findViewById(R.id.PriceIndicator);
            this.bg = (ConstraintLayout) view.findViewById(R.id.lay);
            this.bg2 = (ConstraintLayout) view.findViewById(R.id.constraintLayout);
        }
    }
}
