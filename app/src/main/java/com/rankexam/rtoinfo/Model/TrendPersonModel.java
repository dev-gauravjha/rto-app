package com.rankexam.rtoinfo.Model;

import java.io.Serializable;


public class TrendPersonModel implements Serializable {
    private String personName;
    private String registrationNo;

    public TrendPersonModel() {
    }

    public TrendPersonModel(String str, String str2) {
        this.personName = str;
        this.registrationNo = str2;
    }

    public String getPersonName() {
        return this.personName;
    }

    public String getRegistrationNo() {
        return this.registrationNo;
    }

    public String toString() {
        return "TrendingPerson{personName='" + this.personName + "', registrationNo='" + this.registrationNo + "'}";
    }
}
