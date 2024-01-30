/*Function to read the value of the soil temperature sensor*/
void handleTemperatureGround() {
  // Create the DynamicJsonDocument object with sufficient capacity to store the data
  const size_t capacidadeJson = JSON_OBJECT_SIZE(2);
  DynamicJsonDocument jsonDoc(capacidadeJson);
      // Reading the analog value of the sensor
  int valor_analogico = analogRead(digitPin);

  // Converting analog value to temperature
  float temperature = ((valor_analogico_referencia - valor_analogico) / (coeficiente_termistor + temperatura_referencia));
  // Add the query data to the jsonDoc object
  jsonDoc["tmpG"] = temperature;
  // Convert the jsonDoc object to a JSON string
  String json;
  serializeJson(jsonDoc, json);
  // Set the response header to JSON
  server.sendHeader("Content-Type", "application/json");
  // Send the response with code 200 (OK) and JSON
  server.send(200, "application/json", json);
}