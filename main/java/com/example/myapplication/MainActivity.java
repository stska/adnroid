package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] cityNamesArray = {
            "Moscow",
            "Saint-Petersburg",
            "Warsaw"
    };
    Integer[] imagesArray = {
            R.drawable.rainbow,
            R.drawable.sun,
            R.drawable.wind
    };
    String[] weatherArray = {
            "Rainy",
            "Sunny",
            "Windy"
    };
    String[] temperatureArray = {
            "15",
            "24",
            "10"
    };
    String[] humidityArray = {
            "59 %",
            "65 %",
            "45"
    };
    String[] pressureArray = {
            "743 мм. р",
            "600 мм. р",
            "845 мм. р"
    };
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomList customList = new CustomList(this,imagesArray,cityNamesArray,weatherArray,temperatureArray,humidityArray,pressureArray);
        listView = (ListView) findViewById(R.id.mainListView); //mainListView это id listView из activity_main.xml, туда подгружаем строки
        listView.setAdapter(customList);
    }
}