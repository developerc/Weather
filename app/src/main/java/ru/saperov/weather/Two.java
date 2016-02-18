package ru.saperov.weather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Two extends AppCompatActivity {
    private TextView tvCity, tvMain, tvDescription, tvPressure, tvHumidity, tvTempmin, tvTempMax, tvWindSpeed, tvWindDeg, tvClouds, tvTemp;
    private ImageView ivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvCity = (TextView) this.findViewById(R.id.tvCity);
        tvMain = (TextView) this.findViewById(R.id.tvMain);
        tvDescription = (TextView) this.findViewById(R.id.tvDescription);
        tvPressure = (TextView) this.findViewById(R.id.tvPressure);
        tvHumidity = (TextView) this.findViewById(R.id.tvHumidity);
        tvTempmin = (TextView) this.findViewById(R.id.tvTempmin);
        tvTempMax = (TextView) this.findViewById(R.id.tvTempMax);
        tvWindSpeed = (TextView) this.findViewById(R.id.tvWindSpeed);
        tvWindDeg = (TextView) this.findViewById(R.id.tvWindDeg);
        tvClouds = (TextView) this.findViewById(R.id.tvClouds);
        tvTemp = (TextView) this.findViewById(R.id.tvTemp);
        ivIcon = (ImageView) this.findViewById(R.id.ivIcon);

        tvCity.setText((CharSequence) MainActivity.name);
        tvMain.setText((CharSequence) "main: "+MainActivity.weather_main);
        tvDescription.setText((CharSequence) "descr: "+MainActivity.weather_description);
        tvPressure.setText((CharSequence) "pressure: "+String.valueOf(MainActivity.main_pressure)+"hPa");
        tvHumidity.setText((CharSequence) "humidity: "+String.valueOf(MainActivity.main_humidity)+"%");
        tvTempmin.setText((CharSequence) "temp min: "+ String.format("%.2f", MainActivity.main_temp_min - 273.15) + " C");
        tvTempMax.setText((CharSequence) "temp max: "+String.format("%.2f", MainActivity.main_temp_max - 273.15)+ " C");
        tvWindSpeed.setText((CharSequence) "wind: "+String.valueOf(MainActivity.wind_speed)+" meter/sec");
        tvWindDeg.setText((CharSequence) "wind dir: "+String.valueOf(MainActivity.wind_deg));
        tvClouds.setText((CharSequence) "cloudness: "+String.valueOf(MainActivity.clouds_all)+"%");
        tvTemp.setText((CharSequence) "temp: "+String.format("%.2f", MainActivity.main_temp - 273.15)+ " C");

        switch (MainActivity.weather_icon) {
            case "01d": ivIcon.setImageResource(R.drawable.i01d);
                break;
            case "01n": ivIcon.setImageResource(R.drawable.i01n);
                break;
            case "02d": ivIcon.setImageResource(R.drawable.i02d);
                break;
            case "02n": ivIcon.setImageResource(R.drawable.i02n);
                break;
            case "03d": ivIcon.setImageResource(R.drawable.i03d);
                break;
            case "03n": ivIcon.setImageResource(R.drawable.i03n);
                break;
            case "04d": ivIcon.setImageResource(R.drawable.i04d);
                break;
            case "04n": ivIcon.setImageResource(R.drawable.i04n);
                break;
            case "09d": ivIcon.setImageResource(R.drawable.i09d);
                break;
            case "09n": ivIcon.setImageResource(R.drawable.i09n);
                break;
            case "10d": ivIcon.setImageResource(R.drawable.i10d);
                break;
            case "10n": ivIcon.setImageResource(R.drawable.i10n);
                break;
            case "11d": ivIcon.setImageResource(R.drawable.i11d);
                break;
            case "11n": ivIcon.setImageResource(R.drawable.i11n);
                break;
            case "13d": ivIcon.setImageResource(R.drawable.i13d);
                break;
            case "13n": ivIcon.setImageResource(R.drawable.i13n);
                break;
            case "50d": ivIcon.setImageResource(R.drawable.i50d);
                break;
            case "50n": ivIcon.setImageResource(R.drawable.i50n);
                break;
        }
    }

}
