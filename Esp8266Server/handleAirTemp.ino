/*Function to obtain the value from the air temperature and humidity sensor*/
void handleAirTemp(){
  /*Creates the jsonDoc object*/
  const size_t capacidadeJson = JSON_OBJECT_SIZE(2);
  DynamicJsonDocument jsonDoc(capacidadeJson);

  float humidity = dht.readHumidity();    // Reads air humidity
  float temperature = dht.readTemperature();  // Reads the temperature in degrees Celsius
  
  /*sensor reading validation*/
  if (isnan(humidity) || isnan(temperature)) {
    Serial.println("Erro ao ler o sensor DHT11!");
    return;
  }

  /*stores the values read in the object*/
  jsonDoc["temperature"] = temperature;
  jsonDoc["humidity"] = humidity;

  // Convert the jsonDoc object to a JSON string
  String json;
  serializeJson(jsonDoc, json);
  // Set the response header to JSON
  server.sendHeader("Content-Type", "application/json");
  // Send the response with code 200 (OK) and JSON
  server.send(200, "application/json", json);
}