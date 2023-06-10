package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

public class CharactersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
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
                        setContentView(R.layout.activity_waggels_story);
                        break;
                    case "Barry de Brutale Brul-Aap":
                        setContentView(R.layout.activity_barrys_story);
                        break;
                    case "Harry de Haastende Haas":
                        setContentView(R.layout.activity_harrys_story);
                        break;
                    case "Nellie de Nederige Nijlpaard":
                        setContentView(R.layout.activity_nellies_story);
                        break;
                    case "Victor de Vlugge Vos":
                        setContentView(R.layout.activity_victors_story);
                        break;
                    case "Kevin de Klummelige Kat":
                        setContentView(R.layout.activity_kevins_story);
                        break;
                }
            }
        });
    }

    public void onBackbutton(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
