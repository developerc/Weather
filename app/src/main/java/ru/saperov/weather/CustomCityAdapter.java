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
 * Created by saperov on 31.01.16.
 */
public class CustomCityAdapter extends ArrayAdapter<Cities> {
    public CustomCityAdapter (Context context, ArrayList<Cities> cities) {
        super(context,0,cities);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Cities cities = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_city, parent, false);
        }
        TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
        ImageView ivCity = (ImageView) convertView.findViewById(R.id.ivCity);

        tvCity.setText((CharSequence) cities.mycity);

        if (position==0) ivCity.setImageResource(R.drawable.moscow);
        if (position==1) ivCity.setImageResource(R.drawable.piter);

        return convertView;
    }
}
