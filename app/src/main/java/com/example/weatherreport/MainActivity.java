package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText edSearch;
    Button btnSearch,btnChangeActivity;
    TextView txtName,txtCountry,txtTemp,txtStatus,txtHumidity,txtCloud,txtWind,txtDay;
    ImageView imgIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        edSearch=(EditText) findViewById(R.id.edSearch);
        btnSearch=(Button) findViewById(R.id.btnSearch);
        btnChangeActivity=(Button) findViewById(R.id.btnChangActivity);
        txtName=(TextView) findViewById(R.id.txtName);
        txtCountry=(TextView) findViewById(R.id.txtCountry);
        txtTemp=(TextView) findViewById(R.id.txtTemp);
        txtStatus=(TextView) findViewById(R.id.txtStatus);
        txtHumidity=(TextView) findViewById(R.id.txtHumidity);
        txtCloud=(TextView) findViewById(R.id.txtCloud);
        txtWind=(TextView) findViewById(R.id.txtWind);
        txtDay=(TextView) findViewById(R.id.txtDay);
        imgIcon=(ImageView) findViewById(R.id.imgIcon);
        btnSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city= edSearch.getText().toString();
                GetCurrentWeatherData(city);
            }
        } );
    }
    public void GetCurrentWeatherData(String data){
        /*thực thi những request được gửi đi*/
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=5ceabeb9f84c5ce0b82a8d53243c81f6";
        StringRequest stringRequest=new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("result",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } );
        requestQueue.add(stringRequest);
    }


}