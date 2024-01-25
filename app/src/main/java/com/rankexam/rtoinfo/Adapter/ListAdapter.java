package com.rankexam.rtoinfo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.rankexam.rtoinfo.Model.CitiesModel;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;


public class ListAdapter extends BaseAdapter {
    ArrayList<CitiesModel> ary_states;
    Context context;

    @Override
    public long getItemId(int i) {
        return i;
    }

    public ListAdapter(Context context, ArrayList<CitiesModel> arrayList) {
        this.context = context;
        this.ary_states = arrayList;
    }

    @Override
    public int getCount() {
        return this.ary_states.size();
    }

    @Override
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }


    private class ViewHolder {
        TextView tv_states;

        private ViewHolder() {
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list_offices_state, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.tv_states = (TextView) view.findViewById(R.id.tv_states);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_states.setText(this.ary_states.get(i).getField2());
        return view;
    }
}
