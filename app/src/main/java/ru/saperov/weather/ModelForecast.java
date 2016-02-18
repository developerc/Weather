package ru.saperov.weather;

import java.util.ArrayList;

/**
 * Created by saperov on 07.02.16.
 */
public class ModelForecast {
    public String myForecast;
    static ArrayList<ModelForecast> forecasts;


    public ModelForecast(String myForecast) {this.myForecast=myForecast;}

    public static ArrayList<ModelForecast> getForecasts(){
        forecasts= new ArrayList<ModelForecast>();
        forecasts.add(new ModelForecast("Нет прогноза"));

        return forecasts;
    }

    public static ArrayList<ModelForecast> getMinForecasts(){
        forecasts = new ArrayList<ModelForecast>();

        for(int i=0; i< MainActivity.strForecast.size(); i++) {
            forecasts.add(new ModelForecast(MainActivity.strForecast.get(i)));
        }
        //forecasts.add(new ModelForecast("Прогноз2"));

        return forecasts;
    }
}
