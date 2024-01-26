package com.rankexam.rtoinfo.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.R;
import com.onesignal.OneSignal;

import java.util.Arrays;


public class SplashScreenActivity extends AppCompatActivity {

    InterstitialAd mMobInterstitialAds;

    public void InterstitialLoad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("1ADAD30F02CD84CDE72190C2ABE5EB5E")).build();
        MobileAds.setRequestConfiguration(configuration);
        InterstitialAd.load(getApplicationContext(), getString(R.string.AdMob_Interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                SplashScreenActivity.this.mMobInterstitialAds = interstitialAd;
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
            this.mMobInterstitialAds.show(SplashScreenActivity.this);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash_screen);


//        InterstitialLoad();


//        AudienceNetworkAds.initialize(this);
//        AdSettings.addTestDevice("96b3a1e6-928a-4aa5-8925-87d9801fce5c");
//        new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("D49A22866E5BD4C95060B509FA9FB123"));
//
//        AppLovinSdk.getInstance(this).setMediationProvider("max");
//        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
//            @Override
//            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
//
//            }
//        });
//        AppLovinSdk.getInstance(this).getSettings().setTestDeviceAdvertisingIds(Arrays.asList("257866f2-9d68-4e02-8902-9304f564a7f3"));

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(getResources().getString(R.string.ONESIGNAL_APP_ID));
        OneSignal.promptForPushNotifications();

//        AdsManager.getInstance().init(SplashScreenActivity.this);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (isNetworkConnected()) {
                    startActivity(new Intent(SplashScreenActivity.this.getApplicationContext(), StartActivity.class));
                    ShowFunUAds();
                    finish();
                } else {
                    Toast.makeText(SplashScreenActivity.this, "Please check your internet connections", Toast.LENGTH_SHORT).show();
                }

            }
        }, 6000);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
