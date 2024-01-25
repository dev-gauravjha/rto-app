package com.rankexam.rtoinfo.Extra;


public class QueConstructor {
    private String answer;
    private int id;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String photo;
    private String question;

    public QueConstructor() {
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String str) {
        this.photo = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String str) {
        this.question = str;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public String getOption1() {
        return this.option1;
    }

    public void setOption1(String str) {
        this.option1 = str;
    }

    public String getOption2() {
        return this.option2;
    }

    public void setOption2(String str) {
        this.option2 = str;
    }

    public String getOption3() {
        return this.option3;
    }

    public void setOption3(String str) {
        this.option3 = str;
    }

    public String getOption4() {
        return this.option4;
    }

    public void setOption4(String str) {
        this.option4 = str;
    }

    public QueConstructor(int i, String str, String str2, String str3, String str4, String str5, String str6) {
        this.id = i;
        this.question = str;
        this.answer = str2;
        this.option1 = str3;
        this.option2 = str4;
        this.option3 = str5;
        this.option4 = str6;
        this.photo = str6;
    }

    public QueConstructor(String str, String str2) {
        this.question = str;
        this.answer = str2;
    }
}
