package com.rankexam.rtoinfo.Extra;

import com.google.gson.Gson;

import org.json.JSONObject;

public class AppConfigNotifier {
    private MetaAppConfig metaAppConfig;

    public AppConfigNotifier setJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.metaAppConfig = (MetaAppConfig) new Gson().fromJson(jSONObject.toString(), MetaAppConfig.class);
        }
        return this;
    }

    public MetaAppConfig getMetaAppConfig() {
        return this.metaAppConfig;
    }

}
