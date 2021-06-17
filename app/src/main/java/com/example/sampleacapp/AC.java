package com.example.sampleacapp;
public class AC {

    private String model;
    private String date;
    private String installedPlace;
    private String acType;

    public AC(String model, String date, String installedPlace, String acType) {
        this.model = model;
        this.date = date;
        this.installedPlace = installedPlace;
        this.acType = acType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInstalledPlace() {
        return installedPlace;
    }

    public void setInstalledPlace(String installedPlace) {
        this.installedPlace = installedPlace;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }
}
