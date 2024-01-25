package com.rankexam.rtoinfo.ListenerData;

import android.app.ProgressDialog;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rankexam.rtoinfo.Extra.AppConfigNotifier;
import com.rankexam.rtoinfo.Extra.MetaAppConfig;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


public class RequestLoaders {
    public Context context;
    public boolean isProgressDialogShowing;
    public TaskHandler.JsonResponseHandler jsonResponseHandler;
    public TaskHandler mInstance;
    Map<String, Object> params;
    public ProgressDialog progressDialog;
    int requestMethod;
    public String requestUrl;
    public TaskHandler.ResponseHandler<String> responseHandler;
    String tag;
    private boolean includeHeaders=true;
    private static JSONObject metaAppConfig = null;


    int initialTimeoutMs = 60000;


    public RequestLoaders(TaskHandler m_rtoTaskHandler, int i, Map<String, Object> map, String str, boolean z, Context context, ProgressDialog progressDialog, TaskHandler.JsonResponseHandler jsonResponseHandler, String str2) {
        this.mInstance = m_rtoTaskHandler;
        this.requestMethod = i;
        this.params = map;
        this.requestUrl = str;
        this.isProgressDialogShowing = z;
        this.context = context;
        this.progressDialog = progressDialog;
        this.jsonResponseHandler = jsonResponseHandler;
        this.tag = str2;
    }
    public void requestWithHeaders() {
        JSONObject jSONObject;
        StringBuilder sb = new StringBuilder();
        if (this.requestMethod == 0) {
            for (Map.Entry<String, Object> entry : this.params.entrySet()) {
                sb.append("&");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(this.mInstance.encodeString((String) entry.getValue()));
            }
            jSONObject = null;
        } else {
            jSONObject = new JSONObject(this.params);
        }
        JSONObject jSONObject2 = jSONObject;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.requestUrl);
        sb2.append(sb.length() == 0 ? "" : "?" + ((Object) sb));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(this.requestMethod, sb2.toString(), jSONObject2, new JSONRespondListeners(this), new RequErrorsListener(this)) {


            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() {
                if (RequestLoaders.this.includeHeaders) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(getAndroidId(context));
                    return RequestHelper.getRequestHeaders(RequestLoaders.this.context, sb3.reverse().toString());
                }
                HashMap hashMap = new HashMap();
                MetaAppConfig metaAppConfig = getMetaAppConfig();
                if (metaAppConfig != null && metaAppConfig.getHeaders() != null) {
                    for (String str : metaAppConfig.getHeaders().keySet()) {
                        hashMap.put(str, metaAppConfig.getHeaders().get(str));
                    }
                }
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(this.initialTimeoutMs, 1, 1.0f));
        CustomRequestQueues.getInstance(this.context).addToRequestQueue(jsonObjectRequest, this.tag);
    }
    public static MetaAppConfig getMetaAppConfig() {
        return new AppConfigNotifier().setJson(metaAppConfig).getMetaAppConfig();
    }

    public static String getAndroidId(Context context) {
        String keyDeviceId = "";
        try {
            keyDeviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
            Log.i("getAndroidId",keyDeviceId);
            return keyDeviceId;
        } catch (Exception e) {
            e.printStackTrace();
            return keyDeviceId;
        }

    }

    public void request() {
        JSONObject jSONObject;
        StringBuilder sb = new StringBuilder();
        if (this.requestMethod == 0) {
            for (Map.Entry<String, Object> entry : this.params.entrySet()) {
                sb.append("&");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(this.mInstance.encodeString((String) entry.getValue()));
            }
            jSONObject = null;
        } else {
            jSONObject = new JSONObject(this.params);
        }
        JSONObject jSONObject2 = jSONObject;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.requestUrl);
        sb2.append(sb.length() == 0 ? "" : "?" + ((Object) sb));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(this.requestMethod, sb2.toString(), jSONObject2, new JSONRespondListeners(this), new RequErrorsListener(this));
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(250000, 1, 1.0f));
        CustomRequestQueues.getInstance(this.context).addToRequestQueue(jsonObjectRequest, this.tag);
    }
}
