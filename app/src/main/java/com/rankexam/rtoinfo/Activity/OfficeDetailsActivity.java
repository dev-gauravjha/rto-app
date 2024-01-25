package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.AdsManager;
import com.facebook.ads.InterstitialAd;
import com.rankexam.rtoinfo.Model.CitiesModel;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;


public class OfficeDetailsActivity extends AppCompatActivity {
    TextView address_txt;
    ArrayList<CitiesModel> arrlist;
    TextView code_txt;
    ImageView contact_img;
    TextView contact_txt;
    TextView distinct_txt;
    private InterstitialAd interstitialAd;
    ImageView map_img;
    int pos;
    TextView state_txt;
    String str_states_name;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_office_detail);
        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OfficeDetailsActivity.this.onBackPressed();
            }
        });
        this.code_txt = (TextView) findViewById(R.id.code_txt);
        this.distinct_txt = (TextView) findViewById(R.id.distinct_txt);
        this.state_txt = (TextView) findViewById(R.id.state_txt);
        this.address_txt = (TextView) findViewById(R.id.address_txt);
        this.contact_txt = (TextView) findViewById(R.id.contact_txt);
        this.map_img = (ImageView) findViewById(R.id.map_img);
        this.contact_img = (ImageView) findViewById(R.id.contact_img);
        this.str_states_name = getIntent().getStringExtra("states_name");
        this.pos = getIntent().getIntExtra("position", 0);
        ArrayList<CitiesModel> arrayList = CityActivity.arrlist;
        this.arrlist = arrayList;
        this.code_txt.setText(arrayList.get(this.pos).getField2().substring(0, 4));
        this.distinct_txt.setText(this.arrlist.get(this.pos).getField2().substring(7));
        this.state_txt.setText(this.str_states_name);
        this.address_txt.setText(this.arrlist.get(this.pos).getField3());
        this.contact_txt.setText(this.arrlist.get(this.pos).getField5());
        this.map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + OfficeDetailsActivity.this.address_txt.getText().toString()));
                intent.setPackage("com.google.android.apps.maps");
                OfficeDetailsActivity.this.startActivity(intent);
            }
        });
        this.contact_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OfficeDetailsActivity.this.contact_txt.getText().toString().equals("")) {
                    Toast.makeText(OfficeDetailsActivity.this, "Contact Not Available", 0).show();
                    return;
                }
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + OfficeDetailsActivity.this.contact_txt.getText().toString()));
                OfficeDetailsActivity.this.startActivity(intent);
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
