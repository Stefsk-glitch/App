package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CodeInputActivity extends AppCompatActivity {
    private TextView codeInput;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_code_input);
        codeInput=findViewById(R.id.codeField);
    }

    public void onCodeInputButton(View v) {
        if(codeInput.getText().toString().equals("7385")) {
            Intent intent = new Intent(CodeInputActivity.this, LED_MatrixActivity.class);
            startActivity(intent);
            finish();
        } else {
            //melding met foute code
        }
    }
}
