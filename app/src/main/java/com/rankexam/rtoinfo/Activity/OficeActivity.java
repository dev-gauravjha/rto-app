package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.Adapter.StateListAdpter;
import com.rankexam.rtoinfo.R;


public class OficeActivity extends AppCompatActivity {
    String[] ary_states = {"Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Daman and Diu", "Delhi", "Dadra and Nagar Haveli", "Goa", "Gujarat", "Himachal Pradesh", "Haryana", "Jharkhand", "Jammu and Kashmir", "Karnataka", "Kerala", "Lakshadweep", "Maharashtra", "Meghalaya", "Manipur", "Madhya Pradesh", "Mizoram", "Nagaland", "Odisha", "Punjab", "Puducherry", "Rajasthan", "Tamil Nadu", "Tripura", "Telangana", "Uttarakhand", "Uttar Pradesh", "West Bengal"};

    ListView lv_states;
    StateListAdpter rtoOffice_statesList_adp;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_offices);

        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OficeActivity.this.onBackPressed();
            }
        });
        this.lv_states = (ListView) findViewById(R.id.lv_states);
        StateListAdpter m_rtoOfcStateLstAdpter = new StateListAdpter(this, this.ary_states);
        this.rtoOffice_statesList_adp = m_rtoOfcStateLstAdpter;
        this.lv_states.setAdapter((ListAdapter) m_rtoOfcStateLstAdpter);
        this.lv_states.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(OficeActivity.this, CityActivity.class);
                intent.putExtra("states_name", OficeActivity.this.ary_states[i]);
                OficeActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
