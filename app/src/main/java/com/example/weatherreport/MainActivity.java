package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText edSearch;
    Button btnSearch, btnChangeActivity;
    TextView txtName, txtCountry, txtTemp, txtStatus, txtHumidity, txtCloud, txtWind, txtDay, txtLat, txtLon;
    ImageView imgIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        edSearch = (EditText) findViewById( R.id.edSearch );
        btnSearch = (Button) findViewById( R.id.btnSearch );
        btnChangeActivity = (Button) findViewById( R.id.btnChangeActivity );
        txtName = (TextView) findViewById( R.id.txtName );
        txtCountry = (TextView) findViewById( R.id.txtCountry );
        txtTemp = (TextView) findViewById( R.id.txtTemp );
        txtStatus = (TextView) findViewById( R.id.txtStatus );
        txtHumidity = (TextView) findViewById( R.id.txtHumidity );
        txtCloud = (TextView) findViewById( R.id.txtCloud );
        txtWind = (TextView) findViewById( R.id.txtWind );
        txtDay = (TextView) findViewById( R.id.txtDay );
        imgIcon = (ImageView) findViewById( R.id.imgIcon );
        txtLat = (TextView) findViewById( R.id.txtLat );
        txtLon = (TextView) findViewById( R.id.txtLon );
        btnSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edSearch.getText().toString();
                GetCurrentWeatherData( city );
            }
        } );
        btnChangeActivity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edSearch.getText().toString();
                Intent intent = new Intent( MainActivity.this, Forecast.class );
                intent.putExtra( "name", city );
                intent.putExtra( "lat", txtLat.getText() );
                intent.putExtra( "lon", txtLon.getText() );
                startActivity( intent );
            }
        } );
    }

    public void GetCurrentWeatherData(String data) {
        /*thực thi những request được gửi đi*/
        RequestQueue requestQueue = Volley.newRequestQueue( MainActivity.this );
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=5ceabeb9f84c5ce0b82a8d53243c81f6";
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            String name = jsonObject.getString( "name" );
                            JSONObject jsonObjectCoord = jsonObject.getJSONObject( "coord" );
                            Double lon = jsonObjectCoord.getDouble( "lon" );
                            Double lat = jsonObjectCoord.getDouble( "lat" );
                            txtName.setText( "Name of City: " + name );
                            txtLat.setText( "Latitude: " + lat );
                            txtLon.setText( "Longtitude: " + lon );
                            Date date = new Date();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "EEEE ,dd-MMMM-yyyy, HH:mm:ss" );
                            String Day = simpleDateFormat.format( date );
                            txtDay.setText( Day );
                            JSONArray jsonArrayWeather = jsonObject.getJSONArray( "weather" );
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject( 0 );
                            String status = jsonObjectWeather.getString( "main" );
                            String icon = jsonObjectWeather.getString( "icon" );
                            Picasso.with( MainActivity.this ).load( "https://openweathermap.org/img/wn/" + icon + ".png" ).into( imgIcon );
                            txtStatus.setText( status );
                            JSONObject jsonObjectMain = jsonObject.getJSONObject( "main" );
                            String temp = jsonObjectMain.getString( "temp" );
                            String humid = jsonObjectMain.getString( "humidity" );
                            Double a = Double.valueOf( temp );
                            String temperatue = String.valueOf( a.intValue() );
                            txtTemp.setText( "Temp: " + temperatue + "°C" );
                            txtHumidity.setText( humid + "%" );
                            JSONObject jsonObjectWind = jsonObject.getJSONObject( "wind" );
                            String wind = jsonObjectWind.getString( "speed" );
                            txtWind.setText( wind + "m/s" );
                            JSONObject jsonObjectClouds = jsonObject.getJSONObject( "clouds" );
                            String cloud = jsonObjectClouds.getString( "all" );
                            txtCloud.setText( cloud + "%" );
                            JSONObject jsonObjectSys = jsonObject.getJSONObject( "sys" );
                            String country = jsonObjectSys.getString( "country" );
                            txtCountry.setText( "Country name: " + country );
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