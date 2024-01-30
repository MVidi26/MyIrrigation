/*Function that connects to Mqtt*/
bool connectMQTT() {
  byte tentativa = 0;
  /*Connect to Broker Mqtt*/
  client.setServer(mqttLink, mqttPort);
  /*Set CallBack function*/
  client.setCallback(callback);

  /*Make 5 connection attempts*/
  do {
    /*defines the client_id as ESP8266- concatenated with the Network Mac*/
    String client_id = "ESP8266-";
    client_id += String(WiFi.macAddress());

    if (client.connect(client_id.c_str(), "", "")) {
      Serial.println("Exito na conexão:");
      Serial.printf("Cliente %s conectado ao broker\n", client_id.c_str());
    } else {
      Serial.print("Falha ao conectar: ");
      Serial.print(client.state());
      Serial.println();
      Serial.print("Tentativa: ");
      Serial.println(tentativa);
      delay(2000);
    }
    tentativa++;
  } while (!client.connected() && tentativa < 5);

  /*If the connection is successful, the /startStop topic will be subscribed to, otherwise an error message will be returned.*/
  if (tentativa < 5) {
    // publish and subscribe   
    /*client.publish(topic, "{teste123,113007042022}");*/
    client.subscribe(topic);
    return 1;
  } else {
    Serial.println("Não conectado");    
    return 0;
  }
}