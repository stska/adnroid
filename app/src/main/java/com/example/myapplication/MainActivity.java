package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    String[] cityNamesArray = {
            "Moscow",
            "Saint-Petersburg",
            "Warsaw",
            "New York",
            "Sydney",
            "Tokyo"
    };
    Integer[] imagesArray = {
            R.drawable.rainbow,
            R.drawable.sun,
            R.drawable.wind,
            R.drawable.winter,
            R.drawable.sunglasses,
            R.drawable.moon
    };
    String[] weatherArray = {
            "Rainy",
            "Sunny",
            "Windy",
            "Frosty",
            "Heat",
            "Mild"
    };
    String[] temperatureArray = {
            "15",
            "24",
            "10",
            "-10",
            "+40",
            "+5"
    };
    String[] humidityArray = {
            "59 %",
            "65 %",
            "45 %",
            "50 %",
            "80 %",
            "76 %"
    };
    String[] pressureArray = {
            "743 мм. р",
            "600 мм. р",
            "845 мм. р",
            "665 mm р",
            "720 mm p",
            "7600 mm p"
    };
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main_activity);


        CustomList customList = new CustomList(this, imagesArray, cityNamesArray, weatherArray, temperatureArray, humidityArray, pressureArray);
        listView = (ListView) findViewById(R.id.favListView);
        listView.setAdapter(customList);

        //Этот кусок кода никак не хочет работать. не могу понять почему, скорей всего, что-то не так с фокусом когда я нажимаю или лисенер не туда вешается, так как я пробовал просто при нажатии вывовдить слово в консоль и это тожене работает
        //Значит проблема еще раньше происходит
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ChoosenCityActivity.class);
                startActivity(intent);
            }
        });


        //для загрузки данных
        //CustomList customList = new CustomList(this,imagesArray,cityNamesArray,weatherArray,temperatureArray,humidityArray,pressureArray);
        //  listView = (ListView) findViewById(R.id.mainListView); //mainListView это id listView из activity_main.xml, туда подгружаем строки
        //  listView.setAdapter(customList);
    }



}