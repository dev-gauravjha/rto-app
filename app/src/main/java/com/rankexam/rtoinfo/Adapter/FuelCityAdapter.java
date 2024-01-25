package com.rankexam.rtoinfo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.rankexam.rtoinfo.Activity.StartActivity;
import com.rankexam.rtoinfo.R;
import com.rankexam.rtoinfo.Extra.Constant;
import com.rankexam.rtoinfo.Model.StateListModel;

import java.util.ArrayList;

public class FuelCityAdapter extends RecyclerView.Adapter<FuelCityAdapter.ViewHolder> {
    Context context;
    ArrayList<StateListModel> list;

    public FuelCityAdapter(Context context2, ArrayList<StateListModel> arrayList) {
        this.context = context2;
        this.list = arrayList;
    }

    public void setFilteredList(ArrayList<StateListModel> arrayList) {
        this.list = arrayList;
        notifyDataSetChanged();
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay_fuel, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setIsRecyclable(false);
        final StateListModel stateListModel = this.list.get(i);
        viewHolder.txt.setText(stateListModel.getStateName());
        if (stateListModel.getId().equalsIgnoreCase("null")) {
            viewHolder.layout.setBackgroundResource(R.drawable.bg_fuel3_bg);
            viewHolder.txt.setTextColor(-1);
        }
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
       

            public void onClick(View view) {
                if (!stateListModel.getId().equalsIgnoreCase("null")) {
                    Intent intent = new Intent(FuelCityAdapter.this.context, StartActivity.class);
                    SharedPreferences.Editor edit = FuelCityAdapter.this.context.getSharedPreferences(Constant.MY_PREFS_NAME, 0).edit();
                    edit.putString("cityName", stateListModel.getStateName());
                    edit.putString("cityId", stateListModel.getId());
                    edit.apply();
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView txt;

        public ViewHolder(View view) {
            super(view);
            this.txt = (TextView) view.findViewById(R.id.textView4);
            this.layout = (ConstraintLayout) view.findViewById(R.id.lay);
        }
    }
}
