package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Forecast extends AppCompatActivity {

    String cityname = "";
    ImageView imgback;
    TextView txtName;
    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Weather> arrayWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.forecast );
        imgback = (ImageView) findViewById( R.id.imgBack );
        txtName = (TextView) findViewById( R.id.txtCityName );
        listView = (ListView) findViewById( R.id.lstView );
        arrayWeathers = new ArrayList<Weather>();
        customAdapter = new CustomAdapter( Forecast.this, arrayWeathers );
        listView.setAdapter( customAdapter );
        Intent intent = getIntent();
        String city = intent.getStringExtra( "name" );

        if (city.equals( "" )) {
            cityname = "Saigon";
            Get7DaysData( cityname );
        } else {
            cityname = city;
            Get7DaysData( cityname );
        }
        imgback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );
    }

    private void Get7DaysData(String data) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + data + "&units=metric&cnt=7&appid=5ceabeb9f84c5ce0b82a8d53243c81f6";
        RequestQueue requestQueue = Volley.newRequestQueue( Forecast.this );
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    JSONObject jsonObjectCity = jsonObject.getJSONObject( "city" );
                    String name = jsonObjectCity.getString( "name" );
                    txtName.setText( name );
                    JSONArray jsonArrayList = jsonObject.getJSONArray( "list" );
                    for (int i = 0; i < jsonArrayList.length(); i++) {
                        JSONObject jsonObjectList = jsonArrayList.getJSONObject( i );
                        String day = jsonObjectList.getString( "dt" );
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "EEEE ,dd-MMMM-yyyy, HH:mm:ss" );
//                      System.out.println( jsonArrayList );
//                      System.out.println( day );
                        Date date = new Date();
//                      System.out.println( date );
                        String Day = simpleDateFormat.format( date );
//                      System.out.println(Day);
                        JSONObject jsonObjectTemp=jsonObjectList.getJSONObject( "main" );
                        String max = jsonObjectTemp.getString( "temp_max" );
                        String min = jsonObjectTemp.getString( "temp_min" );
                        Double a = Double.valueOf( max );
                        Double b = Double.valueOf( min );
                        String tempMax = String.valueOf( a.intValue() );
                        String tempMin = String.valueOf( b.intValue() );
                        System.out.println( tempMax );
                        System.out.println( tempMin );
                        JSONArray jsonArrayWeather = jsonObjectList.getJSONArray( "weather" );
                        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject( 0 );
                        String status = jsonObjectWeather.getString( "description" );
                        String icon = jsonObjectWeather.getString( "icon" );
                        arrayWeathers.add( new Weather( Day, status, icon, tempMax, tempMin ) );
                    }
                    customAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add( stringRequest );
    }
}