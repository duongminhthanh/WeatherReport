package com.example.weatherreport;

import java.util.Date;

public class Weather {
    public String day;
    public String status;
    public String Image;
    public String MaxTemp;
    public String MinTemp;

    public Weather(String day, String status, String image, String maxTemp, String minTemp) {
        this.day = day;
        this.status = status;
        Image = image;
        MaxTemp = maxTemp;
        MinTemp = minTemp;
    }

//    public Weather(String status, String image, String maxTemp, String minTemp) {
//        this.status = status;
//        Image = image;
//        MaxTemp = maxTemp;
//        MinTemp = minTemp;
//    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        MaxTemp = maxTemp;
    }

    public String getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(String minTemp) {
        MinTemp = minTemp;
    }
}
