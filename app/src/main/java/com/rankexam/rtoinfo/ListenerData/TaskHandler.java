package com.rankexam.rtoinfo.ListenerData;

import static com.rankexam.rtoinfo.Data.url;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.rankexam.rtoinfo.Extra.GlobalContexts;
import com.rankexam.rtoinfo.Extra.RtoUtil;
import com.rankexam.rtoinfo.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class TaskHandler {
    static {
        System.loadLibrary("hello-jni");
    }
    private static String API_BASE_URL = url;
    private static TaskHandler mInstance;



    public interface JsonResponseHandler {
        void onError(String str);

        void onResponse(JSONObject jSONObject);
    }


    public interface ResponseHandler<T> {
        void onError(String str);

        void onResponse(T t);
    }

    public static TaskHandler newInstance() {
        if (mInstance == null) {
            mInstance = new TaskHandler();
        }
        return mInstance;
    }

    public static String prependAPIBaseUrl(String str) {

        if (!RtoUtil.isNullOrEmpty(API_BASE_URL) && !API_BASE_URL.endsWith("/")) {
//            Log.d("apiURL", "prependAPIBaseUrl:"+ API_BASE_URL);
            API_BASE_URL += "/";
        }

        return API_BASE_URL + str;
    }

    public void fetchVehicleDetails(Context context, String str, boolean z, boolean z2, boolean z3, ResponseHandler<JSONObject> responseHandler) {

        HashMap hashMap = new HashMap();
        hashMap.put("registrationNo", str);
        hashMap.put("key_skip_db", Boolean.valueOf(z));
        hashMap.put("extra", Boolean.valueOf(z2));
        Log.i("fetchVehicleDetails",new Gson().toJson(hashMap).toString());
        requestUrl(context, 1, prependAPIBaseUrl("search_vehicle_details"), "tag_vehicle_details", hashMap, z3 ? context.getString(R.string.loading) : null, new VehiclesDetailRespondHandler(responseHandler));
    }

    private void requestUrl(Context context, int i, String str, String str2, Map<String, Object> map, String str3, JsonResponseHandler jsonResponseHandler) {
        ProgressDialog progressDialog;
        boolean z;
        Context context2 = context;
        if (RtoUtil.isNullOrEmpty(str3) || !RtoUtil.isActivityFinished(context)) {
            progressDialog = null;
            z = false;
        } else {
            ProgressDialog progressDialog2 = new ProgressDialog(context);
            progressDialog2.setMessage(str3);
            progressDialog2.setCancelable(false);
            progressDialog2.setCanceledOnTouchOutside(false);
            progressDialog2.show();
            progressDialog = progressDialog2;
            z = true;
        }
        if (context2 == null) {
            context2 = GlobalContexts.getInstance().getContext();
        }
        new RequestLoaders(this, i, map, str, z, context2, progressDialog, jsonResponseHandler, str2).requestWithHeaders();
    }

    public String encodeString(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public void cancelProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null) {
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                    progressDialog.dismiss();
                }
            } catch (Exception unused) {
            }
        }
    }
}
