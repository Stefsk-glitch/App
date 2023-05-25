#include <PubSubClient.h>
#include <WiFi.h>
#include <WiFiClient.h>
#include <WiFiServer.h>
#include <WiFiUdp.h>
#include <WiFiClientSecure.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include "Adafruit_LEDBackpack.h"

#ifndef _BV
#define _BV(bit) (1<<(bit))
#endif

Adafruit_LEDBackpack matrix = Adafruit_LEDBackpack();

uint8_t counter = 0;

const int trigPin = 5;
const int echoPin = 18;
const int onboardLed = 2;

const float SOUND_SPEED = 0.034;

long duration = 0;
float distanceCm = 0;
String localMessage = "";

const char* ssid = "xxx";
const char* password = "xxx";

const char* mqttServer = "7b10c1a6effd49c798757d01597a1663.s2.eu.hivemq.cloud";
const int mqttPort = 8883;
const char* mqttUser = "espSub";
const char* mqttPassword = "espPasswordH3elZiek";

WiFiClientSecure wifiClient;
PubSubClient mqttClient(wifiClient);

void setup() {
  matrix.begin(0x75);  // pass in the address
  Serial.begin(115200);

  // Connect to Wi-Fi
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
  }
  Serial.println("Connected");

  wifiClient.setInsecure();

  // Set up MQTT client
  mqttClient.setServer(mqttServer, mqttPort);
  mqttClient.setCallback(callback);
  mqttClient.setKeepAlive(60);

  while (!mqttClient.connected()) {
    if (mqttClient.connect("esp", mqttUser, mqttPassword)) {
      Serial.println("mqtt broker connected");
    } else {
      Serial.print("failed with state ");
      Serial.print(mqttClient.state());
      delay(2000);
    }
  }
  mqttClient.subscribe("topic/android");

  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  pinMode(onboardLed, OUTPUT);
}

void loop() {
  mqttClient.loop();

  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  duration = pulseIn(echoPin, HIGH);

  distanceCm = duration * SOUND_SPEED / 2;

  // Check if the incoming message is not null
  if (localMessage != "") {
    // Check if the code is correct
    // TODO: Make the pig head code and check it here instead of checking "lol"
    if (localMessage == "lol") {
      // TODO: Flash pig head 3 times after show balloon and let the balloon fly up until balloon is gone
      Serial.println("CORRECT CODE!");
    }
    else {
      // make whole led matrix RED and after clear
      Serial.println("WRONG CODE!");
    }

    localMessage = "";
  }

  if (distanceCm < 80) {
    for (uint8_t i = 0; i < 8; i++) {
      // draw a diagonal row of pixels
      matrix.displaybuffer[i] = _BV((counter + i) % 16) | _BV((counter + i + 8) % 16)  ;
      matrix.writeDisplay();
      delay(100);
      counter++;
      if (counter >= 16) counter = 0;
    }
    // write the changes we just made to the display
  }
    else {
      // TODO: maak dat ogen open gaan op de led matrix
      digitalWrite(onboardLed, LOW);
      delay(100);
    }

    Serial.print("Distance (cm): ");
    Serial.println(distanceCm);

    delay(1000);
  }


void callback(char* topic, byte* payload, unsigned int length) {
  // handle incoming message from mqtt hivemq
  String message = "";

  for (int i = 0; i < length; i++) {
    message += (char)payload[i];
  }

  localMessage = message;
  Serial.println("Message received on topic " + String(topic) + ": " + message);
}