package com.rankexam.rtoinfo.ListenerData;

import org.json.JSONObject;


public class VehiclesDetailRespondHandler implements TaskHandler.JsonResponseHandler {
    TaskHandler.ResponseHandler<JSONObject> responseHandler;

    public VehiclesDetailRespondHandler(TaskHandler.ResponseHandler<JSONObject> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(jSONObject);
        } catch (Exception unused) {
        }
    }
}
