package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.R;


public class CelebrityListActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_celebrities_lst);

        AdsManager.getInstance().loadBanner(this);
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((ImageView) findViewById(R.id.ll_actor)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "ACTORS");
                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.ll_actress)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "ACTRESSES");
                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.ll_dancers)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "DANCERS");
                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.ll_singers)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "SINGERS");
                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.ll_sports_person)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "SPORTS_PERSONS");
                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.ll_mrperfect)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "TYCOONS");
                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.ll_politician)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebrityListActivity.this.getApplicationContext(), TrendPersonVehiclesActivity.class);
                intent.putExtra("PERSON_TYPE", "POLITICIANS");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }


}
