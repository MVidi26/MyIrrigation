/*CallBack function that listens to messages published in the Mqtt topic /startStop*/
void callback(char *topic, byte *payload, unsigned int length) {
  Serial.println("Message arrived in topic: ");
  Serial.println(topic);
  Serial.println("Message:");
  char message[length +1];
  char *token;
  int num1, num2;
  int seconds = 3000;
 
  for(int i = 0; i < length; i++){
      message[i] = (char)payload[i];
  }
  Serial.println(message);
  Serial.println();
  Serial.println("-----------------------");

  token = strtok(message, ";");
  if (token != NULL){
    num1 = atoi(token);
  }

  token = strtok(NULL, ";");
  if(token != NULL){
    num2 = atoi(token);
  }

  message[length] = '\0';
  digitalWrite(led, num1);
  switch(num2){
    case 0:
      digitalWrite(led6, LOW);
      digitalWrite(led7, LOW);
      digitalWrite(led8, HIGH);
      delay(seconds);
      digitalWrite(led8, LOW);
      break;
    case 1:
      digitalWrite(led6, HIGH);
      break;
    case 7:
      digitalWrite(led7, num2);
      break;
    case 17:
      digitalWrite(led6, HIGH);
      digitalWrite(led7, HIGH);
    default:
      break;
  }
  
}