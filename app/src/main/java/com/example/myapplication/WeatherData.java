package com.example.myapplication;

public class WeatherData {
    private String cityName;
    private String cityTemperature; //cityTempTextView
    private String cityHumidity;  //humidityTextView
    private String cityWindSpeed; //windSpeedTextView
    private String[] cityTempFiveDays;
    private String[] cityHumFiveDays;

    WeatherData(String cityName,String cityTemperature, String cityHumidity,String cityWindSpeed,String[] cityTempFiveDays,String[] cityHumFiveDays){
        this.cityName = cityName;
        this.cityTemperature = cityTemperature;
        this.cityHumidity = cityHumidity;
        this.cityWindSpeed = cityWindSpeed;
        this.cityTempFiveDays = cityTempFiveDays;
        this.cityHumFiveDays = cityHumFiveDays;
    }

}

