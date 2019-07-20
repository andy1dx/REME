package com.reme.remerefrigeneratormanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_splash_screen);
        try {
            //set time in milisecond
            Thread.sleep(3000);
            startActivity(new Intent(SplashScreen.this, MainActivity.class));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
