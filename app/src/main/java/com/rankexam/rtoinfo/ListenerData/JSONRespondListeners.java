package com.rankexam.rtoinfo.ListenerData;

import com.android.volley.Response;
import com.rankexam.rtoinfo.Extra.RtoUtil;
import org.json.JSONObject;


public class JSONRespondListeners implements Response.Listener<JSONObject> {
    RequestLoaders requestLoader;

    public JSONRespondListeners(RequestLoaders m_rtoRequestLoaders) {
        this.requestLoader = m_rtoRequestLoaders;
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        if (this.requestLoader.isProgressDialogShowing && RtoUtil.isActivityFinished(this.requestLoader.context) && this.requestLoader.progressDialog != null && this.requestLoader.progressDialog.isShowing()) {
            this.requestLoader.mInstance.cancelProgressDialog(this.requestLoader.progressDialog);
        }
        this.requestLoader.jsonResponseHandler.onResponse(jSONObject);
    }
}
