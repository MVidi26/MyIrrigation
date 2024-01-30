#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <ESP8266mDNS.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <DHT.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <MQTT.h>
#include <PubSubClient.h>
#include <time.h>
#include <iostream>

#define DHTPIN D2         // Pino ao qual o pino de dados do sensor DHT11 está conectado
#define DHTTYPE DHT11     // Tipo do sensor DHT (DHT11)

DHT dht(DHTPIN, DHTTYPE);

/*MQTTClient client;*/
WiFiClient net;

//Definition Static Address Of Board ESP8266
IPAddress ip(192, 168, 3, 253);
IPAddress gateway(192, 168, 3, 1);
IPAddress subnet(255, 255, 255, 0);
HTTPClient http;
//----------------------------------------
/*Connection Data Of Network And Cloud*/
const char* ssid = "Maicon";
const char* password = "C013R4KX2Qvn";
const char* mqttLink = "ec2-54-92-135-4.compute-1.amazonaws.com";
const int mqttPort = 1883;
const char *topic = "/startStop";
/*-----------------------------------*/
/*Digital and Analog Pins*/
//const int analogPin = A0;
const int digitPin = D1;
const int digitPin2 = D2;
const int led6 = D5;
const int led7 = D3;
const int led8 = D0;
const int analogPin3 = A0;
/*-----------------------------------*/

ESP8266WebServer server(80);
const int led = D4;
// Specific values of your KY-013 thermistor
//Values used during learning
const int analog_ref_val = 500; // Table reference analog value
const float thermistor_coefficient = 10.0; // Thermistor temperature coefficient
const float reference_temperature = 25.0; // Reference temperature corresponding to the analog reference value

//Variables
bool mqttStatus = 0;

//Objects
WiFiClient espClient;
PubSubClient client(espClient);

//Prototipos
bool connectMQTT();
void callback(char *topic, byte * payload, unsigned int length);
/*standard function to check if the server is online*/
void handleRoot() {
  digitalWrite(led, 1);

  String textoHTML;

  textoHTML = "Ola!! Aqui &eacute; o <b>ESP8266</b> falando! ";
  textoHTML += "Porta A0: ";
  textoHTML += analogRead(A0);
   
  server.send(200, "text/html", textoHTML);
  digitalWrite(led, 0);
  // Configuração do pino do sensor como entrada analógica
  pinMode(digitPin, INPUT);
}

/*function that assembles the return response when accessing an invalid endpoint*/
void handleNotFound(){
  digitalWrite(led, 1);
  String message = "File Not Found\n\n";
  message += "URI: ";
  message += server.uri();
  message += "\nMethod: ";
  message += (server.method() == HTTP_GET)?"GET":"POST";
  message += "\nArguments: ";
  message += server.args();
  message += "\n";
  for (uint8_t i=0; i<server.args(); i++){
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(404, "text/plain", message);
  digitalWrite(led, 0);
}

/*--------------------------------------------------------*/
void setup(void){

  /*pin initialization*/
  pinMode(led, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
  pinMode(led8, OUTPUT);
  pinMode(analogPin3, INPUT);

  /*Code to upload the WebServer*/
  digitalWrite(led, 0);
  Serial.begin(115200);
  dht.begin();
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  Serial.println("");

  // While Waiting for Connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  WiFi.config(ip, gateway, subnet);
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
  
  if (MDNS.begin("esp8266")) {
    Serial.println("MDNS responder started");
  }

  //-----------------Endpoints---------------------

  server.on("/", handleRoot);
  /*server.on("/temperature", handleTemperature);*/
  server.on("/temperatureGround", handleTemperatureGround);
  server.on("/airTemp", handleAirTemp);
  /*server.on("/sS", HTTP_POST, sS);*/
  server.on("/umidityGround", handleUmidity);
  server.on("/waterFlow", handleWaterFlow);
  /*----------------------------------------------*/

  /*return response from the server when accessing a valid endpoint*/
  server.on("/inline", [](){
    server.send(200, "text/plain", "this works as well");
  });

  /*server return response when accessing an invalid endpoint*/
  server.onNotFound(handleNotFound);

  server.begin();
  Serial.println("HTTP server started");

  /*get the return of the connection function with Mqtt*/
  mqttStatus = connectMQTT();

}

void loop(void){
  server.handleClient();
  /*if the connection is successful the mqtt client will loop to get the messages that are published*/
  if (mqttStatus){
    client.loop();
  }
}
