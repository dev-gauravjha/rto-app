package com.rankexam.rtoinfo.ListenerData;

import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rankexam.rtoinfo.Extra.RtoUtil;
import com.rankexam.rtoinfo.R;


public class RequErrorsListener implements Response.ErrorListener {
    RequestLoaders requestLoader;

    public RequErrorsListener(RequestLoaders m_rtoRequestLoaders) {
        this.requestLoader = m_rtoRequestLoaders;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (this.requestLoader.isProgressDialogShowing && RtoUtil.isActivityFinished(this.requestLoader.context) && this.requestLoader.progressDialog != null && this.requestLoader.progressDialog.isShowing()) {
            this.requestLoader.mInstance.cancelProgressDialog(this.requestLoader.progressDialog);
        }
        if (volleyError.networkResponse != null) {
            String simpleName = this.requestLoader.getClass().getSimpleName();
            Log.d(simpleName, "onErrorResponse: " + volleyError);
            if (this.requestLoader.jsonResponseHandler != null) {
                this.requestLoader.jsonResponseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
            } else if (this.requestLoader.responseHandler != null) {
                this.requestLoader.responseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
            }
        } else if (this.requestLoader.jsonResponseHandler != null) {
            this.requestLoader.jsonResponseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
        } else if (this.requestLoader.responseHandler != null) {
            this.requestLoader.responseHandler.onError(this.requestLoader.context.getString(R.string.no_info));
        }
    }
}
