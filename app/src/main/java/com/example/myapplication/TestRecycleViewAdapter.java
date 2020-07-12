package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestRecycleViewAdapter extends RecyclerView.Adapter<TestRecycleViewAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private String[] dataSource;
    private WeatherInfo weatherInfo = new WeatherInfo();

   private OnItemClickListener itemClickListener;
    public void setOnItemListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public  TestRecycleViewAdapter(String[] dataSource){
        this.dataSource = dataSource;

    }
     public class ViewHolder extends RecyclerView.ViewHolder{
          private TextView textView;
          private TextView weatherText;
          private TextView pressureText;
          private TextView tempText;
          private TextView humidityText;



       //  private LinearLayout linearLayout;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             //textView = (TextView) itemView;

             weatherText = itemView.findViewById(R.id.weatherInfo);
             pressureText = itemView.findViewById(R.id.pressureText);
             tempText = itemView.findViewById(R.id.temperatureText);
             humidityText = itemView.findViewById(R.id.humidityText);


             textView = itemView.findViewById(R.id.nameTextView);
             textView.setOnClickListener(new View.OnClickListener(){

                 @Override
                 public void onClick(View v) {
                     if(itemClickListener !=null){
                         itemClickListener.onItemClick(v,getAdapterPosition());
                     }
                 }
             });
           // linearLayout = (LinearLayout) itemView;
         }
         public TextView getHumidityText(){
             return humidityText;
         }
         public TextView getTextView(){
             return textView;
            // return linearLayout;
         }
         public TextView getWeatherText(){
             return weatherText;
         }
         public TextView getPressureText(){
             return pressureText;
         }
         public TextView getTempText(){
             return tempText;
         }
     }

    @NonNull
    @Override
    public TestRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_weather_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecycleViewAdapter.ViewHolder holder, int position) {
           holder.getTextView().setText(dataSource[position]);
           holder.getPressureText().setText(weatherInfo.getPressureArray()[position]);
           holder.getTempText().setText(weatherInfo.getTemperatureArray()[position]);
           holder.getWeatherText().setText(weatherInfo.getWeatherArray()[position]);
           holder.getHumidityText().setText(weatherInfo.getHumidityArray()[position]);

    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}
