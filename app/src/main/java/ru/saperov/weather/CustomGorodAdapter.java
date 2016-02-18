package ru.saperov.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saperov on 14.02.16.
 */
public class CustomGorodAdapter extends ArrayAdapter<Goroda> {
    public CustomGorodAdapter(Context context, ArrayList<Goroda> goroda) {
        super(context, 0, goroda);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
// Get the data item for this position
        Goroda goroda = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_goroda, parent, false);
        }

        TextView tvGorod = (TextView) convertView.findViewById(R.id.tvGorod);
        tvGorod.setText((CharSequence) goroda.myGorod);

        return convertView;
    }
}
