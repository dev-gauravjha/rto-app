package com.rankexam.rtoinfo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rankexam.rtoinfo.Model.SignModel;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;


public class CustomAdpter extends BaseAdapter {
    Context f12c;
    LayoutInflater inflater;
    ArrayList<SignModel> signs;

    @Override
    public long getItemId(int i) {
        return i;
    }

    public CustomAdpter(Context context, ArrayList<SignModel> arrayList) {
        this.f12c = context;
        this.signs = arrayList;
    }

    @Override
    public int getCount() {
        return this.signs.size();
    }

    @Override
    public Object getItem(int i) {
        return this.signs.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.f12c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = this.inflater.inflate(R.layout.m_model, viewGroup, false);
        }
        final String text = this.signs.get(i).getText();
        int image = this.signs.get(i).getImage();
        ((TextView) view.findViewById(R.id.nameTxt)).setText(text);
        ((ImageView) view.findViewById(R.id.movieImage)).setImageResource(image);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Toast.makeText(CustomAdpter.this.f12c, text, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
