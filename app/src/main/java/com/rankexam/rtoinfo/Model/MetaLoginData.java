package com.rankexam.rtoinfo.Model;

import java.io.Serializable;

public class MetaLoginData  implements Serializable {
    private String loginTypeKey;
    private String loginTypeValue;
    private String userEncPrefix;

    public String getUserEncPrefix() {
        return this.userEncPrefix;
    }

    public String getLoginTypeKey() {
        return this.loginTypeKey;
    }

    public String getLoginTypeValue() {
        return this.loginTypeValue;
    }

    public String toString() {
        return "MetaLoginData{userEncPrefix='" + this.userEncPrefix + "', loginTypeKey='" + this.loginTypeKey + "', loginTypeValue='" + this.loginTypeValue + "'}";
    }
}
