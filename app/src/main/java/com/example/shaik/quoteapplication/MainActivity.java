package com.example.shaik.quoteapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String quote_string;
    String author;
    String category;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performreq("https://andruxnet-random-famous-quotes.p.mashape.com/");

                textView.setText("Quote:"+quote_string+"\n Author:"+author+"\n Category:"+category);

            }
        });

    }



    JSONArray object;
    public void performreq(final String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();


                        try{
                            object = new JSONArray(response);
                            JSONObject quote = object.getJSONObject(0);
                            quote_string=quote.getString("quote");
                            category=quote.getString("category");
                            author=quote.getString("author");

                        }
                        catch (Exception e){}





                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();



                params.put("X-Mashape-Key","cXYlnkV43amshXd4VgEXtMCViKKsp1IBlYHjsnAXvb9NP6Jeof");

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }



}
