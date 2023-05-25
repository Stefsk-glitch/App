package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

public class LED_MatrixActivity extends AppCompatActivity {

    private static final int GRID_SIZE = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_matrix);

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        // Top Left Button
        Button topLeftButton = new Button(this);
        topLeftButton.setText("Top Left");
        GridLayout.LayoutParams topLeftParams = new GridLayout.LayoutParams();
        topLeftParams.setGravity(Gravity.START);
        topLeftButton.setLayoutParams(topLeftParams);
        gridLayout.addView(topLeftButton);

        // 64 buttons in the grid
        for (int i = 1; i <= GRID_SIZE * GRID_SIZE; i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(i));
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.setGravity(Gravity.CENTER);
            layoutParams.width = 0;
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            button.setLayoutParams(layoutParams);
            gridLayout.addView(button);
        }r

        // Bottom Button
        Button bottomButton = findViewById(R.id.bottom_button);
        bottomButton.setText("Bottom");
    }



}
