package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CharactersActivity extends AppCompatActivity {

    private Button backbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ListView listView = findViewById(R.id.listView1);

        String[] data = {"Waggel het Avontuurlijke Varken", "Barry de Brutale Brul-Aap", "Harry de Haastende Haas", "Nellie de Nederige Nijlpaard", "Victor de Vlugge Vos", "Kevin de Klummelige Kat"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click event here
                String selectedItem = (String) parent.getItemAtPosition(position);
                switch (selectedItem) {
                    case "Waggel het Avontuurlijke Varken":
                        if (Info.unlockedAnimals.contains("pig")) {
                            setContentView(R.layout.activity_waggels_story);
                        } else {
                            Toast toast = Toast.makeText(CharactersActivity.this, "Je hebt deze vriend nog niet ontmoet!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    case "Barry de Brutale Brul-Aap":
                        if (Info.unlockedAnimals.contains("monkey")) {
                            setContentView(R.layout.activity_barrys_story);
                        } else {
                            Toast toast = Toast.makeText(CharactersActivity.this, "Je hebt deze vriend nog niet ontmoet!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    case "Harry de Haastende Haas":
                        if (Info.unlockedAnimals.contains("rabbit")) {
                            setContentView(R.layout.activity_harrys_story);
                        } else {
                            Toast toast = Toast.makeText(CharactersActivity.this, "Je hebt deze vriend nog niet ontmoet!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    case "Nellie de Nederige Nijlpaard":
                        if (Info.unlockedAnimals.contains("hippo")) {
                            setContentView(R.layout.activity_nellies_story);
                        } else {
                            Toast toast = Toast.makeText(CharactersActivity.this, "Je hebt deze vriend nog niet ontmoet!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    case "Victor de Vlugge Vos":
                        if (Info.unlockedAnimals.contains("fox")) {
                            setContentView(R.layout.activity_victors_story);
                        } else {
                            Toast toast = Toast.makeText(CharactersActivity.this, "Je hebt deze vriend nog niet ontmoet!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    case "Kevin de Klummelige Kat":
                        if (Info.unlockedAnimals.contains("cat")) {
                            setContentView(R.layout.activity_kevins_story);
                        } else {
                            Toast toast = Toast.makeText(CharactersActivity.this, "Je hebt deze vriend nog niet ontmoet!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                }
            }
        });

    }

    public void onBackButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
