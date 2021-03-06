package com.example.funretrofit.asytask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.example.funretrofit.AdaterList;
import com.example.funretrofit.R;
import com.example.funretrofit.ResponseList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsynctaskActivity extends AppCompatActivity {

    private AdaterList adaterList;
    private RecyclerView rvMan;
    private Context context;
    private List<ResponseList> listData;
    private String URL = "https://script.googleusercontent.com/macros/echo?user_content_key=8oq-wOy89O9O-1nJy_aBBfyt-rHqlf57mw5jQRSDILYpC8BX3vF3QFThuKv7VUpauHCqFURPLPlrCOQc64BiXD_Cx9_ESm4Im5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnLk7bwoqaLkRq8oON4ADOB2WgLJL8cgHd-Z82xxBhxSNVTj1HkpqLmaLYkfZ3y50kZcdv3aNHr6K&lib=MbdoUtcCtYWaBW4ERzY6s9jyKjfTj9dU6";
    private String JSON_STRING;

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
        setContentView(R.layout.activity_asynctask);

        context = this;

        tvTime = findViewById(R.id.textView);

        rvMan = findViewById(R.id.rvMain);
        rvMan.setHasFixedSize(true);
        rvMan.setLayoutManager(new LinearLayoutManager(this));

        listData = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());

        showData();

    }

    private void showData() {
        class ShowData extends AsyncTask<Void, Void, String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                startTime = SystemClock.uptimeMillis();
                customHAndler.postDelayed(runnable, 0);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                timeSwapbuff+=timeInMillisecond;
                customHAndler.removeCallbacks(runnable);
                JSON_STRING = s;
                showList();
            }



            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                String sHandler = requestHandler.sendGetRequest(URL);
                return sHandler;
            }
        }
        ShowData showData = new ShowData();
        showData.execute();
    }

    private void showList(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray response = jsonObject.getJSONArray("");
            for (int i=0; i<response.length(); i++){
                JSONObject data = response.getJSONObject(i);
                listData.add(new ResponseList(
                        data.getString("timeStamp"),
                        data.getString("nama"),
                        data.getString("jenisKelamin"),
                        data.getString("alamat")

                ));
            }
            adaterList = new AdaterList(listData, context);
            rvMan.setAdapter(adaterList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
