package com.rankexam.rtoinfo.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rankexam.rtoinfo.Extra.ConstantsCele;
import com.rankexam.rtoinfo.Model.TrendPersonModel;
import com.rankexam.rtoinfo.AdsManager;
import com.facebook.ads.InterstitialAd;
import com.rankexam.rtoinfo.Extra.RtoUtil;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TrendPersonVehiclesActivity extends AppCompatActivity {

    Celebrity_Adapter adapter;
    private InterstitialAd interstitialAd;
    private List<TrendPersonModel> personList;
    private String personType;
    RecyclerView recycler_view;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_tp_vehicle);
        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendPersonVehiclesActivity.this.onBackPressed();
            }
        });
        this.recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        this.personType = getIntent().getStringExtra("PERSON_TYPE");
        ArrayList arrayList = new ArrayList();
        this.personList = arrayList;
        List<TrendPersonModel> list = ConstantsCele.trendingPersonsVehicles.get(this.personType);
        Objects.requireNonNull(list);
        arrayList.addAll(list);
        if (this.personType.equals("ACTORS")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_actors);
        } else if (this.personType.equals("ACTRESSES")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_actress);
        } else if (this.personType.equals("DANCERS")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_dancers);
        } else if (this.personType.equals("SINGERS")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_singers);
        } else if (this.personType.equals("SPORTS_PERSONS")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_sport_person);
        } else if (this.personType.equals("TYCOONS")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_mr_perfect);
        } else if (this.personType.equals("POLITICIANS")) {
            this.adapter = new Celebrity_Adapter(this, this.personList, ConstantsCele.img_politician);
        }
        this.recycler_view.setLayoutManager(new LinearLayoutManager(this));
        this.recycler_view.setHasFixedSize(true);
        this.recycler_view.setAdapter(this.adapter);
    }


    @Override
    public void onDestroy() {
        InterstitialAd interstitialAd = this.interstitialAd;
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }




    public class Celebrity_Adapter extends RecyclerView.Adapter<Celebrity_Adapter.ViewHolder> {
        Activity activity;
        int[] img_celebrity;
        private List<TrendPersonModel> personListt;

        public Celebrity_Adapter(Activity activity, List<TrendPersonModel> list, int[] iArr) {
            this.activity = activity;
            this.personListt = list;
            this.img_celebrity = iArr;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_search_vehicles_history_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.txvName.setText(this.personListt.get(i).getPersonName());
            viewHolder.txvRegNo.setText(this.personListt.get(i).getRegistrationNo());
            viewHolder.iv_celebrity.setImageResource(this.img_celebrity[i]);
        }

        @Override
        public int getItemCount() {
            return this.personListt.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView iv_celebrity;
            TextView txvName;
            TextView txvRegNo;

            public ViewHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                this.txvRegNo = (TextView) view.findViewById(R.id.txvRegNo);
                this.txvName = (TextView) view.findViewById(R.id.txvName);
                this.iv_celebrity = (ImageView) view.findViewById(R.id.iv_celebrity);
            }

            @Override
            public void onClick(View view) {
                btnSearchVehicleDetailsClickListener("" + this.txvRegNo.getText().toString());
            }

            public void btnSearchVehicleDetailsClickListener(String str) {
                InputMethodManager inputMethodManager = (InputMethodManager) TrendPersonVehiclesActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (!TrendPersonVehiclesActivity.this.isNetworkConnected()) {
                    Toast.makeText(TrendPersonVehiclesActivity.this, "You are not connected to internet!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String formatString = RtoUtil.formatString(str);
                if (RtoUtil.isNullOrEmpty(formatString) || formatString.length() <= 6) {
                    Toast.makeText(TrendPersonVehiclesActivity.this, "Please enter the correct vehicle no!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("VEHICLE_NO", formatString);
                bundle.putString("content_type", "BUTTON");
                Intent intent = new Intent(TrendPersonVehiclesActivity.this, SearchVehicleDetailActivity.class);
                intent.putExtra("REGISTRATION_NO", formatString);
                intent.putExtra("ACTION", "SAVE");

                AdsManager.getInstance().showInterstitialAd(TrendPersonVehiclesActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        TrendPersonVehiclesActivity.this.startActivity(intent);
                    }
                });
            }
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
