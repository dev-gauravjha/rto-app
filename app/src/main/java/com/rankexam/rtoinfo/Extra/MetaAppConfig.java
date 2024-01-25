package com.rankexam.rtoinfo.Extra;

import com.rankexam.rtoinfo.Model.MetaLoginData;

import java.io.Serializable;
import java.util.Map;

public class MetaAppConfig implements Serializable {
    private String config;
    private boolean fallbackEnabled;
    private Map<String, String> headers;
    private boolean loginEnable;
    private MetaLoginData metaLoginData;
    private boolean thirdPartyEnabled;
    private String userPrefix;

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getUserPrefix() {
        return this.userPrefix;
    }

    public MetaLoginData getMetaLoginData() {
        return this.metaLoginData;
    }

    public boolean isLoginEnable() {
        return this.loginEnable;
    }

    public boolean isThirdPartyEnabled() {
        return this.thirdPartyEnabled;
    }

    public boolean isFallbackEnabled() {
        return this.fallbackEnabled;
    }

    public String getConfig() {
        return this.config;
    }

    public String toString() {
        return "MetaAppConfig{headers=" + this.headers + ", userPrefix='" + this.userPrefix + "', metaLoginData=" + this.metaLoginData + ", loginEnable=" + this.loginEnable + ", thirdPartyEnabled=" + this.thirdPartyEnabled + ", fallbackEnabled=" + this.fallbackEnabled + ", config='" + this.config + "'}";
    }
}