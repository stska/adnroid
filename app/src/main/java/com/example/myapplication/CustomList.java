package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

//Класс для описания своей ячейки из listview_row.xml и наполнения данными, который в моем случаее описаны в MainActivity. А так будут браться из погодного API

public class CustomList extends ArrayAdapter {
    private final Activity context;          //для хранения текущего состояния строки(контекста)

    //описываются поля которые нужно будет заполнить. Это картинка,имя города,погода,температура, влажность и давление
    private final Integer[] imagesArray;
    private final String[] cityArray;
    private final String[] weatherArray;
    private final String[] temperatureArray;
    private final String[] humidityArray;
    private final String[] pressureArray;

    //конструктор класса в которым передаем текущую строку и поля
    public CustomList(Activity context,Integer[] imagesArray,String[] cityArray,String[] weatherArray,String[] temperatureArray, String[] humidityArray,String[] pressureArray){
        super(context,R.layout.listview_row,cityArray);    //я так и не понял плчему без последнего параметра не работает(((

        this.context = context;
        this.imagesArray = imagesArray;
        this.cityArray = cityArray;
        this.weatherArray = weatherArray;
        this.temperatureArray = temperatureArray;
        this.humidityArray = humidityArray;
        this.pressureArray = pressureArray;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();           //
        View rowView = inflater.inflate(R.layout.listview_row,parent,false);// создаем View из xml, в нашем случае из listview_row
        // p.s забавно но если передать вместо parent null и в attachToRoot true, то тоже работает @TODO разобраться почему, так как еси true то приложение крашится

        ImageView imageView = (ImageView)rowView.findViewById(R.id.imageViewSample);
        TextView cityTextField = (TextView)rowView.findViewById(R.id.nameTextView);
        TextView weatherTextField = (TextView)rowView.findViewById(R.id.weatherInfo);
        TextView temperatureTextField = (TextView)rowView.findViewById(R.id.temperatureText);
        TextView humidityTextField = (TextView)rowView.findViewById(R.id.humidityText);
        TextView pressureTextField = (TextView)rowView.findViewById(R.id.pressureText);

        ImageButton imageButtonField = (ImageButton)rowView.findViewById(R.id.addInFavourite);

        imageView.setImageResource(imagesArray[position]);
        cityTextField.setText(cityArray[position]);
        weatherTextField.setText(weatherArray[position]);
        temperatureTextField.setText(temperatureArray[position]);
        humidityTextField.setText(humidityArray[position]);
        pressureTextField.setText(pressureArray[position]);

        return rowView;



    }

}
