package com.rankexam.rtoinfo.Model;

public class StateListModel {
    String id;
    String stateName;

    public StateListModel(String str, String str2) {
        this.stateName = str;
        this.id = str2;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(String str) {
        this.stateName = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }
}
