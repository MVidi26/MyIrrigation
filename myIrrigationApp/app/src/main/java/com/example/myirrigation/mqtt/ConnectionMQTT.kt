package com.example.myirrigation.mqtt

import android.content.Context
import android.util.Log
import com.example.myirrigation.APIEndPoints
import com.example.myirrigation.ActivityHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.io.IOException

class ConnectionMQTT {
    private lateinit var mqttClient1: MqttClient
    private lateinit var mqttClient: MqttAsyncClient
    private var mqtt: MQTTManager = MQTTManager()
    private var api: APIEndPoints = APIEndPoints()
    private val topic = api.topicMqttConectionMobile

    suspend fun connect(broker: String, clientId: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                mqttClient = MqttAsyncClient(broker, clientId, null)
                val token = mqttClient.connect()
                token.waitForCompletion()
                true
            } catch (e: MqttException) {
                false
            }
        }
    }

    suspend fun publish(message: String, qos: Int = 0,
                        retained: Boolean = false) : Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val mqttMessage = MqttMessage(message.toByteArray())
                mqttMessage.qos = qos
                mqttMessage.isRetained = retained

                val token = mqttClient.publish(topic, mqttMessage)
                token.waitForCompletion()
                true
            } catch (e: MqttException) {
                false
            }
        }
    }

    suspend fun subscribe(messageListener: (String) -> Unit) : Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val qos = 0
                val token = mqttClient.subscribe(topic, qos) { _, message ->
                    val payload = String(message.payload)
                    messageListener(payload)
                }
                token.waitForCompletion()
                true
            } catch (e: MqttException) {
                false
            }
        }
    }

    suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            mqttClient.disconnect()
        }
    }

    suspend fun initialize(broker: String) {
        return withContext(Dispatchers.IO) {
            if (mqttClient1 == null || !mqttClient1.isConnected) {
                try {
                    val options = MqttConnectOptions()
                    options.isCleanSession = true
                    mqttClient1 = MqttClient(broker, "")
                    mqttClient1.connect(options)
                } catch (e: MqttException) {
                    Log.e("Erro MQTT", "fun initialize ${e.message}")
                }
            }
        }
    }

    suspend fun subs2(context: Context){
        withContext(Dispatchers.IO) {
            try {
                if (mqttClient1.isConnected && mqttClient1 != null) {
                    mqttClient1.subscribe(topic)
                    mqttClient1.setCallback(object : MqttCallback {
                        override fun connectionLost(cause: Throwable?) {
                            val message = cause!!.message
                            Log.e("Connection Lost", "Conexão Perdida: $message")
                        }

                        override fun messageArrived(topic: String?, message: MqttMessage?) {
                            var payload = message!!.payload

                        }

                        override fun deliveryComplete(token: IMqttDeliveryToken?) {
                            val payload = token!!.message.payload
                            Log.i("Sucess", "Mensagem Recebida: ${payload}")
                        }

                    })
                } else {

                }
            } catch (e: IOException) {
                Log.e("Erro de Subscricao", "fun susb2 ${e.message}")
            }
        }
    }
}