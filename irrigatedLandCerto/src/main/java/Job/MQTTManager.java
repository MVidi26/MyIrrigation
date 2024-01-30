package Job;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTManager {

    private static MqttClient mqttClient;

    public static synchronized void initialize(String broker) {
        if (mqttClient == null || !mqttClient.isConnected()) {
            try {
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);
                mqttClient = new MqttClient(broker, MqttClient.generateClientId());
                mqttClient.connect(options);
            } catch (MqttException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static synchronized void publish(String topic, String message) {
        try {
            if (mqttClient != null && mqttClient.isConnected()) {
                MqttMessage mqttMessage = new MqttMessage(message.getBytes());
                mqttClient.publish(topic, mqttMessage);
            }
        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }
}
