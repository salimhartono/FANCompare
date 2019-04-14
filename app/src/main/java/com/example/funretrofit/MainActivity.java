package com.example.funretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.funretrofit.asytask.AsynctaskActivity;
import com.example.funretrofit.fan.FunActivty;
import com.example.funretrofit.retrofit.RetroActivity;
import com.example.funretrofit.volley.VolleyActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btFan = findViewById(R.id.btFan);
        btFan.setOnClickListener(this);

        Button retro = findViewById(R.id.btRetrofit);
        retro.setOnClickListener(this);

        Button volley = findViewById(R.id.btVolley);
        volley.setOnClickListener(this);


        Button asyntask = findViewById(R.id.btAsyntask);
        asyntask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btFan:
                startActivity(new Intent(MainActivity.this, FunActivty.class));
                break;

            case R.id.btRetrofit:
                startActivity(new Intent(MainActivity.this, RetroActivity.class));
                break;

            case R.id.btVolley:
                startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                break;

            case R.id.btAsyntask:
                startActivity(new Intent(MainActivity.this, AsynctaskActivity.class));
                break;

        }
    }
}
