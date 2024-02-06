package com.rankexam.rtoinfo;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.Map;

public class AdsManager {
    private static AdsManager instance;
    private InterstitialAd admobInterstitial;
    private AdCloseListener adCloseListener;
    private boolean isReload = false;

    int retryAttempt;

    private AdsManager() {

    }

    public static AdsManager getInstance() {
        if (instance == null) {
            instance = new AdsManager();
        }
        return instance;
    }

    public void init(Activity activity) {
        MobileAds.initialize(
                activity,
                initializationStatus -> {
//                    Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
//                    for (String adapterClass : statusMap.keySet()) {
//                        AdapterStatus status = statusMap.get(adapterClass);
//                        Log.d("Ads", String.format(
//                                "Adapter name: %s, Description: %s, Latency: %d",
//                                adapterClass, status.getDescription(), status.getLatency()));
//                        AppLovinPrivacySettings.setHasUserConsent(true, activity);
//
//                    }
                });

        AdSettings.setDataProcessingOptions(new String[]{});
        AppLovinSdk.getInstance(activity).setMediationProvider("max");
        AppLovinSdk.initializeSdk(activity, configuration -> {
        });
        AppLovinSdk sdk = AppLovinSdk.getInstance(activity);
        sdk.getSettings().setMuted(!sdk.getSettings().isMuted());

        AudienceNetworkAds
                .buildInitSettings(activity)
                .withInitListener(initResult -> Log.d(AudienceNetworkAds.TAG, initResult.getMessage()))
                .initialize();
        

        loadInterstitialAd(activity);
    }

    private void loadInterstitialAd(final Activity activity) {
        if (admobInterstitial != null)
            return;

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        InterstitialAd.load(activity, activity.getString(R.string.AdMob_Interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                admobInterstitial = interstitialAd;
                isReload = false;
                admobInterstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        adCloseListener.onAdClosed();
                        loadInterstitialAd(activity);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        adCloseListener.onAdClosed();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        admobInterstitial = null;
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                admobInterstitial = null;
                if (!isReload) {
                    isReload = true;
                    loadInterstitialAd(activity);
                }
            }
        });
    }


    public void showInterstitialAd(Activity activity, final AdCloseListener adCloseListener) {
        try {
            if (canShowInterstitialAdmob(activity)) {
                this.adCloseListener = adCloseListener;
                admobInterstitial.show(activity);
            } else {
                adCloseListener.onAdClosed();
            }
        } catch (Exception e) {
            adCloseListener.onAdClosed();
        }

    }

    private boolean canShowInterstitialAdmob(Context context) {
        return admobInterstitial != null && context instanceof Activity;
    }



    public void loadBanner(final Activity activity) {


        final FrameLayout adContainer = activity.findViewById(R.id.banner_container);

        try {
            AdView adView = new AdView(activity);
            adView.setAdUnitId(activity.getString(R.string.AdMob_Banner));
            adContainer.addView(adView);
            AdSize adSize = getAdSize(activity);
            // Step 4 - Set the adaptive ad size on the ad view.
            adView.setAdSize(adSize);
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            adView.loadAd(adRequest);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {

                    adContainer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                    adContainer.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            });
        } catch (Exception e) {
            Log.d("Ads Manager", e.getMessage());
        }
    }

    private AdSize getAdSize(Activity activity) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);

    }


    public interface AdCloseListener {
        void onAdClosed();
    }




}
