package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Earthquake> {

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Earthquake> earthQuakes) {
        super(context, 0, earthQuakes);
    }

    private void setLocationTextViews(Earthquake currentEarthquake, View convertView){
        String location = currentEarthquake.getmLocation();
        String primaryLoc;
        String offsetLoc;

        if(location.contains("of")){
            String parts[] = location.split("of ");
            offsetLoc = parts[0] + " of";
            primaryLoc = parts[1];
        }else {
            offsetLoc = "Near the";
            primaryLoc = location;
        }

        TextView offsetLocTV = convertView.findViewById(R.id.offset_loc);
        offsetLocTV.setText(offsetLoc);

        TextView primaryLocTV = convertView.findViewById(R.id.primary_loc);
        primaryLocTV.setText(primaryLoc);
    }

    private void setMagnitudeColor(Earthquake currentEarthquake, TextView magnitudeTV){
        double magnitude = Double.parseDouble(currentEarthquake.getmMagnitude());
        int mag = (int) Math.floor(magnitude);

        int colorResourceId;
        switch(mag){
            case 0:
            case 1:
                colorResourceId = R.color.magnitude1;
                break;
            case 2:
                colorResourceId = R.color.magnitude2;
                break;
            case 3:
                colorResourceId = R.color.magnitude3;
                break;
            case 4:
                colorResourceId = R.color.magnitude4;
                break;
            case 5:
                colorResourceId = R.color.magnitude5;
                break;
            case 6:
                colorResourceId = R.color.magnitude6;
                break;
            case 7:
                colorResourceId = R.color.magnitude7;
                break;
            case 8:
                colorResourceId = R.color.magnitude8;
                break;
            case 9:
                colorResourceId = R.color.magnitude9;
                break;
            default:
                colorResourceId = R.color.magnitude10plus;
                break;
        }

        int color = ContextCompat.getColor(getContext(), colorResourceId);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTV.getBackground();
        magnitudeCircle.setColor(color);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTV = convertView.findViewById(R.id.magnitude);
        magnitudeTV.setText(currentEarthquake.getmMagnitude());
        setMagnitudeColor(currentEarthquake, magnitudeTV);

        TextView dateTV = convertView.findViewById(R.id.date);
        dateTV.setText(currentEarthquake.getFormattedDate());

        TextView timeTV = convertView.findViewById(R.id.time);
        timeTV.setText(currentEarthquake.getFormattedTime());

        setLocationTextViews(currentEarthquake, convertView);

        return convertView;
    }
}
