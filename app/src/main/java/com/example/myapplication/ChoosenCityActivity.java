package com.example.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;



public class ChoosenCityActivity extends Activity implements
        CompoundButton.OnCheckedChangeListener {
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
}
