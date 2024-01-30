/*double calculateTemperature(int sensorValue) {
    double voltage = sensorValue * (3.3 / 1023.0); // Convert analog reading to voltage
    // Use the appropriate equation for your NTC NY-028 sensor
    // The equation needs to be adjusted based on the characteristics of your specific sensor
    double resistance = ((3.3 * 10000) / voltage) - 10000; // Calcula a resistência do sensor usando a lei de Ohm
    // Use the appropriate equation for your NTC NY-028 sensor
    // The equation needs to be adjusted based on the characteristics of your specific sensor
    double temperature = (273.15 - (1.0 / ((log(resistance / 10000) / 3950) + (1.0 / (25 + 273.15))))); 
    // Calculate temperature using the Steinhart-Hart equation
    
    return temperature;
}

void handleTemperature() {
  // Create a DynamicJsonDocument object with enough capacity to store your data
  // Capacity depends on the amount of data you intend to store in JSON
  // In this example, we will use a capacity of 256 bytes, but you can adjust it according to your needs
  const size_t capacidadeJson = JSON_OBJECT_SIZE(2);
  DynamicJsonDocument jsonDoc(capacidadeJson);
    int sensorValue = analogRead(analogPin);
    double temperature = calculateTemperature(sensorValue);
  // Add the query data to the jsonDoc object
  jsonDoc["tmpA"] = temperature;
  // Convert the jsonDoc object to a JSON string
  String json;
  serializeJson(jsonDoc, json);
  // Set the response header to JSON
  server.sendHeader("Content-Type", "application/json");
  // Send the response with code 200 (OK) and JSON
  server.send(200, "application/json", json);
}*/