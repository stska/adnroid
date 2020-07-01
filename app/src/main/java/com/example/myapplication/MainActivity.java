package com.example.myapplication;

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
    SearchView searchView;


    static String instanceState = null;     //переменная для выводо данных первый ли это oncreate или нет
    static int counter = 1;           //счетчик сколько раз упере пересоздали активити

    //singleton
    DataStrorage test = DataStrorage.getData();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main_activity);



        final TextView textView = findViewById(R.id.textTestExample);         //текстовое поле в которое будут записываться данные из поиска
        textView.setText(test.data); //запись данныхв это текстовое поля под поиском. Сейчас туда запишутся данные из синглтона, дефолтеное. В нашем случае просто "Test"

        searchView = (SearchView) findViewById(R.id.searchViewId);

        //обработка ввода текста и его отправка
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                  //пробегаемся по списку имебщихся город и сравниваем с тем, что ввели. Я понимаю, что нужен некий фильтр еще здесь, но пока  так
                for (String city : cityNamesArray) {
                    if (city.equals(query)) {
                        textView.setText(query);
                        test.setData(query);  //записываем результат поиска в класс реальзованный по паттерну singleton
                        return false;
                    } else {
                        Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                    }

                }
                test.setData("No Match found");     //записываем результат поиска в класс реальзованный по паттерну singleton
                textView.setText(test.data);
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


        CustomList customList = new CustomList(this, imagesArray, cityNamesArray, weatherArray, temperatureArray, humidityArray, pressureArray);
        listView = (ListView) findViewById(R.id.favListView);
        listView.setAdapter(customList);

        //Этот кусок кода никак не хочет работать. не могу понять почему, скорей всего, что-то не так с фокусом когда я нажимаю или лисенер не туда вешается, так как я пробовал просто при нажатии вывовдить слово в консоль и это тожене работает
        //Значит проблема еще раньше происходит
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), +i + "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ChoosenCityActivity.class);
                startActivity(intent);
            }
        });  */

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent app = new Intent(MainActivity.this,ChoosenCityActivity.class);
                    startActivity(app);
                }else {
                    Intent app = new Intent(MainActivity.this,ChoosenCityActivity.class);
                    startActivity(app);
                }
            }
        });


        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getName(), instanceState + " - onCreate()");

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