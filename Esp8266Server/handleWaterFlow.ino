/*Water Flow Function*/
void handleWaterFlow(){
  /*Creates the jsonDoc object*/
  const size_t capacidadeJson = JSON_OBJECT_SIZE(2);
  DynamicJsonDocument jsonDoc(capacidadeJson);

  // Seed for rand() function based on current time
    srand(time(NULL));

  // Generates a random value between 0 and 1 in double
    double random_value = drand48();

    // Adjusts the value to be in the range between 400 and 1000
    double result = 400 + random_value * (1000 - 400);

  jsonDoc["wf"] = result;
  // Converts the jsonDoc object to a JSON string
  String json;
  serializeJson(jsonDoc, json);
  // Set the response header to JSON
  server.sendHeader("Content-Type", "application/json");
  // Sends the response with code 200 (OK) and JSON
  server.send(200, "application/json", json);
}
