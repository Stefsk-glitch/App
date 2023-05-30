package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.app.builders.MessageBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openMapActivity(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openLedMatrixActivity(View v) {
        Intent intent = new Intent(this, LED_MatrixActivity.class);
        startActivity(intent);
    }
}