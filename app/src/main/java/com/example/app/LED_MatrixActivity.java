
package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.app.builders.MessageBuilder;

public class LED_MatrixActivity extends AppCompatActivity {
    private static final int GRID_SIZE = 8;
    private MessageBuilder messageBuilder = new MessageBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_matrix);

        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        int defaultGrayColor = getResources().getColor(android.R.color.darker_gray);
        int margin = getResources().getDimensionPixelSize(R.dimen.button_margin);

        // 64 buttons in the grid
        for (int i = 1; i <= GRID_SIZE * GRID_SIZE; i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(i));
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.setMargins(margin, margin, margin, margin); // Set margins for spacing
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

        // Bottom Button
        Button bottomButton = findViewById(R.id.bottom_button);
        bottomButton.setText("Bottom");
    }



    private boolean isButtonRed = false;
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

}