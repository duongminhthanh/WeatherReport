package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    String cityname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        Intent intent=getIntent();
        String city=intent.getStringExtra("name");
        Log.d("Result","Data transfer: "+city);
        if (city.equals("")){
            cityname="Saigon";
            Get7DaysData( cityname );
        }else{
            cityname=city;
            Get7DaysData(cityname);
        }
    }
    private void Get7DaysData(String data){
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + data + "&units=metric&cnt=7&appid=5ceabeb9f84c5ce0b82a8d53243c81f6";
        RequestQueue requestQueue= Volley.newRequestQueue( MainActivity2.this );
        StringRequest stringRequest=new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("result","Json: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add(stringRequest);
    }
}