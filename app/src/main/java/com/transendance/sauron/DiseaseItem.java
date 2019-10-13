package com.transendance.sauron;

public class DiseaseItem {

    private String name, symptoms;
    private int id;

    public DiseaseItem(){}

    public DiseaseItem(String name, String symptoms){
        this.name = name;
        this.symptoms = symptoms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
