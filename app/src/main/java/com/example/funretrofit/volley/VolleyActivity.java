package com.example.funretrofit.volley;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.funretrofit.AdaterList;
import com.example.funretrofit.R;
import com.example.funretrofit.ResponseList;
import com.example.funretrofit.retrofit.BaseApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VolleyActivity extends AppCompatActivity {

    private AdaterList adaterList;
    private RecyclerView rvMan;
    private Context context;
    private List<ResponseList> listData;
    BaseApiService apiService;
    private String URL = "https://script.googleusercontent.com/macros/echo?user_content_key=8oq-wOy89O9O-1nJy_aBBfyt-rHqlf57mw5jQRSDILYpC8BX3vF3QFThuKv7VUpauHCqFURPLPlrCOQc64BiXD_Cx9_ESm4Im5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnLk7bwoqaLkRq8oON4ADOB2WgLJL8cgHd-Z82xxBhxSNVTj1HkpqLmaLYkfZ3y50kZcdv3aNHr6K&lib=MbdoUtcCtYWaBW4ERzY6s9jyKjfTj9dU6";


    Handler customHAndler = new Handler();
    private TextView tvTime;
    long startTime = 0L, timeInMillisecond = 0L, timeSwapbuff = 0L, updateTime = 0L;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeInMillisecond = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapbuff + timeInMillisecond;
            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs %= 60;
            int milliSeconds = (int) (updateTime % 1000);
            tvTime.setText("" + mins + ":" + String.format("%2d", secs) + ":" + String.format("%3d", milliSeconds));
            customHAndler.postDelayed(this, 0);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        context = this;

        tvTime = findViewById(R.id.textView);

        rvMan = findViewById(R.id.rvMain);
        rvMan.setHasFixedSize(true);
        rvMan.setLayoutManager(new LinearLayoutManager(this));

        listData = new ArrayList<>();
        loadData();

    }

    private void loadData() {
        startTime = SystemClock.uptimeMillis();
        customHAndler.postDelayed(runnable, 0);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        timeSwapbuff+=timeInMillisecond;
                        customHAndler.removeCallbacks(runnable);
                        
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                listData.add(new ResponseList(
                                        data.getString("timeStamp"),
                                        data.getString("nama"),
                                        data.getString("jenisKelamin"),
                                        data.getString("alamat")
                                ));
                                adaterList = new AdaterList(listData, context);
                                rvMan.setAdapter(adaterList);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(context));
        requestQueue.add(request);
    }
}
