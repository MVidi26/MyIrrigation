/*void sS(String &topic, String &payload){
  String param = payload;
  char comandS [param.length() + 1];
  param.toCharArray(comandS, sizeof(comandS));
  int comand = atoi(comandS);
  digitalWrite(led, comand);

  server.send(200, "text/plain", "Parâmetros POST recebidos com sucesso!");
  
  if (server.hasArg("param1")) {
    String param = server.arg("param1");
    char comandS[param.length() + 1];
    param.toCharArray(comandS, sizeof(comandS));
    int comand = atoi(comandS);
    if (comand = 1) {
      digitalWrite(led6, HIGH);
    } else {
      digitalWrite(led6, LOW);
    }
    

    // Envie uma resposta ao cliente
    server.send(200, "text/plain", "Parâmetros POST recebidos com sucesso!");
  } else {
    // Se os parâmetros estiverem ausentes, envie uma resposta de erro
    server.send(400, "text/plain", "Parâmetros POST ausentes");
  }

}*/