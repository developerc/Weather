package ru.saperov.weather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Three extends AppCompatActivity {
    ArrayList<ModelForecast> arrayOfForecasts;
    CustomForecastAdapter adapter;
    ListView lvForecasts;
    private static DatabaseHelper mDatabaseHelper;
    TextView mInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabaseHelper = new DatabaseHelper(this);

        setTitle("Forecast: " + MainActivity.name);

        //mInfoTextView = (TextView) findViewById(R.id.textView);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        populateForecastsList();
    }

    private void populateForecastsList(){
        //construct of data sources
        /*arrayOfForecasts = ModelForecast.getForecasts();
        //create the adapter to convert the array to a view
        adapter = new CustomForecastAdapter(this, arrayOfForecasts);
        lvForecasts = (ListView) findViewById(R.id.lvForecasts);
        lvForecasts.setAdapter(adapter);*/
                      if(mDatabaseHelper.getFrcstCount() > 0) {
            /*List<Forecast> forecasts = mDatabaseHelper.getAllForecast();
            ArrayAdapter<Forecast> forecastArrayAdapter = new ArrayAdapter<Forecast>(this, R.layout.item_forecast, R.id.ivForecast, forecasts);
            lvForecasts = (ListView) findViewById(R.id.lvForecasts);
            lvForecasts.setAdapter(forecastArrayAdapter);*/
          /*  StringBuilder stringBuilder = new StringBuilder();
            List<Forecast> forecasts = mDatabaseHelper.getAllForecast();
            for (Forecast forecast : forecasts){
                String allForecasts = forecast.getCity_id() +"  ," + forecast.getDescription();
               // stringBuilder.append(allForecasts);
                Log.d("myLogs", allForecasts);
            }*/
            /*List<Forecast> forecasts = mDatabaseHelper.getMinForecast();
            ArrayAdapter<Forecast> arrayAdapter = new ArrayAdapter<Forecast>(this, R.layout.item_forecast, R.id.ivForecast, forecasts);
            lvForecasts = (ListView) findViewById(R.id.lvForecasts);
            lvForecasts.setAdapter(arrayAdapter);*/

           /*StringBuilder stringBuilder = new StringBuilder();
            List<Forecast> forecasts = mDatabaseHelper.getMinForecast();
            for (Forecast forecast : forecasts) {
                String allForecasts = forecast.getName() + "  " + forecast.getCity_id();
                stringBuilder.append(allForecasts);
            }
            mInfoTextView.setText(stringBuilder.toString());*/

            /*Forecast forecast = mDatabaseHelper.getOneForecast(1);
            String mName = forecast.getName() + ", " + forecast.getCity_id();
            mInfoTextView.setText(mName);*/
            arrayOfForecasts = ModelForecast.getMinForecasts();
            //create the adapter to convert the array to a view
            adapter = new CustomForecastAdapter(this, arrayOfForecasts);
            lvForecasts = (ListView) findViewById(R.id.lvForecasts);
            lvForecasts.setAdapter(adapter);

                        } else {
            arrayOfForecasts = ModelForecast.getForecasts();
            //create the adapter to convert the array to a view
            adapter = new CustomForecastAdapter(this, arrayOfForecasts);
            lvForecasts = (ListView) findViewById(R.id.lvForecasts);
            lvForecasts.setAdapter(adapter);
                      }
    }
}
