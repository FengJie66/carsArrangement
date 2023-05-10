package com.example.entity;


public class Facility {

    private double temperature; //温度
    private int humidity; //湿度
    private String name; //名称
    private String place; //位置

    public Facility() {
    }

    public Facility(double temperature, int humidity, String name, String place) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.name = name;
        this.place = place;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    //是否正常
    public boolean isOK(){
        if (this.getTemperature()>30.0||this.getHumidity()>55){
            return false;
        }
        return true;
    }
}
