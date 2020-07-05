package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends AppCompatActivity {

    //данные для подргуки в избранное. Особой роли не играбт, так как делал для тренировки. Потом буду подгружать туда данные из поиска


    ListView listView;
    SearchView searchView;


    static String instanceState = null;     //переменная для выводо данных первый ли это oncreate или нет
    static int counter = 1;           //счетчик сколько раз упере пересоздали активити
    WeatherInfo weatherInfo = new WeatherInfo();
    //singleton
    //DataStrorage test = DataStrorage.getData();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main_activity);


       // TextView textView = findViewById(R.id.textTestExample);         //текстовое поле в которое будут записываться данные из поиска
        //textView.setText(test.data); //запись данныхв это текстовое поля под поиском. Сейчас туда запишутся данные из синглтона, дефолтеное. В нашем случае просто "Test"

        searchView = (SearchView) findViewById(R.id.searchViewId);

        //обработка ввода текста и его отправка
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                  //пробегаемся по списку имебщихся город и сравниваем с тем, что ввели. Я понимаю, что нужен некий фильтр еще здесь, но пока  так
                for(int i = 0;i < weatherInfo.getListOfCities().length; i++){
                    if( weatherInfo.getListOfCities()[i].equals(query)){
                        Intent app = new Intent(MainActivity.this,ChoosenCityActivity.class);
                        app.putExtra("cityIndex",i);
                       // startActivity(app);
                        startActivityForResult(app,777);
                        return true;
                    }
                    //непонятно почему два раза пробегает
                }
                Toast.makeText(getApplicationContext(), "No Match found", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

          //блок проверов для последующего вывода результата в лог и Toast
        if (instanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            counter++;
            instanceState = "Запуск номер: " + counter;

        }

        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getName(), instanceState + " - onCreate()");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 777) {
            super.onActivityResult(requestCode, resultCode, data);

        }
        if(resultCode == WeatherInfo.STATUS_OK) {
            int testIndex = data.getIntExtra("history", 0);
            CustomList customList = new CustomList(this, weatherInfo.getImagesArray()[testIndex], weatherInfo.getCityNamesArray()[testIndex], weatherInfo.getWeatherArray()[testIndex], weatherInfo.getTemperatureArray()[testIndex], weatherInfo.getHumidityArray()[testIndex], weatherInfo.getPressureArray()[testIndex]);
            listView = (ListView) findViewById(R.id.favListView);
            listView.setAdapter(customList);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResumer()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getName(), "onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getName(), "onDestroy");
    }


}