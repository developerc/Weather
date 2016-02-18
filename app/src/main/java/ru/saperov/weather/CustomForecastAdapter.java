package ru.saperov.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saperov on 07.02.16.
 */
public class CustomForecastAdapter extends ArrayAdapter<ModelForecast> {
    /*public CustomForecastAdapter(Context context, int resource) {
        super(context, resource);
    }*/
    public CustomForecastAdapter(Context context, ArrayList<ModelForecast> modelForecasts) {
        super(context, 0, modelForecasts);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ModelForecast modelForecast = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_forecast, parent, false);
        }
        TextView tvForecast = (TextView) convertView.findViewById(R.id.tvForecast);
        ImageView ivForecast =(ImageView) convertView.findViewById(R.id.ivForecast);

        tvForecast.setText(modelForecast.myForecast);
       // if (MainActivity.strIcon.get(position).toString().equals("13d")) ivForecast.setImageResource(R.drawable.i13d);
        switch (MainActivity.strIcon.get(position).toString()) {
            case "01d": ivForecast.setImageResource(R.drawable.i01d);
                break;
            case "01n": ivForecast.setImageResource(R.drawable.i01n);
                break;
            case "02d": ivForecast.setImageResource(R.drawable.i02d);
                break;
            case "02n": ivForecast.setImageResource(R.drawable.i02n);
                break;
            case "03d": ivForecast.setImageResource(R.drawable.i03d);
                break;
            case "03n": ivForecast.setImageResource(R.drawable.i03n);
                break;
            case "04d": ivForecast.setImageResource(R.drawable.i04d);
                break;
            case "04n": ivForecast.setImageResource(R.drawable.i04n);
                break;
            case "09d": ivForecast.setImageResource(R.drawable.i09d);
                break;
            case "09n": ivForecast.setImageResource(R.drawable.i09n);
                break;
            case "10d": ivForecast.setImageResource(R.drawable.i10d);
                break;
            case "10n": ivForecast.setImageResource(R.drawable.i10n);
                break;
            case "11d": ivForecast.setImageResource(R.drawable.i11d);
                break;
            case "11n": ivForecast.setImageResource(R.drawable.i11n);
                break;
            case "13d": ivForecast.setImageResource(R.drawable.i13d);
                break;
            case "13n": ivForecast.setImageResource(R.drawable.i13n);
                break;
            case "50d": ivForecast.setImageResource(R.drawable.i50d);
                break;
            case "50n": ivForecast.setImageResource(R.drawable.i50n);
                break;
        }

        return convertView;
    }
}
