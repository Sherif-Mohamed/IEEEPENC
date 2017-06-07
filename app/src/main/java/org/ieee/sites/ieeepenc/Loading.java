package org.ieee.sites.ieeepenc;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.ieee.sites.ieeepenc.Home.HomeActivity;

import static java.lang.Integer.parseInt;

public class Loading extends AppCompatActivity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        layout = (LinearLayout) findViewById(R.id.load);
        new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                layout.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = StringSingleton.getInstance(Loading.this).getRequestQueue();
                StringRequest counter = new StringRequest(Request.Method.GET, "http://ieeepenc.16mb.com/count.txt",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                HomeActivity.count = parseInt(response);
                                new CountDownTimer(2000, 1000) {
                                    @Override
                                    public void onTick(long l) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        startActivity(new Intent(Loading.this, HomeActivity.class));
                                        finish();
                                    }
                                }.start();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Loading.this, "There was an error fetching the data,please try again later", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                );
                requestQueue.add(counter);
            }
        }.start();

    }
}
