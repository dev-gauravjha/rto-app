package com.rankexam.rtoinfo.Model;


public class SignModel {
    int image;
    String text;

    public int getImage() {
        return this.image;
    }

    public SignModel(String str, int i) {
        this.text = str;
        this.image = i;
    }

    public String getText() {
        return this.text;
    }
}
