void handleUmidity(){
  /*Creates the JsonDoc object*/
  const size_t capacidadeJson = JSON_OBJECT_SIZE(2);
  DynamicJsonDocument jsonDoc(capacidadeJson);

  /*Gets the value from the soil moisture sensor*/
  int soilMoistureValue = analogRead(analogPin3);

  /*Adds the obtained value to the JsonDoc Objects*/
  jsonDoc["umid"] = soilMoistureValue;

  // Converts the jsonDoc object to a JSON string
  String json;
  serializeJson(jsonDoc, json);
  // Set the response header to JSON
  server.sendHeader("Content-Type", "application/json");
  // Send the response with code 200 (OK) and JSON
  server.send(200, "application/json", json);
}