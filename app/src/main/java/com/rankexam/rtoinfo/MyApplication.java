package com.rankexam.rtoinfo;

import android.app.Application;
import android.content.Context;
import com.facebook.ads.AudienceNetworkAds;
import com.firebase.client.Firebase;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application  {

    private AppOpenAds appOpenManager;
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
        AudienceNetworkAds.isInitialized(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        Firebase.setAndroidContext(getApplicationContext());
        appOpenManager = new AppOpenAds(this);

    }





    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
