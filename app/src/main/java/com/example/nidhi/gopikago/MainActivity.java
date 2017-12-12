package com.example.nidhi.gopikago;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    boolean quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quit = false;
        mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!quit)
                    doStuff();
            }
        }, 3000);
        Assets.runningIntent = false;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Assets.on_screen = false;
    }

    @Override
    protected void onPause(){
        super.onPause();

        Assets.on_screen = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        Assets.on_screen = true;
    }
    protected void doStuff() {



        Assets.gameTimer = System.nanoTime() / 1000000000f;
        Intent intent =new Intent(getApplicationContext(),MainThread.class);
        startActivity(intent);
        if(Assets.runningIntent){

        }


    }
}