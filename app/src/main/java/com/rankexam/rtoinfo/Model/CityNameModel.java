package com.rankexam.rtoinfo.Model;

public class CityNameModel {
    String cityName;
    String id;

    public CityNameModel(String str, String str2) {
        this.cityName = str;
        this.id = str2;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }
}
