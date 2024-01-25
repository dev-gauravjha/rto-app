package com.rankexam.rtoinfo.Extra;

import android.content.Context;


public class GlobalContexts {
    private static GlobalContexts mInstance;
    private Context context;

    private GlobalContexts(Context context) {
        if (this.context == null) {
            this.context = context;
        }
    }

    public static void initialize(Context context) {
        if (mInstance == null) {
            mInstance = new GlobalContexts(context);
        }
    }

    public static GlobalContexts getInstance() {
        return mInstance;
    }

    public Context getContext() {
        return this.context;
    }
}
