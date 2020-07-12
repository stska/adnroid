package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CityWeatherDescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CityWeatherDescription extends Fragment {
    private FragmentActivity myContext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    WeatherInfo weatherInfo = new WeatherInfo();
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
   // private String mParam2;

    public CityWeatherDescription() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment CityWeatherDescription.
     */
    // TODO: Rename and change types and number of parameters
   // public static CityWeatherDescription newInstance(String param1, String param2) {
    public static CityWeatherDescription newInstance(int param1) {
        CityWeatherDescription fragment = new CityWeatherDescription();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_city_weather_description, container, false);

        TextView cityNameText = (TextView) view.findViewById(R.id.cityNameView);
        TextView cityTempText = (TextView) view.findViewById(R.id.cityTempTextView);
        TextView cityHumidText =(TextView) view.findViewById(R.id.humidityTextView);
        TextView cityPressureText = (TextView)view.findViewById(R.id.pressureTextView);
        TextView cityWindSpeedText =(TextView) view.findViewById(R.id.windSpeedTextView);
        LinearLayout cityWeatherLayout = (LinearLayout) view.findViewById(R.id.cityWeatherDataLayout);


        //----------------------------------------
        Button checkWeatherBtn = (Button)view.findViewById(R.id.checkWeatherYandex);
        checkWeatherBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://yandex.ru/pogoda/" + weatherInfo.getCityNamesArray()[mParam1].toLowerCase());
                Intent browser = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(browser);
            }
        });
        //----------------------------------------------
        Switch switcher = (Switch)view.findViewById(R.id.toggleID);
        if (switcher != null) {
            switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    if(isChecked){
                         view.findViewById(R.id.cityMainLinearLayout).setBackgroundColor(Color.rgb(173, 181, 189));

                    }else{
                        view.findViewById(R.id.cityMainLinearLayout).setBackgroundColor(Color.WHITE);
                       // view.findViewById(R.id.cityWeatherFrameID).setBackgroundColor(Color.WHITE);
                    }

                }
            });
        }

        cityNameText.setText(weatherInfo.getListOfCities()[mParam1]);
        cityTempText.setText(weatherInfo.getTemperatureArray()[mParam1]);
        cityHumidText.setText(weatherInfo.getHumidityArray()[mParam1]);
        cityPressureText.setText(weatherInfo.getPressureArray()[mParam1]);
        cityWindSpeedText.setText(weatherInfo.getWindArray()[mParam1]);

        cityWeatherLayout.setBackground(getActivity().getApplication().getResources().getDrawable(R.drawable.imagebg));
        initRecycleView(view);
        return view;

    }
    private void initRecycleView(View view){

        RecyclerView recyclerView = view.findViewById(R.id.weatherRecyleViewFiveDays);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayout.HORIZONTAL & LinearLayout.VERTICAL);
        itemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.separator,null));
        recyclerView.addItemDecoration(itemDecoration);

        //DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayout.VERTICAL);
       // itemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_ripple,null));
       // recyclerView.addItemDecoration(itemDecoration);

        LinearLayoutManager layoutManager = new LinearLayoutManager(myContext,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);



        TempRecycleVoewAdapter adapter = new  TempRecycleVoewAdapter();
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

}
