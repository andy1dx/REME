package com.reme.remerefrigeneratormanagement;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    remeDbAdapter helper;

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
            helper = new remeDbAdapter(this);

            viewSizeList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void viewSizeList() {
        Cursor cursor = helper.viewSizeData();

        if (cursor.getCount() == 0){
            startActivity(new Intent(SplashScreen.this, AddSizeRef.class));
        }else{
            startActivity(new Intent(SplashScreen.this, Dashboard.class));
        }
    }

}
