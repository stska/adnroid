package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class ChoosenCityActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {

WeatherInfo weatherInfo = new WeatherInfo();
    String[][] data = {weatherInfo.getCityNamesArray(),weatherInfo.getWeatherArray(),weatherInfo.getWeatherArray(),weatherInfo.getHumidityArray(),weatherInfo.getPressureArray(),weatherInfo.getWindArray()};
  //String[][] data = {cityNamesArray,weatherArray,temperatureArray,humidityArray,pressureArray,windArray};
    int cityIndex;


    //ToggleButton togleButton;
  //  Switch switcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);

        Switch switcher = (Switch)findViewById(R.id.toggleID);
        if (switcher != null) {
            switcher.setOnCheckedChangeListener(this);
        }

        TextView cityNameText = findViewById(R.id.cityNameView);
        TextView cityTempText = findViewById(R.id.cityTempTextView);
        TextView cityHumidText = findViewById(R.id.humidityTextView);
        TextView cityPressureText = findViewById(R.id.pressureTextView);
        TextView cityWindSpeedText = findViewById(R.id.windSpeedTextView);

        LinearLayout cityWeatherLayout = findViewById(R.id.cityWeatherDataLayout);

        Bundle extra = getIntent().getExtras();

           if(extra != null){

               cityIndex = extra.getInt("cityIndex");

                cityNameText.setText(weatherInfo.getListOfCities()[cityIndex]);
                cityTempText.setText(weatherInfo.getTemperatureArray()[cityIndex]);
                cityHumidText.setText(weatherInfo.getHumidityArray()[cityIndex]);
                cityPressureText.setText(weatherInfo.getPressureArray()[cityIndex]);
                cityWindSpeedText.setText(weatherInfo.getWindArray()[cityIndex]);


                cityWeatherLayout.setBackground(getApplication().getResources().getDrawable(R.drawable.imagebg));
        }


           Button goTointernet = findViewById(R.id.checkWeatherYandex);
           goTointernet.setOnClickListener(new View.OnClickListener(){

               @Override
               public void onClick(View view) {
                   Uri uri = Uri.parse("https://yandex.ru/pogoda/" + weatherInfo.getCityNamesArray()[cityIndex].toLowerCase());
                   Intent browser = new Intent(Intent.ACTION_VIEW,uri);
                   startActivity(browser);
               }
           });

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            Toast.makeText(getApplicationContext(), "Yeahhh, look at this text", Toast.LENGTH_SHORT).show();
            findViewById(R.id.cityMainLinearLayout).setBackgroundColor(Color.rgb(173, 181, 189));
            findViewById(R.id.cityParentLayout).setBackgroundColor(Color.rgb(173, 181, 189));
            // findViewById(R.id.cityMainLinearLayout).setBackgroundColor(Color.parseColor("FFB8B3B3"));

        }else{
            Toast.makeText(getApplicationContext(), "Okey, back to you bleak life !!!!!!!", Toast.LENGTH_SHORT).show();
            findViewById(R.id.cityMainLinearLayout).setBackgroundColor(Color.WHITE);
            findViewById(R.id.cityParentLayout).setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("history",cityIndex);
        setResult(WeatherInfo.STATUS_OK,intent);
       // super.onBackPressed();
        finish();
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.class.getName(), "onPause");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(MainActivity.class.getName(), "Restore");
    }
}
