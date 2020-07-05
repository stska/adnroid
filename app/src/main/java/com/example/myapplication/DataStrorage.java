package com.example.myapplication;

public class DataStrorage {
    private static DataStrorage instance = null;
    public String data ;


    private DataStrorage(){
        data = "Test";
    }

    public static DataStrorage getData(){
        return instance = instance != null ? instance : new DataStrorage();
    }
    public void setData(String data){
         this.data = data;
    }

}
