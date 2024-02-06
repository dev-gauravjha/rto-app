package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.rankexam.rtoinfo.Adapter.FuelAdapter;
import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.Extra.BackgroundTask;
import com.rankexam.rtoinfo.Extra.Constant;
import com.rankexam.rtoinfo.Model.FuelModel;
import com.rankexam.rtoinfo.R;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


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

    ArrayList<FuelModel> FuelList;
    String cityId;
    String cityName;
    RecyclerView fuelRec;
    TextView textView8, textView9;
    ImageView iv_share,iv_setting;

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


        this.iv_owner_details = (ImageView) findViewById(R.id.iv_owner_details);
        this.iv_rc_details = (ImageView) findViewById(R.id.iv_rc_details);
        this.iv_rto_office = (ImageView) findViewById(R.id.iv_rto_office);
        this.iv_celebrity_info = (ImageView) findViewById(R.id.iv_celebrity_info);
        this.iv_rto_symbols = (ImageView) findViewById(R.id.iv_rto_symbols);
        this.iv_rto_exam_preparation = (ImageView) findViewById(R.id.iv_rto_exam_preparation);
        this.iv_rto_exam = (ImageView) findViewById(R.id.iv_rto_exam);
        this.iv_rules_rto = (ImageView) findViewById(R.id.iv_rules_rto);
        iv_share= findViewById(R.id.iv_share);
        iv_setting= findViewById(R.id.iv_user);


        cardSearchOwner=findViewById(R.id.cardSearchOwner);
        cardRcBookDetail=findViewById(R.id.cardRcBookDetail);
        cardRtoOffice=findViewById(R.id.cardRtoOffice);
        cardCelebVehicle=findViewById(R.id.cardCelebVehicle);

        ln_question_bank=findViewById(R.id.ln_question_bank);
        ln_rto_exam=findViewById(R.id.ln_rto_exam);
        ln_traffic_signs=findViewById(R.id.ln_traffic_signs);
        ln_traffic_rule=findViewById(R.id.ln_traffic_rule);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        fuelRec = findViewById(R.id.fuel_rec);

        this.FuelList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.MY_PREFS_NAME, 0);
        this.cityName = sharedPreferences.getString("cityName", "Kolkata");
        this.cityId = sharedPreferences.getString("cityId", "4");
        textView8.setText(this.cityName);


        textView9.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, FuelCityActivity.class))
        );

        fetchFuelPrice();

        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermissions();
        }


        iv_setting.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, StartActivity.class));
        });

        iv_share.setOnClickListener(v -> {
            ShareApp();
        });



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

    void fetchFuelPrice(){
        new BackgroundTask(MainActivity.this){
            @Override
            public void doInBackground() {
                String path="https://www.mypetrolprice.com/" + MainActivity.this.cityId + "/Fuel-prices-in-Kolkata";
                Log.d(TAG, "endpoint: "+path);
                try {
                    Elements select = Jsoup.connect("https://www.mypetrolprice.com/" + MainActivity.this.cityId + "/Fuel-prices-in-Kolkata").get().select("div.OuterDiv");
                    int size = select.size();
                    for (int i = 0; i < size; i++) {
                        MainActivity.this.FuelList.add(new FuelModel(select.eq(i).select("div.UCFuelName").text(), select.eq(i).select("div.Italic").text(), select.eq(i).select("span.day").text(), select.eq(i).select("span.month").text(), select.eq(i).select("span.year").text(), select.eq(i).select("div.fnt27").text(), select.eq(i).select("div.fnt18").text(), select.eq(i).select("div.UCFuelName").text()));
                        Log.d(TAG, "doInBackground: "+ FuelList.get(i).getFuelName());
                    }
                } catch (IOException unused) {
                    Log.e(TAG, "doInBackground: "+unused);
                }
            }

            @Override
            public void onPostExecute() {
                fuelRec.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
                RecyclerView recyclerView = fuelRec;
                MainActivity getStartActivity = MainActivity.this;
                recyclerView.setAdapter(new FuelAdapter(getStartActivity, getStartActivity.FuelList));
                fuelRec.setVisibility(View.VISIBLE);
            }
        }.execute();
    }

    private boolean checkAndRequestPermissions() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
        int checkSelfPermission2 = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 6 || iArr[0] == 0 || Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 6);
    }
    private void ShareApp() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(intent, "Share Link"));
    }


}
