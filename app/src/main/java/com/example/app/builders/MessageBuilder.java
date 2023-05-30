package com.example.app.builders;

import android.util.Log;

public class MessageBuilder {
    private StringBuilder message;

    public MessageBuilder() {
        this.message = new StringBuilder();
    }

    private boolean isValidNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            return num >= 1 && num <= 64;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void add(String number) {
        if (isValidNumber(number)) {
            message.append(number);
        } else {
            Log.d("MessageBuilder", "Invalid number: " + number);
        }
    }

    public void printMessage() {
        Log.d("MessageBuilder", message.toString());
    }
}
