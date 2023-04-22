package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.androide);
        mp.start();

    }
}