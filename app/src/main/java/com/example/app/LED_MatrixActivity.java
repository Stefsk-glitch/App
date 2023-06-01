
package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.app.builders.MessageBuilder;
import com.example.app.mqtt.Mqtt;

public class LED_MatrixActivity extends AppCompatActivity {
    private static final int GRID_SIZE = 8;
    private MessageBuilder messageBuilder = new MessageBuilder();
    private Mqtt mqtt = new Mqtt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_matrix);

        try {
            mqtt.connect();
            Toast toast = Toast.makeText(this, "Verbinding gemaakt!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Verbinding gemaakt!", Toast.LENGTH_LONG);
            toast.show();
        }


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackButtonClick());

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(v -> onSendbuttonClick());

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        int defaultGrayColor = getResources().getColor(android.R.color.darker_gray);

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
            button.setBackgroundColor(defaultGrayColor); // Set initial background color
            gridLayout.addView(button);
            button.setOnClickListener(v -> onButtonClick(button));
        }
    }

    private void onButtonClick(Button button) {
        String buttonText = button.getText().toString();
        messageBuilder.add(buttonText);
        Log.d("BUTTON", buttonText);
        messageBuilder.printMessage();

        int defaultGrayColor = getResources().getColor(android.R.color.darker_gray);
        int redColor = getResources().getColor(android.R.color.holo_red_light);

        Drawable buttonBackground = button.getBackground();

        if (buttonBackground instanceof ColorDrawable) {
            int buttonColor = ((ColorDrawable) buttonBackground).getColor();

            if (buttonColor == defaultGrayColor) {
                button.setBackgroundColor(redColor);
            } else if (buttonColor == redColor) {
                button.setBackgroundColor(defaultGrayColor);
            }
        } else {
            button.setBackgroundColor(defaultGrayColor);
        }
    }

    private void onBackButtonClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void onSendbuttonClick() {
        try {
            mqtt.sendMessage(messageBuilder.getMessage());
            Toast toast = Toast.makeText(this, "Opdracht verstuurd!", Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Probeer het opnieuw!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}