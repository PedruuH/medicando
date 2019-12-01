package com.medicando.model;

public class Status {
    private int statusID;
    private String statusFase;

    public Status() {
    }

    public Status(int statusID, String statusFase) {
        this.statusID = statusID;
        this.statusFase = statusFase;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusFase() {
        return statusFase;
    }

    public void setStatusFase(String statusFase) {
        this.statusFase = statusFase;
    }
}
