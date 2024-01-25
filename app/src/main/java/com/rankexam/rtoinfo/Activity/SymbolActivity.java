package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.R;


public class SymbolActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_symbol);
        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SymbolActivity.this.onBackPressed();
            }
        });
        ((CardView) findViewById(R.id.cv_mandatory)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymbolActivity.this, SymbolDetailActivity.class);
                intent.putExtra("passvalue", "Mandatory");

                AdsManager.getInstance().showInterstitialAd(SymbolActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        SymbolActivity.this.startActivity(intent);
                    }
                });
            }
        });
        ((CardView) findViewById(R.id.cv_cautionary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymbolActivity.this, SymbolDetailActivity.class);
                intent.putExtra("passvalue", "Cautionary");
                AdsManager.getInstance().showInterstitialAd(SymbolActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        SymbolActivity.this.startActivity(intent);
                    }
                });
            }
        });
        ((CardView) findViewById(R.id.cv_informatory)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymbolActivity.this, SymbolDetailActivity.class);
                intent.putExtra("passvalue", "Informatory");
                AdsManager.getInstance().showInterstitialAd(SymbolActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        SymbolActivity.this.startActivity(intent);
                    }
                });
            }
        });
        ((CardView) findViewById(R.id.cv_roadsignals)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymbolActivity.this, SymbolDetailActivity.class);
                intent.putExtra("passvalue", "Road & Signals");
                AdsManager.getInstance().showInterstitialAd(SymbolActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        SymbolActivity.this.startActivity(intent);
                    }
                });
            }
        });
        ((CardView) findViewById(R.id.cv_drivingrules)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymbolActivity.this, SymbolDetailActivity.class);
                intent.putExtra("passvalue", "Driving Rules");
                AdsManager.getInstance().showInterstitialAd(SymbolActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        SymbolActivity.this.startActivity(intent);
                    }
                });
            }
        });
        ((CardView) findViewById(R.id.cv_trafficpolice_signals)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymbolActivity.this, SymbolDetailActivity.class);
                intent.putExtra("passvalue", "Traffic Police Signals");
                AdsManager.getInstance().showInterstitialAd(SymbolActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        SymbolActivity.this.startActivity(intent);
                    }
                });
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
