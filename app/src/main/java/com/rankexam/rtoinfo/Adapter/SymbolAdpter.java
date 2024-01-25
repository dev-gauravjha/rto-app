package com.rankexam.rtoinfo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.rankexam.rtoinfo.R;


public class SymbolAdpter extends RecyclerView.Adapter<SymbolAdpter.ViewHolder> {
    int[] img_ary;
    String[] txt_ary;

    public SymbolAdpter(String[] strArr, int[] iArr) {
        this.txt_ary = strArr;
        this.img_ary = iArr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_symbolic_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.iv_imgg.setImageResource(this.img_ary[i]);
        viewHolder.tv_txtt.setText(this.txt_ary[i]);
    }

    @Override
    public int getItemCount() {
        return this.txt_ary.length;
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_imgg;
        public TextView tv_txtt;

        public ViewHolder(View view) {
            super(view);
            this.iv_imgg = (ImageView) view.findViewById(R.id.iv_imgg);
            this.tv_txtt = (TextView) view.findViewById(R.id.tv_txtt);
        }
    }
}
