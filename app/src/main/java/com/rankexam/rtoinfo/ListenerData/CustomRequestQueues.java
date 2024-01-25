package com.rankexam.rtoinfo.ListenerData;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CustomRequestQueues {
    private static CustomRequestQueues mInstance;
    private Context context;
    private RequestQueue requestQueue;

    private CustomRequestQueues(Context context) {
        this.context = context;
    }

    public static synchronized CustomRequestQueues getInstance(Context context) {
        CustomRequestQueues customRequestQueue;
        synchronized (CustomRequestQueues.class) {
            if (mInstance == null) {
                mInstance = new CustomRequestQueues(context);
            }
            customRequestQueue = mInstance;
        }
        return customRequestQueue;
    }

    private RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(this.context);
        }
        return this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "CustomRequestQueue";
        }
        request.setTag(str);
        getRequestQueue().add(request);
    }

}
