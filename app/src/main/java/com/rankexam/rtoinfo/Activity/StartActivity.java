package com.rankexam.rtoinfo.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.rankexam.rtoinfo.Adapter.FuelAdapter;
import com.rankexam.rtoinfo.Model.FuelModel;
import com.rankexam.rtoinfo.R;
import com.rankexam.rtoinfo.Extra.Constant;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "123";
    ArrayList<FuelModel> FuelList;
    String cityId;
    String cityName;
    public OutputStream outputStream;
    TextView textView8, textView9;
    RecyclerView fuelRec;
    boolean doubleBackToExitPressedOnce = false;


    private NativeAd mobNativeView;

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
                if (StartActivity.this.mobNativeView != null) {
                    StartActivity.this.mobNativeView.destroy();
                }
                StartActivity.this.mobNativeView = nativeAd;
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

    InterstitialAd mMobInterstitialAds;

    public void InterstitialLoad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1ADAD30F02CD84CDE72190C2ABE5EB5E")).build();
        MobileAds.setRequestConfiguration(configuration);
        InterstitialAd.load(getApplicationContext(), getString(R.string.AdMob_Interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                StartActivity.this.mMobInterstitialAds = interstitialAd;
                interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                            }
                        });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            }
        });
    }

    private void ShowFunUAds() {
        if (this.mMobInterstitialAds != null) {
            this.mMobInterstitialAds.show(StartActivity.this);
        }
    }
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.start_activity);
        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermissions();
        }
        NativeLoad();
        InterstitialLoad();
        this.FuelList = new ArrayList<>();
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        fuelRec = findViewById(R.id.fuel_rec);
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.MY_PREFS_NAME, 0);
        this.cityName = sharedPreferences.getString("cityName", "Kolkata");
        this.cityId = sharedPreferences.getString("cityId", "4");
        textView8.setText(this.cityName);
        new GetData().execute(new String[0]);

        textView9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, FuelCityActivity.class));
                ShowFunUAds();
            }
        });
    }
    private class GetData extends AsyncTask<String, String, String> {
        StringBuilder gold22kp;
        StringBuilder gold24kp;

        private GetData() {
            this.gold24kp = new StringBuilder();
            this.gold22kp = new StringBuilder();
        }


        public String doInBackground(String[] strArr) {
            try {
                Elements select = Jsoup.connect("https://www.mypetrolprice.com/" + StartActivity.this.cityId + "/Fuel-prices-in-Kolkata").get().select("div.OuterDiv");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    StartActivity.this.FuelList.add(new FuelModel(select.eq(i).select("div.UCFuelName").text(), select.eq(i).select("div.Italic").text(), select.eq(i).select("span.day").text(), select.eq(i).select("span.month").text(), select.eq(i).select("span.year").text(), select.eq(i).select("div.fnt27").text(), select.eq(i).select("div.fnt18").text(), select.eq(i).select("div.UCFuelName").text()));
                }
                return null;
            } catch (IOException unused) {
                return null;
            }
        }


        public void onPostExecute(String str) {
            super.onPostExecute(str);
            fuelRec.setLayoutManager(new LinearLayoutManager(StartActivity.this, 0, false));
            RecyclerView recyclerView = fuelRec;
            StartActivity getStartActivity = StartActivity.this;
            recyclerView.setAdapter(new FuelAdapter(getStartActivity, getStartActivity.FuelList));
            fuelRec.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.privacy) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.privacy))));
        } else if (viewId == R.id.rate) {
            StoreLink();
        } else if (viewId == R.id.share) {
            ShareApp();
        } else if (viewId == R.id.start) {
            startActivity(new Intent(StartActivity.this, MainActivity.class));
        }
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

    public void StoreLink() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    private void ShareApp() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(intent, "Share Link"));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "double tap to exit!", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);

    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }




}
