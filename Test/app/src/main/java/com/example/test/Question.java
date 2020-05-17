package com.example.test;

public class Question {
    private int ID;
    private String vopros;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String otvet;


    private boolean read=false;

    public Question(int ID, String vopros, String answerA, String answerB, String answerC, String answerD, String otvet) {
        this.ID = ID;
        this.vopros = vopros;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.otvet = otvet;
    }


    public int getID() {
        return ID;
    }

    public String getVopros() {
        return vopros;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getOtvet() {
        return otvet;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setVopros(String vopros) {
        this.vopros = vopros;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setOtvet(String otvet) {
        this.otvet = otvet;
    }
}
