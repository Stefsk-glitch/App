package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CharactersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_characters);
    }

    public void onBackbutton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
