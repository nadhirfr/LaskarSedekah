package com.laskarsedekah.laskarsedekah.Adapter;

/**
 * Created by FACHRUL on 5/10/2016.
 * ini custom adapter untuk listview
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laskarsedekah.laskarsedekah.NavMenu.CaraSedekah;
import com.laskarsedekah.laskarsedekah.R;


public class CaraSedekah_LevelAdapter extends ArrayAdapter<CaraSedekah.Level> {

    static Context context;
    static int layoutResourceId;
    CaraSedekah.Level data[] = null;

    public CaraSedekah_LevelAdapter(Context context, int layoutResourceId, CaraSedekah.Level[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            //row.setMinimumHeight(200);
            holder = new WeatherHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.iv_bank);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtTitle2 = (TextView) row.findViewById(R.id.txtTitle2);
            holder.txtTitle3 = (TextView) row.findViewById(R.id.txtTitle3);

            row.setTag(holder);
        } else {
            holder = (WeatherHolder) row.getTag();
        }

        CaraSedekah.Level weather = data[position];
        holder.txtTitle.setText(weather.title);
        holder.txtTitle2.setText(weather.title2);
        holder.txtTitle3.setText(weather.title3);
        holder.imgIcon.setImageResource(weather.icon);

        return row;
    }

    static class WeatherHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtTitle2;
        TextView txtTitle3;
        //    ImageView imgIcon2;
    }

}

