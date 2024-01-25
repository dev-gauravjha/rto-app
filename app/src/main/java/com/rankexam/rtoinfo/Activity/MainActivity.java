package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "123";

    ImageView iv_celebrity_info;
    ImageView iv_owner_details;
    ImageView iv_rc_details;
    ImageView iv_rto_exam;
    ImageView iv_rto_exam_preparation;
    ImageView iv_rto_office;
    ImageView iv_rto_symbols;
    ImageView iv_rules_rto;
    private NativeAd mobNativeView;


    CardView cardSearchOwner,cardRcBookDetail,cardRtoOffice,cardCelebVehicle;
    LinearLayout ln_question_bank,ln_rto_exam,ln_traffic_signs,ln_traffic_rule;


    private void NativeBinding(NativeAd nativeAd, NativeAdView adView) {
        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    public void NativeShow(final FrameLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(getApplication(), getString(R.string.AdMob_Native));

        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {

                boolean isDestroyed = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    isDestroyed = isDestroyed();
                }
                if (isDestroyed || isFinishing() || isChangingConfigurations()) {
                    nativeAd.destroy();
                    return;
                }
                if (MainActivity.this.mobNativeView != null) {
                    MainActivity.this.mobNativeView.destroy();
                }
                MainActivity.this.mobNativeView = nativeAd;
                NativeAdView adView = (NativeAdView) getLayoutInflater().inflate(R.layout.mobnative, null);
                NativeBinding(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });
        VideoOptions videoOptions = new VideoOptions.Builder().build();
        com.google.android.gms.ads.nativead.NativeAdOptions adOptions = new com.google.android.gms.ads.nativead.NativeAdOptions.Builder().setVideoOptions(videoOptions).build();
        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {


            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());


    }

    public void NativeLoad() {
        NativeShow((FrameLayout) findViewById(R.id.mobadslayout));
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        NativeLoad();
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onBackPressed();
            }
        });

        this.iv_owner_details = (ImageView) findViewById(R.id.iv_owner_details);
        this.iv_rc_details = (ImageView) findViewById(R.id.iv_rc_details);
        this.iv_rto_office = (ImageView) findViewById(R.id.iv_rto_office);
        this.iv_celebrity_info = (ImageView) findViewById(R.id.iv_celebrity_info);
        this.iv_rto_symbols = (ImageView) findViewById(R.id.iv_rto_symbols);
        this.iv_rto_exam_preparation = (ImageView) findViewById(R.id.iv_rto_exam_preparation);
        this.iv_rto_exam = (ImageView) findViewById(R.id.iv_rto_exam);
        this.iv_rules_rto = (ImageView) findViewById(R.id.iv_rules_rto);

        cardSearchOwner=findViewById(R.id.cardSearchOwner);
        cardRcBookDetail=findViewById(R.id.cardRcBookDetail);
        cardRtoOffice=findViewById(R.id.cardRtoOffice);
        cardCelebVehicle=findViewById(R.id.cardCelebVehicle);

        ln_question_bank=findViewById(R.id.ln_question_bank);
        ln_rto_exam=findViewById(R.id.ln_rto_exam);
        ln_traffic_signs=findViewById(R.id.ln_traffic_signs);
        ln_traffic_rule=findViewById(R.id.ln_traffic_rule);




        this.cardSearchOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, EnterInformationActivity.class));
                    }
                });

            }
        });
        this.cardRcBookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, EnterInformationActivity.class));
                    }
                });
            }
        });
        this.cardRtoOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, OficeActivity.class));
                    }
                });
            }
        });
        this.ln_traffic_signs
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, SymbolActivity.class));
                    }
                });
            }
        });
        this.ln_question_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(MainActivity.this, LanguageSelectActivity.class);
                        intent.putExtra("from", "from_preparation");
                        MainActivity.this.startActivity(intent);
                    }
                });
            }
        });
        this.ln_rto_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(MainActivity.this, LanguageSelectActivity.class);
                        intent.putExtra("from", "from_exam");
                        MainActivity.this.startActivity(intent);
                    }
                });
            }
        });
        this.cardCelebVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, CelebrityListActivity.class));
                    }
                });
            }
        });
        this.ln_traffic_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdsManager.getInstance().showInterstitialAd(MainActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                        intent.putExtra("from", "from_exam");
                        MainActivity.this.startActivity(intent);
                    }
                });
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
