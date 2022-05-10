package com.example.weatherreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Weather> arrayList;

    public CustomAdapter(Context context, ArrayList<Weather> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.row_listview,null);
        Weather weather=arrayList.get( i );
        TextView txtDate=(TextView) view.findViewById(R.id.txtDate);
        TextView txtStatus=(TextView) view.findViewById(R.id.txtStatus);
        TextView txtMaxTemp=(TextView) view.findViewById(R.id.txtMaxTemp);
        TextView txtMinTemp=(TextView) view.findViewById(R.id.txtMinTemp);
        ImageView imgStatus=(ImageView) view.findViewById(R.id.imgStatus ) ;
        txtDate.setText(weather.day);
        txtStatus.setText(weather.status);
        txtMaxTemp.setText(weather.MinTemp+"°C");
        txtMinTemp.setText(weather.MaxTemp+"°C");
        Picasso.with( context ).load( "https://openweathermap.org/img/wn/" + weather.Image + ".png" ).into( imgStatus );

        return view;
    }
}
