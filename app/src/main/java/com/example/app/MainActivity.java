package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import javax.net.ssl.SSLSocketFactory;
import java.util.Arrays;
import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.androide);
        mp.start();

        MqttClient client = null;
        try {
            // serverURI in format: "protocol://name:port"
            client = new MqttClient(
                    "ssl://7b10c1a6effd49c798757d01597a1663.s2.eu.hivemq.cloud:8883",
                    MqttClient.generateClientId(),
                    new MemoryPersistence());
        }
        catch (MqttException e) {
            throw new RuntimeException(e);
        }

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName("AndroidPhone");
        mqttConnectOptions.setPassword("Stronk!PasswordSuperAdmin1".toCharArray());
        mqttConnectOptions.setSocketFactory(SSLSocketFactory.getDefault());
        try {
            client.connect(mqttConnectOptions);
        }
        catch (MqttException e) {
            throw new RuntimeException(e);
        }

        client.setCallback(new MqttCallback() {

            @Override
            // Called when the client lost the connection to the broker
            public void connectionLost(Throwable cause) {
                System.out.println("client lost connection " + cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println(topic + ": " + Arrays.toString(message.getPayload()));
            }

            @Override
            // Called when an outgoing publish is complete
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("delivery complete " + token);
            }
        });

        try {
            client.subscribe("#", 1);
        }
        catch (MqttException e) {
            throw new RuntimeException(e);
        }

        try {
            client.publish(
                    "topic",
                    "Hello World!".getBytes(UTF_8),
                    2,
                    false);
        }
        catch (MqttException e) {
            throw new RuntimeException(e);
        }

        try {
            client.disconnect();
        }
        catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }
}