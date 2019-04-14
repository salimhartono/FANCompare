package com.example.funretrofit.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.funretrofit.AdaterList;
import com.example.funretrofit.R;
import com.example.funretrofit.ResponseList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroActivity extends AppCompatActivity {

    private AdaterList adaterList;
    private RecyclerView rvMan;
    private Context context;
    private List<ResponseList> listData;
    BaseApiService apiService;

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
        setContentView(R.layout.activity_retro);
        context = this;

        tvTime = findViewById(R.id.textView);

        apiService = ClientRetro.getApiService();
        listData = new ArrayList<>();
        rvMan = findViewById(R.id.rvMain);
        rvMan.setHasFixedSize(true);
        rvMan.setLayoutManager(new LinearLayoutManager(this));

        showData();
    }

    private void showData() {
        startTime = SystemClock.uptimeMillis();
        customHAndler.postDelayed(runnable, 0);

        Call<List<ResponseList>> getData = apiService.getData();
        getData.enqueue(new Callback<List<ResponseList>>() {
            @Override
            public void onResponse(Call<List<ResponseList>> call, Response<List<ResponseList>> response) {
                if (response.isSuccessful()) {
                    timeSwapbuff+=timeInMillisecond;
                    customHAndler.removeCallbacks(runnable);
                    listData = response.body();
                    adaterList = new AdaterList(listData, context);
                    rvMan.setAdapter(adaterList);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseList>> call, Throwable t) {

            }
        });

    }
}
