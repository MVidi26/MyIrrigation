package com.example.myirrigation.mqtt

import com.example.myirrigation.ActivityHome
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

class MQTTManager {

    private var mqttClient: MqttClient? = null

    @Synchronized
    fun initialize(broker: String?) {
        if (mqttClient == null || !mqttClient!!.isConnected) {
            try {
                val options = MqttConnectOptions()
                options.isCleanSession = true
                mqttClient = MqttClient(broker, MqttClient.generateClientId())
                mqttClient!!.connect(options)
            } catch (e: MqttException) {
                e.printStackTrace()
            }
        }
    }

    @Synchronized
    fun publish(topic: String?, message: String) {
        try {
            if (mqttClient != null && mqttClient!!.isConnected) {
                val mqttMessage = MqttMessage(message.toByteArray())
                mqttClient!!.publish(topic, mqttMessage)
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun subscribe(topic: String?, activityHome: ActivityHome) {
        try {
            if (mqttClient != null && mqttClient!!.isConnected) {
                mqttClient!!.subscribe(topic)
                mqttClient!!.setCallback(object : MqttCallback {
                    override fun connectionLost(cause: Throwable) {
                        // Lógica para lidar com a perda de conexão
                    }

                    override fun messageArrived(topic: String, message: MqttMessage) {
                        // Lógica para lidar com a chegada de uma nova mensagem
                        println(
                            "Nova mensagem recebida no tópico "
                                    + topic + ": " + message.payload
                        )
                        val content = message.payload.toString()
                        NotificationUtils.showNotification(activityHome, "Info", content)
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken) {
                        // Lógica para lidar com a conclusão da entrega
                    }
                })
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

}