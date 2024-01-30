### This application is part of the final graduation project of the Federal Institute of Education, 
### Science, and Technology of Rio Grande do Sul - Veranópolis Campus.


This is an Android application that is used in conjunction with the irrigation pivot control and monitoring system.
It was developed in Kotlin, using components provided by Android Studio, as well as Retrofit, Coroutines, REST and MQTT.

Through the application, the user logs in. The application, in turn, using Retrofit with Coroutines, 
performs a POST to the Java server, retrieves user data, and stores it in a kind of session within the application itself. 
Upon completing the login and navigating to the main screen, the application establishes a connection 
with the MQTT Broker located on an AWS EC2 instance, subscribes to a topic, and listens for messages 
published by the Java server. When a message is published, a notification is triggered on the device.

Additionally, through Retrofit, there is the option to start and stop the sensor value checking service on the server. 
This functionality can be found in the Job menu with Start and Stop buttons.

#### Technologies used in this application:

* Coroutines
* Retrofit
* REST
* MQTT Protocol
* Firebase