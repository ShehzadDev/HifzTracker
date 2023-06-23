package com.example.hifztracker;

public class DailyTask {
    private int id;
    private int studentId;
    private String sabaq;
    private String sabaqi;
    private String manzil;

    public DailyTask(int id, int studentId, String sabaq, String sabaqi, String manzil) {
        this.id = id;
        this.studentId = studentId;
        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.manzil = manzil;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getSabaq() {
        return sabaq;
    }

    public String getSabaqi() {
        return sabaqi;
    }

    public String getManzil() {
        return manzil;
    }
}
